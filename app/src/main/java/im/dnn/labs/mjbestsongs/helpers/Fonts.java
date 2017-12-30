package im.dnn.labs.mjbestsongs.helpers;

import android.content.Context;
import android.graphics.Typeface;

public class Fonts {
    private Context ctx;
    private String path = "fonts/";

    public Fonts (Context ctx) {
        this.ctx = ctx;
    }

    public Typeface set (String FontFamily) {
        String _path = path + FontFamily + "/regular.ttf";
        return Typeface.createFromAsset (ctx.getAssets (), _path);
    }

    public Typeface set (String FontFamily, String FontStyle) {
        String _path = path + FontFamily + "/" + FontStyle + ".ttf";
        return Typeface.createFromAsset (ctx.getAssets (), _path);
    }
}
