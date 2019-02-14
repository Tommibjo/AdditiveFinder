package com.example.tommi.additivefinder.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import com.example.tommi.additivefinder.httprequests.HttpRequest;
import com.example.tommi.additivefinder.httprequests.SqlQueries;
import com.example.tommi.additivefinder.httprequests.VolleyResponseListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String databaseName = "additives.db";
    private static final int databaseVersion = 1;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            Contract.ContractEntry.TABLE_NAME + " (" +
            Contract.ContractEntry._ID + " INTEGER PRIMARY KEY," +
            Contract.ContractEntry.COLUMN_NAME_ELGA + " TEXT," +
            Contract.ContractEntry.COLUMN_NAME_COMPETITOR + " TEXT)";
    private HttpRequest httpRequest;

    public DataBaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
        this.httpRequest = new HttpRequest(context);

    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        httpRequest.get(new VolleyResponseListener() {

            @Override
            public void onVolleySuccess(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String elga = obj.getString("elga");
                        String competitor = obj.getString("competitor");
                        db.execSQL(SqlQueries.insertInto(elga,competitor));
                    }
                } catch (JSONException e) {
                    System.out.println("Error: " + e);
                }
            }
        });
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade juoksee nyt");

    }

    public SQLiteDatabase getDatabase(){
        return this.getReadableDatabase();
    }
}

