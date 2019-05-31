package com.example.sport_app.Model;

import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class ProfileExercise implements Serializable {

    private HashMap<Integer, Exercise> myExercises;
    private ArrayList<Training> myTrainings;
    private int lastId = 0;

    public ProfileExercise() {
        this.myExercises = new HashMap<>(1);
        this.myTrainings = new ArrayList<>(1);
    }

    public ProfileExercise(HashMap myExercises) {
        this.myExercises = myExercises;
    }

    public HashMap<Integer, Exercise> getMyExercises() {
        return myExercises;
    }

    public ArrayList<String> getMyExercisesAsString() {
        ArrayList<String> exosString = new ArrayList<>();

        for(int i = 0; i <  myExercises.size(); i++) {
            Exercise exo = myExercises.get(i);
            exosString.add(exo.getName());
        }
        return exosString;
    }

    public void addExercise(Exercise exercise) {
        this.myExercises.put(lastId, exercise);
        lastId++;
    }

    public void addTraining(Training training){
        this.myTrainings.add(training);
    }

    public ArrayList<Training> getMyTrainings() {
        return myTrainings;
    }

    public void setTraining(Training training, int listIndex){
        this.myTrainings.set(listIndex, training);
    }

}