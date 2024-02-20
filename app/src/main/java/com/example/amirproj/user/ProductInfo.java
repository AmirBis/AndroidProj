package com.example.amirproj.user;

import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_PRICE;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_COLOR;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_IMAGE;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_TYPE;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amirproj.dataTables.DBHelper;
import com.example.amirproj.classes.Cart;
import com.example.amirproj.classes.Product;
import com.example.amirproj.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProductInfo extends AppCompatActivity  {
    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, productname, productprice;
    Button addtoCart;
    int quantity;
    double basePrice = 0;
    DBHelper dbHelper;
    String selectedid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        productname = findViewById(R.id.ProductNameInfo);
        productprice =findViewById(R.id.ProductPrice);
        addtoCart = findViewById(R.id.addtocart);
        dbHelper = new DBHelper(this);
        selectedid = getIntent().getExtras().getString("id");
        setProduct();

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(this, SettingsFragment.class);
                // startActivity(intent);
                // once this button is clicked we want to save our values in the database and send those values
                // right away to summary activity where we display the order info

                SaveCart();
            }
        });

        /*
         * plus button for quaninty of product that buy him
         */
        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // car price

                    quantity++;
                    displayQuantity();
                    double prodPrice = basePrice * quantity;
                    productprice.setText("₪ " + prodPrice);


            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quantity == 0) {
                    Toast.makeText(getBaseContext(), "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    double prodPrice = basePrice * quantity;
                    productprice.setText("₪ " + prodPrice);
                }
            }
        });

    }
    private void setProduct() {

        dbHelper.OpenReadAble();
        Product p=new Product();
        Cursor c = p.SelectById(dbHelper.getDb(),selectedid);
        if(c!=null){
            c.moveToFirst();
            productname.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_TYPE)));
            basePrice=c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));
            byte[] image = c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 ,image.length);
            imageView.setImageBitmap(bm);
        }
        dbHelper.Close();

    }
    private void SaveCart() {
        FirebaseAuth fauth = FirebaseAuth.getInstance();
        FirebaseUser curruser = fauth.getCurrentUser();
        // getting the values from our views
        dbHelper.OpenWriteAble();
        Cart cart = new Cart(curruser.getUid(), Integer.parseInt(selectedid),quantity);
        cart.Add(dbHelper.getDb());
        dbHelper.Close();
        Toast.makeText(getBaseContext(), "Added To Cart", Toast.LENGTH_SHORT).show();


    }
    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }


}