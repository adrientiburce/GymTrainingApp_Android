package com.example.sport_app.Model;

public class Exercise {

    private String name;
    private String muscle;

    private double maxWeight;

    public Exercise(String name, String muscle) {
        this.name = name;
        this.muscle = muscle;
    }

    public String getName() {
        return name;
    }

    public String getMuscle() {
        return muscle;
    }
}

