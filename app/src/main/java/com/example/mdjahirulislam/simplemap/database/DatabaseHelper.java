package com.example.mdjahirulislam.simplemap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Trainer on 3/29/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tour_app";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER_REGISTRATION = "user_registration_table";
    public static final String TABLE_TRAVEL_EVENT = "travel_event_table";
    public static final String TABLE_TRAVEL_EVENT_EXPENSE = "travel_event_expense_table";
    public static final String TABLE_TRAVEL_EVENT_MOMENT = "travel_event_moment_table";


    // column of TABLE_USER_REGISTRATION

    public static final String COL_USER_ID = "user_id";
    public static final String COL_USER_UNIQUE_ID = "user_unique_id";
    public static final String COL_USER_FULL_NAME = "user_full_name";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_USER_MOBILE_NO = "user_mobile_no";
    public static final String COL_USER_ADDRESS = "user_address";
    public static final String COL_USER_CREATED_AT = "user_created_at";



    // column of TABLE_TRAVEL_EVENT

    public static final String COL_TRAVEL_EVENT_ID = "travel_event_id";
    public static final String COL_TRAVEL_EVENT_UNIQUE_ID = "travel_event_unique_id";
    public static final String COL_TRAVEL_EVENT_USER_UNIQUE_ID = "travel_event_user_unique_id";
    public static final String COL_TRAVEL_EVENT_ESTIMATED_BUDGET = "travel_event_estimated_budget";
    public static final String COL_TRAVEL_EVENT_DESTINATION = "travel_event_destination";
    public static final String COL_TRAVEL_EVENT_FROM_DATE = "travel_event_from_date";
    public static final String COL_TRAVEL_EVENT_TO_DATE = "travel_event_to_date";
    public static final String COL_TRAVEL_EVENT_CREATED_AT = "travel_event_created_at";




    // column of TABLE_TRAVEL_EVENT_EXPENSE

    public static final String COL_TRAVEL_EVENT_EXPENSE_ID = "travel_event_expense_id";
    public static final String COL_TRAVEL_EVENT_EXPENSE_UNIQUE_ID = "travel_event_expense_unique_id";
    public static final String COL_TRAVEL_EVENT_EXPENSE_EVENT_UNIQUE_ID = "travel_event_expense_event_unique_id";
    public static final String COL_TRAVEL_EVENT_EXPENSE_DETAILS = "travel_event_expense_details";
    public static final String COL_TRAVEL_EVENT_EXPENSE_AMOUNT = "travel_event_expense_amount";
    public static final String COL_TRAVEL_EVENT_EXPENSE_DATE = "travel_event_expense__date";
    public static final String COL_TRAVEL_EVENT_EXPENSE_VOUCHER = "travel_event_expense_voucher";
    public static final String COL_TRAVEL_EVENT_EXPENSE_CREATED_AT = "travel_event_expense_created_at";



    // column of TABLE_TRAVEL_EVENT_MOMENT

    public static final String COL_TRAVEL_EVENT_MOMENT_ID = "travel_event_moment_id";
    public static final String COL_TRAVEL_EVENT_MOMENT_UNIQUE_ID = "travel_event_moment_unique_id";
    public static final String COL_TRAVEL_EVENT_MOMENT_EVENT_UNIQUE_ID = "travel_event_moment_event_unique_id";
    public static final String COL_TRAVEL_EVENT_MOMENT_DETAILS = "travel_event_moment_details";
    public static final String COL_TRAVEL_EVENT_MOMENT_IMAGE = "travel_event_moment_image";
//    public static final String COL_TRAVEL_EVENT_FROM_DATE = "travel_event_from_date";
//    public static final String COL_TRAVEL_EVENT_TO_DATE = "travel_event_to_date";
    public static final String COL_TRAVEL_EVENT_MOMENT_CREATED_AT = "travel_event_moment_created_at";


    /*public static final String CREATE_MOVIE_TABLE1 = "create table tbl_movie(tbl_id integer primary key, tbl_name text, tbl_year text);";*/

    public static final String CREATE_USER_REGISTRATION_TABLE = "create table "+ TABLE_USER_REGISTRATION +"("+
            COL_USER_ID +" integer primary key, "+
            COL_USER_UNIQUE_ID +" integer, "+
            COL_USER_FULL_NAME +" text, "+
            COL_USER_NAME +" text, "+
            COL_USER_MOBILE_NO +" text, "+
            COL_USER_ADDRESS +" text, "+
            COL_USER_CREATED_AT +" text);";


    public static final String CREATE_TRAVEL_EVENT_TABLE = "create table "+ TABLE_TRAVEL_EVENT +"("+
            COL_TRAVEL_EVENT_ID +" integer primary key, "+
            COL_TRAVEL_EVENT_UNIQUE_ID +" integer, "+
            COL_TRAVEL_EVENT_USER_UNIQUE_ID +" integer, "+
            COL_TRAVEL_EVENT_DESTINATION +" text, "+
            COL_TRAVEL_EVENT_ESTIMATED_BUDGET +" text, "+
            COL_TRAVEL_EVENT_FROM_DATE +" text, "+
            COL_TRAVEL_EVENT_TO_DATE +" text, "+
            COL_TRAVEL_EVENT_CREATED_AT +" text);";


    public static final String CREATE_TRAVEL_EVENT_EXPENSE_TABLE = "create table "+ TABLE_TRAVEL_EVENT_EXPENSE +"("+
            COL_TRAVEL_EVENT_EXPENSE_ID +" integer primary key, "+
            COL_TRAVEL_EVENT_EXPENSE_UNIQUE_ID +" integer, "+
            COL_TRAVEL_EVENT_EXPENSE_EVENT_UNIQUE_ID +" integer, "+
            COL_TRAVEL_EVENT_EXPENSE_DETAILS +" text, "+
            COL_TRAVEL_EVENT_EXPENSE_AMOUNT +" text, "+
            COL_TRAVEL_EVENT_EXPENSE_DATE +" text, "+
            COL_TRAVEL_EVENT_EXPENSE_VOUCHER +" text, "+
            COL_TRAVEL_EVENT_EXPENSE_CREATED_AT +" text);";



    public static final String CREATE_TRAVEL_EVENT_MOMENT_TABLE = "create table "+ TABLE_TRAVEL_EVENT_MOMENT +"("+
            COL_TRAVEL_EVENT_MOMENT_ID +" integer primary key, "+
            COL_TRAVEL_EVENT_MOMENT_UNIQUE_ID +" integer, "+
            COL_TRAVEL_EVENT_MOMENT_EVENT_UNIQUE_ID +" integer, "+
            COL_TRAVEL_EVENT_MOMENT_DETAILS +" text, "+
            COL_TRAVEL_EVENT_MOMENT_IMAGE +" text, "+
            COL_TRAVEL_EVENT_MOMENT_CREATED_AT +" text);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_REGISTRATION_TABLE);
        db.execSQL(CREATE_TRAVEL_EVENT_TABLE);
        db.execSQL(CREATE_TRAVEL_EVENT_EXPENSE_TABLE);
        db.execSQL(CREATE_TRAVEL_EVENT_MOMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_USER_REGISTRATION);
        db.execSQL("drop table if exists "+ TABLE_TRAVEL_EVENT);
        db.execSQL("drop table if exists "+ TABLE_TRAVEL_EVENT_EXPENSE);
        db.execSQL("drop table if exists "+ TABLE_TRAVEL_EVENT_MOMENT);
        onCreate(db);
    }
}
