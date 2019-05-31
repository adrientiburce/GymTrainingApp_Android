package com.example.sport_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sport_app.Adapter.SessionAdapter;
import com.example.sport_app.Model.Exercise;
import com.example.sport_app.Model.ProfileExercise;
import com.example.sport_app.Model.Session;
import com.example.sport_app.Model.Training;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class ListSessionActivity extends AppCompatActivity {

    private TextView mListName;
    private AutoCompleteTextView mInputSession;
    private Button mButtonSumbit;
    RecyclerView recyclerView;

    // TODO
    HashMap<String, String> CountryData;

    private ProfileExercise currentProfile;
    private Training mCurrentTraining;

    private HashMap<Integer, Exercise> allExercises;
    private ArrayList<String> stringExos;

    String pseudo;
    int indexOfClickedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);

        //wire widgets
        mListName = findViewById(R.id.textView_list_name);
        mButtonSumbit = findViewById(R.id.btn_submit_task);
        mButtonSumbit.setEnabled(false);
        mInputSession = findViewById(R.id.edt_add_task);


        // TODO change
        CountryData = new HashMap<String, String>();
        CountryData.put("India", "IN");
        CountryData.put("United States", "US");
        CountryData.put("United Kingdom", "GB");
        CountryData.put("Italy", "IT");


        // get needed info
        indexOfClickedList = Integer.valueOf(getIntent().getStringExtra("listToDisplay"));
        currentProfile = Preferences.getProfile(ListSessionActivity.this);
        mCurrentTraining = currentProfile.getMyTrainings().get(indexOfClickedList);
        allExercises = currentProfile.getMyExercises();
        stringExos = currentProfile.getMyExercisesAsString();

        // set choice list for session name
        //ArrayList<String> allExos = currentProfile.getMyExercisesAsString();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, stringExos );
        mInputSession.setAdapter(adapter);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, CountryData.keySet().toArray(new String[0]));
//        mInputSession.setThreshold(2);
//        mInputSession.setAdapter(adapter);
        mInputSession.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                mInputSession.showDropDown();
                        if (!hasFocus) {
                String exoValue = mInputSession.getText().toString();

//                boolean isCorrectExo = stringExos.has();
//                Log.v("ListSession",
//                        "Selected Country Code: " + code);
//                if (!isCorrectExo) {
//                    mInputSession.setError("Invalid Country");
//                }
            }
            }
        });


        //declare recyclerView
        recyclerView = findViewById(R.id.listTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // generateRecyclerAdapter();
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mListName.append(" " + mCurrentTraining.getTrainingName());
        generateRecyclerAdapter();

        mInputSession.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mButtonSumbit.setEnabled(s.toString().length() >= 2);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mButtonSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTaskDescription = mInputSession.getText().toString();

                // add new task in preferences
                Toast.makeText(ListSessionActivity.this, "Ajout de : " + newTaskDescription, Toast.LENGTH_SHORT).show();

                mCurrentTraining.addSession(new Session(newTaskDescription));

                currentProfile.setTraining(mCurrentTraining, indexOfClickedList);


                Preferences.setPrefs("exercises", new Gson().toJson(currentProfile), ListSessionActivity.this);

                // update view
                mInputSession.setText("");
                generateRecyclerAdapter();

            }
        });

    }

    private void generateRecyclerAdapter() {

        Training currentTraining = currentProfile.getMyTrainings().get(indexOfClickedList);

        if (currentTraining.getSession() != null) {
            recyclerView.setAdapter(new SessionAdapter(currentTraining.getSession(), new SessionAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Session item, int position) {

                    Toast.makeText(ListSessionActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                    Intent showSession = new Intent(ListSessionActivity.this, SessionActivity.class);
                    showSession.putExtra("sessionToDisplay", String.valueOf(position));

                    startActivity(showSession);
                }
            }));
        }
    }
}
