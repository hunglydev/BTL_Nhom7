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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }
}