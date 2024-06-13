package com.example.btl_nhom7.teacher_view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_nhom7.R;
import com.example.btl_nhom7.database.SqlHelper;
import com.example.btl_nhom7.model.DetailedAssignment;
import com.example.btl_nhom7.model.Student;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    com.example.btl_nhom7.databinding.ActivityRatingBinding binding;
    ArrayList<Student> students = new ArrayList<>();
    String classID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.btl_nhom7.databinding.ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnBack.setOnClickListener(v -> {
            finish();
        });
        Intent intent = getIntent();
         classID = intent.getStringExtra("classID");
//        String className = intent.getStringExtra("className");
//        String date = intent.getStringExtra("date");
//        String startTime = intent.getStringExtra("startTime");
//        String endTime = intent.getStringExtra("endTime");
//        String roomName = intent.getStringExtra("roomName");
        int roomType = intent.getIntExtra("roomType",0); //
        String task = intent.getStringExtra("task");
     //   String roomID = intent.getStringExtra("roomID");
        updateCheckBoxes(roomType);
        binding.txtTask.setText(task);
        SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
      students= sqlHelper.getStudentsInClassWithRating(classID, 1);
        students.forEach(student -> {
            binding.txtStudent.append(student.toString() + "\n");
        });
        binding.rdNotComplete.setChecked(true);
        binding.btnRating.setOnClickListener(v -> {
            boolean allDevicesChecked = binding.cbDevice1.isChecked() && binding.cbDevice2.isChecked() &&
                    binding.cbDevice3.isChecked() && binding.cbDevice4.isChecked();
            if(students.size()>0){
                if(allDevicesChecked && binding.rdComplete.isChecked()){
                    updateStudentRating(2);
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (binding.rdComplete.isChecked() && binding.txtReason.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Vui lòng tích đầy đủ các thiết bị", Toast.LENGTH_SHORT).show();
                } else{
                    updateStudentRating(0);
                    Toast.makeText(this, "Sinh viên phải trực nhật lại", Toast.LENGTH_SHORT).show();
                    finish();

                }
            } else{
                Toast.makeText(this, "Vui lòng phân công sinh viên trực nhật", Toast.LENGTH_SHORT).show();
            }


        });

    }


    private void updateCheckBoxes(int roomType) {

        if (roomType == 0) {
            binding.cbDevice1.setText("Điều hòa");
            binding.cbDevice2.setText("Quạt");
            binding.cbDevice3.setText("Máy chiếu");
            binding.cbDevice4.setText("Đèn");
        } else {
            binding.cbDevice1.setText("Máy tính");
            binding.cbDevice2.setText("Điều hòa");
            binding.cbDevice3.setText("Máy chiếu");
            binding.cbDevice4.setText("Đèn");
        }
    }
    private void updateStudentRating(int newRating) {
        SqlHelper sqlHelper = new SqlHelper(this);
        for (Student student : students) {
            sqlHelper.updateStudentRating(student.getIdStudent(), classID, newRating);
        }
        finish();  // Optionally close the activity
    }

}