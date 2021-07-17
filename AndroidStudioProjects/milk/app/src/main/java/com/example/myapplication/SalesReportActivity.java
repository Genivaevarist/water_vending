package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SalesReportActivity extends AppCompatActivity {
    private TextView sales;
    CardView cardview;
    ImageView date_btn;
    TextView txt_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);
        sales= findViewById(R.id.sales_view);
        cardview = findViewById(R.id.card_milk);
        date_btn = findViewById(R.id.btnDate);
        txt_date = findViewById(R.id.date);

        salesData();
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SalesReportActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SalesReportActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                               String startTime =year + "/" + (month + 1) + "/" + day+" 03:00:00";
                                String endTime =year + "/" + (month + 1) + "/" + (day+1)+" 02:59:00";
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                System.out.println("gos");
                                Date date = null;
                                Date date1 = null;
                                try {
                                    date = sdf.parse(startTime);
                                    date1 = sdf.parse(endTime);
                                    long millis = date.getTime();
                                    long millis1 = date1.getTime();
                                    txt_date.setText("start "+millis+" end "+millis1);
                                } catch (ParseException e) {
                                    System.out.println(e);
                                    e.printStackTrace();
                                }
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
               // String myDate = "2014/10/29 18:10:45";


            }
        });

    }
    public void getSalesData(){
        Toast.makeText(this, "get data", Toast.LENGTH_SHORT).show();
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, "https://nodemcu-a0d8a-default-rtdb.firebaseio.com/sensor.json", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sales.setText(response.length());
                System.out.println("response"+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SalesReportActivity.this, "error "+error, Toast.LENGTH_SHORT).show();
                System.out.println("Error: "+error);
            }
        });
        Volley.newRequestQueue(this).add(request);
    }
    public void salesData(){
        //sales.setText("500");
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, "https://nodemcu-a0d8a-default-rtdb.firebaseio.com/sensor.json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(WaterLevelActivity.this, response.get("waterlevel").toString(), Toast.LENGTH_SHORT).show();
                JSONArray jsonArray = new JSONArray();
//simply put obj into jsonArray
                jsonArray.put(response);
                try {
                    sales.setText(jsonArray.get(0).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("response"+jsonArray.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SalesReportActivity.this, "get data fail"+error, Toast.LENGTH_SHORT).show();
                System.out.println(error);
            }
        });
        Volley.newRequestQueue(this).add(request);
    }
}