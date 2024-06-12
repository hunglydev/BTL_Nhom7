package com.example.btl_nhom7;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom7.database.SqlHelper;
import com.example.btl_nhom7.databinding.ActivityDetailBinding;
import com.example.btl_nhom7.model.DetailedAssignment;
import com.example.btl_nhom7.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private TextView tvStartTime, tvEndTime;
    private Button btnStartTime, btnEndTime;

    private Calendar startTimeCalendar, endTimeCalendar;
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvStartTime = (TextView) findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        btnStartTime = findViewById(R.id.btnStartTime);
        btnEndTime = findViewById(R.id.btnEndTime);
        startTimeCalendar = Calendar.getInstance();
        endTimeCalendar = Calendar.getInstance();

        Intent intent = getIntent();
        String classID = intent.getStringExtra("classID");
        String roomID = intent.getStringExtra("roomID");
        String startTime = intent.getStringExtra("startTime");
        SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
        ArrayList<Student> students = sqlHelper.getStudentsInClassWithRating(classID,1);
        DetailedAssignment assignment = sqlHelper.getDetailedAssignment(classID, roomID, startTime);
        if (assignment != null) {
            // Update your UI with the information from assignment
            binding.edClass.setText(assignment.getClassName());
            binding.txtSeletedDate.setText(assignment.getDay());
            binding.tvStartTime.setText(assignment.getStartTime());
            binding.tvEndTime.setText(assignment.getEndTime());
            binding.etRoom.setText(assignment.getRoomName());
            if(assignment.getRoomType()==0){
                binding.rbTheory.setChecked(true);
                binding.rbPractice.setChecked(false);
            }
            else{

                binding.rbPractice.setChecked(true);binding.rbTheory.setChecked(false);
            }
        }
        ListView lvStudents = findViewById(R.id.lvStudents);
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        lvStudents.setAdapter(adapter);
        lvStudents.setAdapter(adapter);
        binding.btnSelectDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and show it
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // Use the selected date as you need
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    // Format the date and set it to TextView
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    binding.txtSeletedDate.setText(dateFormat.format(calendar.getTime()));
                }
            }, year, month, day);

            datePickerDialog.show();
        });
        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = startTimeCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = startTimeCalendar.get(Calendar.MINUTE);
                new TimePickerDialog(DetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                new TimePickerDialog(DetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
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

