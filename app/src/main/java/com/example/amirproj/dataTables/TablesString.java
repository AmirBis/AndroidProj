package com.example.amirproj.dataTables;

import android.provider.BaseColumns;

public class TablesString {

    public TablesString() {
    }
    //region Product Table
    public static class ProductTable implements BaseColumns {
        public static final String TABLE_PRODUCTNAME = "Product";
        public static final String COLUMN_PRODUCT_TYPE = "ProductType";
        public static final String COLUMN_PRODUCT_IMAGE = "ProductImage";
        public static final String COLUMN_PRODUCT_PRICE = "Price";
        public static final String COLUMN_PRODUCT_COLOR = "COLOR";
        public static final String COLUMN_PRODUCT_MAXSPEED = "MaxSpeed";
        public static final String COLUMN_PRODUCT_SECTO100 = "SecTo100";
        public static final String COLUMN_PRODUCT_HORSEPOWER = "horsePower";




    }
    //endregion

    //region Cart Table
    public static class CartTable implements BaseColumns {
        public static final String TABLE_CART = "Cart";
        public static final String COLUMN_PRODUCT_ID = "PID";
        public static final String COLUMN_USER_ID = "UID";
        public static final String COLUMN_AMOUNT = "amount";


    }
    //endregion

    //region Sale Table
    public static class SaleTable implements BaseColumns {
        public static final String TABLE_SALE = "SALE";
        public static final String COLUMN_SALE_PROD_ID = "PID";
        public static final String COLUMN_SALE_USER_ID = "UID";
    }
    //endregion
}
