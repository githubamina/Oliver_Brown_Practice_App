package com.example.moo.coffeeorder;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.moo.coffeeorder.fragments.FoodMenuFragment;
import com.example.moo.coffeeorder.fragments.LocationsFragment;
import com.example.moo.coffeeorder.fragments.MainFragment;
import com.example.moo.coffeeorder.fragments.OffersFragment;
import com.example.moo.coffeeorder.fragments.OrderFragment;
import com.example.moo.coffeeorder.fragments.PreferenceActivity;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Menu object for drawer/tool bar menu items
    private Menu menu;

    //NavigationView object to be used for nav drawer
    public NavigationView navigationView;

    //Int variable to store the current menuItemId
    //used to prevent doubles of fragments being opened and added to the back stack
    private int menuItemId;

    //Create instance of DatabaseHandler so that the database can be accessed
    DatabaseHandler db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set view as activity_main
        setContentView(R.layout.activity_main);

        //Prevent app from being used in horizontal mode
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //Inflate toolbar from app_bar_main.xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        setSupportActionBar(toolbar);

        //Disable the app title from displaying in the action bar
        //so that the logo and menu items can be displayed instead
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //Set the app logo (from the manifest) to appear in the action bar
        toolbar.setLogo(R.drawable.logo);

        //Inflate drawerlayout from activity_main.xml
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);




        //Create ActionBarDrawerToggle object to be used when the menu icon in the toolbar is tapped
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        //addDrawerListener takes in toggle parameter to listen for drawer open and close events
        //so that when the user taps the drawable button, the nav drawer slides out
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        //syncState syncs the state of the drawer indicator
        toggle.syncState();




        //Initialize navigationView to nav_view from activity_main.xml
        navigationView = (NavigationView) findViewById(R.id.nav_view);


        //Set listener to the navigation drawer to listen for nav menu item clicks
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);

        }



        //Set the fragment container to contain the main fragment content on creation of activity
        openMainFrag();







        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }

    }



    /**
     * Handles item selections in the navigation drawer
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Inflate drawer layout from activity_main.xml
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Check if the current selected menu item is the one being tapped
        //and return false to avoid the fragment from being created again
        if (menuItemId == item.getItemId()) {
            drawer.closeDrawer(navigationView);
            return false;
        }


        //Get the currently selected item's Id, and use switch statement
        //to carry out appropriate actions for selected item
        switch (item.getItemId()) {
            case R.id.nav_home:
                //Open MainFragment
                openMainFrag();
                //Update menu highlights/icons
                setIcon(0);
                //Close drawer after item is selected
                drawer.closeDrawer(GravityCompat.START);
                //Update menuItemId to prevent this item's fragment from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;

            case R.id.nav_order:
                //Open OrderFragment
                openOrderFrag();
                //Update menu highlights/icons
                setIcon(1);
                //Close drawer after item is selected
                drawer.closeDrawer(GravityCompat.START);
                //Update menuItemId to prevent this item's fragment from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;

            case R.id.nav_offers:
                //Open OffersFragment
                openOffersFrag();
                //Update menu highlights/icons
                setIcon(2);
                //Close drawer after item is selected
                drawer.closeDrawer(GravityCompat.START);
                //Update menuItemId to prevent this item's fragment from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;

            case R.id.nav_locations:
                //Open LocationsFragment
                openLocationsFrag();
                //Update menu highlights/icons
                setIcon(3);
                //Close drawer after item is selected
                drawer.closeDrawer(GravityCompat.START);
                //Update menuItemId to prevent this item's fragment from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;

            case R.id.nav_menu:
                //Pass arbitrary number to setIcon for default menu icons
                setIcon(9);
                //Close drawer after item is selected
                drawer.closeDrawer(GravityCompat.START);
                //Open FoodMenuFragment
                openFoodMenuFrag();
                //Update menuItemId to prevent this item's fragment from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;

            case R.id.settings:
                //Close drawer after item is selected
                drawer.closeDrawer(GravityCompat.START);
                //Intent to start PreferenceActivity
                Intent openSettings = new Intent(this, PreferenceActivity.class);
                startActivity(openSettings);
                //Update menuItemId to prevent this item's activity from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;

            case R.id.facebook:
                //Close drawer after item is selected
                drawer.closeDrawer(GravityCompat.START);
                //Oliver Brown facebook url
                String url = "https://www.facebook.com/oliverbrowncafe/";
                //Intent to open OB fb page
                Intent openFB = new Intent(Intent.ACTION_VIEW);
                openFB.setData(Uri.parse(url));
                startActivity(openFB);
                //Update menuItemId to prevent the multiple pages being opened
                menuItemId = item.getItemId();
                return true;

            case R.id.instagram:
                //Close drawer after item is selected
                drawer.closeDrawer(GravityCompat.START);
                //Oliver Brown instagram url
                String instaurl = "https://www.instagram.com/oliverbrownofficial/?hl=en";
                //Intent to open OB ig page
                Intent openIG = new Intent(Intent.ACTION_VIEW);
                openIG.setData(Uri.parse(instaurl));
                startActivity(openIG);
                //Update menuItemId to prevent multiple pages being opened
                menuItemId = item.getItemId();
                return true;

            case R.id.logout:
                //Close drawer after item is selected
                drawer.closeDrawer(GravityCompat.START);
                //Intent to exit app
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);


            default:
                return false;
        }


    }


    /**
     * Inflates menu from activity_main_drawer.xml
     * sets overflow items to be invisible in the actionbar
     * so that only the main items appear
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate activity_main_drawer.xml into a menu object
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);

        //Initialize menu object
        //so that it can be used to find menu items
        this.menu = menu;

        //Set the visibility of the extra menu items to false so
        //that the only ones that appear as icons in the toolbar
        //are the ones likely to be used most often
        MenuItem foodmenu = menu.findItem(R.id.nav_menu);
        foodmenu.setVisible(false);
        MenuItem settings = menu.findItem(R.id.settings);
        settings.setVisible(false);
        MenuItem social = menu.findItem(R.id.socials);
        social.setVisible(false);
        MenuItem facebook = menu.findItem(R.id.facebook);
        facebook.setVisible(false);
        MenuItem instagram = menu.findItem(R.id.instagram);
        instagram.setVisible(false);

        return true;
    }

    /**
     * Handle action bar menu item clicks here
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Check value of menuItemId and if it is the same as the currently selected item,
        //return early to prevent the same fragment from being recreated
        if (menuItemId == item.getItemId()) {
            return false;
        }

       //Get the currently selected item's Id, and use switch statement
        //to carry out appropriate actions for selected item
        switch (item.getItemId()) {
            case R.id.nav_home:
                //Open MainFragment
                openMainFrag();
                //Update menu highlights/icons
                setIcon(0);
                //Update menuItemId to prevent this item's fragment from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;

            case R.id.nav_order:
                //Open OrderFragment
                openOrderFrag();
                //Update menu highlights/icons
                setIcon(1);
                //Update menuItemID to prevent this item's fragment from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;

            case R.id.nav_offers:
                //Open the OffersFragment
                openOffersFrag();
                //Update menu highlights/icons
                setIcon(2);
                //Update menuItemID to prevent this item's fragment from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;

            case R.id.nav_locations:
                //Open the LocationsFragment
                openLocationsFrag();
                //Update menu highlights/icons
                setIcon(3);
                //Update menuItemID to prevent this item's fragment from being launched again
                //if the user taps the icon again
                menuItemId = item.getItemId();
                return true;
            default:
                return false;

        }
    }



    /**
     * Method to be called when the home menu item is selected
     * Opens the home/main fragment
     */
    public void openMainFrag(){
        //Create instance of MainFragment
        MainFragment mainFragment = new MainFragment();
        //Create Fragment Manager instance
        FragmentManager manager = getSupportFragmentManager();
        //Replace current fragment in location_fragment with mainFragment
        manager.beginTransaction()
                .replace(R.id.location_fragment, mainFragment)
                .commit();

    }


    /**
     * Method to be called when the order menu item is selected
     * Opens the order fragment
     */
    public void openOrderFrag() {
        //Create instance of OrderFragment
        OrderFragment orderFragment = new OrderFragment();
        //Create FragmentManager instance
        FragmentManager manager = getSupportFragmentManager();
        //Replace current fragment in location_fragment with orderFragment
        manager.beginTransaction()
                .replace(R.id.location_fragment, orderFragment)
                .commit();

    }


    /**
     * Method to be called when the offers menu item is selected
     * Opens the offers fragment
     */
    public void openOffersFrag() {
        //Create instance of OffersFragment
        OffersFragment offersFragment = new OffersFragment();
        //Create FragmentManager instance
        FragmentManager manager = getSupportFragmentManager();
        //Replace current fragment in location_fragment with offersFragment
        manager.beginTransaction()
                .replace(R.id.location_fragment, offersFragment)
                .commit();

    }

    /**
     * Method to be called when the locations menu item is selected
     * Opens the locations fragment
     */
    public void openLocationsFrag() {
        //Create instance of LocationsFragment
        LocationsFragment locationsFragment = new LocationsFragment();
        //Create FragmentManager instance
        FragmentManager manager = getSupportFragmentManager();
        //Replace current fragment in location_fragment with locationsFragment
        manager.beginTransaction()
                .replace(R.id.location_fragment, locationsFragment)
                .commit();


    }

    public void openFoodMenuFrag(){
        //Create instance of FoodMenuFragment
        FoodMenuFragment foodFragment = new FoodMenuFragment();
        //Create FragmentManager instance
        FragmentManager manager = getSupportFragmentManager();
        //Replace current fragment in location_fragment with foodFragment
        manager.beginTransaction()
                .replace(R.id.location_fragment, foodFragment)
                .commit();

    }






    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        //check is the database is open and close it if it is
        if(db != null){db.close();}

        //Resets menuItemId so that menu items can be selected again
        menuItemId = -1;

    }


    /**
     * Helper method that takes in an int representing the id of the current
     * menu item and updates the action bar icons to reflect the current selection
     * also sets the corresponding navigation drawer item to be checked
     * @param itemId id int of the current menu item selection
     */
    public void setIcon(int itemId){

        Menu nMenu = navigationView.getMenu();

        switch(itemId){
            case 0:
                nMenu.getItem(0).setChecked(true);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_user2));
                menu.getItem(1).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_order));
                menu.getItem(2).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_offers));
                menu.getItem(3).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_place));
                break;
            case 1:
                nMenu.getItem(1).setChecked(true);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_user));
                menu.getItem(1).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_order2));
                menu.getItem(2).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_offers));
                menu.getItem(3).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_place));
                break;
            case 2:
                nMenu.getItem(2).setChecked(true);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_user));
                menu.getItem(1).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_order));
                menu.getItem(2).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_offers2));
                menu.getItem(3).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_place));
                break;
            case 3:
                nMenu.getItem(3).setChecked(true);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_user));
                menu.getItem(1).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_order));
                menu.getItem(2).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_offers));
                menu.getItem(3).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_place2));
                break;
            default:
                menu.getItem(0).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_user));
                menu.getItem(1).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_order));
                menu.getItem(2).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_offers));
                menu.getItem(3).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_place));
        }
    }


    /**
     * Opens PreferenceActivity
     *To be called when settings icon in content_main is tapped
     */
    public void openSet(View view) {
        Intent openSettings = new Intent(this, PreferenceActivity.class);
        startActivity(openSettings);
    }
}











