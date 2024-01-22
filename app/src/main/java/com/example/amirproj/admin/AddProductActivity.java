package com.example.amirproj.admin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.amirproj.classes.Product;
import com.example.amirproj.dataTables.DBHelper;
import com.example.amirproj.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_COLOR;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_HORSEPOWER;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_IMAGE;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_MAXSPEED;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_PRICE;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_SECTO100;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_TYPE;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText etname,etColor,horsepower,etPrice, secto100, maxspeed;
    ImageButton imageButton;
    Button addButton,btUpdate,btDelete;
    boolean SelectedNewImage;
    Product p;
    Uri selectedImageUri;
    String selectedId;
    DBHelper dbHelper;
    byte[] image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        etname = findViewById(R.id.etProdName);
        etColor = findViewById(R.id.etColor);
        maxspeed = findViewById(R.id.maxspeed);
        horsepower = findViewById(R.id.horsepower);
        secto100 = findViewById(R.id.secto100);
        etPrice = findViewById(R.id.etPrice);
        imageButton = findViewById(R.id.imageButton);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        btUpdate= findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(this);
        btDelete = findViewById(R.id.btDelete);
        btDelete.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        dbHelper = new DBHelper(this);
        dbHelper.OpenWriteAble();

        Intent i =getIntent();
        if(i.getStringExtra("Selected_Id")==null){
            btDelete.setVisibility(View.GONE);
            btUpdate.setVisibility(View.GONE);
        }
        else{
            addButton.setVisibility(View.GONE);
            selectedId = i.getStringExtra("Selected_Id");
            setProduct();
        }

    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.addButton){
            dbHelper = new DBHelper(this);

            byte[] data  = imageViewToByte();
            p=new Product(etname.getText().toString(),etColor.getText().toString(),
                    Integer.parseInt(horsepower.getText().toString()),
                    Double.parseDouble(etPrice.getText().toString()),
                    Integer.parseInt(secto100.getText().toString()),
                    Integer.parseInt(maxspeed.getText().toString()),
                    data);

            dbHelper.OpenWriteAble();
            if(p.Add(dbHelper.getDb())>-1){
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                dbHelper.Close();
                Intent i = new Intent(this,ShowProduct.class);
                startActivity(i);
            }
        }
        if(view.getId()==R.id.btUpdate){
            p.setPid(Integer.parseInt(String.valueOf(selectedId)));
            p.setProdtype(etname.getText().toString());
            p.setMaxspeed(Integer.parseInt(maxspeed.getText().toString()));
            p.setColor(etColor.getText().toString());
            p.setHorsepower(Integer.parseInt(horsepower.getText().toString()));
            p.setPrice(Double.parseDouble(etPrice.getText().toString()));
            if(SelectedNewImage)
                p.setImageByte(imageViewToByte());
            else
                p.setImageByte(image);
            dbHelper.OpenWriteAble();
            p.Update(dbHelper.getDb(),Integer.parseInt(selectedId));
            dbHelper.Close();
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ShowProduct.class);
            startActivity(i);
        }
        if(view.getId()==R.id.btDelete){
            dbHelper.OpenWriteAble();
            p.Delete(dbHelper.getDb(),Integer.parseInt(selectedId));
            dbHelper.Close();
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ShowProduct.class);
            startActivity(i);
        }
        if(view.getId()==R.id.imageButton){
            Intent gallery = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, RESULT_LOAD_IMAGE);
        }
    }


    public byte[] imageViewToByte() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver() ,selectedImageUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }
    private void SetIntentString() {
        Intent i = getIntent();
        if(i.getStringExtra("Selected_Id")==null){
            btDelete.setVisibility(View.GONE);
            btUpdate.setVisibility(View.GONE);
        }
        else {
            addButton.setVisibility(View.GONE);
            selectedId = i.getStringExtra("Selected_Id");
            setProduct();
        }
    }
    private void setProduct() {

        dbHelper.OpenReadAble();
        p=new Product();
        Cursor c = p.SelectById(dbHelper.getDb(),selectedId);
        if(c!=null){
            c.moveToFirst();
            etname.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_TYPE)));
            horsepower.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_HORSEPOWER)));
            secto100.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SECTO100)));
            maxspeed.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_MAXSPEED)));
            etColor.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_COLOR)));
            etPrice.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE)));
            image = c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 ,image.length);
            imageButton.setImageBitmap(bm);
        }
        dbHelper.Close();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1){
            selectedImageUri = data.getData();
            imageButton.setImageURI(selectedImageUri);
            SelectedNewImage = true;
        }
    }

}