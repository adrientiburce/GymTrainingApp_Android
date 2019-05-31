package com.example.sport_app.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Training implements Serializable {

private ArrayList<Session> mSession = new ArrayList<>();

    private String trainingName;

    private Date date;


    /**
     * constructor used when user create a new training from ListExercise
     *
     * @param trainingName entered by the user
     */
    public Training(String trainingName, Date date) {
        this.trainingName = trainingName;
        this.date = date;
        this.mSession = new ArrayList<>(1);
    }

    public Training(ArrayList<Session> session, String titreListToDo) {
        this.mSession = session;
        this.trainingName = titreListToDo;
    }

    public ArrayList<Session> getSession() {
        return mSession;
    }

    public String getTrainingName() {
        return trainingName;
    }


    public Date getDate() {
        return date;
    }

    public void addSession(Session session) {
        this.mSession.add(session);
    }

    public void setTask(Session item, int listIndex) {
        this.mSession.set(listIndex, item);
    }
}
