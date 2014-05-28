package com.rubymen.bowlingandroid.models;

import com.google.gson.annotations.SerializedName;


public class Throw {

    @SerializedName("Id")
    private int id;

    @SerializedName("Fallen_skittles")
    private int fallenSkittles;

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
