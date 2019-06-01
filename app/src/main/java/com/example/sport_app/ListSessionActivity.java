package com.example.sport_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sport_app.Adapter.SessionAdapter;
import com.example.sport_app.Model.Exercise;
import com.example.sport_app.Model.ProfileExercise;
import com.example.sport_app.Model.Session;
import com.example.sport_app.Model.Training;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListSessionActivity extends AppCompatActivity {

    private TextView mListName;
    private Spinner mInputSession;
    private Button mButtonSumbit;
    RecyclerView recyclerView;

    private ProfileExercise currentProfile;
    private Training currentTraining;
    private ArrayList<Exercise> allExercises;

    int indexExoSelected;
    int indexOfClickedTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);

        //wire widgets
        mListName = findViewById(R.id.textView_list_name);
        mButtonSumbit = findViewById(R.id.btn_submit_task);
        mInputSession = findViewById(R.id.edt_add_task);

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
            recyclerView.setAdapter(new SessionAdapter(currentTraining.getSession(), new SessionAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Session item, int position) {

                    Intent showSession = new Intent(ListSessionActivity.this, SessionActivity.class);
                    showSession.putExtra("currentTraining", String.valueOf(indexOfClickedTraining));
                    showSession.putExtra("sessionToDisplay", String.valueOf(position));
                    startActivity(showSession);
                }
            }));
        }
    }
}
