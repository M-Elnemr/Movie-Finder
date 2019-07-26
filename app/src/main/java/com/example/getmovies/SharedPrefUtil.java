package com.example.getmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class SharedPrefUtil {

    public static void saveString(Context context, String key, String value){
        WeakReference<Context> weakReference = new WeakReference<>(context);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(weakReference.get());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void removeString (Context context, String key){
        WeakReference<Context> weakReference = new WeakReference<>(context);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(weakReference.get());

        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static String getString (Context context, String key){
        WeakReference<Context> weakReference = new WeakReference<>(context);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(weakReference.get());

        return preferences.getString(key, " ");
    }

    public static boolean hasKey(Context context, String key){
        WeakReference<Context> weakReference = new WeakReference<>(context);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(weakReference.get());

        return preferences.contains(key);
    }

}