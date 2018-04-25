package com.vzw.hss.myverizon.a20180424kbsnycschools.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by c0shak8 on 4/25/2018.
 */

public class PersistentStorage {

    private static final String PREFES_NAME = "SCHOOL_DATA";

    public static void saveData(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFES_NAME,	Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("DATA", key);
        editor.commit();
    }

    public static String getData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFES_NAME,	Context.MODE_PRIVATE);
        String key = prefs.getString("DATA", null);
        return key;
    }

}
