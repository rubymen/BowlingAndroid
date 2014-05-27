package com.rubymen.bowlingandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.rubymen.bowlingandroid.models.Game;
import com.rubymen.bowlingandroid.models.Player;
import com.rubymen.bowlingandroid.providers.MainProvider;

import java.util.ArrayList;


public class CreateGameActivity extends Activity implements View.OnClickListener, OnItemClickListener {

    private ArrayAdapter adapter;

    private ProgressDialog dialog;

    private ListView listView;

    private ArrayList<String> players = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        players = new ArrayList<String>();

        listView = (ListView) findViewById(R.id.added_players_list);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, players);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        Button addPlayerBtn = (Button) findViewById(R.id.add_player_btn);
        addPlayerBtn.setOnClickListener(this);

        Button addGameBtn = (Button) findViewById(R.id.add_game_btn);
        addGameBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button caller = (Button) v;

        if (caller.getId() == R.id.add_player_btn) {
            addPlayer();
        } else if (caller.getId() == R.id.add_game_btn) {
            new CreatingGame().execute();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        players.remove(i);

        adapter.notifyDataSetChanged();
    }

    private void addPlayer() {
        EditText player = (EditText) findViewById(R.id.add_player_text);
        String pseudo = player.getText().toString();

        players.add(pseudo);

        adapter.notifyDataSetChanged();

        player.setText("");
    }

    private class CreatingGame extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... strings) {
            Game game = MainProvider.createGame();

            for (String pseudo : players) {
                MainProvider.addPlayerToGame(game.getId(), new Player(pseudo));
            }

            return null;
        }

        protected void onPreExecute() {
            dialog = ProgressDialog.show(CreateGameActivity.this, "", "Création de la partie...", true);
        }

        protected void onPostExecute(String result) {
            dialog.hide();

            Intent createGameActivity = new Intent();
            setResult(RESULT_OK, createGameActivity);
            finish();
        }

    }

}
