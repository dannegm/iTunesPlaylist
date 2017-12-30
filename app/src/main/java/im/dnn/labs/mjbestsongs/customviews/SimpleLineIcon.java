package im.dnn.labs.mjbestsongs.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import im.dnn.labs.mjbestsongs.helpers.Fonts;

public class SimpleLineIcon extends AppCompatTextView {

    Context ctx;

    public SimpleLineIcon (Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs, defStyle);

        ctx = context;
        try {
            init (attrs);
        } catch (Exception e) {
            Log.e ("FontTextView", "Algunos de los parámetros de configuración no se introdujeron correctamente");
        }

    }

    public SimpleLineIcon (Context context, AttributeSet attrs) {
        super (context, attrs);

        ctx = context;
        try {
            init (attrs);
        } catch (Exception e) {
            Log.e ("FontTextView", "Algunos de los parámetros de configuración no se introdujeron correctamente");
        }

    }

    private void init (AttributeSet attrs) throws Exception {
        Fonts fonts = new Fonts (ctx);
        String icon = attrs.getAttributeValue ("http://schemas.android.com/apk/res-auto", "si_icon");

        Typeface font = fonts.set ("simplelineicons");
        int icon_id = getResources ().getIdentifier (icon, "string", ctx.getPackageName ());

        setText (getResources ().getString (icon_id) );
        setTypeface (font);
    }
}
