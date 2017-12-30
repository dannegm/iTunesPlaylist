package im.dnn.labs.mjbestsongs.iTunes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    public Integer resultCount;
    public List<Track> tracks = new ArrayList<> ();

    public Playlist (JSONObject json) {
        try {
            this.resultCount = json.getInt ("resultCount");
            JSONArray res = json.getJSONArray ("results");

            for (int x = 0; x < res.length (); x++) {
                Track track = new Track (res.getJSONObject (x));
                tracks.add (track);
            }
        } catch (JSONException e) { e.printStackTrace (); }
    }
}
