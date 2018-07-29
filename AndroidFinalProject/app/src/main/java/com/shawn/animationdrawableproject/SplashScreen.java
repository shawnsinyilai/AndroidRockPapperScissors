package com.shawn.animationdrawableproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    private static final int time=6000;
    private AnimationDrawable animationDrawable;
    private ImageView startAnime;
    private ProgressBar progressBar;
    private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        slashScreen();
        runningAnime();
        music();
    }

    public void slashScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(SplashScreen.this,MenuActivity.class));
            }
        },time);
    }
    @Override
    protected void onStart() {
        super.onStart();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
    public void runningAnime(){
        progressBar=(ProgressBar)findViewById(R.id.loading);

      thread= new Thread(new Runnable() {
           @Override
           public void run() {
                        progressBar.setVisibility(View.VISIBLE);
               try {
                   Thread.sleep(time);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } finally {
                   thread.interrupt();
               }
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       startAnime=(ImageView)findViewById(R.id.startAnime);
                       animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(getBaseContext()
                               , R.drawable.runningchoices);
                       startAnime.setImageDrawable(animationDrawable);
                       animationDrawable.start();
                   }
               });

           }
       });
        try {
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            thread.interrupt();
        }
    }
    public void music(){
        startService(new Intent(this, BackgroundSoundService.class));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, BackgroundSoundService.class));
    }

}


