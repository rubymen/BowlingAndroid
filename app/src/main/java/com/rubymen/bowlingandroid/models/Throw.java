package com.rubymen.bowlingandroid.models;

import com.google.gson.annotations.SerializedName;


/**
 * Throw model
 */
public class Throw {

    /**
     * Id of the throw
     */
    @SerializedName("Id")
    private int id;

    /**
     * Number of fallen skittles for the throw
     */
    @SerializedName("Fallen_skittles")
    private int fallenSkittles;

    /**
     * Position of throw into the turn
     */
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFallenSkittles() {
        return fallenSkittles;
    }

    public void setFallenSkittles(int fallenSkittles) {
        this.fallenSkittles = fallenSkittles;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
