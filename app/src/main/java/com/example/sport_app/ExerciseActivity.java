package com.example.sport_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sport_app.Adapter.TrainingAdapter;
import com.example.sport_app.Model.Exercise;
import com.example.sport_app.Model.Training;
import com.example.sport_app.Model.ProfileListToDo;
import com.google.gson.Gson;

public class ExerciseActivity extends AppCompatActivity {

    private TextView mListName;
    private EditText mInputTask;
    private Button mButtonSumbit;
    RecyclerView recyclerView;

    private Training mCurrentTraining;
    private ProfileListToDo currentProfile;

    String pseudo;
    int indexOfClickedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //wire widgets
        mListName = findViewById(R.id.textView_list_name);
        mInputTask = findViewById(R.id.edt_add_task);
        mButtonSumbit = findViewById(R.id.btn_submit_task);
        mButtonSumbit.setEnabled(false);

        // get needed info
        pseudo = Preferences.getPrefs("pseudo", this);
        indexOfClickedList = Integer.valueOf(getIntent().getStringExtra("listToDisplay"));
        mCurrentTraining = Preferences.getCurrentListToDo(pseudo, indexOfClickedList, this);
        currentProfile = Preferences.getCurrentProfile(pseudo, this);

        //declare recyclerView
        recyclerView = findViewById(R.id.listTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        generateRecyclerAdapter();
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mListName.append(" " + mCurrentTraining.getTrainingName());


        mInputTask.addTextChangedListener(new TextWatcher() {
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
                String newTaskDescription = mInputTask.getText().toString();

                // add new task in preferences
                Toast.makeText(ExerciseActivity.this, "Ajout de : " + newTaskDescription, Toast.LENGTH_SHORT).show();

                mCurrentTraining.addTask(new Exercise(newTaskDescription));
                currentProfile.setToDoList(mCurrentTraining, indexOfClickedList);

                Gson gson = new Gson();
                Preferences.setPrefs("profile_" + pseudo, gson.toJson(currentProfile), ExerciseActivity.this);

                // update view
                mInputTask.setText("");
                generateRecyclerAdapter();

            }
        });

    }

    private void generateRecyclerAdapter() {

        final Training currentTraining = Preferences.getCurrentListToDo(pseudo, indexOfClickedList, this);

        if (currentTraining.getExercise() != null) {
            recyclerView.setAdapter(new TrainingAdapter(currentTraining.getExercise(), new TrainingAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Exercise item, int position) {

                    // update task and list
//                    Exercise taskClicked = currentTraining.getExercise().get(position);
//                    taskClicked.setFait(!item.isFait());
//                    currentTraining.setTask(taskClicked, position);
//
//
//                    // update profile
//                    currentProfile.setToDoList(currentTraining, indexOfClickedList);
//                    Gson gson = new Gson();
//                    Preferences.setPrefs("profile_" + pseudo, gson.toJson(currentProfile), ExerciseActivity.this);
                }
            }));
        }
    }
}
