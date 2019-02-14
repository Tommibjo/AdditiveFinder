package com.example.tommi.additivefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import com.example.tommi.additivefinder.database.*;

import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ArrayList<String> searchResult;
    private ListView listView;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.searchResult = new ArrayList<>();
        this.searchView = findViewById(R.id.searchView);
        this.searchView.setIconified(false);
        this.searchView.setQueryHint("Kilpailijan lisäaine tähän.");
        this.listView = findViewById(R.id.listView);
        this.dataBaseHelper = new DataBaseHelper(this);
        this.dataBaseHelper.getWritableDatabase();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("onQueryTextSubmit: " + query);
                Cursor cursor = dataBaseHelper.getCorrespondingElga(query);
                if (cursor.moveToFirst()) {
                    String outcome = cursor.getString(cursor.getColumnIndex("elga"));
                    searchResult.clear();
                    searchResult.add(outcome);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, searchResult);
                    listView.setAdapter(adapter);
                } else {
                    searchResult.clear();
                    searchResult.add("Ei tuloksia haulla.");
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, searchResult);
                    listView.setAdapter(adapter);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listView.setAdapter(null);
                return false;
            }

        });

    }
}
