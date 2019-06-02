package com.example.sport_app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.TextView;

public class NavActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeActivity = new Intent(NavActivity.this, MainActivity.class);
                    startActivity(homeActivity);
                case R.id.navigation_exercises:
                    Intent allExowActivity = new Intent(NavActivity.this, ExerciseActivity.class);
                    startActivity(allExowActivity);
                case R.id.navigation_trainings:
                    Intent trainingActivity = new Intent(NavActivity.this, TrainingActivity.class);
                    startActivity(trainingActivity);
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
