package com.example.btl_nhom7;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom7.database.AssignmentHelper;
import com.example.btl_nhom7.database.ClassHelper;
import com.example.btl_nhom7.database.ClassStudentHelper;
import com.example.btl_nhom7.database.RoomHelper;
import com.example.btl_nhom7.database.StudentHelper;
import com.example.btl_nhom7.database.TeacherHelper;
import com.example.btl_nhom7.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ClassHelper classHelper = new ClassHelper(this);
        RoomHelper roomHelper = new RoomHelper(this);
        StudentHelper studentHelper = new StudentHelper(this);
        TeacherHelper teacherHelper = new TeacherHelper(this);
        AssignmentHelper assignmentHelper = new AssignmentHelper(this);
        ClassStudentHelper classStudentHelper = new ClassStudentHelper(this);
        studentHelper.getWritableDatabase();
        teacherHelper.getWritableDatabase();
        classHelper.getWritableDatabase();
        classStudentHelper.getWritableDatabase();
        roomHelper.getWritableDatabase();
        assignmentHelper.getWritableDatabase();

    }


}