package com.example.sport_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.sport_app.Adapter.ExerciseAdapter;
import com.example.sport_app.Model.Exercise;
import com.example.sport_app.Model.Training;
import com.example.sport_app.Model.ProfileListToDo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {

    private TextView txt_welcome;
    private EditText mInputList;
    private Button mButtonSubmit;
    RecyclerView recyclerView;

    private String pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        //wire widgets
        txt_welcome = findViewById(R.id.txt_welcome);
        mInputList = findViewById(R.id.edt_add_list);
        mButtonSubmit = findViewById(R.id.btn_submit_list);


        //pseudo = getIntent().getStringExtra("pseudo");
        pseudo = Preferences.getPrefs("pseudo", this);

        txt_welcome.setText(this.getIntent().getStringExtra("welcome_message"));


        mButtonSubmit.setEnabled(false);

        //declare recyclerView
        recyclerView = findViewById(R.id.listToDo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        generateRecyclerAdapter();
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mInputList.addTextChangedListener(new TextWatcher() {

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

                String newListTitle = mInputList.getText().toString();
                // update preferences
                ProfileListToDo currentProfile = Preferences.getCurrentProfile(pseudo, TrainingActivity.this);

                // TODO change this when app finished
//              currentProfile.addToDoList(new Training(newListTitle));
                createDebugList(newListTitle, currentProfile);

                Gson gson = new Gson();
                Preferences.setPrefs("profile_" + pseudo, gson.toJson(currentProfile), TrainingActivity.this);

                // update view
                mInputList.setText("");
                generateRecyclerAdapter();
            }
        });

    }

    /**
     * used to debug recycler view with 50 items
     *
     * @param newListTitle
     * @param currentProfile
     */
    private void createDebugList(String newListTitle, ProfileListToDo currentProfile) {
        if (newListTitle.equals("debug")) {
            Training debugRecyclerList = new Training(new ArrayList<Exercise>(), newListTitle);
            for (int i = 0; i < 50; i++) {
                debugRecyclerList.addTask(new Exercise("tache n° " + i));
            }
            currentProfile.addToDoList(debugRecyclerList);
        } else {
            currentProfile.addToDoList(new Training(newListTitle));
        }
    }

    private void generateRecyclerAdapter() {
        ProfileListToDo currentProfile = Preferences.getCurrentProfile(pseudo, TrainingActivity.this);

        if (currentProfile != null) {
            recyclerView.setAdapter(new ExerciseAdapter(currentProfile.getMyToDoLists(), new ExerciseAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Training item, int position) {
                    // start and go on clicked list view
                    Intent showList = new Intent(TrainingActivity.this, ExerciseActivity.class);
                    showList.putExtra("listToDisplay", String.valueOf(position));
                    startActivity(showList);
                }
            }));
        }
    }

}
