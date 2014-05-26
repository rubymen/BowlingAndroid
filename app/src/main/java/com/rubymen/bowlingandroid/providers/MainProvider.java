package com.rubymen.bowlingandroid.providers;

import com.google.gson.Gson;
import com.rubymen.bowlingandroid.models.Game;
import com.rubymen.bowlingandroid.models.Player;


public class MainProvider {

    private static Game[] games;

    public static Game[] getGames() {
        return games;
    }

    public static void fetchGamesFromWebservice() {
        RestProvider client = new RestProvider("http://bowling.noip.me/games/");

        try {
            client.Execute(RequestMethod.GET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String response = client.getResponse();

        Gson gson = new Gson();
        games = gson.fromJson(response, Game[].class);
    }

    public static Game createGame() {
        RestProvider client = new RestProvider("http://bowling.noip.me/games/new");

        try {
            client.Execute(RequestMethod.POST);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String response = client.getResponse();

        Gson gson = new Gson();
        Game game = gson.fromJson(response, Game.class);

        return game;
    }

    public static void addPlayerToGame(String id, Player p) {
        RestProvider client = new RestProvider("http://bowling.noip.me/games/" + id + "/player");
        client.AddParam("Pseudo", p.getPseudo());

        try {
            client.Execute(RequestMethod.POST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
