package com.rubymen.bowlingandroid;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import com.rubymen.bowlingandroid.adapters.ListGamesAdapter;
import com.rubymen.bowlingandroid.models.Game;
import com.rubymen.bowlingandroid.providers.MainProvider;

public class GamesActivity extends Activity {

    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        new Async().execute();
        listView = (ListView) findViewById(R.id.list_game_inprogress);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idGame = (TextView) view.findViewById(R.id.id);
                Intent detailPage = new Intent(GamesActivity.this, ShowGameActivity.class);
                detailPage.putExtra("idGame", idGame.getText());
                startActivity(detailPage);
            }
        });
    }

    private class Async extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            MainProvider.fetchGamesFromWebservice();
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Game[] games = MainProvider.getGames();
            final ListGamesAdapter adapter = new ListGamesAdapter(GamesActivity.this, R.layout.game_template, games);
            listView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}
