package com.example.tommi.additivefinder.httprequests;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.tommi.additivefinder.database.Contract;

public final class SqlQueries {

    private SqlQueries() {

    }

    public static Cursor getCorrespondingElga(SQLiteDatabase database, String competitorsAdditive) {
        System.out.println("GetData käynnissä");
        SQLiteDatabase db = database;

        Cursor res = db.rawQuery("SELECT elga FROM additives WHERE competitor LIKE ?", new String[]{"%" + competitorsAdditive + "%"});
        return res;
    }

    public static String insertInto(String elga, String competitor) {
        System.out.println("insertInto käynnissä");
        String SQL_INSERT_ENTRIES = "INSERT INTO " + Contract.ContractEntry.TABLE_NAME + " (" +
                Contract.ContractEntry.COLUMN_NAME_COMPETITOR + " ," +
                Contract.ContractEntry.COLUMN_NAME_ELGA + ") " +
                "VALUES (" + "'" + competitor + "'," + "'" + elga + "')";
        return SQL_INSERT_ENTRIES;
    }

}
