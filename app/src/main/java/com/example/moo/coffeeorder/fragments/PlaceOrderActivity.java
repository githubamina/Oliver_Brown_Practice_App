package com.example.moo.coffeeorder.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.moo.coffeeorder.MainActivity;
import com.example.moo.coffeeorder.R;

import org.w3c.dom.Text;

import java.util.ArrayList;



public class PlaceOrderActivity extends AppCompatActivity {

    //TextView to display user's order
    TextView orderReceipt;

    //TextView to display cost of user's order
    TextView orderTotal;

    //String containing user's order to be sent via email intent
    String orderSummary;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout content_place_order.xml
        setContentView(R.layout.content_place_order);


        //Initialize orderTotal to the_price TextView in content_place_order
        orderTotal = (TextView) findViewById(R.id.the_price);

        //Initialize orderReceipt to the_receipt TextView in content_place_order
        orderReceipt = (TextView) findViewById(R.id.the_receipt);

        //Get intent
        Intent intent = getIntent();

        //Store user's order in order String
        String order = intent.getStringExtra("ItemName");

        //Store price of user;s order in orderPrice int
        int orderPrice = intent.getIntExtra("ItemPrice", 0);

        //Set orderReceipt text to order string
        orderReceipt.append(order);

        //Set orderTotal text to orderPrice
        orderTotal.setText("Total: $" + orderPrice);


        //Get ref to sharedpreferences
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //Get user's username from shared prefs and store in user string
        String user = sharedpreferences.getString("usernameKey", "");


        //Concatenate order, orderPrice and user to create orderSummary
        orderSummary = order + "\n" + orderPrice + "\n" + user;


        

    }

    public void sendOrder(View view) {
        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "New Order");
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        //Send intent back to CreateOrderFragment to tell it to refresh order data
        // Prepare data intent
        Intent data = new Intent();
        data.putExtra("returnString", "");
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);


        //Close the PlaceOrderActivity
        finish();



    }

    public void goBack(View view) {
        //User wants to add more to their order so simply return to CreateOrderFragment and previous
        //order data will still be saved
        onBackPressed();

    }

    public void cancel(View view) {
        //Send intent back to CreateOrderFragment to tell it to refresh order data
        // Prepare data intent
        Intent data = new Intent();
        data.putExtra("returnString", "");
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        //Close the PlaceOrderActivity
        finish();
    }



}
