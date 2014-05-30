package com.rubymen.bowlingandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rubymen.bowlingandroid.adapters.ListGamesAdapter;
import com.rubymen.bowlingandroid.models.Game;
import com.rubymen.bowlingandroid.providers.MainProvider;

import java.util.ArrayList;

public class GamesActivity extends Activity implements View.OnClickListener, OnItemClickListener {

    /**
     * List adapter for players
     */
    private ListGamesAdapter adapter;

    /**
     * Local list of games
     */
    private ArrayList<Game> games;

    /**
     * List view
     */
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        games = new ArrayList<Game>();

        listView = (ListView) findViewById(R.id.games_list);

        adapter = new ListGamesAdapter(this, R.layout.game_template, games);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        new LoadGames().execute();

        Button newGameBtn = (Button) findViewById(R.id.new_game_btn);
        newGameBtn.setOnClickListener(this);
    }

    /**
     * Buttons bind
     * @param v Current view
     */
    public void onClick(View v) {
        Button caller = (Button) v;

        if (caller.getId() == R.id.new_game_btn) {
            Intent createGameActivity = new Intent(this, CreateGameActivity.class);
            startActivityForResult(createGameActivity, 1);
        }
    }

    /**
     * Access to game view on click
     * @param adapterView Adapter
     * @param view Current view
     * @param i Game clicked
     * @param l The row id of the game that was clicked
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView idGame = (TextView) view.findViewById(R.id.game_id);
        Intent showGameActivity = new Intent(GamesActivity.this, ShowGameActivity.class);
        showGameActivity.putExtra("id", idGame.getText());
        startActivityForResult(showGameActivity, 1);
    }

    /**
     * Handler when app comes on this page, refresh games
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            new LoadGames().execute();
        }
    }

    /**
     * Async class to load games
     */
    private class LoadGames extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            MainProvider.fetchGamesFromWebservice();
            return null;
        }

        protected void onPreExecute() {
            games.clear();
            Toast.makeText(getApplicationContext(), "Chargement des parties...", Toast.LENGTH_SHORT).show();
        }

        protected void onPostExecute(String result) {
            Game[] loadedGames = MainProvider.getGames();

            for (Game g : loadedGames) {
                games.add(g);
            }

            adapter.notifyDataSetChanged();

            Toast.makeText(getApplicationContext(), "C'est fait !", Toast.LENGTH_SHORT).show();
        }

    }

}
