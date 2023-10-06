package com.uniquename.my_first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.uniquename.my_first_app.classes.FastFood;
import com.uniquename.my_first_app.classes.FastFoodMeal;
import com.uniquename.my_first_app.database.DatabaseHelper;

import java.util.ArrayList;

public class SeeRestaurantMealsActivity extends AppCompatActivity {

    ListView mGetMealsList;
    TextView mFastFoodName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_restaurant_meals);
        setTitleText();
        setFastFoodMealsAdapter();
    }

    public ArrayList<FastFoodMeal> getFastFoodMeals(){
        Intent getIntent=getIntent();
        int fastFoodId=getIntent.getIntExtra("FastFoodId",-1);
        DatabaseHelper dbHelper=new DatabaseHelper(SeeRestaurantMealsActivity.this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        return dbHelper.getFastFoodMeals(db,fastFoodId);

    }

    public void setFastFoodMealsAdapter(){

        mGetMealsList=findViewById(R.id.list_restaurant_meals);
        ArrayList <FastFoodMeal> getRestaurantMeals=getFastFoodMeals();
        RestaurantMealsAdapter restaurantMealsAdapter=new RestaurantMealsAdapter(SeeRestaurantMealsActivity.this,getRestaurantMeals);
        mGetMealsList.setAdapter(restaurantMealsAdapter);
    }

    public void setTitleText(){
        mFastFoodName=findViewById(R.id.fast_food_meals_title);
        Intent getIntent=getIntent();
        int fastFoodId=getIntent.getIntExtra("FastFoodId",-1);
        DatabaseHelper dbHelper=new DatabaseHelper(SeeRestaurantMealsActivity.this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        FastFood getFastFood=dbHelper.findFastFood(db,fastFoodId);
        mFastFoodName.setText("Les plats chez"+" "+getFastFood.getTitle());
    }

}

