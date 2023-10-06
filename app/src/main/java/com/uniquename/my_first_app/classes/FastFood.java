package com.uniquename.my_first_app.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Blob;

public class FastFood  {
    int id;
    String title;
    String description;

    public FastFood(int id, String title, String description){
        this.id=id;
        this.title=title;
        this.description=description;

    }

    public FastFood(int id, String title, String description,byte[] photo){
        this.id=id;
        this.title=title;
        this.description=description;


    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

}
