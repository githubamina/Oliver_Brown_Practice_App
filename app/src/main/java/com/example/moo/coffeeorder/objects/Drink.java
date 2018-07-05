package com.example.moo.coffeeorder.objects;

import android.provider.BaseColumns;

/**
 * Created by moo on 11/2/18.
 */

public class Drink {

    //Private variables for drink name, description and image
    private String mName;
    private String mDescription;
    private int mImageResourceId;


    //Constructors

    public Drink(){}

    public Drink(String name, String description, int imageResourceId){
        mName = name;
        mDescription = description;
        mImageResourceId = imageResourceId;
    }


    //Get method for Id
    public String getId(){
        return BaseColumns._ID;
    }

    //Get method for name
    public String getName(){
        return mName;
    }

    //Set method for name
    public void setName(String name){
        mName = name;
    }

    //Get method for description
    public String getDescription(){
        return mDescription;
    }

    //Set method for description
    public void setDescription(String description){
        mDescription = description;
    }

    //Get method for image resource id
    public int getImageResourceId(){
        return mImageResourceId;
    }

    //Set method for image resource id
    public void setImageResourceId(int imageResourceId){
        mImageResourceId = imageResourceId;
    }
}
