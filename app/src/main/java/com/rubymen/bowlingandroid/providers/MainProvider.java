package com.rubymen.bowlingandroid.providers;

import com.google.gson.Gson;
import com.rubymen.bowlingandroid.models.Game;
import com.rubymen.bowlingandroid.models.Player;


public class MainProvider {

    /**
     * Persist fetched games
     */
    private static Game[] games;

    public static Game[] getGames() {
        return games;
    }

    /**
     * Load games for webservice
     */
    public static void fetchGamesFromWebservice() {
        RestProvider client = new RestProvider("http://bowling.noip.me/games/recents");

        try {
            client.Execute(RequestMethod.GET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String response = client.getResponse();

        Gson gson = new Gson();
        games = gson.fromJson(response, Game[].class);
    }

    /**
     * Load specific game from the webservice
     * @param id Id of the game
     * @return The fetched game
     */
    public static Game fetchGameFromWebservice(int id) {
        RestProvider client = new RestProvider("http://bowling.noip.me/games/" + id);

        try {
            client.Execute(RequestMethod.GET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String response = client.getResponse();

        Gson gson = new Gson();
        Game game = gson.fromJson(response, Game.class);

        return game;
    }

    /**
     * Find a game into the `games` array
     * @param id Id of the game
     * @return The finded game
     */
    public static Game findGameById(int id) {
        Game game = new Game();

        if (id != 0) {
            for (Game g : games) {
                if (id == g.getId()) {
                    game = g;
                }
            }
        }

        return game;
    }

    /**
     * Create a game in the webservice
     * @return The created game
     */
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

    /**
     * Add player to a game into the webservice
     * @param id Id of the game
     * @param p Player to attach
     */
    public static void addPlayerToGame(int id, Player p) {
        RestProvider client = new RestProvider("http://bowling.noip.me/games/" + id + "/player");
        client.AddParam("Pseudo", p.getPseudo());

        try {
            client.Execute(RequestMethod.POST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
