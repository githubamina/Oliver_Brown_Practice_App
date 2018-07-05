package com.example.moo.coffeeorder.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moo.coffeeorder.LocationAdapter;
import com.example.moo.coffeeorder.MainActivity;
import com.example.moo.coffeeorder.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Fragment that attempts to display map with closest store location to user. Also contains a list of stores and will display
 * a  map image and store details of the selected store.
 */
public class LocationsFragment extends Fragment implements OnMapReadyCallback{


    //Create googlemap object
     GoogleMap map;

    //Create list of address objects to use to get addresses of store locations
    List<Address> addresses = null;

    //Create Location Services Client
    FusedLocationProviderClient mFusedLocationClient;

    //Create GeoDataClient instance to provide access to Google's database of Places
    protected GeoDataClient mGeoDataClient;


    //Create instance of PlaceBufferResponse to store the Places information retrieved from getPlaceById
    PlaceBufferResponse places;


    //Create an ArrayList of Place objects to store the Places retrieved from getPlaceById
    ArrayList<Place> oblocations = new ArrayList<Place>();;


    //Create latlng instance to store user's current location
    LatLng userLocation;


    //Programatically create a mapFragment object to avoid null pointer exceptions when referencing mapFragment
    SupportMapFragment mapFragment;

    //TextView to store the suburb of the closest store location
    TextView suburbText;

    //TextView to store the address of the closest store location
    TextView addressText;

    //TextView to store the phone number of the closest store location
    TextView phonenumText;

    //TextView to store the distance in km to the nearest store
    TextView distanceText;



    //TextView to be displayed when GPS/Locations services are unavailable
    TextView locationUnavailable;


    //RelativeLayout to contain map
    RelativeLayout mapContainer;


    //Imageview to store location map
    ImageView locationImage;





    //ArrayList to store Locations and their extras so that they can be sorted using a
    // comparator in order to find the closest store to current user location
   ArrayList<Location> storeLocationsArrayList = new ArrayList<>();


    public LocationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Inflate fragment_locations layout
        View rootView = inflater.inflate(R.layout.fragment_locations, container, false);


        //Initialize view objects to respective views in fragment_locations
        suburbText = (TextView) rootView.findViewById(R.id.suburbText);

        addressText = (TextView) rootView.findViewById(R.id.addressText);

        phonenumText = (TextView) rootView.findViewById(R.id.phonenumText);

        distanceText = (TextView) rootView.findViewById(R.id.distanceText);

        locationUnavailable = (TextView) rootView.findViewById(R.id.locations_unavailable);

        mapContainer = (RelativeLayout) rootView.findViewById(R.id.map_container);

        locationImage = (ImageView) rootView.findViewById(R.id.location_image);



        //Initialize fused location provider client
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(getActivity(), null);




        // Create a list of locations
        ArrayList<String> locations = new ArrayList<String>();
        locations.add("Auburn");
        locations.add("Bankstown");
        locations.add("Beverly Hills");
        locations.add("Blacktown");
        locations.add("Brighton Le Sands");
        locations.add("Burwood Railway");
        locations.add("Burwood Westfield");
        locations.add("Canley Heights");
        locations.add("Carlingford");
        locations.add("Casula");
        locations.add("Chatswood");
        locations.add("Cherrybrook");
        locations.add("Crows Nest");
        locations.add("Eastwood");
        locations.add("Granville");
        locations.add("Guildford");
        locations.add("Haymarket");
        locations.add("Homebush DFO");
        locations.add("Hornsby");
        locations.add("Hurstville");
        locations.add("Lane Cove");
        locations.add("Lidcombe");
        locations.add("Little Saigon Bankstown");
        locations.add("Liverpool");
        locations.add("Macarthur Square");
        locations.add("Mascot");
        locations.add("Merrylands");
        locations.add("Miranda");
        locations.add("Mt Druitt");
        locations.add("Parramatta");
        locations.add("Penrith Panthers Club");
        locations.add("Rhodes");
        locations.add("Rouse Hill");
        locations.add("Strathfield");
        locations.add("Sydney International Airport");
        locations.add("The Ponds");
        locations.add("Top Ryde City");
        locations.add("Top Ryde Terrace");
        locations.add("Wetherill Park");
        locations.add("World Square Sydney");
        locations.add("Western Sydney University Bankstown");
        locations.add("Western Sydney University Parramatta");
        locations.add("Zetland");



        // Create a LocationAdapter and pass in locations string array as data.
        LocationAdapter locationAdapter = new LocationAdapter(getContext(), locations);

        // Initialize ListView object to location_list ListView in fragment_locations
        ListView locationListView = (ListView) rootView.findViewById(R.id.location_list);

        //Set the location adapter on the ListView so that a list item for each location will be displayed
        locationListView.setAdapter(locationAdapter);


        //Set an OnItemClickLister on the locationListview to handle what happens for each item tap
        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Hide the mapContainer so that location map images can be displayed instead
                mapContainer.setVisibility(View.GONE);

                //Hide the distance text view as location services won't be used to calculate distance from stores to current location
                distanceText.setVisibility(View.GONE);

                //Hide location services unavailable textview as location services won't be used at this point
                locationUnavailable.setVisibility(View.GONE);

                //Show location image ImageView so that the map images can be displayed for each location
                locationImage.setVisibility(View.VISIBLE);

                //Switch statement using currently selected item's position to determine what text and image to display
                switch(position){
                    case 0:
                        Glide.with(getContext()).load(R.drawable.auburn).into(locationImage);
                        suburbText.setText("Auburn");
                        addressText.setText("Shop Q2/3\nAuburn Central Shopping Centre\n57-59 Queen Street");
                        phonenumText.setText("(02) 9643 1480");
                        break;

                    case 1:
                        Glide.with(getContext()).load(R.drawable.bankstown).into(locationImage);
                        suburbText.setText("Bankstown");
                        addressText.setText("Shop 231, Ground Floor\nBankstown Central Shopping Centre\nStacey Street, 2200");
                        phonenumText.setText("(02) 8739 0417");
                        break;

                    case 2:
                        Glide.with(getContext()).load(R.drawable.beverlyhills).into(locationImage);
                        suburbText.setText("Beverly Hills");
                        addressText.setText("S544 King Georges Road, 2209");
                        phonenumText.setText("(02) 9586 1116");
                        break;

                    case 3:
                        Glide.with(getContext()).load(R.drawable.blacktown).into(locationImage);
                        suburbText.setText("Blacktown");
                        addressText.setText("Level 2, Shop K3.06\nWestpoint Shopping Centre\n17 Patrick St, 2148");
                        phonenumText.setText("(02) 9676 3002");
                        break;

                    case 4:
                        Glide.with(getContext()).load(R.drawable.brighton).into(locationImage);
                        suburbText.setText("Brighton Le Sands");
                        addressText.setText("369 BAY ST 2216");
                        phonenumText.setText("(02) 9567 4842");
                        break;

                    case 5:
                        Glide.with(getContext()).load(R.drawable.burwoodrail).into(locationImage);
                        suburbText.setText("Burwood Railway");
                        addressText.setText("1 Railway Parade, 2134");
                        phonenumText.setText("(02) 9747 3353");
                        break;

                    case 6:
                        Glide.with(getContext()).load(R.drawable.burwoodw).into(locationImage);
                        suburbText.setText("Burwood Westfield");
                        addressText.setText("Shop CM01100\nBurwood Westfield Shopping Centre\nBurwood Rd, 2134");
                        phonenumText.setText("(02) 9744 0967");
                        break;

                    case 7:
                        Glide.with(getContext()).load(R.drawable.canleyheights).into(locationImage);
                        suburbText.setText("Canley Heights");
                        addressText.setText("Shop 1\n235 Canley Vale Rd, 2166");
                        phonenumText.setText("(02)9727 3636");
                        break;

                    case 8:
                        Glide.with(getContext()).load(R.drawable.carlingford).into(locationImage);
                        suburbText.setText("Carlingford");
                        addressText.setText("Shop SP112, Level 1\nCarlingford Court Shopping Centre\nCnr Pennant Hills Rd and Carlingford Road, 2118");
                        phonenumText.setText("02 9871 3772");
                        break;

                    case 9:
                        Glide.with(getContext()).load(R.drawable.casula).into(locationImage);
                        suburbText.setText("Casula");
                        addressText.setText("Shop 1\nCasula Mall Shopping Centre\n1 Ingham Dr, 2170");
                        phonenumText.setText("(02) 8798 7768");
                        break;

                    case 10:
                        Glide.with(getContext()).load(R.drawable.chatswood).into(locationImage);
                        suburbText.setText("Chatswood");
                        addressText.setText("Shop 212, Level 2\nChatswood Westfield Shopping Centre\n1 Anderson St, 2067");
                        phonenumText.setText("(02) 8283 7675");
                        break;

                    case 11:
                        Glide.with(getContext()).load(R.drawable.cherrybrook).into(locationImage);
                        suburbText.setText("Cherrybrook");
                        addressText.setText("Shop P2A\nCherrybrook Village Shopping Centre\n41-47 Shepherds Drive, 2126");
                        phonenumText.setText("(02) 9481 4638");
                        break;

                    case 12:
                        Glide.with(getContext()).load(R.drawable.crowsnest).into(locationImage);
                        suburbText.setText("Crows Nest");
                        addressText.setText("Shop 3\nCrows Nest Shopping Centre\n34-48 Alexander St, 2065");
                        phonenumText.setText("02 9439 9899");
                        break;

                    case 13:
                        Glide.with(getContext()).load(R.drawable.eastwood).into(locationImage);
                        suburbText.setText("Eastwood");
                        addressText.setText("45 Rowe St, 2122");
                        phonenumText.setText("(02) 9858 1170");
                        break;

                    case 14:
                        Glide.with(getContext()).load(R.drawable.granville).into(locationImage);
                        suburbText.setText("Granville");
                        addressText.setText("Ground Floor, 29 South Street");
                        phonenumText.setText("n/a");
                        break;

                    case 15:
                        Glide.with(getContext()).load(R.drawable.guildford).into(locationImage);
                        suburbText.setText("Guildford");
                        addressText.setText("271 Guildford Road");
                        phonenumText.setText("02 9632 2729");
                        break;

                    case 16:
                        Glide.with(getContext()).load(R.drawable.haymarket).into(locationImage);
                        suburbText.setText("Haymarket");
                        addressText.setText("Shop 6\nThe Quay Shopping Centre\n61-79 Quay Street, 2000");
                        phonenumText.setText("(02) 7900 5711");
                        break;

                    case 17:
                        Glide.with(getContext()).load(R.drawable.homebushdfo).into(locationImage);
                        suburbText.setText("Homebush DFO");
                        addressText.setText("Shop 3-046\nDirect Factory Outlet Homebush\n3-5 Underwood Road, 2140");
                        phonenumText.setText("(02) 8040 9212");
                        break;

                    case 18:
                        Glide.with(getContext()).load(R.drawable.hornsby).into(locationImage);
                        suburbText.setText("Hornsby");
                        addressText.setText("Shop 2034\nHornsby Westfield Shopping Centre\n236 Pacific Highway, 2077");
                        phonenumText.setText("(02) 9482 9819");
                        break;

                    case 19:
                        Glide.with(getContext()).load(R.drawable.hurstville).into(locationImage);
                        suburbText.setText("Hurstville");
                        addressText.setText("Roof Top Pavillion C\nHurstville Westfield Shopping Centre\nPark Rd, 2220");
                        phonenumText.setText("(02) 9579 1160");
                        break;

                    case 20:
                        Glide.with(getContext()).load(R.drawable.lanecove).into(locationImage);
                        suburbText.setText("Lane Cove");
                        addressText.setText("Shop 15 and 16\nLane Cove Market Square\n24-28 Burns Bay Road, 2066");
                        phonenumText.setText("(02) 9090 2596");
                        break;

                    case 21:
                        Glide.with(getContext()).load(R.drawable.lidcombe).into(locationImage);
                        suburbText.setText("Lidcombe");
                        addressText.setText("Shop 29 & 30\nLidcombe Shopping Centre\n92 Parramatta Rd, 2141");
                        phonenumText.setText("(02) 9737 9219");
                        break;

                    case 22:
                        Glide.with(getContext()).load(R.drawable.littlesaigonbankstown).into(locationImage);
                        suburbText.setText("Little Saigon Bankstown");
                        addressText.setText("Ground Floor\nLittle Saigon Plaza\n462 Chapel Road, 2200");
                        phonenumText.setText("(02) 8739 8122");
                        break;

                    case 23:
                        Glide.with(getContext()).load(R.drawable.liverpool).into(locationImage);
                        suburbText.setText("Liverpool");
                        addressText.setText("Shop 2016\nLiverpool Westfield Shopping Centre\nMacquarie Street, 2170");
                        phonenumText.setText("(02) 8712 9299");
                        break;

                    case 24:
                        Glide.with(getContext()).load(R.drawable.macarthursquare).into(locationImage);
                        suburbText.setText("Macarthur Square Campbelltown");
                        addressText.setText("Lev.3, Tenancy U156\nMacarthur Square Shopping Centre 200 Gilchrist Dr");
                        phonenumText.setText("0434 000 264");
                        break;

                    case 25:
                        Glide.with(getContext()).load(R.drawable.mascot).into(locationImage);
                        suburbText.setText("Mascot");
                        addressText.setText("Shop 1\nMascot Central\n19-23 Kent Road, 2020");
                        phonenumText.setText("(02) 8319 4029");
                        break;

                    case 26:
                        Glide.with(getContext()).load(R.drawable.merrylands).into(locationImage);
                        suburbText.setText("Merrylands");
                        addressText.setText("Shop 1065B\nStockland Merrylands Shopping Centre\nMcFarlane Street, 2160");
                        phonenumText.setText("(02) 9637 9729");
                        break;

                    case 27:
                        Glide.with(getContext()).load(R.drawable.miranda).into(locationImage);
                        suburbText.setText("Miranda");
                        addressText.setText("Shop 3200\nMiranda Westfield Shopping Centre\n600 Kingsway, 2228");
                        phonenumText.setText("(02) 9531 8014");
                        break;

                    case 28:
                        Glide.with(getContext()).load(R.drawable.mtdruitt).into(locationImage);
                        suburbText.setText("Mt Druitt");
                        addressText.setText("Shop 180\nMt Druitt Westfield Shopping Centre\nCnr Carlisle Ave & Luxford Road, 2770");
                        phonenumText.setText("(02) 9832 3005");
                        break;

                    case 29:
                        Glide.with(getContext()).load(R.drawable.parramatta).into(locationImage);
                        suburbText.setText("Parramatta");
                        addressText.setText("Shop 2\nEastbrook Centre\n286-290 Church Street, 2150");
                        phonenumText.setText("(02) 8810 6555");
                        break;

                    case 30:
                        Glide.with(getContext()).load(R.drawable.penrithpanthers).into(locationImage);
                        suburbText.setText("Penrith Panthers Club");
                        addressText.setText("Panthers World of Entertainment (Leagues Club) 123 Mulgoa Rd");
                        phonenumText.setText("(02) 4701 5816");
                        break;

                    case 31:
                        Glide.with(getContext()).load(R.drawable.rhodes).into(locationImage);
                        suburbText.setText("Rhodes");
                        addressText.setText("Shop 51, Ground Floor\nRhodes Waterside Shopping Centre\n1 Rider Blvd, 2138");
                        phonenumText.setText("(02) 8757 3655");
                        break;

                    case 32:
                        Glide.with(getContext()).load(R.drawable.rousehill).into(locationImage);
                        suburbText.setText("Rouse Hill");
                        addressText.setText("Shop GR074\nRouse Hill Town Centre\nCnr Windsor Road & White Hart Drive, 2155");
                        phonenumText.setText("(02) 8814 7917");
                        break;

                    case 33:
                        Glide.with(getContext()).load(R.drawable.strathfield).into(locationImage);
                        suburbText.setText("Strathfield");
                        addressText.setText("Shop 32\nStrathfield Plaza\n11 The Boulevard, 2135");
                        phonenumText.setText("(02) 8283 7765");
                        break;

                    case 34:
                        Glide.with(getContext()).load(R.drawable.sydneyintlairport).into(locationImage);
                        suburbText.setText("Sydney International Airport");
                        addressText.setText("Sites 2-1680 & 2-1680L\nT1 International Terminal,\nSydney (Kingsford Smith) Airport 2020");
                        phonenumText.setText("(02) 9669 1188");
                        break;

                    case 35:
                        Glide.with(getContext()).load(R.drawable.theponds).into(locationImage);
                        suburbText.setText("The Ponds");
                        addressText.setText("Shop 8\nThe Ponds Shopping Centre\nCnr of the Ponds Boulevard & Riverbank Dr, 2769");
                        phonenumText.setText("(02) 9629 9833");
                        break;

                    case 36:
                        Glide.with(getContext()).load(R.drawable.topryde).into(locationImage);
                        suburbText.setText("Top Ryde City");
                        addressText.setText("Shop C3000 Ground Floor\nTop Ryde City Shopping Centre\n109 Blaxland Rd, 2112");
                        phonenumText.setText("(02) 9809 7007");
                        break;

                    case 37:
                        Glide.with(getContext()).load(R.drawable.toprydeterrace).into(locationImage);
                        suburbText.setText("Top Ryde Terrace");
                        addressText.setText("Level 1\nTop Ryde City Shopping Centre\n109-129 Blaxland Road, 2112");
                        phonenumText.setText("(02) 8057 0558");
                        break;

                    case 38:
                        Glide.with(getContext()).load(R.drawable.whetherillpark).into(locationImage);
                        suburbText.setText("Wetherill Park");
                        addressText.setText("Shop 229\nStockland Wetherill Park Shopping Centre\n581-583 Polding St, 2164");
                        phonenumText.setText("(02) 9609 1468");
                        break;

                    case 39:
                        Glide.with(getContext()).load(R.drawable.wsubankstown).into(locationImage);
                        suburbText.setText("Western Sydney University Bankstown");
                        addressText.setText("1.G.03 Main Building 1\nWestern Sydney University\nBullecourt Avenue, 2214\nMilperra");
                        phonenumText.setText("n/a");
                        break;

                    case 40:
                        Glide.with(getContext()).load(R.drawable.wsuparra).into(locationImage);
                        suburbText.setText("Western Sydney University Parramatta");
                        addressText.setText("16 of Building EG\nWestern Sydney University\nParramatta South Campus, 2150\nRydalmere");
                        phonenumText.setText("n/a");
                        break;

                    case 41:
                        Glide.with(getContext()).load(R.drawable.worldsquare).into(locationImage);
                        suburbText.setText("World Square Sydney");
                        addressText.setText("Shop 10\nWorld Square Shopping Centre\n680 George Street, 2000");
                        phonenumText.setText("(02) 9261 2991");
                        break;

                    case 42:
                        Glide.with(getContext()).load(R.drawable.zetland).into(locationImage);
                        suburbText.setText("Zetland");
                        addressText.setText("T.08\nEast Village Shopping Centre\n2-4 Defries Ave, 2017");
                        phonenumText.setText("(02) 9662 1494");
                        break;

                    default:
                        suburbText.setText("none");

                }
            }
        });









        // Inflate the layout for this fragment
        return rootView;



    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create new instance of LocationManager to use to get current location
        LocationManager locationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);

        //Booleans to store whether or not GPS and Network settings are enabled on user's phone
        boolean gps_enabled = false;
        boolean network_enabled = false;


        //Try-catch blocks to store whether or not GPS and Network settings are enabled in respective bboolean variables
        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}



        //What happens if neither GPS or Network settings are enabled
        if(!gps_enabled && !network_enabled) {

            //New FragmentManager instance
            FragmentManager fragmentManager = getChildFragmentManager();

            //Check is mapFragment is null
            //and remove it to prevent mapAsyncTask from being called
            if(mapFragment == null) {
                mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
                fragmentManager.beginTransaction()
                        .remove(mapFragment)
                        .commit();
            }
            //Display location services unavailable text view
            locationUnavailable.setVisibility(View.VISIBLE);

            // Create an Alert Dialog to alert user that location services are disabled
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("Location settings are currently disabled. Please enable location settings and restart app");

            dialog.show();

        }

        //What happens if GPS or Network settings are enabled
        else {

            //Check permission
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                //New instance of FragmentManager
                FragmentManager fragmentManager = getChildFragmentManager();

                //Check if mapFragment is null
                //and remove it to prevent mapAsyncTask from being called
                if(mapFragment == null) {
                    mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
                    fragmentManager.beginTransaction()
                            .remove(mapFragment)
                            .commit();
                }
                //Display location services unavailable text view
                locationUnavailable.setVisibility(View.VISIBLE);

                // Create an Alert Dialog to alert user that location service permissions are disabled
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage("Location permissions are currently disabled. Please enable location permissions and restart app");

                dialog.show();


            } else{
                //Tag for map fragment to be used to determine if map already exists
                String MAP_FRAGMENT = "map_fragment";

                //New instance of FragmentManager
                FragmentManager fragmentManager = getChildFragmentManager();

                // Check if map already exists
                mapFragment = (SupportMapFragment) fragmentManager
                        .findFragmentByTag(MAP_FRAGMENT);

                // Create new Map instance if it doesn't exist
                if(mapFragment == null){
                    mapFragment = SupportMapFragment.newInstance();
                    fragmentManager.beginTransaction()
                            .replace(R.id.map, mapFragment, MAP_FRAGMENT)
                            .commit();
                }
                //Call onMapReady
                mapFragment.getMapAsync(this);

            }

        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Initialize map
        map = googleMap;

        Log.d("Map initialized", "yay");


        //Try-Catch block for getting current user location
        try{
            //Attempt to get user location
            mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>(){
                //If location successfully retrieved:
                @Override
                public void onSuccess(final Location location){

                    Log.d("onSuccess", "yes");

                    //Initialize userLocation to current Location
                    userLocation = new LatLng(location.getLatitude(), location.getLongitude());


                    Log.d("userLocation got", "mhm");




                    //Use getPlaceById to get a PlaceBufferResponse with store place objects
                    mGeoDataClient.getPlaceById("ChIJmwPYyTyuEmsR3uWBiHOZ0Bg", "ChIJWb4BKaGfEmsR1eqO8y2VJRU", "ChIJPxFKAxyfEmsRxO8OE9YSPbM",
                            "ChIJBUEIXdSgEmsR1Cca3tZN-XM", "ChIJX6QUdYSnEmsRhOomCvdBK-0", "ChIJj39hGgakEmsRBkaXR7RZj78", "ChIJWcMD1zCkEmsRY7-wfk6Zoc0",
                            "ChIJx-bt8x6jEmsRchUM_fecJ88", "ChIJq6qqqra8EmsREUl9ocJqXrA", "ChIJGea5m-akEmsRoJ7_43eQ7c8", "ChIJQVrqUaWlEmsRgZ84VYf3NtU",
                            "ChIJ76DOtrqlEmsRdoqJ7KkvPDQ", "ChIJh-Sby9WoEmsRA1xSlkUzQ78", "ChIJuyipg-mxEmsRV2r6D-d1kfI"
                            ).addOnCompleteListener(getActivity(), new OnCompleteListener<PlaceBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                            //If Places successfully added to BufferResponse
                            if (task.isSuccessful()) {

                                Log.d("Places added to br", "yes");

                                //Get the places from the BufferResponse
                                places = task.getResult();

                                Log.d("places in place var", "yes");

                                //Iterate through the results (the Places) and add each place to ArrayList oblocations
                                for(int i = 0; i < places.getCount(); i++){
                                    oblocations.add(places.get(i));

                                }


                                Log.d("Places arraylist", "yep");

                                //new Handler object
                                Handler mainHandler = new Handler(Looper.getMainLooper());

                                //Runnable to update UI
                                Runnable myRunnable = new Runnable() {
                                    @Override
                                    public void run() {



                                        //String to use as key for location extra: distance from location
                                        final String EXTRA_DISTANCE = "com.example.moo.locations.distance";


                                        //String to use as key for phone number extra
                                        final String EXTRA_PHONENUM = "com.example.moo.locations.phonenum";


                                        //Comparator to sort locations based on distance from current location
                                        class DistanceComparator implements Comparator<Location> {

                                            @Override
                                            public int compare(Location loc1, Location loc2) {
                                                float dist = loc1.getExtras().getFloat(EXTRA_DISTANCE) - loc2.getExtras().getFloat(EXTRA_DISTANCE);
                                                if (dist > 0.00001) return 1;
                                                if (dist < -0.00001) return -1;
                                                return 0;
                                            }
                                        }


                                        //For loop that iterates through each place in oblocations
                                        //and creates a location object using the places' latitude and longitutde values
                                        //also sets extras bundle to each location
                                        // containing distance from each location to the current location (float)
                                        //and phone number for each location (string)
                                        for (int i = 1; i < oblocations.size(); i++) {

                                            //store latitude from current place in double var
                                            double lat = oblocations.get(i).getLatLng().latitude;
                                            //store longitude from current place in double var
                                            double lon = oblocations.get(i).getLatLng().longitude;

                                            //store current place phone number in string
                                            String ph = (oblocations.get(i).getPhoneNumber()).toString();

                                            //create new location for current place
                                            Location storeLocation = new Location("storeLocation");

                                            //Set latitude and longitude for current place's location
                                            storeLocation.setLatitude(lat);
                                            storeLocation.setLongitude(lon);


                                            //Bundle to store
                                            // the distance from current location to user's location
                                            // and the phone number for current location
                                            Bundle extras = new Bundle();

                                            //Store current location's distance in bundle
                                            extras.putFloat(EXTRA_DISTANCE, location.distanceTo(storeLocation));
                                            //Store current locations' phone number in bundle
                                            extras.putString(EXTRA_PHONENUM, ph);

                                            //Set the extras bundle to current location
                                            storeLocation.setExtras(extras);

                                            //Add location to the storeLocationsArrayList array so we can access them later
                                            storeLocationsArrayList.add(storeLocation);




                                        }

                                        //Release BufferedResponse
                                        places.release();


                                        //Call DistanceComparator on storeLocationsArrayList
                                        //in order to sort from closest to farthest location
                                        Collections.sort(storeLocationsArrayList, new DistanceComparator());


                                        // Add a marker on closest location and move the camera
                                        LatLng x = new LatLng(storeLocationsArrayList.get(0).getLatitude(), storeLocationsArrayList.get(0).getLongitude()) ;
                                        map.addMarker(new MarkerOptions().position(x).title("Nearest store location"));
                                        map.moveCamera(CameraUpdateFactory.newLatLng(x));
                                        map.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );



                                        //New Geocoder object to get closest location data
                                        Geocoder gcd = new Geocoder(getActivity(), Locale.getDefault());


                                        //Try-catch block to attempt to get closest location's address
                                        try {
                                            //gets the address from location
                                            addresses = gcd.getFromLocation(storeLocationsArrayList.get(0).getLatitude(), storeLocationsArrayList.get(0).getLongitude(), 1);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        //If address successfully retrieved:
                                        if (addresses.size() > 0) {

                                            //Set text for closest Location info TextViews using address and extras data
                                            suburbText.setText(addresses.get(0).getLocality());
                                            addressText.setText(addresses.get(0).getAddressLine(0));
                                            phonenumText.setText(storeLocationsArrayList.get(0).getExtras().getString(EXTRA_PHONENUM));
                                            distanceText.setText((String.format(Locale.ENGLISH, "%.2f", storeLocationsArrayList.get(0).getExtras().getFloat(EXTRA_DISTANCE) / 1000)) + " km");

                                        }


                                    }



                                };

                                mainHandler.post(myRunnable);


                            } else{
                                //If user's current location not retrieved successfully"
                                //Hide the map
                                FragmentManager fragmentManager = getChildFragmentManager();
                                if(mapFragment == null) {
                                    mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
                                    fragmentManager.beginTransaction()
                                            .remove(mapFragment)
                                            .commit();
                                }
                                //Display location services unavailable text view
                                locationUnavailable.setVisibility(View.VISIBLE);
                            }
                        }

                    });
                }
            });

        } catch(SecurityException e){
            e.printStackTrace();
            //Hide the map
            FragmentManager fragmentManager = getChildFragmentManager();
            if(mapFragment == null) {
                mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
                fragmentManager.beginTransaction()
                        .remove(mapFragment)
                        .commit();
            }
            //Display location services unavailable text view
            locationUnavailable.setVisibility(View.VISIBLE);
        }


    }
}
