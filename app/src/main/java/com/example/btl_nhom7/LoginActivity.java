package com.example.btl_nhom7;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btl_nhom7.database.SqlHelper;
import com.example.btl_nhom7.databinding.ActivityLoginBinding;
import com.example.btl_nhom7.model.Student;
import com.example.btl_nhom7.model.Teacher;
import com.example.btl_nhom7.teacher_view.TeacherDetailClassActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ArrayList<Student> studentList = new ArrayList<>();
    ArrayList<Teacher> teachersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
       // sqlHelper.insertSampleData(db);
        studentList = sqlHelper.getAllStudent();
        teachersList = sqlHelper.getAllTeacher();
        binding.btnLogin.setOnClickListener(v -> {
            String username = binding.txtUsername.getText().toString();
            String password = binding.txtPassword.getText().toString();
            Student student = sqlHelper.checkStudentLogin(username, password);

            if (student != null) {
                Intent intent = new Intent(this, DetailOfClassActivity.class);

                Toast.makeText(this, "Logged in as Student", Toast.LENGTH_SHORT).show();
                intent.putExtra("data", student.getIdStudent());
                startActivity(intent); // Change to your student activity
                return;
            }

            // Check if the user is a teacher
            Teacher teacher = sqlHelper.checkTeacherLogin(username, password);
            if (teacher != null) {
                Toast.makeText(this, "Logged in as Teacher", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, TeacherDetailClassActivity.class);
                intent.putExtra("data", teacher.getId());
                startActivity(intent); // Change to your teacher activity
                return;
            }
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        });
    }


}