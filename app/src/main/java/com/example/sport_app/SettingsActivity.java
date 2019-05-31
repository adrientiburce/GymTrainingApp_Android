package com.example.sport_app;

import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity {


    private CheckBoxPreference mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        //PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();


    }

//    private void clearPrefs() {
//        PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
//    }
}
