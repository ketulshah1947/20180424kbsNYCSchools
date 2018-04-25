package com.vzw.hss.myverizon.a20180424kbsnycschools.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.Gson;
import com.vzw.hss.myverizon.a20180424kbsnycschools.model.Score;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * Created by c0shak8 on 4/25/2018.
 */

public class Utils {

    private static HashMap<String, Score> scoreMap;

    public static HashMap<String, Score> getScoreMap() {
        return scoreMap;
    }


    /*
    Following method is using Gson to parse downloaded json and create an object.
    Later that object is used to create hashmap.
     */
    public static void setScoreMap(String response) {
        Gson gson = new Gson();
        try {
            JSONArray array = new JSONArray(response);
            scoreMap = new HashMap<>(array.length());
            for (int i = 0; i < array.length(); i++) {
                try {
                    Score score = gson.fromJson(array.getJSONObject(i).toString(), Score.class);
                    scoreMap.put(score.getName(), score);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
