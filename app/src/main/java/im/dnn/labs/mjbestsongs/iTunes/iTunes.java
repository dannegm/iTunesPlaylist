package im.dnn.labs.mjbestsongs.iTunes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import im.dnn.labs.mjbestsongs.helpers.Http.Client;

public class iTunes {
    private static final String ITUNES_API = "https://itunes.apple.com/search?term=";

    public Playlist searchPlaylist (final String artis) {
        Playlist playlist = null;
        Client client = new Client (ITUNES_API + sanityURL (artis));
        try {
            JSONObject itunes_response = client.get ().object ();
            playlist = new Playlist (itunes_response);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return playlist;
    }

    private String sanityURL (String text) {
        return text;
    }
}
