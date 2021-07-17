package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class WaterLevelActivity extends AppCompatActivity {
    TextView txt_level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_level);
           txt_level= findViewById(R.id.level);
           level();


            }
            public void level(){
//
                //Toast.makeText(WaterLevelActivity.this, "get data", Toast.LENGTH_SHORT).show();
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, "https://nodemcu-a0d8a-default-rtdb.firebaseio.com/LEVEL.json", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Toast.makeText(WaterLevelActivity.this, response.get("waterlevel").toString(), Toast.LENGTH_SHORT).show();
                            txt_level.setText(response.get("waterlevel").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WaterLevelActivity.this, "get data fail"+error, Toast.LENGTH_SHORT).show();
                        System.out.println(error);
                    }
                });
                Volley.newRequestQueue(this).add(request);
            }

    }
