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


import com.example.sport_app.Adapter.TrainingAdapter;
import com.example.sport_app.Model.ProfileExercise;
import com.example.sport_app.Model.Training;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TrainingActivity extends AppCompatActivity {

    private TextView txt_welcome;
    private EditText mInputSessionName;
    private Button mButtonSubmit;
    RecyclerView recyclerView;
    ProfileExercise currentProfile;
    private String pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        //wire widgets
        txt_welcome = findViewById(R.id.txt_welcome);
        mInputSessionName = findViewById(R.id.edt_add_list);
        mButtonSubmit = findViewById(R.id.btn_submit_list);
        currentProfile = Preferences.getProfile(TrainingActivity.this);


        // add welcome messag with pseudo
        pseudo = Preferences.getPrefs("pseudo", this);
        txt_welcome.setText(this.getIntent().getStringExtra("welcome_message"));


        mButtonSubmit.setEnabled(false);

        //declare recyclerView
        recyclerView = findViewById(R.id.listToDo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        generateRecyclerAdapter();

        //generateRecyclerAdapter();
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

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

                String newListTitle = mInputSessionName.getText().toString();

                // update preferences
                Date currentDate = new Date();

                // TODO change this when app finished
                currentProfile.addTraining(new Training(newListTitle, currentDate));
                //createDebugList(newListTitle, currentProfile);

                Gson gson = new Gson();
                Preferences.setPrefs("exercises", gson.toJson(currentProfile), TrainingActivity.this);

                // update view
                mInputSessionName.setText("");
                generateRecyclerAdapter();

            }
        });

    }


//    private void createDebugList(String newListTitle, ProfileExercise currentProfile) {
//        if (newListTitle.equals("debug")) {
//            Training debugRecyclerList = new Training(new ArrayList<Session>(), newListTitle);
//            for (int i = 0; i < 50; i++) {
//                debugRecyclerList.addSession(new Session("tache n° " + i));
//            }
//            currentProfile.addToDoList(debugRecyclerList);
//        } else {
//            currentProfile.addToDoList(new Training(newListTitle));
//        }
//    }

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
