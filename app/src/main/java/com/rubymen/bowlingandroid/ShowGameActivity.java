package com.rubymen.bowlingandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.rubymen.bowlingandroid.adapters.GameAdapter;
import com.rubymen.bowlingandroid.models.Game;
import com.rubymen.bowlingandroid.providers.MainProvider;


public class ShowGameActivity extends Activity {

    private Game game;

    private GameAdapter adapter;

    private int id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_game);

        id = Integer.parseInt(getIntent().getStringExtra("id"));

        game = MainProvider.findGameById(id);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.game_list);
        adapter = new GameAdapter(ShowGameActivity.this, game);
        listView.setAdapter(adapter);
    }

    private class LoadGame extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            game = MainProvider.fetchGameFromWebservice(id);
            return null;
        }

        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Chargement de la partie...", Toast.LENGTH_SHORT).show();
        }

        protected void onPostExecute(String result) { }

    }

}
