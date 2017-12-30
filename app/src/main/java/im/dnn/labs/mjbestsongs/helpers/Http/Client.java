package im.dnn.labs.mjbestsongs.helpers.Http;

import android.graphics.Bitmap;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Client {

    private String urlRquest;
    private Map<String, String> parms;

    public Client (String url) {
        this.urlRquest = url;
    }

    public JsonResponse get () throws ExecutionException, InterruptedException {
        GetRequest req = new GetRequest ();
        String res = req.execute (urlRquest).get ();
        return new JsonResponse (res);
    }

    public JsonResponse post () throws ExecutionException, InterruptedException {
        PostRequest req = new PostRequest ();
        String res = req.execute (urlRquest).get ();
        return new JsonResponse (res);
    }

    public InputStream stream () throws ExecutionException, InterruptedException {
        StreamRequest req = new StreamRequest ();
        return req.execute (urlRquest).get ();
    }

    public Bitmap image () throws ExecutionException, InterruptedException {
        ImageRequest req = new ImageRequest ();
        return req.execute (urlRquest).get ();
    }

    public Client params (Map<String, String> parms) {
        this.parms = parms;
        return this;
    }
}
