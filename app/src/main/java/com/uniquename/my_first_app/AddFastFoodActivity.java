package com.uniquename.my_first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uniquename.my_first_app.database.DatabaseHelper;

public class AddFastFoodActivity extends AppCompatActivity {
    EditText mRestaurantName;
    EditText mRestaurantDescription;
    Button mAddRestaurantButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fast_food);
        initializeFields();
        addFastFood();
    }
    public void initializeFields(){
        mRestaurantName=findViewById(R.id.restaurant_name);
        mRestaurantDescription=findViewById(R.id.restaurant_description);
        mAddRestaurantButton=findViewById(R.id.add_fast_food_btn);
    }
    public void addFastFood(){
        DatabaseHelper dbHelper=new DatabaseHelper(AddFastFoodActivity.this);
        mAddRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRestaurantName.getText().toString().isEmpty() || mRestaurantDescription.getText().toString().isEmpty()){
                    Toast.makeText(AddFastFoodActivity.this,"Viullez preciser un nom et une description",Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteDatabase db=dbHelper.getWritableDatabase();
                    dbHelper.insertFastFoodRestaurant(db,mRestaurantName.getText().toString(),mRestaurantDescription.getText().toString());
                    Toast.makeText(AddFastFoodActivity.this,"Restaurant Ajouté !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


