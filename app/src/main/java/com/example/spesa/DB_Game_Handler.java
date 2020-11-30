package com.example.spesa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DB_Game_Handler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gameManager";
    private static final String TABLE_GAME = "game";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MARK = "mark";

    public DB_Game_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAME_TABLE = "CREATE TABLE " + TABLE_GAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_MARK + " TEXT" + ")";
        db.execSQL(CREATE_GAME_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);

        // Create tables again
        onCreate(db);
    }

    // code to add the new food
    void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, game.getName()); // Name
        values.put(KEY_MARK, game.getMark()); // Mark

        // Inserting Row
        db.insert(TABLE_GAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // Deleting single food
    public void deleteGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GAME, KEY_ID + " = ?",
                new String[] { String.valueOf(game.getId()) });
        db.close();
        //String cont_rem = "id:" + food.getID();
        //Log.d("Numero contatto: ", cont_rem);
    }

    // code to get the single food
    Game getGame(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GAME, new String[] { KEY_ID,
                        KEY_NAME, KEY_MARK}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Game game = new Game(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        // return game
        return game;
    }

    // code to get all foods in a list view
    public List<Game> getAllGame() {
        List<Game> gameList = new ArrayList<Game>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //com.example.spesa.Contact contact = new com.example.spesa.Contact();
                Game game = new Game("Minecraft", "10");
                game.setId(Integer.parseInt(cursor.getString(0)));
                game.setName(cursor.getString(1));
                game.setMark(cursor.getString(2));
                // Adding food to list
                gameList.add(game);
            } while (cursor.moveToNext());
        }
        // return food list
        return gameList;
    }

    // code to update the single food
    public int updateGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, game.getName());
        values.put(KEY_MARK, game.getMark());

        // updating row
        return db.update(TABLE_GAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(game.getId()) });
    }

    // Getting foods Count
    public int getGameCount() {
        String countQuery = "SELECT  * FROM " + TABLE_GAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}


