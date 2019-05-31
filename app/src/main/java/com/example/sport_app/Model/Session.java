package com.example.sport_app.Model;

import java.io.Serializable;

public class Session implements Serializable {

    private String name;
    private int set;
    private int reps;
    private double weight;


    /**
     * Constructor with name, created from exercise list
     *
     * @param name of the item
     */
    public Session(String name) {
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

    public String getName() {
        return name;
    }
}
