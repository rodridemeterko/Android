package com.ime.comp2017.peacedriveapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by demeterko on 27/05/2016.
 */
public class parseImagesJSON {
    public static String[] imagesURL;
    public static String[] imagesId;

    public static final String JSON_ARRAY = "resultado";
    public static final String KEY_ID = "map_id";
    public static final String KEY_URI = "map_url";

    private JSONArray mapas = null;

    private String json;

    public parseImagesJSON(String json) { this.json = json; }

    public void parseJSON(){
        JSONObject jsonObj = null;
        try{
            jsonObj = new JSONObject(json);
            mapas = jsonObj.getJSONArray(JSON_ARRAY);

            imagesId = new String[mapas.length()];
            imagesURL = new String[mapas.length()];

            for (int i=0;i<mapas.length();i++){
                JSONObject jo = mapas.getJSONObject(i);
                imagesId[i] = jo.getString(KEY_ID);
                imagesURL[i] = jo.getString(KEY_URI);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
