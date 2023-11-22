package com.example.amirproj.user;

import static com.example.amirproj.dataTables.TablesString.ProductTable.*;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.amirproj.classes.Product;
import com.example.amirproj.classes.productAdapter;
import com.example.amirproj.dataTables.DBHelper;
import com.example.amirproj.R;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<Product> productList;
    RecyclerView recyclerView;
    productAdapter mAdapter;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        productList = new ArrayList<>();
        dbHelper = new DBHelper(inflater.getContext());
        dbHelper = dbHelper.OpenReadAble();
        Product p = new Product(),p2=new Product();
        Cursor c = p.Select(dbHelper.getDb());
        c.moveToFirst();
        while(!c.isAfterLast()){
            p2 = new Product(c.getInt(c.getColumnIndexOrThrow(_ID)),
                    c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_TYPE)),
                    c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_HORSEPOWER)),
                    c.getInt(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SECTO100)),
                    c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_MAXSPEED)),
                    c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE)),
                    c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE)),
                    c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_COLOR)));
            productList.add(p2);
            c.moveToNext();
        }
        // adapter
        mAdapter = new productAdapter(inflater.getContext(), productList);
        recyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return  view;
    }
}