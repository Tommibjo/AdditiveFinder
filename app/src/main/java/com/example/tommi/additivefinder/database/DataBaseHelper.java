package com.example.tommi.additivefinder.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.example.tommi.additivefinder.httprequests.HttpRequest;
import com.example.tommi.additivefinder.httprequests.VolleyResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/*
* Tämä luokka perii SQLiteOpenHelper luokan toiminnallisuudet:
*  ->onCreate
*  ->onUpgrade
*
* "Helper class to manage database creation and version management."
* - https://www.tutorialspoint.com/http/http_requests.htm
* */
public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String databaseName = "additives.db";
    private static final int databaseVersion = 1;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            Contract.ContractEntry.TABLE_NAME + " (" +
            Contract.ContractEntry._ID + " INTEGER PRIMARY KEY," +
            Contract.ContractEntry.COLUMN_NAME_ELGA + " TEXT," +
            Contract.ContractEntry.COLUMN_NAME_COMPETITOR + " TEXT)";



    // httpRequest olio hallinnoi Rasperry HTTP yhteyksiä.
    private HttpRequest httpRequest;

    public DataBaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
        this.httpRequest = new HttpRequest(context);

    }



    // OnCreate (SQLiteOpenHelper yliluokan metodi).
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("OnCreate kutsuttu");
        httpRequest.get(new VolleyResponseListener() {

            @Override
            public void onVolleySuccess(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String id = obj.getString("id");
                        String elga = obj.getString("elga");
                        String competitor = obj.getString("competitor");
                        System.out.println("JSON to string: " + id + ": " + elga + " = " + competitor);
                    }
                } catch (JSONException e) {
                    System.out.println("Error: " + e);
                }
            }
        });

        // Luodaan database (sql
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(insertInto("elga1", "kilpailija1"));
        db.execSQL(insertInto("elga1", "kiplailija2"));
        db.execSQL(insertInto("elga2", "kilpailija1"));
        db.execSQL(insertInto("elga2", "kiplailija2"));
    }

    // OnCreate (SQLiteOpenHelper yliluokan metodi.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade juoksee nyt");

    }




    /*
     *
     * Pitäisi laittaa omaan luokkaansa
     *
     * */


    public Cursor getCorrespondingElga(String competitorsAdditive) {
        System.out.println("GetData käynnissä");
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT elga FROM additives WHERE competitor LIKE ?", new String[]{"%" + competitorsAdditive + "%"});
        return res;
    }

    public String insertInto(String elga, String competitor) {
        System.out.println("insertInto käynnissä");
        String SQL_INSERT_ENTRIES = "INSERT INTO " + Contract.ContractEntry.TABLE_NAME + " (" +
                Contract.ContractEntry.COLUMN_NAME_COMPETITOR + " ," +
                Contract.ContractEntry.COLUMN_NAME_ELGA + ") " +
                "VALUES (" + "'" + competitor + "'," + "'" + elga + "')";
        return SQL_INSERT_ENTRIES;
    }

}

