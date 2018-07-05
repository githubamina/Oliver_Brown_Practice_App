package com.example.moo.coffeeorder.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moo.coffeeorder.R;
import com.example.moo.coffeeorder.fragments.CreateOrderFragment;

/**
 * Fragment that displays store info, provides link to create new order, displays past/favourite user orders
 */
public class OrderFragment extends Fragment  {

    public OrderFragment(){

    }


    /**
     * Opens the CreateOrderFragment where users select drinks to order
     */
    public void openCreateOrderFrag() {
        //Create instance of CreateOrderFragment
        CreateOrderFragment createOrderFragment = new CreateOrderFragment();
        //Create FragmentManager instance
        FragmentManager manager = getFragmentManager();
        //Replace current fragment in location_fragment with createOrderFragment
        manager.beginTransaction()
                .replace(R.id.location_fragment, createOrderFragment)
                .addToBackStack(null)
                .commit();

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the layout now so store_info TextView and create_new button can be referenced
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);


        //Get TextView to display the selected (from spinner) store's details
        final TextView store_info = (TextView) rootView.findViewById(R.id.store_info);


        // Create an ArrayAdapter, passing in the store_array string array from strings.xml, and the default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.stores_array, android.R.layout.simple_spinner_item);


        //Create spinner object and initialize to store_spinner in fragment_order.xml
        Spinner spinner = (Spinner) rootView.findViewById(R.id.store_spinner);

        // Specify the default layout to use when the list of spinner choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the array adapter to the spinner
        spinner.setAdapter(adapter);

        //Set on item selected listener on spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                //Use switch statement, passing in position of selected item to determine
                //which store's details to display in store_info TextView
                switch (position){
                    case 0:
                        store_info.setText("Mon - Fri\n7:00am - 12:00am\n Sat - Sun\n8:00am - 12:00am\n\n(02) 9643 1480");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 1:
                        store_info.setText("Mon - Sun\n8:00am - 11:00pm\n\n(02) 8739 0417");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 2:
                        store_info.setText("Mon - Thu\n8:00am - 11:00pm\nFri - Sun\n8:00am - 12:00am\n\n(02) 9586 1116");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 3:
                        store_info.setText("Mon - Wed & Fri\n8:00am - 5:30pm\nThursday\n8:00am - 9:00pm\nSat - Sun 9:00am - 5:00pm\n\n(02) 9676 3002");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 4:
                        store_info.setText("(02) 9567 4842");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 5:
                        store_info.setText("Mon - Fri\n7:00am - 11:00pm\nSat - Sun\n8:00am - 11:00pm\n\n(02) 9747 3353");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 6:
                        store_info.setText("Mon - Wed\n8:30am - 10:00pm\nThu - Sat\n8:30am - 10:30pm\nSunday\n9:00am - 10:30pm\n\n(02) 9744 0967");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 7:
                        store_info.setText("Mon - Thu\n9:00am - 11:00pm\nFri - Sun\n9:00am - 11:30pm\n\n(02)9727 3636");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 8:
                        store_info.setText("Sun - Thu\n8:00am - 10:00pm\nFri - Sat\n8:00am - 11:00pm\n\n(02) 9871 3772");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 9:
                        store_info.setText("Mon- Sat\n6:30am-11:00pm\nSun\n7:30am-11:00pm\n\n(02) 8798 7768");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 10:
                        store_info.setText("Tue - Thu\n7:30am - 10:00pm\nSun - Mon\n7:30am - 10:00pm\n\n(02) 8283 7675");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 11:
                        store_info.setText("Sun - Thu\n8:00am - 10:00pm\nFri - Sat\n8:00am - 10:30pm\n\n(02) 9481 4638");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 12:
                        store_info.setText("Mon - Thu & Sun\n7:00am - 10:30pm\nFri - Sat\n7:00am - 11pm\n\n02 9439 9899");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 13:
                        store_info.setText("Mon - Sun\n8:00am - 6:00pm\n\n(02) 8040 9212");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 14:
                        store_info.setText("Mon - Sun\n8:00am - 12:00am\n\n(02) 9858 1170");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 15:
                        store_info.setText("Monday - Thursday\n6:00am - 10:00pm\nFriday\n6:00am - 11:00pm\nSaturday\n7:00am - 11:00pm\nFriday\n7:00am - 10:00pm");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 16:
                        store_info.setText("Mon - Fri\n7.00am - 10.00pm\nSat\n8.00am - 10.00pm\nSun\n9.00am - 10.00pm\n\n02 9632 2729");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 17:
                        store_info.setText("Monday\n7:00am - 11:00pm\nTue - Fri\n7:00am - 12:00am\nSaturday\n8:00am - 12:00am\nSunday\n8:00am - 11:00pm\n\n(02) 9482 9819");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 18:
                        store_info.setText("Sun - Thur\n9:00am- 10:00pm\nFri - Sat\n9:00am-11:00pm\n\n(02) 9579 1160");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 19:
                        store_info.setText("Sun - Sat\n8:00am - 10:00pm\n\n(02) 9090 2596");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 20:
                        store_info.setText("Mon - Wed\n8:30am -7:00pm\nThu - Fri\n8:30am -10:00pm\nSat\n9:00am -10:00pm\nSun\n9:00am - 8:00pm\n\n(02) 9737 9219");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 21:
                        store_info.setText("Mon-Fri\n8:00am-10:30pm\nSat-Sun\n9:00am-10:30pm\n\n(02) 8739 8122");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 22:
                        store_info.setText("Mon - Sat\n7:30am - 11:00pm\nSunday\n8:00am - 11:00pm\n\n(02) 8712 9299");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 23:
                        store_info.setText("Mon - Wed + Fri - Sat\n8:00AM - 5:30PM\nThu\n8:00am - 9:00pm\n\n0434 000 264");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 24:
                        store_info.setText("Mon - Sun\n7:00am - 10:00pm\n\n(02) 8319 4029");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 25:
                        store_info.setText("Mon - Wed\n8:00am - 10:30pm\nThu - Sun\n8:00am - 11:00pm\n\n(02) 9637 9729");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 26:
                        store_info.setText("Mon - Thurs\n8:00am - 10:00pm\nFri - Sat\n8:00am - 10:30pm\nSun\n8:00am - 9:30pm\n\n(02) 9531 8014");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 27:
                        store_info.setText("Mon - Sat\n8:00am - 11:00pm\nSunday\n9:00am - 11:00pm\n\n(02) 9832 3005");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 28:
                        store_info.setText("Mon - Thu\n08:00 - 23:00\nFri - Sun\n08:00 - 00:00\n\n(02) 8810 6555");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 29:
                        store_info.setText("Mon - Sun\n8:00am - Till late\n\n(02) 4701 5816");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 30:
                        store_info.setText("Mon -Fri\n7:00am - 11:00pm\nSat - Sun\n7:30am - 11:30pm\n\n(02) 8757 3655");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 31:
                        store_info.setText("Mon - Wed\n8:00am - 10:00pm\nThu - Fri\n8:00am - 11:00pm\nSat - Sun\n8:30am - 10:00pm\n\n(02) 8814 7917");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 32:
                        store_info.setText("Mon - Sat\n7:00am - 12:00am\nSunday\n8:00am - 12:00am\n\n(02) 8283 7765");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 33:
                        store_info.setText("Mon - Sun\n6:00am - 8:00pm\n\n(02) 9669 1188");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 34:
                        store_info.setText("Mon - Sun\n8:00am - 10:00pm\n\n(02) 9629 9833");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 35:
                        store_info.setText("Mon - Sun\n07:00 - 21:00\n\n(02) 7900 5711");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 36:
                        store_info.setText("Mon - Sun\n9:00am - 11:00pm\n\n(02) 9809 7007");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 37:
                        store_info.setText("Mon - Sun\n8:00am - 11:00pm\n\n(02) 8057 0558");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 38:
                        store_info.setText("Mon - Fri\n8:00am - 4:00pm");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 39:
                        store_info.setText("Mon - Sun\n8:00am - 10:30pm\n\n(02) 9609 1468");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 40:
                        store_info.setText("Mon - Thu:\n7:30am - 8:00pm\nFri:\n7:30am- 7:00pm\nSat:\n9:00am -3:00pm\nSun:\nClosed (Only open during exams)");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 41:
                        store_info.setText("Mon - Fri\n7:30am - 11:30pm\nSat - Sun\n8:30am - 11.30pm\n\n(02) 9261 2991");
                        store_info.setTextColor(Color.parseColor("#ffffff"));
                        break;

                    case 42:
                        store_info.setText("Mon - Fri\n8:00am - 10:00pm\nSat - Sun\n8:00am - 10:00pm\n\n(02) 9662 1494");
                        store_info.setTextColor(Color.parseColor("#ffffff"));

                    default:
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //Inflate new Order button
        final Button newOrderbutton = (Button) rootView.findViewById(R.id.create_new);

        //Set onclicklistener on new order button
        newOrderbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //onClick open CreateOrderFragment
                openCreateOrderFrag();
            }
        });


        return rootView;
    }



}
