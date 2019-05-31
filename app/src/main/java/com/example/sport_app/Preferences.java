package com.example.sport_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sport_app.Model.ProfileExercise;
import com.example.sport_app.Model.Training;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Preferences {

    public static void setPrefs(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }


    /**
     * convert pseudos string from preferences into an ArrayList
     *
     * @param key     of preferences
     * @param context of SharedPreferences
     * @return get ArrayList from preferences
     */
    public static ArrayList<String> getArrayPrefs(String key, Context context) {
        String stringPref = Preferences.getPrefs(key, context);
        ArrayList<String> arrayPrefs;

        if (stringPref.equals("")) {
            arrayPrefs = new ArrayList<>();
        } else {
            arrayPrefs = new Gson().fromJson(stringPref, new TypeToken<ArrayList<String>>() {
            }.getType());
        }

        return arrayPrefs;
    }


    public static ProfileExercise getProfile(Context context) {
        String allExercises = getPrefs("exercises", context);
        ProfileExercise exos = new Gson().fromJson(allExercises, ProfileExercise.class);
        if (exos == null) {
            return new ProfileExercise();
        } else {
            return exos;
        }
    }

    /**
     * get current list (clicked by user) from preferences
     *
     * @param pseudo             pseudo of current user
     * @param indexOfCurrentList index of clicked list
     * @param context
     * @return Training of current List
     */
//    public static Training getCurrentListToDo(String pseudo, int indexOfCurrentList, Context context) {
//        String profile = Preferences.getPrefs("profile_" + pseudo, context);
//        ProfileExercise currentProfile = new Gson().fromJson(profile, ProfileExercise.class);
//
//        return currentProfile.getMyToDoLists().get(indexOfCurrentList);
//    }

}
