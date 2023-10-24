package com.example.amirproj.dataTables;
import com.example.amirproj.dataTables.TablesString.*;
public class QueryString {


    //region Create Tables
    public static final String SQL_CREATE_PRODUCT =
            "CREATE TABLE " + ProductTable.TABLE_PRODUCTNAME + " (" +
                    ProductTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ProductTable.COLUMN_PRODUCT_TYPE + " TEXT," +
                    ProductTable.COLUMN_PRODUCT_COLOR + " TEXT," +
                    ProductTable.COLUMN_PRODUCT_PRICE + " INTEGER," +
                    ProductTable.COLUMN_PRODUCT_MAXSPEED + " INTEGER,"+
                    ProductTable.COLUMN_PRODUCT_HORSEPOWER + " INTEGER,"+
                    ProductTable.COLUMN_PRODUCT_SECTO100 + " INTEGER,"+
                    ProductTable.COLUMN_PRODUCT_IMAGE + " BLOB);";

    public static final String SQL_CREATE_CART =
            "CREATE TABLE " + CartTable.TABLE_CART + " (" +
                    CartTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CartTable.COLUMN_PRODUCT_ID + " INTEGER," +
                    CartTable.COLUMN_USER_ID + " TEXT);";

    public static final String SQL_CREATE_SALE =
            "CREATE TABLE " + SaleTable.TABLE_SALE + " (" +
                    SaleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SaleTable.COLUMN_SALE_PROD_ID + " INTEGER," +
                    SaleTable.COLUMN_SALE_USER_ID + " TEXT);";

    //endregions

    //region Delete Tables

    public static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + ProductTable.TABLE_PRODUCTNAME;

    public static final String SQL_DELETE_CART =
            "DROP TABLE IF EXISTS " + CartTable.TABLE_CART;

    public static final String SQL_DELETE_SALE =
            "DROP TABLE IF EXISTS " + SaleTable.TABLE_SALE;

    //endregion
}
