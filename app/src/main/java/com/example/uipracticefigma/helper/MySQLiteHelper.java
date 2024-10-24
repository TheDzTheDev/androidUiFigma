package com.example.uipracticefigma.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper  {

    private static final String DATABASE_NAME = "wex_linx.db";
    private static final int DATABASE_VERSION = 3;

    //Users Details for login
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_URL = "url";
    public static final String COLUMN_URL_ID = "id";
    public static final String COLUMN_URL_URL = "url_path";


    //Images
    public static final String TABLE_IMAGE_USER = "images_user";
    public static final String COLUMN_IMAGE_ID_USER = "id";
    public static final String COLUMN_IMAGE_DATA_USER = "image_data";

    //system settings
    public static final String TABLE_SYSTEM_SETTING = "system_setting";
    public static final String COLUMN_SYSTEM_SETTING_ID = "system_setting_id";
    public static final String COLUMN_REST_URL = "rest_url";
    public static final String COLUMN_REST_USERNAME = "rest_username";
    public static final String COLUMN_REST_PASSWORD = "rest_password";


    //IMAGE FOR USER PROFILE
    private static final String CREATE_TABLE_IMAGE_USER = "CREATE TABLE "
            + TABLE_IMAGE_USER + "("
            + COLUMN_IMAGE_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IMAGE_DATA_USER + " BLOB NOT NULL);";

    private static final String CREATE_USERS = "CREATE TABLE "
            + TABLE_USERS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " text not null, "
            + COLUMN_PASSWORD + " text not null);";

    private static final String CREATE_URL = "create table "
            + TABLE_URL + "(" + COLUMN_URL_ID
            + " integer primary key autoincrement, "
            + COLUMN_URL_URL + " text not null); ";

    private static final String DATABASE_CREATE_SYSTEM_SETTING = "create table  IF NOT EXISTS "
            + TABLE_SYSTEM_SETTING + "(" + COLUMN_SYSTEM_SETTING_ID
            + " integer primary key autoincrement, "
            + COLUMN_REST_URL + " text not null,"
            + COLUMN_REST_USERNAME + " text not null,"
            + COLUMN_REST_PASSWORD + " text not null); ";



    private static final String Insert_url = "insert into url"
            + " (url_path) values "
//			+ "('http://192.168.254.108:8080/FMSFifasci/') ;";
//			+ "('http://192.168.0.111.108:8080/FMSFifasci/') ;";
//	        + "('http://192.168.254.108:8080/FMSFifasci/') ;";
//            + "('http://192.168.1.22:8080/FMSFifasci/') ;";
//            + "('http://192.168.0.169:8080/fmsv2') ;";
//    + "('http://10.199.1.133:8080/fmsv2/') ;";customize
//			+ "('http://10.199.1.148:8080/FMSFifasci/') ;";//OLD
//			+ "('http://192.168.254.155:8080/FMSFifasci/') ;";
//			+ "('http://192.168.1.21:8080/FMSFifasci/') ;";
//			+ "('https://fms.itresources.ph:8080/FMSFifasci/') ;";
            + "('http://192.168.0.169:8080/wex_linx') ;";

    private static final String INSERT_SYSTEM_SETTING = "insert into "+TABLE_SYSTEM_SETTING
            + " ("+COLUMN_REST_URL+","+COLUMN_REST_USERNAME+","+COLUMN_REST_PASSWORD+") values "
//			+"('http://192.168.254.105:8080','admin','secret')";
//			+"('http://192.168.1.14:8080','admin','secret')";
//			+"('http://192.168.1.18:8080','admin','secret')";
//			+ "('http://192.168.254.119:8080','admin','secret') ;";
//			+ "('http://192.168.254.108:8080','admin','secret') ;";
//			+ "('http://192.168.254.155:8080','admin','secret') ;";
//			+ "('http://192.168.1.12:8080','admin','secret') ;";
            //PROD
//            + "('http://116.50.230.252:8080','admin','secret') ;";
            //  + "('https://prealert.myitresources.com.ph:8080','admin','secret') ;";//UAT
//			+ "('http://192.168.1.21:8080','admin','secret') ;";
            //		+ "('http://192.168.1.2:8080','admin','secret') ;";
            +"('http://192.168.0.169:8080','admin','secret') ;";//localhost
    //+ "('http://192.168.0.140:8080','admin','secret') ;";
    //UAT
    //+ "('https://prealert.myitresources.com.ph:8080','admin','secret') ;";
    //+ "('http://192.168.0.110:8080','admin','6GwRcX') ;";
    /*+ "('http://192.168.1.23:8080','admin','secret') ;";*/

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    //CREATE TABLES HERE
    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d("MySQLiteHelper", "onCreate method is called.");
        database.execSQL(DATABASE_CREATE_SYSTEM_SETTING);
        database.execSQL(INSERT_SYSTEM_SETTING);
        database.execSQL(CREATE_URL);
        database.execSQL(Insert_url);
        database.execSQL(CREATE_USERS);
        database.execSQL(CREATE_TABLE_IMAGE_USER);

    }
    //UPGRADING OF DATABASE VERSION
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_URL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYSTEM_SETTING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE_USER);
        onCreate(db);
    }

}

