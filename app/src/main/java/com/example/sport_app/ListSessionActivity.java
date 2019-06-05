package com.example.sport_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sport_app.Adapter.SessionAdapter;
import com.example.sport_app.Model.Exercise;
import com.example.sport_app.Model.ProfileExercise;
import com.example.sport_app.Model.Session;
import com.example.sport_app.Model.Training;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListSessionActivity extends AppCompatActivity {

    private TextView mListName;
    private Spinner mInputSession;
    private ImageButton mButtonSumbit;
    RecyclerView recyclerView;

    private ProfileExercise currentProfile;
    private Training currentTraining;
    private ArrayList<Exercise> allExercises;

    int indexExoSelected;
    int indexOfClickedTraining;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeActivity = new Intent(ListSessionActivity.this, MainActivity.class);
                    startActivity(homeActivity);
                    return true;
                case R.id.navigation_exercises:
                    Intent allExowActivity = new Intent(ListSessionActivity.this, ExerciseActivity.class);
                    startActivity(allExowActivity);
                    return true;
                case R.id.navigation_trainings:
                    Intent trainingActivity = new Intent(ListSessionActivity.this, TrainingActivity.class);
                    startActivity(trainingActivity);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_session);

        //wire widgets
        mListName = findViewById(R.id.textView_list_name);
        mButtonSumbit = findViewById(R.id.btn_submit_task);
        mInputSession = findViewById(R.id.edt_add_task);

        BottomNavigationView bottomNavView = findViewById(R.id.nav_view);
        bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        // get needed info
        indexOfClickedTraining = Integer.valueOf(getIntent().getStringExtra("currentTraining"));
        currentProfile = Preferences.getProfile(ListSessionActivity.this);
        currentTraining = currentProfile.getMyTrainings().get(indexOfClickedTraining);
        allExercises = currentProfile.getMyExercises();

        // set choice list with exercises name
        ArrayAdapter<Exercise> adapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, allExercises);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInputSession.setAdapter(adapter);

        mInputSession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("CAT", String.valueOf(position));
                indexExoSelected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //declare recyclerView
        recyclerView = findViewById(R.id.listTask);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        generateRecyclerAdapter();

        mListName.append(" " + currentTraining.getTrainingName());


        mButtonSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // add new exo in current training
                currentTraining.addSession(new Session(allExercises.get(indexExoSelected), indexExoSelected));
                currentProfile.setTraining(indexOfClickedTraining, currentTraining);
                Preferences.setPrefs("exercises", new Gson().toJson(currentProfile), ListSessionActivity.this);

                // update view
                generateRecyclerAdapter();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // on force la récup du training updaté
        currentTraining = currentProfile.getMyTrainings().get(indexOfClickedTraining);
        generateRecyclerAdapter();
    }

    private void generateRecyclerAdapter() {

        if (currentTraining.getSession() != null) {
            Log.i("CAT", "generate Adapter");
            recyclerView.setAdapter(new SessionAdapter(currentTraining.getSession(),
                    new SessionAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Session item, int position) {

                            Intent showSession = new Intent(ListSessionActivity.this, SessionActivity.class);
                            showSession.putExtra("currentTraining", String.valueOf(indexOfClickedTraining));
                            showSession.putExtra("sessionToDisplay", String.valueOf(position));
                            startActivity(showSession);
                        }
                    },
                    new SessionAdapter.OnItemDeleteClickListener() {
                        @Override
                        public void onItemClick(Session item, int position) {

                            Toast.makeText(ListSessionActivity.this, item.getExercise().toString() + " supprimé " + position, Toast.LENGTH_SHORT).show();

                            // remove session from Training
                            currentProfile.setTraining(indexOfClickedTraining, currentTraining);
                            Preferences.setPrefs("exercises", new Gson().toJson(currentProfile), ListSessionActivity.this);
                        }
                    }
            ));
        }
    }
}
