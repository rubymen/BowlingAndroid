package com.rubymen.bowlingandroid;

import android.app.Activity;
import android.os.Bundle;

import com.rubymen.bowlingandroid.providers.MainProvider;


public class CreateGame extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        new Thread(new Runnable() {
            public void run() {
                MainProvider.fetchPlayersFromWebservice();
                System.out.println(MainProvider.getPlayers());
            }
        }).start();
    }

}
