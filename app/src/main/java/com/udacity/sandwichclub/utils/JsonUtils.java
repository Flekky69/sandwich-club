package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    @SuppressWarnings("unused")
    public static Sandwich parseSandwichJson(String json) {

        JSONObject mJSONObject;
        JSONObject nameObject;
        String name;
        List<String> alsoKnownAs;
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients;
        Sandwich readyToEat;

        if (json == null || json.equals("")) {
            return null;
        }
        try {
            mJSONObject = new JSONObject(json);
            name = getJsonNames(mJSONObject);
            alsoKnownAs = getJsonAlsoKnownAs(mJSONObject);
            placeOfOrigin = getJsonPlaceOfOrigin(mJSONObject);
            description = getJsonDescription(mJSONObject);
            image = getJsonImage(mJSONObject);
            ingredients = getJsonIngredients(mJSONObject);
            readyToEat = new Sandwich(name, alsoKnownAs, placeOfOrigin, description, image, ingredients);

            return readyToEat;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getJsonNames(JSONObject jsonObject) throws JSONException {
        String mainName;
        JSONObject mMainName = jsonObject.getJSONObject("name");
        mainName = mMainName.getString("mainName");
        Log.d(TAG, "mainName: " + mainName);
        return mainName;
    }

    private static List<String> getJsonAlsoKnownAs(JSONObject jsonObject) throws JSONException {
        JSONObject mMainName = jsonObject.getJSONObject("name");
        JSONArray alsoKnownAs = mMainName.getJSONArray("alsoKnownAs");
//        Log.d(TAG, "getJsonAlsoKnownAs JSONArray size: "+alsoKnownAs.length());
        List<String> listToReturn = new ArrayList<>();
        for (int i = 0; i < alsoKnownAs.length(); i++) {
            Log.d(TAG, "getJsonAlsoKnownAs adding: " + alsoKnownAs.getString(i));
            listToReturn.add(alsoKnownAs.getString(i));
        }
        return listToReturn;

    }

    private static String getJsonPlaceOfOrigin(JSONObject jsonObject) throws JSONException {
        String placeOfOrigin;
        placeOfOrigin = jsonObject.getString("placeOfOrigin");
        Log.d(TAG, "place of origin: " + placeOfOrigin);
        return placeOfOrigin;

    }

    private static String getJsonDescription(JSONObject jsonObject) throws JSONException {
        String description;
        description = jsonObject.getString("description");
        Log.d(TAG, "description: " + description);
        return description;
    }

    private static String getJsonImage(JSONObject jsonObject) throws JSONException {
        String image;
        image = jsonObject.getString("image");
        Log.d(TAG, "image: " + image);
        return image;
    }

    private static List<String> getJsonIngredients(JSONObject jsonObject) throws JSONException {
        JSONArray mIngredients = jsonObject.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < mIngredients.length(); i++) {
            Log.d(TAG, "Adding ingredient: " + mIngredients.getString(i));
            ingredients.add(mIngredients.getString(i));
        }
        return ingredients;
    }

}
