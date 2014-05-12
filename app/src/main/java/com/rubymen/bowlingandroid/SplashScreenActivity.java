package com.rubymen.bowlingandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreenActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent games = new Intent(SplashScreenActivity.this, GamesActivity.class);
                startActivity(games);
                finish();
            }
        }, 2000);
    }

}
