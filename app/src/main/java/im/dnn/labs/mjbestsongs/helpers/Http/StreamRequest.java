package im.dnn.labs.mjbestsongs.helpers.Http;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StreamRequest extends AsyncTask<String, Void, InputStream> {

    @Override
    protected InputStream doInBackground (String... params) {
        String stringUrl = params[0];
        InputStream result;

        try {
            URL urlRquest = new URL (stringUrl);
            HttpURLConnection connection = (HttpURLConnection) urlRquest.openConnection ();

            connection.setDoInput(true);
            connection.connect ();

            result = connection.getInputStream ();
        } catch (IOException e) {
            e.printStackTrace ();
            result = null;
        }

        return result;
    }

    @Override
    protected void onPostExecute (InputStream result){
        super.onPostExecute (result);
    }
}
