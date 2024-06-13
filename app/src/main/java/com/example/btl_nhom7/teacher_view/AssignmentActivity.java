package com.example.btl_nhom7.teacher_view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom7.R;
import com.example.btl_nhom7.database.SqlHelper;
import com.example.btl_nhom7.databinding.ActivityAssignmentBinding;
import com.example.btl_nhom7.model.Student;

import java.util.ArrayList;

public class AssignmentActivity extends AppCompatActivity {
    ActivityAssignmentBinding binding;
    ArrayList<Student> students = new ArrayList<>();
    ArrayAdapter<Student> studentArrayAdapter;
    SqlHelper sqlHelper;
    String classID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        classID = intent.getStringExtra("classID");
        sqlHelper = new SqlHelper(getApplicationContext());
        refreshStudentList();

        binding.lvStudents.setOnItemClickListener((parent, view, position, id) -> {
            Student selectedStudent = students.get(position);
            binding.etMSV.setText(selectedStudent.getIdStudent());
            binding.etName.setText(selectedStudent.getName());
        });

        binding.btnAssign.setOnClickListener(v -> {
            String studentId = binding.etMSV.getText().toString();
            if (!studentId.isEmpty()) {
                sqlHelper.updateStudentRating(studentId, classID, 1);
                refreshStudentList();
                Toast.makeText(this, "Phân công thành công", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnBack.setOnClickListener(v -> {
            setResult(RESULT_OK); // Set RESULT_OK to trigger refresh in the previous activity
            finish();
        });

    }
    private void refreshStudentList() {
        students = sqlHelper.getStudentsInClassWithRating(classID, 0);
        studentArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        binding.lvStudents.setAdapter(studentArrayAdapter);
    }
}
