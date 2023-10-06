package com.uniquename.my_first_app;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.uniquename.my_first_app.classes.FastFood;
import com.uniquename.my_first_app.classes.FastFoodMeal;

import java.util.ArrayList;

public class RestaurantMealsAdapter extends ArrayAdapter<FastFoodMeal> {
    public ArrayList<FastFoodMeal> fastFoodMeals;
    TextView mMealTitle;
    TextView mMealPrice;


    public RestaurantMealsAdapter(Context context, ArrayList <FastFoodMeal> fastFoodMeals){
        super(context,0,fastFoodMeals);
        this.fastFoodMeals=fastFoodMeals;

    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.restaurant_see_meals,parent,false);
        }
        FastFoodMeal fastFoodMealItem= fastFoodMeals.get(position);
        mMealTitle=convertView.findViewById(R.id.meal_title);
        mMealPrice=convertView.findViewById(R.id.meal_price);

        mMealTitle.setText(fastFoodMealItem.getMealTitle());
        mMealPrice.setText(String.valueOf(fastFoodMealItem.getPrice()+ " "+"$"));

        return convertView;

    }




}
