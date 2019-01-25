package com.example.tommi.additivefinder.httprequests;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class HttpRequest {

    private RequestQueue queue;
    private String url;

    /* Vaihdat vain tämän IP:n rasperry PI:n IP:ksi, niin saa ladata sieltä nuo datat */
    public HttpRequest(Context context) {
        queue = Volley.newRequestQueue(context);
        url = "http://10.0.2.2:8080/additives/";
    }

    // GET request toimii!
    public void getRequest() {
        System.out.println("getRequest aktivoitu");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("RESPONSE: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERRORLISTENER: EI TOIMINUT");
            }

        });
        queue.add(jsonArrayRequest);
    }
}

