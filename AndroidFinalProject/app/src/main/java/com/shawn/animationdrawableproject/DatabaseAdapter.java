package com.shawn.animationdrawableproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseAdapter {

    static final String KEY_ID="_id";
    static final String KEY_TIME="time";
    static final String KEY_NAME="name";
    static final String KEY_SCORE="score";
    static final String KEY_SCORE_LOSE="lose_score";
    static final String KEY_SCORE_DRAW="draw_score";
    private static final String TABLE_NAME="scoreboard";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private Cursor cursor;
    private ContentValues contentValues;

    public DatabaseAdapter(Context context){
        this.context=context;
        openDatabase();
    }

    public void openDatabase(){
        databaseHelper= new DatabaseHelper(context);
        sqLiteDatabase=databaseHelper.getWritableDatabase();
        Log.i("DATABASE==",sqLiteDatabase.toString());
    }

    public long createScoreboardList(String time,String name,String score,String lose_score,String draw_score){
        try {
            contentValues=new ContentValues();
            contentValues.put(KEY_TIME, time);
            contentValues.put(KEY_NAME, name);
            contentValues.put(KEY_SCORE, score);
            contentValues.put(KEY_SCORE_LOSE, lose_score);
            contentValues.put(KEY_SCORE_DRAW, draw_score);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor listContacts(){
        cursor=sqLiteDatabase.query(
                TABLE_NAME,
                new String []{KEY_ID,KEY_TIME,KEY_NAME,KEY_SCORE,KEY_SCORE_LOSE,KEY_SCORE_DRAW},
                null,null,
                "_id",null,"score desc","10");
        if (cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

}

