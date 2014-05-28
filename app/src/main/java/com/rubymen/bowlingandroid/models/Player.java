package com.rubymen.bowlingandroid.models;

import com.google.gson.annotations.SerializedName;


public class Player {

    @SerializedName("Id")
    private int id;

    @SerializedName("Pseudo")
    private String pseudo;

    private Turn[] turns;

    public Player(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Turn[] getTurns() {
        return turns;
    }

    public void setTurns(Turn[] turns) {
        this.turns = turns;
    }

}
