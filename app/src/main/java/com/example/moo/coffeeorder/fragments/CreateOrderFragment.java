package com.example.moo.coffeeorder.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moo.coffeeorder.CustomItemClickListener;
import com.example.moo.coffeeorder.DatabaseHandler;
import com.example.moo.coffeeorder.DrinkAdapter;
import com.example.moo.coffeeorder.R;
import com.example.moo.coffeeorder.objects.Drink;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 *Fragment that display list of items user can choose to order
 */
public class CreateOrderFragment extends Fragment {


    //Instance of databasehandler
    DatabaseHandler db;

    //String to store the user's order
    String receiptText = "";

    //int to store the price of the user's order
    int totalPrice;


    //Request code to be used in sending intent to/from PlaceOrderFragment
    int REQUEST_CODE;



    public CreateOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Initialize databaseHandler
        db = new DatabaseHandler(getContext());


        //Create data to store in database
        //ArrayList to initially store drinks
        final ArrayList<Drink> drinks = new ArrayList<Drink>();
        //Creating new drink objects
        drinks.add(new Drink("Milk Iced Chocolate", getString(R.string.iced_choc_description), R.drawable.icechoc));
        drinks.add(new Drink("Dark Iced Chocolate", getString(R.string.iced_choc_description), R.drawable.icechoc));
        drinks.add(new Drink("White Iced Chocolate", getString(R.string.iced_choc_description), R.drawable.icechoc));
        drinks.add(new Drink("Iced Choc Banana (Milk)", getString(R.string.iced_choc_banana), R.drawable.icechoc));
        drinks.add(new Drink("Iced Choc Banana (Dark)", getString(R.string.iced_choc_banana), R.drawable.icechoco));
        drinks.add(new Drink("Iced Choc Banana (White", getString(R.string.iced_choc_banana), R.drawable.icechoco));
        drinks.add(new Drink("Iced Choc Coconut (Milk)", getString(R.string.iced_choc_coconut), R.drawable.icechoc));
        drinks.add(new Drink( "Iced Choc Coconut (Dark)", getString(R.string.iced_choc_coconut), R.drawable.icechoc));
        drinks.add(new Drink("Iced Choc Coconut (White)", getString(R.string.iced_choc_coconut), R.drawable.icechoc));
        drinks.add(new Drink("Milk Iced Mocha", getString(R.string.iced_mocha_description), R.drawable.icechoco));
        drinks.add(new Drink("Dark Iced Mocha", getString(R.string.iced_mocha_description), R.drawable.icechoco));
        drinks.add(new Drink("White Iced Mocha", getString(R.string.iced_mocha_description), R.drawable.icechoco));
        drinks.add(new Drink("Sneaky Snickers (Milk)", getString(R.string.sneaky_snickers), R.drawable.snick));
        drinks.add(new Drink("Sneaky Snickers (Dark)", getString(R.string.sneaky_snickers), R.drawable.snick));
        drinks.add(new Drink("Sneaky Snickers (White)", getString(R.string.sneaky_snickers), R.drawable.snick));
        drinks.add(new Drink("Iced Black Forest (Milk)", getString(R.string.iced_black_forest), R.drawable.icechoc));
        drinks.add(new Drink("Iced Black Forest (Dark)", getString(R.string.iced_black_forest), R.drawable.icechoc));
        drinks.add(new Drink("Iced Black Forest (White)", getString(R.string.iced_black_forest), R.drawable.icechoc));
        drinks.add(new Drink("Pink Choc", getString(R.string.pink_choc), R.drawable.blends));
        drinks.add(new Drink("Iced Choc Mojito", getString(R.string.iced_choc_mojito), R.drawable.blends));
        drinks.add(new Drink("Cookies & Cream", getString(R.string.cookies_cream), R.drawable.blends));
        drinks.add(new Drink("Iced Green Tea Latte", getString(R.string.green_tea_ice), R.drawable.icedleaves));
        drinks.add(new Drink("Iced Chai Latte", getString(R.string.chai_latte_ice), R.drawable.icedleaves));
        drinks.add(new Drink("Iced Coffee", getString(R.string.iced_coffee), R.drawable.icedleaves));
        drinks.add(new Drink("Iced Long Black", getString(R.string.iced_long_black), R.drawable.icedleaves));
        drinks.add(new Drink("Very Berry Crush", getString(R.string.berry_crush), R.drawable.crushes));
        drinks.add(new Drink("Lemon, Lime & Mint Crush", getString(R.string.lemon_lime), R.drawable.crushes));
        drinks.add(new Drink("Mango Passionfruit Crush", getString(R.string.mango_passionfruit), R.drawable.crushes));
        drinks.add(new Drink("Yuzu Crush", getString(R.string.yuzu), R.drawable.moreblends));
        drinks.add(new Drink("Lychee Crush", getString(R.string.lychee), R.drawable.moreblends));
        drinks.add(new Drink("Passionfruit Crush", getString(R.string.passionfruit), R.drawable.moreblends));
        drinks.add(new Drink("Strawberry Yoghurt Smoothie", getString(R.string.strawberry_yo), R.drawable.yogurtsmoothies));
        drinks.add(new Drink("Blueberry Yoghurt Smoothie", getString(R.string.blueberry_yo), R.drawable.yogurtsmoothies));
        drinks.add(new Drink("Strawberry Milkshake", getString(R.string.milkshake), R.drawable.milkshakes));
        drinks.add(new Drink("Caramel Milkshake", getString(R.string.milkshake), R.drawable.milkshakes));
        drinks.add(new Drink("Vanilla Milkshake", getString(R.string.milkshake), R.drawable.milkshakes));
        drinks.add(new Drink("Cherry Iced Tea", getString(R.string.iced_tea), R.drawable.teas));
        drinks.add(new Drink("Yuzu Iced Tea", getString(R.string.iced_tea), R.drawable.teas));
        drinks.add(new Drink("Mint Iced Tea", getString(R.string.iced_tea), R.drawable.teas));
        drinks.add(new Drink("Mango Iced Tea", getString(R.string.iced_tea), R.drawable.teas));
        drinks.add(new Drink("Oliver's Lemonade", getString(R.string.lemonade), R.drawable.teas));



        //Iterate through drinks list and store each item inside the drink table in the database
        for(int i = 0; i < drinks.size(); i++) {
            db.addDrink(drinks.get(i));
        }


        //Inflate fragment_create_order layout so that the recyclerview can be referenced below
        View rootView = inflater.inflate(R.layout.fragment_create_order, container, false);




        //Create a DrinkAdapter
        // pass in the drink database data to be displayed
        //set CustomItemClickListener to handle each item selection
        DrinkAdapter mAdapter = new DrinkAdapter(getContext(), db.getAllDrinks(), new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position){
                //String to store current selected drink's name
                String itemName = db.getAllDrinks().get(position).getName();

                //Add the current selected drink's name to the receiptText
                receiptText += ("\n" + itemName);

                //Increment the total price of the order by 8
                totalPrice += 8;

                //Set the request code to be used in sending data to PlaceOrderActivity
                REQUEST_CODE = 5;

                //Intent to open PlaceOrderActivity
                Intent placeOrder = new Intent(getContext(), PlaceOrderActivity.class);
                //Set receiptText and totalPrice as extras to send to PlaceOrderActivity
                placeOrder.putExtra("ItemName", receiptText);
                placeOrder.putExtra("ItemPrice", totalPrice);
                //Start PlaceOrderActivity
                startActivityForResult(placeOrder, REQUEST_CODE);

            }
        });



        //Get recyclerview from fragment_create_order.xml
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        //Set the drinkadapter on the recyclerview so that each drink item will be displayed and handled properly
        mRecyclerView.setAdapter(mAdapter);

        //Set linearlayoutmanager on the recyclerview
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return rootView;
    }

    /**
     *Retrieve data from PlaceDataActivity once it is closed
     * to determine if user wants to add to their order
     * or begin a new order
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            receiptText = data.getStringExtra("returnString");
            totalPrice = data.getIntExtra("returnInt", 0);
        }
    }

}
