package com.example.amirproj.classes;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.amirproj.dataTables.DBHelper;
import com.example.amirproj.dataTables.TablesString;
import com.example.amirproj.R;

import com.example.amirproj.user.info;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static com.example.amirproj.dataTables.TablesString.ProductTable.*;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<Cart> cartList;
    int pid;
    String uid;
    View view,v;
    Context context;
    int i=1 ;
    double sum ;
    ViewHolder h;

    public CartAdapter(View v,Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
        this.v = v;
        sum=0;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        view = LayoutInflater.from(context).inflate(R.layout.each_cart_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        h=holder;
        // here we will find the position and start setting the output on our views
        int pid = cartList.get(position).getPid();
        int amount = cartList.get(position).getAmount();
        Product p = new Product();
        DBHelper dbHelper = new DBHelper(view.getContext());
        dbHelper.OpenReadAble();
        Cursor c = p.SelectById(dbHelper.getDb(), pid+"");
        c.moveToFirst();
        if(c!=null){
            String typeOfProduct = c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_TYPE));
            int hop = c.getInt(c.getColumnIndexOrThrow(COLUMN_PRODUCT_HORSEPOWER));
            double price = c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));
            byte[] images = c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
            Bitmap bm = BitmapFactory.decodeByteArray(images, 0 ,images.length);
            sum+=price*amount;

            holder.totalprice.setText(sum + "");
            holder.tvTypeOfProduct.setText(typeOfProduct);
            holder.tvcar.setText(hop+"");
            holder.imageOfProduct.setImageBitmap(bm);
            holder.tvAmount.setText(amount+"");
            holder.carPrice.setText(String.valueOf(price));
        }
        dbHelper.Close();
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder{

        // here we will find the views on which we will inflate our data

        TextView tvTypeOfProduct, tvcar,tvAmount,totalprice,carPrice;
        ImageView imageOfProduct,deleteitem;
        ImageButton plusquantity,minusquantity;

        public ViewHolder(View itemView) {
            super(itemView);
            carPrice = itemView.findViewById(R.id.carprice);
            tvTypeOfProduct = itemView.findViewById(R.id.eachCartItemName);
            tvcar = itemView.findViewById(R.id.eachCartItemYOPTv);
            imageOfProduct = itemView.findViewById(R.id.eachCartItemIV);
            tvAmount = itemView.findViewById(R.id.eachCartItemQuantityTV);
            totalprice = v.findViewById(R.id.cartFragmentTotalPriceTv);
            deleteitem = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            deleteitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbHelper = new DBHelper(context);
                    dbHelper.OpenWriteAble();
                    totalprice.setText("0");
                    cartList.get(getPosition()).Delete(dbHelper.getDb(),cartList.get(getPosition()).getCartid());
                    cartList.remove(getPosition());
                    notifyDataSetChanged();
                    dbHelper.Close();

                }
            });
            minusquantity = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
            minusquantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x =Integer.parseInt(tvAmount.getText().toString());
                    if(x>1){
                        tvAmount.setText(String.valueOf(x-1));
                        sum-=Double.parseDouble(carPrice.getText().toString());
                        totalprice.setText(sum + "");
                    }
                }
            });
            plusquantity = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            plusquantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                         int x =Integer.parseInt(tvAmount.getText().toString());
                        tvAmount.setText(String.valueOf(x+1));
                        sum+=Double.parseDouble(carPrice.getText().toString());
                        totalprice.setText(sum+"");

                }
            });

        }

    }
}

