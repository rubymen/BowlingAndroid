package com.rubymen.bowlingandroid.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Game model
 */
public class Game implements Serializable {

    /**
     * Id of the game
     */
    @SerializedName("Id")
    private int id;

    /**
     * Id of the lane
     */
    @SerializedName("Lane_id")
    private String lane_id;

    /**
     * Array of Player attached to the game
     */
    private Player[] players;

    /**
     * Current state of the game
     */
    @SerializedName("State")
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLane_id() {
        return lane_id;
    }

    public void setLane_id(String lane_id) {
        this.lane_id = lane_id;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
