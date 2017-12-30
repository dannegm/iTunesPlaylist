package im.dnn.labs.mjbestsongs.helpers.Http;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonResponse {
    private String serializedJson;
    public JsonResponse (String json) {
        this.serializedJson = json;
    }

    public JSONObject object () throws JSONException {
        return new JSONObject (this.serializedJson);
    }
    public JSONArray array () throws JSONException {
        return new JSONArray (this.serializedJson);
    }
    public String body () {
        return this.serializedJson;
    }
}
