package com.example.complexjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Find_view_id();

        //=========================================================
        //Json Obeject Request
        //==========================================================

        progressBar.setVisibility(View.VISIBLE);

        String url = "https://ashadacademy.000webhostapp.com/Apps/complex.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar.setVisibility(View.GONE);
                try {

                    //=====================================================
                    //json Object
                    //=====================================================

                    String title = response.getString("title");
                    String collage = response.getString("collage");
                    String age = response.getString("age");

                    textView.append(""+title+"\n"+collage+"\n"+age);

                    //=====================================================
                    //json Object
                    //=====================================================


                    //=====================================================
                    //json array
                    //=====================================================


                    JSONArray jsonArray = response.getJSONArray("video_id");
                    for (int i =0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String titles = jsonObject.getString("title");
                        String v_id = jsonObject.getString("vodeo_id");

                        textView.append("\n\n"+titles+"\n"+v_id);

                    }

                    //=====================================================
                    //json array
                    //=====================================================



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(""+error);
                progressBar.setVisibility(View.GONE);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(jsonObjectRequest);

        //=========================================================
        //Json Obeject Request
        //==========================================================


    }

    private void Find_view_id() {

        progressBar = findViewById(R.id.progber);
        textView = findViewById(R.id.textview);

    }
}