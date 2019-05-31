package com.example.sport_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private AutoCompleteTextView inputName;
    private Button btnOk = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // wire widgets
        btnOk = findViewById(R.id.btnOk);
        inputName = findViewById(R.id.autocompleteInput);

        btnOk.setOnClickListener(this);
        btnOk.setEnabled(false);

        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnOk.setEnabled(s.length() > 1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // récupération du pseudo dans les préférences
        String pseudo = Preferences.getPrefs("pseudo", this);
        inputName.setText(pseudo);


        // set autocomplete with pseudos
        ArrayList<String> listPseudo = Preferences.getArrayPrefs("stringPseudo", this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listPseudo);
        inputName.setAdapter(adapter);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnOk) {

            String pseudo = inputName.getText().toString();
            Intent choiceListActivity = new Intent(this, TrainingActivity.class);


            //add pseudo to Preferences
            Preferences.setPrefs("pseudo", pseudo, this);

            // send as intent to second activity
            choiceListActivity.putExtra("pseudo", pseudo);

            // add Array of Pseudo in preferences
            ArrayList<String> listPseudo = Preferences.getArrayPrefs("stringPseudo", this);

            // check if pseudo already exist
            if (!listPseudo.contains(pseudo)) {
                listPseudo.add(pseudo);
                choiceListActivity.putExtra("welcome_message", getString(R.string.welcome_message) + " " + pseudo);
            } else {
                choiceListActivity.putExtra("welcome_message", getString(R.string.welcome_back) + " " + pseudo);
            }

            Gson gsonBuilder = new GsonBuilder().create();

            // Convert Java Array into JSON
            String newStringPseudo = gsonBuilder.toJson(listPseudo);
            Preferences.setPrefs("stringPseudo", newStringPseudo, this);

            startActivity(choiceListActivity);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(settingsActivity);
        }
        if (id == R.id.action_results) {
            Intent resultActivity = new Intent(this, ResultActivity.class);
            startActivity(resultActivity);
        }
        if (id == R.id.action_exercises) {
            Intent allExowActivity = new Intent(this, ExerciseActivity.class);
            startActivity(allExowActivity);
        }
        return super.onOptionsItemSelected(item);
    }
}
