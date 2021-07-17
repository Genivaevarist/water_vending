package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;



public class Daily extends Fragment {
    private Button mPickDateButton;

    private TextView mShowSelectedDateText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final  View root = inflater.inflate(R.layout.daily_layaout,container,false);

     return root;
    }
}
    // Inflate the layout for this fragment.
    // Inflate the layout for this fragment.
