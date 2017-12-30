package im.dnn.labs.mjbestsongs.helpers.Http;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PostRequest extends AsyncTask<String, Void, String> {
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;

    @Override
    protected String doInBackground (String... params){
        String stringUrl = params[0];
        String result;
        String inputLine;

        try {
            URL urlRquest = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) urlRquest.openConnection ();

            connection.setRequestMethod ("POST");
            connection.setReadTimeout (READ_TIMEOUT);
            connection.setConnectTimeout (CONNECTION_TIMEOUT);
            connection.connect ();

            InputStreamReader streamReader = new InputStreamReader (connection.getInputStream ());

            BufferedReader reader = new BufferedReader (streamReader);
            StringBuilder stringBuilder = new StringBuilder ();

            while ((inputLine = reader.readLine ()) != null) {
                stringBuilder.append (inputLine);
            }

            reader.close ();
            streamReader.close ();

            result = stringBuilder.toString ();

        } catch (IOException e) {
            e.printStackTrace ();
            result = null;
        }

        return result;
    }

    @Override
    protected void onPostExecute (String result){
        super.onPostExecute (result);
    }
}

