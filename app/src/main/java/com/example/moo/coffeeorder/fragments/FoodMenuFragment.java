package com.example.moo.coffeeorder.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moo.coffeeorder.FoodItemAdapter;
import com.example.moo.coffeeorder.R;
import com.example.moo.coffeeorder.objects.Product;

import java.util.ArrayList;

/**
 * Fragment that displays a menu of food items in a list
 */
public class FoodMenuFragment extends Fragment {


    public FoodMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        //ArrayList to store product objects
        final ArrayList<Product> products = new ArrayList<Product>();
        //Create new product objects and add to products ArrayList
        products.add(new Product("Fondue for one", "Fresh strawberries served with bananas, marshmallows and premium Belgian Chocolate", R.drawable.fondue));
        products.add(new Product("Fondue for two", "Fresh strawberries, banana, marshmallows, banana bread and pretzels served with premium Belgian Chocolate", R.drawable.fondue));
        products.add(new Product("Strawberry Dip", "Fresh strawberries served with premium Milk or Dark Belgian Chocolate", R.drawable.strawdip));
        products.add(new Product("Baked Churros for one/two", "Freshly baked to order, sprinkled with cinnamon and icing sugar and served with premium Belgian Chocolate", R.drawable.churros));
        products.add(new Product("Strawberry Churros for one/two", "Our churros served with premium Belgian Chocolate and strawberries", R.drawable.strawchu));
        products.add(new Product("Ice Cream Waffle for one/two", "OB waffle/s served with vanilla ice cream and our premium Belgian Chocolate", R.drawable.icewaffles));
        products.add(new Product("Frutti Waffle for one/two", "OB waffle/s, strawberries, banana and vanilla ice cream served with premium Belgian Chocolate", R.drawable.fruttiwaffles));
        products.add(new Product("Grilled Banana & Walnuts Waffle for one/two", "Waffle/s topped with grilled banana, walnuts and vanilla ice cream served with premium Belgian Chocolate", R.drawable.bwwaffles));
        products.add(new Product("Mixed Berry Waffle for one/two", "OB waffle/s, mixed berries, vanilla ice cream topped with maple syrup", R.drawable.mbwaffles));
        products.add(new Product("S'mores Waffle for one/two", "Waffle/s, toasted marshmallows and vanilla ice cream served with premium Belgian Chocolate", R.drawable.smoreswaffles));
        products.add(new Product("Waffle Bites", "Waffle bites served with honeycomb, strawberries and whipped cream with a shot of our premium milk or dark Belgian Chocolate", R.drawable.wafflebites));
        products.add(new Product("OB Crepe/ Strawberry & Banana Crepe", "Crepe served with vanilla ice cream and Belgian Chocolate (+ strawberries and banana", R.drawable.crepe));
        products.add(new Product("Marshmallow Crepe", "OB Crepe served with toasted marshmallows, vanilla ice cream, whipped cream and chocolate sauce", R.drawable.marshcrepe));
        products.add(new Product("Cookies & Cream Crepe", "OB Crepe served with Oreo cookies, whipped cream and chocolate sauce", R.drawable.cccrepe));
        products.add(new Product("Frutti Sundae", "Two flavours of fruit gelato topped with bananas, strawberries, whipped cream, sweet flakes and a Chocolate wafer stick", R.drawable.frsundae));
        products.add(new Product("Black Forest Sundae", "Chocolate gelato topped with mixed berries, whipped cream, sweet flakes and a Chocolate wafer stick", R.drawable.bfsundae));
        products.add(new Product("Choc Mountain Sundae", "Chocolate gelato topped with choc brownies, whipped cream, sweet flakes and a Chocolate wafer stick", R.drawable.cmsundae));
        products.add(new Product("Crush Cookies Sundae", "A duo of cookies & cream and vanilla ice cream served with Oreo cookies, whipped cream, sweet flakes and a Chocolate wafer stick", R.drawable.ccsundae));
        products.add(new Product("Tropical Citrus Bomb Sundae", "Lemon sorbet topped with passionfruit, lime, fresh mint leaves, whipped cream, sweet flakes and a chocolate wafer stick", R.drawable.tcbsundae));




        //Inflate layout of fragment now so that the recyclerview can be referenced below
        View rootView = inflater.inflate(R.layout.fragment_obmenu, container, false);


        //Instantiate a FoodItemAdapter and pass in products ArrayList as data to be displayed
        FoodItemAdapter foodItemAdapter = new FoodItemAdapter(getContext(), products);


        //Initialize mRecyclerView to menurecyclerview in fragment_obmenu
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.menurecyclerview);

        //Set the foodItemAdapter on the recyclerview so the food items are displayed in menurecyclerview
        mRecyclerView.setAdapter(foodItemAdapter);

        //Set linearlayoutmanager on the recyclerview
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return rootView;

    }

}
