package com.example.sport_app;

import android.os.Bundle;

import com.example.sport_app.Adapter.ExercisesAdapter;
import com.example.sport_app.Model.Exercise;
import com.example.sport_app.Model.ProfileExercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {


    private EditText edtMuscle, edtExerciseName;
    private FloatingActionButton mButtonSubmit;
    RecyclerView recyclerView;
    ProfileExercise allExercises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //wire widgets
        edtMuscle = findViewById(R.id.edt_muscle);
        edtExerciseName = findViewById(R.id.edt_exerciseName);
        mButtonSubmit = findViewById(R.id.submit_exercise);

        allExercises = Preferences.getProfile(ExerciseActivity.this);

        //declare recyclerView
        recyclerView = findViewById(R.id.list_exercise);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        generateRecyclerAdapter();
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String muscleInput = edtMuscle.getText().toString();
                String exerciseInput = edtExerciseName.getText().toString();

                // update preferences
                allExercises.addExercise(new Exercise(exerciseInput, muscleInput));
                Preferences.setPrefs("exercises", new Gson().toJson(allExercises), ExerciseActivity.this);

                // update view
                edtMuscle.setText("");
                edtExerciseName.setText("");

            }
        });
    }


    private void generateRecyclerAdapter() {

        if (allExercises != null) {
            recyclerView.setAdapter(new ExercisesAdapter(allExercises.getMyExercises(),
                    new ExercisesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Exercise item, int position) {

                        }
                    }

            ));
        }
    }

}
