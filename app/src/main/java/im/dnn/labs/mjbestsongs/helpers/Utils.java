package im.dnn.labs.mjbestsongs.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import im.dnn.labs.mjbestsongs.R;

public class Utils {
    public static final int sdkVersion = Build.VERSION.SDK_INT;

    Context ctx;
    Activity atx;

    public Utils (Context ctx) {
        this.ctx = ctx;
    }

    public Utils (Context ctx, Activity atx) {
        this.ctx = ctx;
        this.atx = atx;
    }

    public void to (Class actv) {
        Class actualClass = ctx.getClass ();
        if (actualClass != actv) {
            Intent intent = new Intent (ctx, actv);
            atx.startActivity (intent);
        }
    }

    public void simpleAlert (String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder (atx);
        builder.setMessage (message);
        builder.setPositiveButton(ctx.getString (R.string.ok), new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog dialog = builder.create ();
        dialog.show ();
    }

    public void alertToEscape (String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder (atx);
        builder.setMessage (message);

        builder.setPositiveButton (ctx.getString (R.string.ok), new DialogInterface.OnClickListener () {
            public void onClick (DialogInterface dialog, int id) {
                escape ();
            }
        });

        AlertDialog dialog = builder.create ();
        dialog.show ();
    }

    public boolean IsConnected () {
        ConnectivityManager conMgr = (ConnectivityManager) ctx.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getNetworkInfo (ConnectivityManager.TYPE_MOBILE).getState () == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState () == NetworkInfo.State.CONNECTED) {
            return true;
        } else if (conMgr.getNetworkInfo (ConnectivityManager.TYPE_MOBILE).getState () == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo (ConnectivityManager.TYPE_WIFI).getState () == NetworkInfo.State.DISCONNECTED) {
            return false;
        } else {
            return false;
        }
    }

    public void escape () {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory (Intent.CATEGORY_HOME);
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
        atx.startActivity (intent);
    }

    public int getColor (int id) {
        if (sdkVersion >= 23) {
            return ctx.getColor(id);
        } else {
            return ContextCompat.getColor(ctx, id);
        }
    }

    public Drawable getDrawable (int id) {
        if (sdkVersion >= 22) {
            return ctx.getDrawable(id);
        } else {
            return ContextCompat.getDrawable(ctx, id);
        }
    }
}
