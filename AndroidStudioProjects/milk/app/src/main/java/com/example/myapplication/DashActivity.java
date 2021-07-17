package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


import java.util.Objects;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;
import pl.pawelkleczkowski.customgauge.CustomGauge;
;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;
import pl.pawelkleczkowski.customgauge.CustomGauge;

public class DashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
      CustomGauge  customGauge = (CustomGauge) findViewById(R.id.gauge2);
       PieView pieView_soil = (PieView) findViewById(R.id.pieView_soil);
        TextView txt_sales =  (TextView) findViewById(R.id.salesview);
        TextView txt_temp =  (TextView) findViewById(R.id.text1);

        Firebase.setAndroidContext(this);

        final Firebase mRef1 = new Firebase("https://nodemcu-a0d8a-default-rtdb.firebaseio.com/DHT11/Temperature");
        Firebase mRef2 = new Firebase("https://nodemcu-a0d8a-default-rtdb.firebaseio.com/ULTRA/Distance");

        pieView_soil.setPercentageBackgroundColor(getResources().getColor(R.color.brown));


        txt_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashActivity.this,SalesReportActivity.class);
                startActivity(i);
            }
        });
        mRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                float i = Float.parseFloat(value);
                pieView_soil.setPieInnerPadding(30);
                pieView_soil.setPercentageTextSize(20);
                pieView_soil.setPercentage(i);
                PieAngleAnimation animation = new PieAngleAnimation(pieView_soil);
                animation.setDuration(2000); //This is the duration of the animation in millis
                pieView_soil.startAnimation(animation);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txt_temp.setText(value+"°C");
                int i=Integer.parseInt(value.replaceAll("[\\D]", ""));
                customGauge.setValue(i*10);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
}