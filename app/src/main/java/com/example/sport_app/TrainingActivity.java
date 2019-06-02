package com.example.sport_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.sport_app.Adapter.TrainingAdapter;
import com.example.sport_app.Model.ProfileExercise;
import com.example.sport_app.Model.Training;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TrainingActivity extends AppCompatActivity {

    private EditText mInputSessionName;
    private Button mButtonSubmit;
    RecyclerView recyclerView;
    ProfileExercise currentProfile;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeActivity = new Intent(TrainingActivity.this, MainActivity.class);
                    startActivity(homeActivity);
                    return true;
                case R.id.navigation_exercises:
                    Intent allExowActivity = new Intent(TrainingActivity.this, ExerciseActivity.class);
                    startActivity(allExowActivity);
                    return true;
                case R.id.navigation_trainings:
                    Intent trainingActivity = new Intent(TrainingActivity.this, TrainingActivity.class);
                    startActivity(trainingActivity);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        //wire widgets
        mInputSessionName = findViewById(R.id.edt_add_list);
        mButtonSubmit = findViewById(R.id.btn_submit_list);
        currentProfile = Preferences.getProfile(TrainingActivity.this);
        mButtonSubmit.setEnabled(false);

        BottomNavigationView bottomNavView = findViewById(R.id.nav_view);
        bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //declare recyclerView
        recyclerView = findViewById(R.id.listToDo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        generateRecyclerAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mInputSessionName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mButtonSubmit.setEnabled(s.toString().length() > 2);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get info
                String newListTitle = mInputSessionName.getText().toString();
                Date currentDate = new Date();

                // persist Preferences
                currentProfile.addTraining(new Training(newListTitle, currentDate));
                Gson gson = new Gson();
                Preferences.setPrefs("exercises", gson.toJson(currentProfile), TrainingActivity.this);

                // update view
                mInputSessionName.setText("");
                generateRecyclerAdapter();

            }
        });

    }

    private void generateRecyclerAdapter() {

        if (currentProfile != null) {
            recyclerView.setAdapter(new TrainingAdapter(currentProfile.getMyTrainings(), new TrainingAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Training item, int position) {

                    Intent showSession = new Intent(TrainingActivity.this, ListSessionActivity.class);
                    showSession.putExtra("currentTraining", String.valueOf(position));
                    startActivity(showSession);

                }
            }));
        }
    }
}
