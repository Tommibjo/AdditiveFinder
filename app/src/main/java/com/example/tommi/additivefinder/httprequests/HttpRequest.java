package com.example.tommi.additivefinder.httprequests;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tommi.additivefinder.database.DataBaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequest {

    private RequestQueue queue;
    private String url;


    public HttpRequest(Context context) {
        this.queue = Volley.newRequestQueue(context);
        this.url = "http://91.155.196.209:8080/AdditivesApi-1.0-SNAPSHOT/additives/";
    }


    public void get(final VolleyResponseListener listener) {

        // A request for retrieving a JSONArray response body at a given URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listener.onVolleySuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JsonArrayRequest failed.");
            }
        });
        queue.add(jsonArrayRequest);
    }
}

