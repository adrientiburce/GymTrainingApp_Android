package com.example.sport_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView inputName;
    private Button btnOk = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // wire widgets
        btnOk = findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choiceListActivity = new Intent(MainActivity.this, TrainingActivity.class);
                startActivity(choiceListActivity);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(settingsActivity);
        }
        if (id == R.id.action_results) {
            Intent resultActivity = new Intent(this, ResultActivity.class);
            startActivity(resultActivity);
        }
        if (id == R.id.action_exercises) {
            Intent allExowActivity = new Intent(this, ExerciseActivity.class);
            startActivity(allExowActivity);
        }
        return super.onOptionsItemSelected(item);
    }
}
