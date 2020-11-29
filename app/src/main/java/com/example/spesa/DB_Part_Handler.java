package com.example.spesa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DB_Part_Handler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "foodManager";
    private static final String TABLE_FOOD = "food";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DB_Part_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_FOOD_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);

        // Create tables again
        onCreate(db);
    }

    // code to add the new food
    void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName()); // Food Name

        // Inserting Row
        db.insert(TABLE_FOOD, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // Deleting single food
    public void deleteFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD, KEY_ID + " = ?",
                new String[] { String.valueOf(food.getId()) });
        db.close();
        //String cont_rem = "id:" + food.getID();
        //Log.d("Numero contatto: ", cont_rem);
    }

    // code to get the single food
    Food getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOOD, new String[] { KEY_ID,
                        KEY_NAME}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Food food = new Food(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        // return food
        return food;
    }

    // code to get all foods in a list view
    public List<Food> getAllFood() {
        List<Food> foodList = new ArrayList<Food>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //com.example.spesa.Contact contact = new com.example.spesa.Contact();
                Food food = new Food(100, "Bohoh");
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                // Adding food to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return food list
        return foodList;
    }

    // code to update the single food
    public int updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());

        // updating row
        return db.update(TABLE_FOOD, values, KEY_ID + " = ?",
                new String[] { String.valueOf(food.getId()) });
    }

    // Getting foods Count
    public int getFoodCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}


