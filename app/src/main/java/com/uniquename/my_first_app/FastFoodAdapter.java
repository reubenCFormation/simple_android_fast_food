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

import java.util.ArrayList;

public class FastFoodAdapter extends ArrayAdapter<FastFood> {
    public ArrayList<FastFood> fastFoodList;
    TextView getFastFoodTitle;
    TextView getFastFoodDescription;


    Button seeMealsBtn;

    Button addMealBtn;

    public FastFoodAdapter(Context context, ArrayList <FastFood> fastFoodList){
        super(context,0,fastFoodList);
            this.fastFoodList=fastFoodList;

    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.fast_food_view,parent,false);
        }
        FastFood fastFoodItem= fastFoodList.get(position);

        getFastFoodTitle=convertView.findViewById(R.id.fast_food_title);
        getFastFoodDescription=convertView.findViewById(R.id.fast_food_description);
        getFastFoodDescription=convertView.findViewById(R.id.fast_food_description);
        seeMealsBtn=convertView.findViewById(R.id.see_meals_btn);
        addMealBtn=convertView.findViewById(R.id.add_meal_btn);

        getFastFoodTitle.setText(fastFoodItem.getTitle());
        getFastFoodDescription.setText(fastFoodItem.getDescription());
        treatClickOnAddMealBtn(addMealBtn,fastFoodItem);
        treatClickOnSeeMealsBtn(seeMealsBtn,fastFoodItem);

        return convertView;

    }

    public void treatClickOnAddMealBtn(Button btn,FastFood fastFood){
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent addMealIntent=new Intent(getContext(),AddMealActivity.class);
                        addMealIntent.putExtra("FastFoodId",fastFood.getId());
                        getContext().startActivity(addMealIntent);
                    }
                });
    }

    public void treatClickOnSeeMealsBtn(Button btn,FastFood fastFood){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeMealsIntent=new Intent(getContext(),SeeRestaurantMealsActivity.class);
                seeMealsIntent.putExtra("FastFoodId",fastFood.getId());
                getContext().startActivity(seeMealsIntent);
            }
        });
    }


 }
