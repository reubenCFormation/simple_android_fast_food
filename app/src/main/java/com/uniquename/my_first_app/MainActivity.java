package com.uniquename.my_first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.uniquename.my_first_app.classes.FastFood;
import com.uniquename.my_first_app.database.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   ListView mListFastFoods;
   Button mAddFastFoodBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigateAddFastFoodAct();
        displayFastFoods();
    }

    public void navigateAddFastFoodAct(){
        mAddFastFoodBtn=findViewById(R.id.add_fast_food_btn);
        mAddFastFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddFastFoodActivity.class);
                startActivity(intent);
            }
        });
    }

    public void displayFastFoods(){
        mListFastFoods=findViewById(R.id.list_fast_foods);
        DatabaseHelper dbHelper=new DatabaseHelper(MainActivity.this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        ArrayList<FastFood> getFastFoods = dbHelper.getAllFastFoods(db);
        FastFoodAdapter fastFoodAdapter=new FastFoodAdapter(MainActivity.this,getFastFoods);
        mListFastFoods.setAdapter(fastFoodAdapter);

    }








}
