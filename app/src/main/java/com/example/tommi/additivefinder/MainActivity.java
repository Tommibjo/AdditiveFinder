package com.example.tommi.additivefinder;

import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.ArrayList;
import com.example.tommi.additivefinder.database.*;
import com.example.tommi.additivefinder.httprequests.HttpRequest;

import android.database.Cursor;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> searchResult;
    ListView listView;
    SearchView searchView;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchResult = new ArrayList<>();

        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
        searchView.setIconified(false);
        searchView.setQueryHint("Kilpailijan lisäaine tähän.");
        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.getWritableDatabase();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("onQueryTextSubmit: " + query);
                Cursor cursor = dataBaseHelper.getData(query);
                if(cursor.moveToFirst()){
                    String outcome = cursor.getString(cursor.getColumnIndex("elga"));
                    searchResult.clear();
                    searchResult.add(outcome);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, searchResult);
                    listView.setAdapter(adapter);
                }else{
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
