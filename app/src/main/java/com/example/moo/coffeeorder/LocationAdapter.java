package com.example.moo.coffeeorder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by moo on 23/3/18.
 */

public class LocationAdapter extends ArrayAdapter<String>{


    //LocationAdapter constructor
    public LocationAdapter(@NonNull Context context, ArrayList<String> locations) {
        super(context, 0, locations);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View locationItemView = convertView;
        if (locationItemView == null) {
            locationItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.location_item, parent, false);
        }


        // Get the location object located at this position in the list
        String currentLocation = getItem(position);

        // Find location_text_view in the location_item.xml layout
        TextView locTextView = (TextView) locationItemView.findViewById(R.id.location_text_view);

        //Set currentLocation text to locTextView
        locTextView.setText(currentLocation);



        // Return the whole location item layout so that it can be shown in the ListView.
        return locationItemView;

    }


}
