package com.example.moo.coffeeorder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moo.coffeeorder.objects.Product;

import java.util.ArrayList;

/**
 * Created by moo on 29/3/18.
 */

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ProductViewHolder>{

    private ArrayList<Product> mProductList;

    private LayoutInflater mInflater;

    private Context mContext;

    //ProductViewHolder to store the views that display the Drink information
    class ProductViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView productNameTextView;
        public final ImageView productImageView;
        public final TextView descriptionTextView;
        final FoodItemAdapter mAdapter;


        public ProductViewHolder(View itemView, FoodItemAdapter adapter){
            super(itemView);

            //Initialize views to product_item.xml views
            productImageView = (ImageView) itemView.findViewById(R.id.product_image);
            productNameTextView = (TextView) itemView.findViewById(R.id.product_name);
            descriptionTextView = (TextView) itemView.findViewById(R.id.product_description);
            this.mAdapter = adapter;

        }

    }



    //FoodItemAdapter constructor
    public FoodItemAdapter(Context context, ArrayList<Product> productList){
        mInflater = LayoutInflater.from(context);
        this.mProductList = productList;
        this.mContext = context;
    }


    /**
     *Inflates view holder layout and sets on click listener for each item
     */
    @Override
    public FoodItemAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate product_item layout
        View mItemView = mInflater.inflate(R.layout.product_item, parent, false);
        //Return ProductViewHolder object
        return new FoodItemAdapter.ProductViewHolder(mItemView,this);
    }

    /**
     * Sets current drink item + data to current view holder
     */
    @Override
    public void onBindViewHolder(FoodItemAdapter.ProductViewHolder holder, int position) {
        //Get current product
        Product mCurrent = mProductList.get(position);
        //Set product name
        holder.productNameTextView.setText(mCurrent.getName());
        //Set product description
        holder.descriptionTextView.setText(mCurrent.getDescription());
        //Use glide to load image
        Glide.with(mContext).load(mCurrent.getImageResourceId()).into(holder.productImageView);



    }

    /**
     * Returns the number of items in the product list
     */
    @Override
    public int getItemCount() {
        return mProductList.size();
    }



}
