package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;


public class DashboardActivity extends AppCompatActivity {
    private Button mPickDateButton;

    private TextView mShowSelectedDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mPickDateButton = findViewById(R.id.pick_date_button);
        mShowSelectedDateText = findViewById(R.id.show_selected_date);
        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();

        // now define the properties of the
        // materialDateBuilder
        materialDateBuilder.setTitleText("SELECT A DATE");

        // now create the instance of the material date
        // picker
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        // handle select date button which opens the
        // material design date picker
        mPickDateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // getSupportFragmentManager() to
                        // interact with the fragments
                        // associated with the material design
                        // date picker tag is to get any error
                        // in logcat
                        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });

        // now handle the positive button click from the
        // material design date picker
        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {

                        // if the user clicks on the positive
                        // button that is ok button update the
                        // selected date
                        mShowSelectedDateText.setText("Selected Date is : " + materialDatePicker.getHeaderText());
                        // in the above statement, getHeaderText
                        // will return selected date preview from the
                        // dialog
                    }
                });




    }


}