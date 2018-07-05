package com.example.moo.coffeeorder.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moo.coffeeorder.R;

/**
 * Fragment that displays special offers to user
 */
public class OffersFragment extends Fragment {


    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_offers.xml
        return inflater.inflate(R.layout.fragment_offers, container, false);
    }

}
