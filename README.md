# Inventory stage 1 description
UDACITY ABND course-Project 8 Inventory app stage 1 
The goal of this app is to create a database schema for a Book Store using SQLite 3, to keep track of product inventory.
The main table called product has these information fields:
- product name of type string
- price of type int 
- quantity of type int
- supplier name of type string
- supplier phone number of type string
### notes
The code is written using Android Studio 3.1.3, using target specification for Phone and Tablet, API 15: Android 4.0.3 (IceCreamSandwich) and later.
For submission i clean the project, so you receive the error message "cannot resolve simbol R". This will disappeares when yuo rebuild.

### CatalogAcivity 
is the main activity opened by launching app. It has method to insertProduct() and displayDatabaseInfo().It shown also an overflow menu on the top bar, that provides insert of dummy data.
### EditorActivity 
this class has the task to add a new product, using a ContentValues object where column names are the keys, and product attributes from the editor are the values. It is called by the screen right bottom fab red button. .
### data package contains:
- ProductContract class whic provides all the mnemonic names for book_store db column so i'm less likely to use incorrect values. It establishes a contract between the content provider and other application
- ProductDbHelper, subclass of SQLiteOpenHelper. It is a database helper that manages creation, upgrade and version management.
### License
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
