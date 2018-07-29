package com.shawn.animationdrawableproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


import static android.os.Process.killProcess;
import static android.os.Process.myPid;

public class MenuActivity extends AppCompatActivity {
    Button btn_start, btn_scores,btn_exit;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        btn_exit=(Button)findViewById(R.id.exits_btn);
        btn_scores=(Button)findViewById(R.id.scores_btn);
        btn_start=(Button)findViewById(R.id.start_btn);
        btn_start.setOnClickListener(myListener);
        btn_scores.setOnClickListener(myListener);
        btn_exit.setOnClickListener(myListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    View.OnClickListener myListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start_btn:
                    startActivity(new Intent(MenuActivity.this,MainActivity.class));
                    break;
                case R.id.scores_btn:
                    startActivity(new Intent(MenuActivity.this,RankingsActivity.class));
                    break;
                case R.id.exits_btn:
                    moveTaskToBack(true);
                    killProcess(myPid());
                    System.exit(0);
                    break;
            }

        }
    };
}
