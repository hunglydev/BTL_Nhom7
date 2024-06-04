package com.example.btl_nhom7;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class Detail extends AppCompatActivity {

    private TextView tvStartTime, tvEndTime;
    private Button btnStartTime, btnEndTime;

    private Calendar startTimeCalendar, endTimeCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvStartTime = (TextView) findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        btnStartTime = findViewById(R.id.btnStartTime);
        btnEndTime = findViewById(R.id.btnEndTime);
        startTimeCalendar = Calendar.getInstance();
        endTimeCalendar = Calendar.getInstance();

        ListView lvStudents = findViewById(R.id.lvStudents);
        String[] students = {"Trần Minh Hiếu","Lý Hải Hưng"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        lvStudents.setAdapter(adapter);
        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = startTimeCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = startTimeCalendar.get(Calendar.MINUTE);
                new TimePickerDialog(Detail.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        startTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        startTimeCalendar.set(Calendar.MINUTE, minute);
                        tvStartTime.setText(String.format("%02d:%02d %s", hourOfDay % 12 == 0 ? 12 : hourOfDay % 12, minute, hourOfDay >= 12 ? "PM" : "AM"));
                    }
                }, hour, minute, false).show();
            }
        });

        btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = endTimeCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = endTimeCalendar.get(Calendar.MINUTE);
                new TimePickerDialog(Detail.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        endTimeCalendar.set(Calendar.MINUTE, minute);
                        tvEndTime.setText(String.format("%02d:%02d %s", hourOfDay % 12 == 0 ? 12 : hourOfDay % 12, minute, hourOfDay >= 12 ? "PM" : "AM"));
                    }
                }, hour, minute, false).show();
            }
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button
            }
        });

        Button btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle confirm button
            }
        });
    }
}

