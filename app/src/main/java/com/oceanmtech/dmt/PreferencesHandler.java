package com.oceanmtech.dmt;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHandler {
    public static boolean getBooleanPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return preferences.getBoolean(key,false);
    }

    public static void setBooleanPreference(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public static String getStringPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return preferences.getString(key,"N/A");
    }

    public static void setStringPreference(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

}
