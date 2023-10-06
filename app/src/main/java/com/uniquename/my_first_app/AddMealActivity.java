package com.uniquename.my_first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquename.my_first_app.classes.FastFood;
import com.uniquename.my_first_app.database.DatabaseHelper;

public class AddMealActivity extends AppCompatActivity {
    EditText mAddMealTitle;
    EditText mAddMealPrice;
    Button mAddMealBtn;

    TextView mGetAddMealTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        initiliazeFields();
        setTitleText();
        addMealToRestaurant();

    }

    public void initiliazeFields(){
        mAddMealTitle=findViewById(R.id.add_meal_title);
        mAddMealPrice=findViewById(R.id.add_meal_price);
        mAddMealBtn=findViewById(R.id.add_meal_btn);

    }

    public void setTitleText(){
        mGetAddMealTitle=findViewById(R.id.add_fast_food_meal_title);
        FastFood getFastFood=getFastFood();
        mGetAddMealTitle.setText("Ajouter un repas chez"+" "+getFastFood.getTitle());
    }



    public FastFood getFastFood(){
        Intent getIntent=getIntent();
        int fastFoodId=getIntent.getIntExtra("FastFoodId",-1);
        DatabaseHelper dbHelper=new DatabaseHelper(AddMealActivity.this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        FastFood getFastFood=dbHelper.findFastFood(db,fastFoodId);
        return getFastFood;
    }

    public void addMealToRestaurant(){
      mAddMealBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
                handleAddMeal();
          }
      });

    }

    public void handleAddMeal(){
        if(mAddMealTitle.getText().toString().isEmpty() || mAddMealPrice.getText().toString().isEmpty()){
            Toast.makeText(AddMealActivity.this,"Viullez ajouter un prix et un titre",Toast.LENGTH_SHORT).show();
        }
        else{
            FastFood getFastFood=getFastFood();
            DatabaseHelper dbHelper=new DatabaseHelper(AddMealActivity.this);
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            dbHelper.insertMealToFastFood(db,mAddMealTitle.getText().toString(),Integer.parseInt(mAddMealPrice.getText().toString()),getFastFood.getId());
            Toast.makeText(AddMealActivity.this,"Repas bien ajouté !",Toast.LENGTH_SHORT).show();
        }
    }


}