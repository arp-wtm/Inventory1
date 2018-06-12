package com.example.antonella.inventory1;
/*
 * made by Antonella on 06/09/2018 using ud845 course Udacity
 * examples that are under this license:
 *
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antonella.inventory1.data.ProductContract.ProductEntry;
import com.example.antonella.inventory1.data.ProductDbHelper;

import static android.widget.Toast.LENGTH_LONG;


/**
 * Displays the list of products that are stored in the book-store db.
 */
public class CatalogActivity extends AppCompatActivity {


    /**
     * Database helper that will provide us access to the database
     */
    private ProductDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        /*
         * To access book_store database, we instantiate our ProductDbHelper
         * and pass the context, which is the current activity
         */
        mDbHelper = new ProductDbHelper(this);

        displayDatabaseInfo();

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen
     * TextView about the state of the book_store database.
     */
    @SuppressWarnings("TryFinallyCanBeTryWithResources")
    private void displayDatabaseInfo() {


        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns of db
        // you will use for the query.
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                ProductEntry.TABLE_NAME,   // The table to query
                projection,                // The columns to return
                null,             // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,              // Don't group the rows
                null,               // Don't filter by row groups
                null);             // The sort order


        try {
            // central text view that shows info to the user
            TextView displayView = findViewById(R.id.central_text_view);
            //string to count the number of entries in the product table
            String rowsNumber = getString(R.string.rows_number) + String.valueOf(cursor.getCount()) + getString(R.string.products);

            // set the central text view to display the number of rows in the Cursor
            displayView.setText(rowsNumber);

            // add a header in the central text with
            // -id -name - price - quantity - supplier_name -supplier_phone_number
            displayView.append(ProductEntry._ID + " - " +
                    ProductEntry.COLUMN_PRODUCT_NAME + " - " +
                    ProductEntry.COLUMN_PRODUCT_PRICE + " - " +
                    ProductEntry.COLUMN_PRODUCT_QUANTITY + " - " +
                    ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " - " +
                    ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + "\n");


            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);

                // Display the values from each column of the current row
                // in the cursor in the central text view
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierPhoneNumber));
            }

        } finally {
            // Always close the cursor when you're done reading from it.
            // This releases all its resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded book data into the database.
     * For debugging purposes only.
     */
    private void insertProduct() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and book's attributes are the values.
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, "book 1");
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, 10);
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, 17);
        values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "dummy supplier");
        values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, 190);


        // Insert a new row for a dummy product in the db, returning the ID of that new row.
        // The first argument for db.insert() is the table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for book 1.
        db.insert(ProductEntry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks on the Home/Up button,
        // using the parent activity declared in AndroidManifest.xml.
        // id defines user clicked menu option
        int id = item.getItemId();

        switch (id) {

            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertProduct();
                displayDatabaseInfo();
                return true;

            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Display a toast msg with info to delete data
                Toast.makeText(this, R.string.delete_data, LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
