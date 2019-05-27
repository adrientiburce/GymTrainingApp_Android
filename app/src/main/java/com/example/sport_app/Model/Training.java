package com.example.sport_app.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Training implements Serializable {

    private ArrayList<Exercise> mExercise;

    private String trainingName;


    /**
     * constructor used when user create a new list from TrainingActivity
     *
     * @param trainingName entered by the user
     */
    public Training(String trainingName) {
        this.trainingName = trainingName;
        this.mExercise = new ArrayList<>();
    }

    public Training(ArrayList<Exercise> exercise, String titreListToDo) {
        this.mExercise = exercise;
        this.trainingName = titreListToDo;
    }

    public ArrayList<Exercise> getExercise() {
        return mExercise;
    }

    public String getTrainingName() {
        return trainingName;
    }


    public void addTask(Exercise task) {
        this.mExercise.add(task);
    }

    public void setTask(Exercise item, int listIndex) {
        this.mExercise.set(listIndex, item);
    }
}
