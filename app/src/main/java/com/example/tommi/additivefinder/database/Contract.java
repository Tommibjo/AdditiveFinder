package com.example.tommi.additivefinder.database;

import android.provider.BaseColumns;

public final class Contract
{
    private Contract() {

    }

    /* Inner class that defines the table contents */
    public static class ContractEntry implements BaseColumns {
        public static final String TABLE_NAME = "additives";
        public static final String COLUMN_NAME_ELGA = "elga";
        public static final String COLUMN_NAME_COMPETITOR = "competitor";
    }
}

