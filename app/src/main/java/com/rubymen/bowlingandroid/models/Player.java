package com.rubymen.bowlingandroid.models;

import com.google.gson.annotations.SerializedName;


/**
 * Player model
 */
public class Player {

    /**
     * Id of the player
     */
    @SerializedName("Id")
    private int id;

    /**
     * Pseudo of the player
     */
    @SerializedName("Pseudo")
    private String pseudo;

    /**
     * Array of Turn
     */
    private Turn[] turns;

    /**
     * Constructor of a Player
     * @param pseudo Name of the Player
     */
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
