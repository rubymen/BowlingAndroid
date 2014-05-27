package com.rubymen.bowlingandroid;

import android.app.Activity;
import android.os.Bundle;


public class ShowGameActivity extends Activity {

    private int id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_game);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
    }

}
