package com.example.sport_app.Model;

import java.io.Serializable;

public class Exercise implements Serializable {

    private String name;
    private int set;
    private int reps;
    private double weight;
    private String muscle;


    /**
     * Constructor with item name
     *
     * @param name of the item
     */
    public Exercise(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }
}
