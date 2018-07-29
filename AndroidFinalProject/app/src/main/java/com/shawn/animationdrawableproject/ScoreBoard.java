package com.shawn.animationdrawableproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ScoreBoard extends AppCompatActivity {

    private TextView txtWin, txtLose, txtDraw;
    private Button btn_restart,btn_ranking;
    private String new_name, new_score, new_time,new_lose,new_draw;
    private EditText name;
    private TextView txtTime;
    private DatabaseAdapter databaseAdapter;
    private int gottenIntentWin,gottenIntentLose,gottenIntentDraw;
    private Intent intent;
    private long date;
    private SimpleDateFormat simpleDateFormat;
    private String dateString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        initialisation();
        getValues();
        getTime();
        }

    @Override
    protected void onStart() {
        super.onStart();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void getTime(){

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    while (!isInterrupted()){
                        try {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    date=System.currentTimeMillis();
                                    simpleDateFormat=new SimpleDateFormat("MMM dd yyy h:mm", Locale.TAIWAN);
                                    dateString=simpleDateFormat.format(date);
                                    txtTime.setText(dateString);

                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
        }
    public void initialisation(){
        databaseAdapter=new DatabaseAdapter(ScoreBoard.this);
        txtTime=(TextView)findViewById(R.id.txttime);
        txtWin=(TextView)findViewById(R.id.tvWin);
        txtLose=(TextView)findViewById(R.id.tvLose);
        txtDraw=(TextView)findViewById(R.id.tvDraw);
        name=(EditText)findViewById(R.id.enter_name);
        btn_restart=(Button)findViewById(R.id.btn_restart);
        btn_ranking=(Button)findViewById(R.id.btn_addToSql);
        btn_ranking.setOnClickListener(myListener);
        btn_restart.setOnClickListener(myListener);
    }
    public void getValues(){
        intent=this.getIntent();
        gottenIntentWin=intent.getIntExtra( "wincount",1);
        gottenIntentLose=intent.getIntExtra("losecount",2);
        gottenIntentDraw=intent.getIntExtra("drawncount",3);
        txtWin.setText(new StringBuffer("").append(gottenIntentWin).append(""));
        txtLose.setText(new StringBuffer("").append(gottenIntentLose).append(""));
        txtDraw.setText(new StringBuffer("").append(gottenIntentDraw).append(""));
    }

    View.OnClickListener myListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_restart:
                    startActivity(new Intent(ScoreBoard.this,MainActivity.class));
                    break;
                case R.id.btn_addToSql:
                    getTime();
                    new_name=name.getText().toString().trim();
                    new_score=txtWin.getText().toString();
                    new_draw=txtDraw.getText().toString();
                    new_lose=txtLose.getText().toString();
                    new_time=txtTime.getText().toString();


                    Log.i("new_time=", new_time);
                    Log.i("new_name=", new_name);
                    Log.i("new_score=", new_score);
                    Log.i("new_draw=", new_draw);
                    Log.i("new_lose=", new_lose);

                    if(new_name.isEmpty()||new_name.length()==0||new_name.equals("")){
                        Toast.makeText(ScoreBoard.this,"Please, enter your name ",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        try {
                            databaseAdapter.createScoreboardList(new_time,new_name,new_score,new_lose,new_draw);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            startActivity(new Intent(ScoreBoard.this,RankingsActivity.class));
                        }
                    }
                    break;
            }
        }
    };


}
