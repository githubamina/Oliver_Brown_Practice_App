package com.example.moo.coffeeorder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moo.coffeeorder.objects.Drink;

import java.util.LinkedList;

/**
 * Created by moo on 13/2/18.
 */

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {



    //Linked list of drinks
    private LinkedList<Drink> mDrinkList;

    private LayoutInflater mInflater;

    private Context mContext;

    CustomItemClickListener listener;



    //DrinkViewHolder to store the views that display the Drink information
    class DrinkViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView drinkNameTextView;
        public final ImageView drinkImageView;
        final DrinkAdapter mAdapter;


        public DrinkViewHolder(View itemView, DrinkAdapter adapter){
            super(itemView);

            //Initialize views to drink_item.xml views
            drinkImageView = (ImageView) itemView.findViewById(R.id.menu_image);
            drinkNameTextView = (TextView) itemView.findViewById(R.id.drinkName);
            this.mAdapter = adapter;

        }

    }



    //DrinkAdapter constructor
    public DrinkAdapter(Context context, LinkedList<Drink> drinkList, CustomItemClickListener listener){
        mInflater = LayoutInflater.from(context);
        this.mDrinkList = drinkList;
        this.mContext = context;
        this.listener = listener;
    }


    /**
     *Inflates view holder layout and sets on click listener for each item
     */
    @Override
    public DrinkAdapter.DrinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate drink_item layout
        View mItemView = mInflater.inflate(R.layout.drink_item, parent, false);


        //Initialize DrinkViewHolder to mItemView
        final DrinkViewHolder mDrinkViewHolder = new DrinkViewHolder(mItemView, this);

        //Set on click listener so that each item click can be handled appropriately
        mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mDrinkViewHolder.getAdapterPosition());
            }
        });

        //return DrinkViewHolder object
        return mDrinkViewHolder;
    }


    /**
     *Sets current drink item + data to current view holder
     */
    @Override
    public void onBindViewHolder(DrinkViewHolder holder, int position) {
        //Get current drink
        Drink mCurrent = mDrinkList.get(position);
        //Set drink name text
        holder.drinkNameTextView.setText(mCurrent.getName());
        //Use glide to load image
        Glide.with(mContext).load(mCurrent.getImageResourceId()).into(holder.drinkImageView);

    }


    /**
     * Returns the number of items in the drink list
     */
    @Override
    public int getItemCount() {
        return mDrinkList.size();
    }




}
