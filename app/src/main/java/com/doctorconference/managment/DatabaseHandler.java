package com.doctorconference.managment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class DatabaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 16;
    // Database Name
    private static final String DATABASE_NAME = "DoctorManagmentDB";
    // Contacts table name
    private static final String TABLE_NAME = "user_registration";
    // Contacts Table Columns names
    private static final String KEY_ID    = "user_id";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_PWD = "pwd";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADMIN = "admin";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_FNAME + " TEXT,"
                + KEY_LNAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PWD + " TEXT,"
                + KEY_ADMIN + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);

    }
    // Adding new contact
    void addContact(GetSetData contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, contact.getmFirstName());
        values.put(KEY_LNAME, contact.getmLastName());
        values.put(KEY_EMAIL, contact.getmEmail());
        values.put(KEY_PWD, contact.getmPwD());
        values.put(KEY_ADMIN, contact.getmAdmin());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts
    public List<GetSetData> checkUserDetails(String pwd,String email,boolean allrecord) {
        List<GetSetData> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery;
        if(allrecord)
           selectQuery = "SELECT  * FROM " + TABLE_NAME ;
        else
            selectQuery = "SELECT  * FROM " + TABLE_NAME +" where "+ KEY_EMAIL +"='"+email+"' AND "+ KEY_PWD +"='"+pwd+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GetSetData contact = new GetSetData();
                contact.setmRecodeID(""+Integer.parseInt(cursor.getString(0)));
                contact.setmFirstName(cursor.getString(1));
                contact.setmLastName(cursor.getString(2));
                contact.setmEmail(cursor.getString(3));
                contact.setmPwd(cursor.getString(4));
                contact.setmAdmin(cursor.getString(5));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }
}
