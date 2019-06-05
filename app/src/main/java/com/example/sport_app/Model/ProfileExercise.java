package com.example.sport_app.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class ProfileExercise implements Serializable {

    private ArrayList<Exercise> myExercises;
    private ArrayList<Training> myTrainings;

    public ProfileExercise() {
        this.myExercises = generateExercises();
        this.myTrainings = new ArrayList<>(1);
    }

    public ArrayList<Exercise> getMyExercises() {
        return myExercises;
    }


    public void addExercise(Exercise exercise) {
        this.myExercises.add(exercise);
    }

    public void addTraining(Training training) {
        this.myTrainings.add(training);
    }

    public ArrayList<Training> getMyTrainings() {
        return myTrainings;
    }

    public void setTraining(int listIndex, Training training) {
        this.myTrainings.set(listIndex, training);
    }




    public ArrayList<Exercise> generateExercises() {
        ArrayList<Exercise> mesExos = new ArrayList<>();
        // pecs
        Exercise pompes = new Exercise("Pompes", "pecs");
        Exercise dc = new Exercise("DC", "pecs");
        Exercise pap = new Exercise("Papillon", "pecs");

        // jambes
        Exercise squat = new Exercise("Squat", "jambes");
        Exercise presse = new Exercise("Presse", "jambes");
        Exercise ext = new Exercise("Leg Extension", "jambes");
        Exercise ischio = new Exercise("Leg ischio", "jambes");

        // Bras
        Exercise curl = new Exercise("Curl Bicep", "Biceps");
        Exercise traction = new Exercise("Traction supination", "Biceps");
        Exercise marteau = new Exercise("Curl Marteau", "Biceps");


        mesExos.add(pompes);
        mesExos.add(dc);
        mesExos.add(pap);
        mesExos.add(squat);
        mesExos.add(presse);
        mesExos.add(ext);

        return mesExos;

    }

}