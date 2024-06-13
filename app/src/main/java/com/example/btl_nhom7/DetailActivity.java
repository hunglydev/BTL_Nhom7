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
        disableButton();
        tvStartTime = (TextView) findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        startTimeCalendar = Calendar.getInstance();
        endTimeCalendar = Calendar.getInstance();
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
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

    }
    private void disableButton(){
        binding.rbPractice.setClickable(false);
        binding.rbTheory.setClickable(false);
        binding.cbDevice1.setClickable(false);
        binding.cbDevice2.setClickable(false);
        binding.cbDevice3.setClickable(false);
        binding.cbDevice4.setClickable(false);
    }
}

