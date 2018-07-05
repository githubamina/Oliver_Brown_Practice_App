package com.example.moo.coffeeorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.moo.coffeeorder.objects.Drink;

import java.util.LinkedList;

/**
 * Created by moo on 11/2/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "drinksMenu";

    //Drinks table name
    private static final String TABLE_DRINKS = "drinks";


    //Table column names
    private static final String KEY_ID = BaseColumns._ID;
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE_RESOURCE_ID = "image_resource_id";


    //Constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Query used for creating drinks table
    private static final  String CREATE_DRINKS_TABLE =
            "CREATE TABLE " + TABLE_DRINKS +
            "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_DESCRIPTION + " TEXT," + KEY_IMAGE_RESOURCE_ID + " INTEGER" + ");";



    //Creating table
    @Override
    public void onCreate(SQLiteDatabase db) { //Creates new database
        db.execSQL(CREATE_DRINKS_TABLE); //Create drink table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On upgrade, drop older table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINKS);

        //Create new table
        onCreate(db);

    }


    //Adding new drink
    public void addDrink (Drink drink){
        //Get writable database to edit database
        SQLiteDatabase db = this.getWritableDatabase();

        //Create new contentvalues object to store data to be inserted into database
        ContentValues values = new ContentValues();
        //Insert data into content values object
        values.put(KEY_NAME, drink.getName()); //Drink name
        values.put(KEY_DESCRIPTION, drink.getDescription()); //Drink description
        values.put(KEY_IMAGE_RESOURCE_ID, drink.getImageResourceId()); //Drink image id

        //Inserting row
        db.insert("drinks", null, values);
        db.close(); //Closing database connection

    }

    /**
     * Get a single Drink from database
     * @param drink_id id of drink
     * @return a single drink object
     */
    public Drink getDrink(long drink_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_DRINKS + " WHERE " + KEY_ID +
                " = " +  drink_id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Drink cd = new Drink();

        cd.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        cd.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
        cd.setImageResourceId(cursor.getInt(cursor.getColumnIndex(KEY_IMAGE_RESOURCE_ID)));

        return cd;
    }

    /**
     * Get all the drinks from database
     * @return list of all drinks
     */
    public LinkedList<Drink> getAllDrinks(){
        LinkedList<Drink> drinkList = new LinkedList<Drink>();
        String selectQuery = "SELECT * FROM " + "drinks";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do {
                Drink cd = new Drink();
                cd.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                cd.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                cd.setImageResourceId(cursor.getInt(cursor.getColumnIndex(KEY_IMAGE_RESOURCE_ID)));

                //Adding to drink list
                drinkList.add(cd);
            } while(cursor.moveToNext());
        }


        return drinkList;
    }

    /**
     * Updating a drink in the database
     */
    public int updateDrink(Drink drink){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, drink.getName());
        values.put(KEY_DESCRIPTION, drink.getDescription());
        values.put(KEY_IMAGE_RESOURCE_ID, drink.getImageResourceId());

        //Updating row
        return db.update(TABLE_DRINKS, values, KEY_ID + " = ?",new String[] { String.valueOf(drink.getId())});
    }

    /**
     * Deleting a drink
     */
    public void deleteDrink(long drink_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DRINKS, KEY_ID + " = ?",
                new String[] { String.valueOf(drink_id)});
    }

}
