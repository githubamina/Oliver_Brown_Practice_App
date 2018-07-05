package com.example.moo.coffeeorder;

import android.view.View;

/**
 * CustomItemClickListener interface to be used in CreateOrderFragment
 * to set appropriate behaviour for when the user selects a drink item from the list to order
 */


public interface CustomItemClickListener {

    public void onItemClick(View v, int position);

}
