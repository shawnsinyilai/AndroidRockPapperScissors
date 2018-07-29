package com.shawn.animationdrawableproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class RankingsActivity extends AppCompatActivity {

    private SimpleCursorAdapter simpleCursorAdapter;
    private Intent intent;
    private DatabaseAdapter databaseAdapter;
    private ListView gameRankings;
    private Button return_to_game;
    private TextView no_records;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        initialisation();
        displayList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void initialisation(){

        no_records=(TextView)findViewById(R.id.no_records);
        gameRankings=(ListView)findViewById(R.id.game_rankings);
        return_to_game=(Button)findViewById(R.id.return_to_game);
        databaseAdapter= new DatabaseAdapter(RankingsActivity.this);
        return_to_game.setOnClickListener(myListener);
        Log.i("dbCount=",String.valueOf(databaseAdapter.listContacts().getCount()));

    }

    public void displayList(){
        cursor= databaseAdapter.listContacts();
        String [] columns=new String[]{
          databaseAdapter.KEY_TIME,
          databaseAdapter.KEY_NAME,
          databaseAdapter.KEY_SCORE,
          databaseAdapter.KEY_SCORE_LOSE,
          databaseAdapter.KEY_SCORE_DRAW

        };
        int [] view = new int[]{
                R.id.txt_time,
                R.id.txt_name,
                R.id.txt_score,
                R.id.txt_score_lose,
                R.id.txt_score_draw
        };
        simpleCursorAdapter=new SimpleCursorAdapter(
                RankingsActivity.this,
                R.layout.list_layout,
                cursor,columns,view,0
        );
       gameRankings.setAdapter(simpleCursorAdapter);
    }

    View.OnClickListener myListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(RankingsActivity.this,MainActivity.class));
        }
    };
}
