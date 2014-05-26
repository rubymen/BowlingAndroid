package com.rubymen.bowlingandroid.providers;

import com.google.gson.Gson;
import com.rubymen.bowlingandroid.models.Game;

public class MainProvider {

    private static Game[] games;

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

    public static Game[] getGames() {
        return games;
    }

}
