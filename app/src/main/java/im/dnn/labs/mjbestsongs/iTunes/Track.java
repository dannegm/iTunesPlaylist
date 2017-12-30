package im.dnn.labs.mjbestsongs.iTunes;

import org.json.JSONException;
import org.json.JSONObject;

public class Track {
    public String collectionName;
    public String trackName;
    public String trackViewUrl;
    public String previewUrl;
    public String artworkUrl100;
    public Double trackPrice;
    public Integer trackNumber;
    public Integer trackTimeMillis;
    public String currency;

    private JSONObject json;

    public Track (JSONObject json) {
        this.json = json;
        try {
            this.collectionName = json.has ("collectionName") ? json.getString ("collectionName") : "No collection";
            this.trackName = json.has ("trackName") ? json.getString ("trackName") : "No name";
            this.trackViewUrl = json.has ("trackViewUrl") ? json.getString ("trackViewUrl") : "https://itunes.apple.com/";
            this.previewUrl = json.has ("previewUrl") ? json.getString ("previewUrl") : "https://itunes.apple.com/";
            this.artworkUrl100 = json.has ("artworkUrl100") ? json.getString ("artworkUrl100") : "#";
            this.trackPrice = json.has ("trackPrice") ? json.getDouble ("trackPrice") : 0;
            this.trackNumber = json.has ("trackNumber") ? json.getInt ("trackNumber") : 0;
            this.trackTimeMillis = json.has ("trackTimeMillis") ? json.getInt ("trackTimeMillis") : 0;
            this.currency = json.has ("currency") ? json.getString ("currency") : "USD";
        } catch (JSONException e) { e.printStackTrace (); }
    }

    public String serialize () {
        return json.toString ();
    }
}
