package com.example.moo.coffeeorder.fragments;


import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moo.coffeeorder.MainActivity;
import com.example.moo.coffeeorder.R;



/**
 * Fragment for the home/user content
 */
public class MainFragment extends Fragment {


    //TextView for user's name
    TextView nameText;

    //TextView for user's email
    TextView emailText;




    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the content_main layout for this fragment
        View rootView = inflater.inflate(R.layout.content_main, container, false);

     SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getContext());


        //Initialize TextViews to respective views in content_main.xml
        nameText = (TextView) rootView.findViewById(R.id.user_name);

        emailText = (TextView) rootView.findViewById(R.id.user_email);




        //Set TextView texts to sharedpreferences values
        emailText.setText(sharedpreferences.getString("usernameKey", null));

        nameText.setText(sharedpreferences.getString("nameKey", null));


        return rootView;
    }


}
