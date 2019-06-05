package com.example.sport_app.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Training implements Serializable {

    private ArrayList<Session> mSession;

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

    public void removeSession(int indexSession){
        this.mSession.remove(indexSession);
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

    public void setSession(int indexSession, Session updatedSession) {
        this.mSession.set(indexSession, updatedSession);
    }
}
