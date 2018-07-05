package com.example.moo.coffeeorder.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;

import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moo.coffeeorder.LogInActivity;
import com.example.moo.coffeeorder.MainActivity;
import com.example.moo.coffeeorder.R;
import com.takisoft.fix.support.v7.preference.EditTextPreference;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;


/**
 * Activity containing SettingsFragment
 */
public class PreferenceActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout to activity_preference.xml
        setContentView(R.layout.activity_preference);

        //Create instance of SettingsFragment
        SettingsFragment settingsFragment = new SettingsFragment();
        //Create FragmentManager instance
        FragmentManager manager = getSupportFragmentManager();
        //Place foodFragment in content (FrameLayout in activity_preference.xml)
        manager.beginTransaction()
                .replace(R.id.content, settingsFragment)
                .commit();


    }


    /**
     * SettingsFragment that displays three preferences that the user can edit
     */
    public static class SettingsFragment extends PreferenceFragmentCompat {


        @Override
        public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {


            // Set the XML settings resource that holds the preferences
            setPreferencesFromResource(R.xml.settings, rootKey);



        }

    }
}

