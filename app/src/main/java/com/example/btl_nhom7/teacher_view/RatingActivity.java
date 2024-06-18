package com.example.btl_nhom7.teacher_view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom7.R;
import com.example.btl_nhom7.database.SqlHelper;
import com.example.btl_nhom7.model.DetailedAssignment;
import com.example.btl_nhom7.model.Student;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    com.example.btl_nhom7.databinding.ActivityRatingBinding binding;
    ArrayList<Student> students = new ArrayList<>();
    String classID, roomID, startTime, date;
    int isRated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.btl_nhom7.databinding.ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set back button listener
        binding.btnBack.setOnClickListener(v -> finish());

        // Get data from intent
        Intent intent = getIntent();
        classID = intent.getStringExtra("classID");
        roomID = intent.getStringExtra("roomID");
        startTime = intent.getStringExtra("startTime");
        isRated = intent.getIntExtra("isRated", 0);
        Log.d("RatingActivity", "isRated value: " + isRated);
        int roomType = intent.getIntExtra("roomType", 0);
        String task = intent.getStringExtra("task");
        date = intent.getStringExtra("date");
        // Update UI based on retrieved data
        updateCheckBoxes(roomType);
        binding.txtTask.setText(task);
        if(isRated == 1){
            binding.rdIsRated.setChecked(true);
            disableRatingControls();
        }


        // Load students
        SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
        DetailedAssignment detailedAssignment = sqlHelper.getDetailedAssignment(classID, roomID, startTime, date);
        if (detailedAssignment != null) {
            binding.txtTask.setText(detailedAssignment.getTask());
            binding.txtReason.setText(detailedAssignment.getNote()); // Displaying the note
            if (detailedAssignment.getIsRated() == 1) {
                binding.rdIsRated.setChecked(true);
                disableRatingControls();
            }
        } else {
            Log.d("RatingActivity", "No detailed assignment found");
        }
        students = sqlHelper.getStudentsInClassWithRating(classID, 1);
        students.forEach(student -> binding.txtStudent.append(student.toString() + "\n"));

        // Handle rating button click
        binding.btnRating.setOnClickListener(v -> handleRatingSubmission(sqlHelper));
    }



    private void handleRatingSubmission(SqlHelper sqlHelper) {
        boolean allDevicesChecked = binding.cbDevice1.isChecked() && binding.cbDevice2.isChecked() &&
                binding.cbDevice3.isChecked() && binding.cbDevice4.isChecked();

        if (isRated == 0) { // Check if the session has not been rated
            if (!students.isEmpty()) {
                if (allDevicesChecked && binding.rdComplete.isChecked()) {
                    updateStudentRating(2, sqlHelper);
                } else if (binding.rdComplete.isChecked()) {
                    Toast.makeText(this, "Kiểm tra tất cả thiết bị.", Toast.LENGTH_SHORT).show();
                } else {
                    updateStudentRating(0, sqlHelper);
                }
            } else {
                Toast.makeText(this, "Không sinh viên nào hoàn thành nhiệm vụ.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ca trực nhật này đã được đánh giá.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStudentRating(int newRating, SqlHelper sqlHelper) {
        String note = binding.txtReason.getText().toString(); // Get the note from the UI
        for (Student student : students) {
            sqlHelper.updateStudentRating(student.getIdStudent(), classID, newRating);
        }
        updateAssignmentDetails(sqlHelper, newRating); // Pass note to the assignment update
        Toast.makeText(this, "Ratings and notes updated successfully.", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void updateAssignmentDetails(SqlHelper sqlHelper, int newRating) {
        String note = binding.txtReason.getText().toString();
        sqlHelper.updateAssignment(classID, date,  startTime, roomID, note,1 ); // Mark as rated
        Toast.makeText(this, "Ratings updated successfully.", Toast.LENGTH_SHORT).show();
    }

    private void updateCheckBoxes(int roomType) {
        String[] devices = roomType == 0 ? new String[]{"Điều hòa", "Quạt", "Máy chiếu", "Đèn"} :
                new String[]{"Máy tính", "Điều hòa", "Máy chiếu", "Đèn"};
        binding.cbDevice1.setText(devices[0]);
        binding.cbDevice2.setText(devices[1]);
        binding.cbDevice3.setText(devices[2]);
        binding.cbDevice4.setText(devices[3]);
    }
    private void disableRatingControls() {
        binding.btnRating.setEnabled(false); // Disable the rating button
        // Optionally disable other interactive elements like checkboxes, etc.
        binding.cbDevice1.setEnabled(false);
        binding.cbDevice2.setEnabled(false);
        binding.cbDevice3.setEnabled(false);
        binding.cbDevice4.setEnabled(false);
        binding.txtTask.setEnabled(false);
        binding.txtStudent.setEnabled(false);
        binding.rdComplete.setEnabled(false);
        binding.rdNotComplete.setEnabled(false);
        binding.txtReason.setEnabled(false);
    }

}
