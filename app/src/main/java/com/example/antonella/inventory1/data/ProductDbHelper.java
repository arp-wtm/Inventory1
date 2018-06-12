
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

package com.example.antonella.inventory1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.antonella.inventory1.data.ProductContract.ProductEntry;

/**
 * Database helper that manages creation, upgrade and version management.
 */

public class ProductDbHelper extends SQLiteOpenHelper {


    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "book_store.db";

    /**
     * Database version. If you change the database schema,
     * you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link ProductDbHelper}.
     *
     * @param context of the app
     *                calling the method of superclass con context passed, DATABASE_NAME,
     *                a cursor factory that is null to use that of default and the version
     */
    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the product table
        String SQL_CREATE_PRODUCT_TABLE = "CREATE TABLE " + ProductEntry.TABLE_NAME + " ( "
                + ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductEntry.COLUMN_PRODUCT_NAME + " TEXT, "
                + ProductEntry.COLUMN_PRODUCT_PRICE + " INTEGER, "
                + ProductEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER, "
                + ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " TEXT, "
                + ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + " TEXT );";

        /*
         * Execute the SQL statement on the SQLite DATABASE instance class db
         * param passed into onCreate
         */
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);


    }

    /**
     * This is called when the database needs to be upgraded.
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.

    }
}
