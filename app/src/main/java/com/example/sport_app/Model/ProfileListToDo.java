package com.example.sport_app.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileListToDo implements Serializable {

    private String pseudo;
    private ArrayList<Training> myToDoLists;

    /**
     * constructor if current user never signed in
     *
     * @param pseudo entered by the user
     */
    public ProfileListToDo(String pseudo) {
        this.pseudo = pseudo;
        this.myToDoLists = new ArrayList<>();
    }

    public ArrayList<Training> getMyToDoLists() {
        return myToDoLists;
    }

    public void addToDoList(Training todoList) {
        this.myToDoLists.add(todoList);
    }

    public void setToDoList(Training list, int listIndex) {
        this.myToDoLists.set(listIndex, list);
    }
}
