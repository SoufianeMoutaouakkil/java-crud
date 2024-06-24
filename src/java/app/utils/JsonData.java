package app.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonData {
    JsonObject jsonObject;
    
    public JsonData(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(String key) {
        JsonElement je = jsonObject.get(key);
        if (je == null) {
            return null;
        }
        return je.getAsString();
    }

    public int getInt(String key) {
        JsonElement je = jsonObject.get(key);
        if (je == null) {
            return 0;
        }
        return je.getAsInt();
    }
}
