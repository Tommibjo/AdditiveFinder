package com.example.tommi.additivefinder.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.example.tommi.additivefinder.httprequests.HttpRequest;

public class DataBaseHelper extends SQLiteOpenHelper {

    HttpRequest httpRequest;
    private static final String databaseName = "additives.db";
    private static final int databaseVersion = 1;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + Contract.ContractEntry.TABLE_NAME + " (" +
            Contract.ContractEntry._ID + " INTEGER PRIMARY KEY," +
            Contract.ContractEntry.COLUMN_NAME_COMPETITOR + " TEXT," +
            Contract.ContractEntry.COLUMN_NAME_ELGA + " TEXT)";

    public DataBaseHelper(Context context){
        super(context, databaseName, null, databaseVersion);
        this.httpRequest = new HttpRequest(context);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    System.out.println("OnCreate käynnissä");
    db.execSQL(SQL_CREATE_ENTRIES);
    db.execSQL(setData("MX100", "MF 710 M"));
    db.execSQL(setData("DW308L", "308LT0"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
        httpRequest.getRequest();
    }

    public Cursor getData(String competitorsAdditive){
        System.out.println("GetData käynnissä");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select elga from additives where competitor LIKE ?", new String[] {"%" + competitorsAdditive + "%"});
        return res;
    }

    public String setData(String competitor, String elga){
        System.out.println("setData käynnissä");
        String SQL_INSERT_ENTRIES = "INSERT INTO " + Contract.ContractEntry.TABLE_NAME + " (" +
                Contract.ContractEntry.COLUMN_NAME_COMPETITOR + " ," +
                Contract.ContractEntry.COLUMN_NAME_ELGA + ") " +
                "VALUES (" + "'" + competitor + "'," + "'" + elga + "')";
        return SQL_INSERT_ENTRIES;
    }
}

