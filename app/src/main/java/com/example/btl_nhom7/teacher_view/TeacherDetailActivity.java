package com.example.btl_nhom7.teacher_view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom7.database.SqlHelper;
import com.example.btl_nhom7.databinding.ActivityTeacherDetailBinding;
import com.example.btl_nhom7.model.DetailedAssignment;
import com.example.btl_nhom7.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TeacherDetailActivity extends AppCompatActivity {

    ActivityTeacherDetailBinding binding;
    private Calendar startTimeCalendar, endTimeCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startTimeCalendar = Calendar.getInstance();
        endTimeCalendar = Calendar.getInstance();

        Intent intent = getIntent();
        String classID = intent.getStringExtra("classID");
        String roomID = intent.getStringExtra("roomID");
        String startTime = intent.getStringExtra("startTime");

        SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
        DetailedAssignment assignment = sqlHelper.getDetailedAssignment(classID, roomID, startTime);
        ArrayList<Student> students = sqlHelper.getStudentsInClassWithRating(classID, 1);

        if (assignment != null) {
            binding.edClass.setText(assignment.getClassName());
            binding.txtSeletedDate.setText(assignment.getDay());
            binding.tvStartTime.setText(assignment.getStartTime());
            binding.tvEndTime.setText(assignment.getEndTime());
            binding.etRoom.setText(assignment.getRoomName());
            // Adjust radio buttons based on the room method
            binding.rbTheory.setChecked(assignment.getRoomType() == 0);
            binding.rbPractice.setChecked(assignment.getRoomType() == 1);
        }

        // Student list setup
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        binding.lvStudents.setAdapter(adapter);

        // Date picker setup
        binding.btnSelectDate.setOnClickListener(v -> showDatePicker());
        binding.btnStartTime.setOnClickListener(v -> showTimePicker(startTimeCalendar, binding.tvStartTime));
        binding.btnEndTime.setOnClickListener(v -> showTimePicker(endTimeCalendar, binding.tvEndTime));

        // Back and confirm buttons
        binding.btnBack.setOnClickListener(v -> finish());
        binding.btnConfirm.setOnClickListener(v -> {
            // Update the database with new details from editable fields
            // sqlHelper.updateAssignmentDetails(...);
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            binding.txtSeletedDate.setText(dateFormat.format(calendar.getTime()));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePicker(Calendar calendar, TextView timeTextView) {
        new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            timeTextView.setText(String.format(Locale.getDefault(), "%02d:%02d %s", hourOfDay % 12 == 0 ? 12 : hourOfDay % 12, minute, hourOfDay >= 12 ? "PM" : "AM"));
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }
}
