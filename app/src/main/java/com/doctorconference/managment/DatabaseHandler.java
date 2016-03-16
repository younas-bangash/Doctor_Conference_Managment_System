package com.doctorconference.managment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 23;
    // Database Name
    private static final String DATABASE_NAME = "DoctorManagmentDB";
    // Contacts table name
    private static final String TABLE_NAME = "user_registration";
    private static final String TABLE_NAME_TOPICS = "topics";
    private static final String TABLE_NAME_CONF = "conferences";
    // Contacts Table Columns names
    private static final String KEY_ID    = "user_id";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_PWD = "pwd";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADMIN = "admin";
    private static final String KEY_TOPICID    = "topic_id";
    private static final String KEY_TOPIC_TITTLE = "topic_title";
    private static final String KEY_TOPIC_DETAILS = "topic_details";

    private static final String KEY_CONFICID    = "conf_id";
    private static final String KEY_CONF_TITTLE = "conf_title";
    private static final String KEY_CONF_DETAILS = "conf_date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create user table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_FNAME + " TEXT,"
                + KEY_LNAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PWD + " TEXT,"
                + KEY_ADMIN + " TEXT" + ")";
        //create table for topics
        String CREATE_TOPIC_TABLE = "CREATE TABLE " + TABLE_NAME_TOPICS + "("
                + KEY_TOPICID + " INTEGER PRIMARY KEY,"
                + KEY_TOPIC_TITTLE + " TEXT,"
                + KEY_TOPIC_DETAILS + " TEXT" + ")";
        //create table for topics
        String CREATE_CONF_TABLE = "CREATE TABLE " + TABLE_NAME_CONF + "("
                + KEY_CONFICID + " INTEGER PRIMARY KEY,"
                + KEY_CONF_TITTLE + " TEXT,"
                + KEY_CONF_DETAILS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_TOPIC_TABLE);
        db.execSQL(CREATE_CONF_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TOPICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CONF);
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
    // Adding new contact
    void addTopics(GetSetData contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TOPIC_TITTLE, contact.getmTopicTitle());
        values.put(KEY_TOPIC_DETAILS, contact.getmTopicDetails());
        // Inserting Row
        db.insert(TABLE_NAME_TOPICS, null, values);
        db.close(); // Closing database connection
    }

    // Adding new confrence
    void addConf(GetSetData contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CONF_TITTLE, contact.getmTopicTitle());
        values.put(KEY_CONF_DETAILS, contact.getmTopicDetails());
        // Inserting Row
        db.insert(TABLE_NAME_CONF, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Topics
    public List<GetSetData> GetConfrences(String conficid,boolean allconf) {
        List<GetSetData> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery;
        if(allconf)
            selectQuery = "SELECT  * FROM " + TABLE_NAME_CONF +" order by "+KEY_CONFICID+" DESC";
        else
            selectQuery = "SELECT  * FROM " + TABLE_NAME_CONF +" where "+ KEY_CONFICID +"='"+conficid+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GetSetData contact = new GetSetData();
                contact.setmTopicD(""+Integer.parseInt(cursor.getString(0)));
                contact.setmTopicTitle(cursor.getString(1));
                contact.setmTopicDetails(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }


    // Getting All Topics
    public List<GetSetData> GetTopics(String topicid,boolean alltopics) {
        List<GetSetData> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery;
        if(alltopics)
            selectQuery = "SELECT  * FROM " + TABLE_NAME_TOPICS +" order by "+KEY_TOPICID+" DESC";
        else
            selectQuery = "SELECT  * FROM " + TABLE_NAME_TOPICS +" where "+ KEY_TOPICID +"='"+topicid+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GetSetData contact = new GetSetData();
                contact.setmTopicD(""+Integer.parseInt(cursor.getString(0)));
                contact.setmTopicTitle(cursor.getString(1));
                contact.setmTopicDetails(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }




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
