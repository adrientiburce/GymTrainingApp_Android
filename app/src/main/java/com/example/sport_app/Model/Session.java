package com.example.sport_app.Model;

import java.io.Serializable;

public class Session implements Serializable {

    private Exercise mExercise;
    private int indexExercise;
    private int set;
    private int reps;
    private int weight;


    /**
     * Constructor with name, created from exercise list
     *
     * @param exercise of the item
     */
    public Session(Exercise exercise, int indexExercise) {
        this.mExercise = exercise;
        this.indexExercise = indexExercise;
    }


    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Exercise getExercise() {
        return mExercise;
    }

    public void setExercise(Exercise exercise) {
        mExercise = exercise;
    }

    public int getIndexExercise() {
        return indexExercise;
    }

    public String getInfos() {
        if (set != 0 && reps != 0) {
            return "Séries : " + set +
                    ", Reps : " + reps +
                    "     Poids = " + weight + "kg";

        }
        return "";
    }

}
