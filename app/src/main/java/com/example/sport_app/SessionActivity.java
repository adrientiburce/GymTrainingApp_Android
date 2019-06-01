package com.example.sport_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sport_app.Model.ProfileExercise;
import com.example.sport_app.Model.Session;
import com.example.sport_app.Model.Training;
import com.google.gson.Gson;


public class SessionActivity extends AppCompatActivity {

    private SeekBar edtWeight;
    private EditText edtReps;
    private EditText edtSet;
    private TextView showWeight;
    private Button submitBtn;

    int weightSelected;
    int indexOfCurrentSession;
    int indexOfCurrentTraining;

    private ProfileExercise profile;
    private Training currentTraining;
    private Session currentSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        //wire widgets
        edtWeight = findViewById(R.id.edt_weight);
        showWeight = findViewById(R.id.txt_weight);
        edtReps = findViewById(R.id.edt_reps);
        edtSet = findViewById(R.id.edt_sets);
        submitBtn = findViewById(R.id.btn_submit);

        // get session and training from preferences
        indexOfCurrentSession = Integer.valueOf(getIntent().getStringExtra("sessionToDisplay"));
        indexOfCurrentTraining = Integer.valueOf(getIntent().getStringExtra("currentTraining"));

        profile = Preferences.getProfile(SessionActivity.this);
        currentTraining = profile.getMyTrainings().get(indexOfCurrentTraining);
        currentSession = currentTraining.getSession().get(indexOfCurrentSession);
    }


    @Override
    protected void onStart() {
        super.onStart();

        // set infos if previously created
        showWeight.setText(String.valueOf(currentSession.getWeight()) + " kg");
        edtWeight.setProgress(currentSession.getWeight());
        edtReps.setText(String.valueOf(currentSession.getReps()));
        edtSet.setText(String.valueOf(currentSession.getSet()));


        edtWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 2;
                progress = progress * 2;

                weightSelected = progress;
                String strProgress = String.valueOf(progress);
                showWeight.setText(strProgress + " kg");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int reps = Integer.valueOf(edtReps.getText().toString());
                int sets = Integer.valueOf(edtSet.getText().toString());

                // modify current session and append to traingin
                currentSession.setReps(reps);
                currentSession.setSet(sets);
                currentSession.setWeight(weightSelected);

                currentTraining.setSession(indexOfCurrentSession, currentSession);
                profile.setTraining(indexOfCurrentTraining, currentTraining);

                Preferences.setPrefs("exercises", new Gson().toJson(profile), SessionActivity.this);

                Intent listSession = new Intent(SessionActivity.this, ListSessionActivity.class);
                listSession.putExtra("currentTraining", String.valueOf(indexOfCurrentSession));
                startActivity(listSession);

            }
        });

    }
}