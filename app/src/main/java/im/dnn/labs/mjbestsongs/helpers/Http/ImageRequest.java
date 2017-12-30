package im.dnn.labs.mjbestsongs.helpers.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;

public class ImageRequest extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground (String... params) {
        String stringUrl = params[0];
        Bitmap result;

        try {
            InputStream in = new java.net.URL (stringUrl).openStream ();
            result = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace ();
            result = null;
        }

        return result;
    }

    @Override
    protected void onPostExecute (Bitmap result){
        super.onPostExecute (result);
    }
}
