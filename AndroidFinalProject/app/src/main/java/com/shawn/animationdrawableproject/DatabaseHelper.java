package com.shawn.animationdrawableproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String KEY_ID="_id";
    private static final String KEY_TIME="time";
    private static final String KEY_NAME="name";
    private static final String KEY_SCORE="score";
    private static final String KEY_SCORE_LOSE="lose_score";
    private static final String KEY_SCORE_DRAW="draw_score";
    private static final String NAME_OF_DATABASE="MyScoreBoard";
    private static final String TABLE_NAME="scoreboard";
    private static final int DB_VERSION=4;

    public DatabaseHelper(Context context) {
        super(context, NAME_OF_DATABASE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String DATABASE_CREATE =" CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                KEY_ID + " Integer PRIMARY KEY autoincrement, " +
                KEY_TIME + "," +
                KEY_NAME  + "," +
                KEY_SCORE+ " Integer," +//cast to integer
                KEY_SCORE_LOSE+ " Integer, " +
                KEY_SCORE_DRAW+ " Integer " +
                ") ; ";
                db.execSQL(DATABASE_CREATE);
                /*if you want to execute something in database without concerning its output
                 (e.g create/alter tables), then use execSQL,
                 but if you are expecting some results in return against your query
                 (e.g. select records) then use rawQuery*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
