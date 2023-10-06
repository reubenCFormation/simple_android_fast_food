package com.uniquename.my_first_app.classes;

public class FastFoodMeal {
    int id;
    int restaurants_fast_food_id;
    String meal_title;
    int price ;

    public FastFoodMeal(int id,String meal_title,int price,int restaurants_fast_food_id){
        this.id=id;
        this.restaurants_fast_food_id=restaurants_fast_food_id;
        this.meal_title=meal_title;
        this.price=price;
    }

    public int getId(){
        return this.id;
    }
    public int getPrice(){
        return this.price;
    }
    public String getMealTitle(){
        return this.meal_title;
    }

    public int getRestaurantFastFoodId(){
        return this.restaurants_fast_food_id;
    }
}
