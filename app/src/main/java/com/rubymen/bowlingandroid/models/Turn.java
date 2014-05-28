package com.rubymen.bowlingandroid.models;

import com.google.gson.annotations.SerializedName;


public class Turn {

    @SerializedName("Id")
    private int id;

    @SerializedName("Score")
    private int score;

    @SerializedName("throws")
    private Throw[] throwsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Throw[] getThrowsList() {
        return throwsList;
    }

    public void setThrowsList(Throw[] throwsList) {
        this.throwsList = throwsList;
    }

}
