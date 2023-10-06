package com.uniquename.my_first_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.uniquename.my_first_app.classes.FastFood;
import com.uniquename.my_first_app.classes.FastFoodMeal;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DatabaseName="Fast Food";
    private static final int DatabaseVersion=1;

    private final String FAST_FOOD_TABLE="restaurants_fast_food";

    private final String FAST_FOOD_MEALS_LIST="fast_food_meals_list";

    public DatabaseHelper(Context context){
        super(context,DatabaseName,null,DatabaseVersion);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("Create TABLE if not exists " + FAST_FOOD_TABLE+" (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(255),description TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + FAST_FOOD_MEALS_LIST + " (id INTEGER PRIMARY KEY AUTOINCREMENT, restaurants_fast_food_id INTEGER, meal_title VARCHAR(255), meal_price INTEGER, FOREIGN KEY (restaurants_fast_food_id) REFERENCES " + FAST_FOOD_TABLE + " (id))");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists restaurants_fast_food");
        db.execSQL("DROP table if exists fast_food_meals_list");
    }

    public void insertFastFoodRestaurant(SQLiteDatabase db, String title, String description){
        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("description",description);
        db.insert(FAST_FOOD_TABLE,null,values);

    }

    public void insertMealToFastFood(SQLiteDatabase db,String title, int price,int restaurants_fast_food_id){
        ContentValues values=new ContentValues();
        values.put("meal_title",title);
        values.put("meal_price",price);
        values.put("restaurants_fast_food_id",restaurants_fast_food_id);
        db.insert(FAST_FOOD_MEALS_LIST,null,values);

    }


    public ArrayList<FastFood> getAllFastFoods(SQLiteDatabase db){

        ArrayList <FastFood> fastFoods = new ArrayList <>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ FAST_FOOD_TABLE,null);
        int idIndex = cursor.getColumnIndex("id");
        int titleIndex = cursor.getColumnIndex("title");
        int descriptionIndex = cursor.getColumnIndex("description");
        while(cursor.moveToNext()){
            int id = cursor.getInt(idIndex);
            String title = cursor.getString(titleIndex);
            String description = cursor.getString(descriptionIndex);

            // Check if the "photo" column exists in this row

            FastFood fastFood=new FastFood(id,title,description);
            fastFoods.add(fastFood);

        }
        return fastFoods;
    }

    public ArrayList <FastFoodMeal> getFastFoodMeals(SQLiteDatabase db, int fast_food_id){
        ArrayList <FastFoodMeal> fastFoodMeals=new ArrayList <>();
        String[] selectionArgs={String.valueOf(fast_food_id)};
        Cursor cursor=db.rawQuery("SELECT * FROM "+ FAST_FOOD_MEALS_LIST+" WHERE restaurants_fast_food_id=?",selectionArgs);

        int idIndex=cursor.getColumnIndex("id");
        int titleIndex=cursor.getColumnIndex("meal_title");
        int priceIndex=cursor.getColumnIndex("meal_price");
        int restaurantIdIndex=cursor.getColumnIndex("restaurants_fast_food_id");

        while(cursor.moveToNext()){
            int id=cursor.getInt(idIndex);
            String title=cursor.getString(titleIndex);
            int price=cursor.getInt(priceIndex);
            int restaurantId=cursor.getInt(restaurantIdIndex);
            FastFoodMeal fastFoodMeal=new FastFoodMeal(id,title,price,restaurantId);
            fastFoodMeals.add(fastFoodMeal);
        }

        return fastFoodMeals;

    }

    public FastFood findFastFood(SQLiteDatabase db,int id){
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor=db.rawQuery("SELECT * FROM "+ FAST_FOOD_TABLE+" WHERE id=?",selectionArgs);
        if(cursor.moveToFirst()){
            int restaurantId=cursor.getColumnIndex("id");
            int titleIndex=cursor.getColumnIndex("title");
            int descriptionIndex=cursor.getColumnIndex("description");

            return new FastFood(cursor.getInt(restaurantId),cursor.getString(titleIndex),cursor.getString(descriptionIndex));
        }
        return null;
    }



}
