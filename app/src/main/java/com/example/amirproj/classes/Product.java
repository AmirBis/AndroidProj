package com.example.amirproj.classes;

import static com.example.amirproj.dataTables.TablesString.ProductTable.*;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.BaseColumns;

public class Product implements SqlInterface{

    //region Attribute
    protected int pid;
    protected String prodname;
    protected String prodtype;
    protected int HORSEPOWER;
    protected int SECTO100;
    protected int MAXSPEED;
    protected byte[] imageByte;
    protected double saleprice;
    protected double price;

    //endregion

    //region Constructors
    public Product(String prodname,String prodtype,int HORSEPOWER,double price,double buyprice,byte[] image,int SECTO100,int MAXSPEED){
        this.saleprice=saleprice;
        this.price=price;
        this.prodname=prodname;
        this.prodtype=prodtype;
        this.HORSEPOWER=HORSEPOWER;
        this.SECTO100=SECTO100;
        this.MAXSPEED=MAXSPEED;
        this.imageByte = image;
    }
    //endregion

    //region Add,Delete,Update,Select Sql
    @Override
    public long Add(SQLiteDatabase db) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TABLE_PRODUCTNAME, prodname);
        values.put(COLUMN_PRODUCT_TYPE, prodtype);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(SECTO100, SECTO100);
        values.put(COLOR, COLOR);
        values.put(MAXSPEED, MAXSPEED);
        values.put(HORSEPOWER, HORSEPOWER);
        values.put(COLUMN_PRODUCT_IMAGE, imageByte);


// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_PRODUCTNAME, null, values);

    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {id+""};
// Issue SQL statement.
        return db.delete(TABLE_PRODUCTNAME, selection, selectionArgs);

    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(TABLE_PRODUCTNAME, prodname);
        values.put(COLUMN_PRODUCT_TYPE, prodtype);
        values.put(COLOR, COLOR);
        values.put(MAXSPEED, MAXSPEED);
        values.put(HORSEPOWER, HORSEPOWER);
        values.put(SECTO100,SECTO100);
        values.put(COLUMN_PRODUCT_IMAGE, imageByte.toString());

// Which row to update, based on the title
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { id+"" };

        return  db.update(
                TABLE_PRODUCTNAME,
                values,
                selection,
                selectionArgs);

    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                TABLE_PRODUCTNAME,
                COLUMN_PRODUCT_TYPE,
                COLUMN_PRODUCT_IMAGE,
                HORSEPOWER,
                SECTO100,
                MAXSPEED,
                COLOR,
                COLUMN_PRODUCT_PRICE
        };
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " DESC";
        Cursor c = db.query(TABLE_PRODUCTNAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        return c;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdtype() {
        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype;
    }

    public int getHORSEPOWER() {
        return HORSEPOWER;
    }

    public void setHORSEPOWER(int HORSEPOWER) {
        this.HORSEPOWER = HORSEPOWER;
    }

    public int getSECTO100() {
        return SECTO100;
    }

    public void setSECTO100(int SECTO100) {
        this.SECTO100 = SECTO100;
    }

    public int getMAXSPEED() {
        return MAXSPEED;
    }

    public void setMAXSPEED(int MAXSPEED) {
        this.MAXSPEED = MAXSPEED;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //endregion

    //region Setter and Getter


}
