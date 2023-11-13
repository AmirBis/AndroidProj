package com.example.amirproj.classes;

import static com.example.amirproj.dataTables.TablesString.ProductTable.*;
import static com.example.amirproj.dataTables.TablesString.ProductTable.COLUMN_PRODUCT_COLOR;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Product implements SqlInterface{

    //region Attribute
    protected int pid;
    protected String prodtype;

    protected String color;
    protected int horsepower;
    protected int secto100;
    protected int maxspeed;
    protected byte[] imageByte;
    protected double price;

    //endregion

    //region Constructors
    public Product(String prodtype, String color, int horsepower, double price, int secto100, int maxspeed ,byte[] image){
        this.price=price;
        this.prodtype=prodtype;
        this.color = color;
        this.horsepower=horsepower;
        this.secto100=secto100;
        this.maxspeed=maxspeed;
        this.imageByte = image;
    }

    public Product() {

    }

    public Product(Product p) {
    }
    //endregion

    //region Add,Delete,Update,Select Sql
    @Override
    public long Add(SQLiteDatabase db) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_TYPE, prodtype);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(COLUMN_PRODUCT_SECTO100, secto100);
        values.put(COLUMN_PRODUCT_COLOR, color);
        values.put(COLUMN_PRODUCT_MAXSPEED, maxspeed);
        values.put(COLUMN_PRODUCT_HORSEPOWER, horsepower);
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
        values.put(COLUMN_PRODUCT_TYPE, prodtype);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(COLUMN_PRODUCT_SECTO100, secto100);
        values.put(COLUMN_PRODUCT_COLOR, color);
        values.put(COLUMN_PRODUCT_MAXSPEED, maxspeed);
        values.put(COLUMN_PRODUCT_HORSEPOWER, horsepower);
        values.put(COLUMN_PRODUCT_IMAGE, imageByte);

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
                COLUMN_PRODUCT_HORSEPOWER,
                COLUMN_PRODUCT_SECTO100,
                COLUMN_PRODUCT_MAXSPEED,
                COLUMN_PRODUCT_COLOR,
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
    public Cursor SelectById(SQLiteDatabase db,String id) {
        String[] projection = {
                BaseColumns._ID,
                TABLE_PRODUCTNAME,
                COLUMN_PRODUCT_TYPE,
                COLUMN_PRODUCT_IMAGE,
                COLUMN_PRODUCT_HORSEPOWER,
                COLUMN_PRODUCT_SECTO100,
                COLUMN_PRODUCT_MAXSPEED,
                COLUMN_PRODUCT_COLOR,
                COLUMN_PRODUCT_PRICE

        };
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = {id};

        Cursor c = db.query(
                TABLE_PRODUCTNAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null  );
        return c;
    }
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getProdtype() {
        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype;
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
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getSecto100() {
        return secto100;
    }

    public void setSecto100(int secto100) {
        this.secto100 = secto100;
    }

    public int getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(int maxspeed) {
        this.maxspeed = maxspeed;
    }



    @Override
    public String toString() {
        return  prodtype ;
    }
    //endregion

    //region Setter and Getter


}
