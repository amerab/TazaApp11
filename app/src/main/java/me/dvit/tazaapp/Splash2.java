package me.dvit.tazaapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash2 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {
         final int SPLASH_DISPLAY_LENGTH = 900;
        super.onCreate(icicle);
        setContentView(R.layout.splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash2.this,MainActivity.class);
                Splash2.this.startActivity(mainIntent);
                Splash2.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
