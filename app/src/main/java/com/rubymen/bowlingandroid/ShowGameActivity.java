package com.rubymen.bowlingandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.rubymen.bowlingandroid.adapters.GameAdapter;
import com.rubymen.bowlingandroid.models.Game;
import com.rubymen.bowlingandroid.models.Player;
import com.rubymen.bowlingandroid.providers.MainProvider;

import java.util.ArrayList;


public class ShowGameActivity extends Activity implements View.OnClickListener {

    private GameAdapter adapter;

    private Game game;

    private int id;

    private ArrayList<Player> players;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_game);

        players = new ArrayList<Player>();

        id = Integer.parseInt(getIntent().getStringExtra("id"));

        game = MainProvider.findGameById(id);

        for (Player p : game.getPlayers()) {
            players.add(p);
        }

        setTitle("Jeu de la piste " + game.getLane_id());

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.game_list);
        adapter = new GameAdapter(ShowGameActivity.this, players);
        listView.setAdapter(adapter);

        Button refreshGameBtn = (Button) findViewById(R.id.refresh_game_btn);
        refreshGameBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button caller = (Button) v;

        if (caller.getId() == R.id.refresh_game_btn) {
            new LoadGame().execute();
        }
    }

    private class LoadGame extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            game = MainProvider.fetchGameFromWebservice(id);

            for (Player p : game.getPlayers()) {
                players.add(p);
            }

            return null;
        }

        protected void onPreExecute() {
            players.clear();
            Toast.makeText(getApplicationContext(), "Chargement de la partie...", Toast.LENGTH_SHORT).show();
        }

        protected void onPostExecute(String result) {
            adapter.notifyDataSetChanged();
        }

    }

}
