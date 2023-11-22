package com.example.amirproj.classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.amirproj.R;

import com.example.amirproj.classes.Product;
import com.example.amirproj.user.productInfo;


import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder> {

    List<Product> productList;
    Context context;

    public productAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.eachview, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views

        String nameofProduct = productList.get(position).getProdtype();
        Double price = productList.get(position).getPrice();
        byte[] images = productList.get(position).getImageByte();
        Bitmap bm = BitmapFactory.decodeByteArray(images, 0 ,images.length);

        holder.tvNameOfProduct.setText(nameofProduct);
        holder.tvPriceOfProduct.setText(price+"");
        holder.imageOfProduct.setImageBitmap(bm);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView tvNameOfProduct, tvPriceOfProduct;
        ImageView imageOfProduct,addtocart;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNameOfProduct = itemView.findViewById(R.id.eachCarImage);
            tvPriceOfProduct = itemView.findViewById(R.id.eachCarPriceTv);
            imageOfProduct = itemView.findViewById(R.id.eachCarImage);
            addtocart = itemView.findViewById(R.id.eachCarAddToCartBtn);
            addtocart.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.eachCarAddToCartBtn){

            }
            else{


            Intent intent = new Intent(v.getContext(), productInfo.class);
            intent.putExtra("id",productList.get(getLayoutPosition()).getPid()+"");
            v.getContext().startActivity(intent);}
        }
    }
}