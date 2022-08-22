package org.nmerrell;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

public class JsonDetails {
    private final Map<String, String> mMountDetails;
    private final JsonObject mJsonObject = new JsonObject();
    private final Gson gson;
    public JsonDetails(Map<String, String> mountDetails) {
        this.mMountDetails = mountDetails;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        createJsonArray();
    }

    public String printDetails() {
        return gson.toJson(mJsonObject);
    }

    private void createJsonArray() {
        JsonArray jsonArray = new JsonArray();

        for (Map.Entry<String, String> entry : mMountDetails.entrySet()) {
            String fileName = entry.getKey();
            int fileSize = Integer.parseInt(entry.getValue());

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(fileName, fileSize);
            jsonArray.add(jsonObject);
        }
        mJsonObject.add("files", jsonArray);
    }
}
