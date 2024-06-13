package com.example.btl_nhom7.teacher_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.btl_nhom7.DetailActivity;
import com.example.btl_nhom7.R;
import com.example.btl_nhom7.adapter.AssignmentAdapter;
import com.example.btl_nhom7.database.SqlHelper;
import com.example.btl_nhom7.databinding.ActivityTeacherDetailClassBinding;
import com.example.btl_nhom7.model.Assignment;

import java.util.ArrayList;

public class TeacherDetailClassActivity extends AppCompatActivity {
    ActivityTeacherDetailClassBinding binding;
    ArrayList<Assignment> assignments = new ArrayList<>();
    ArrayList<String> classIDs = new ArrayList<>();
    SqlHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherDetailClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String teacherId = intent.getStringExtra("data");

        sqlHelper = new SqlHelper(getApplicationContext());
        classIDs = sqlHelper.getClassesOfTeacher(teacherId);

        if (!classIDs.isEmpty()) {
            setupRecyclerView(classIDs.get(0));
            binding.tvTitle.setText("Chi tiết Lớp: " + classIDs.get(0));
        } else {
            binding.tvTitle.setText("Không có lớp nào");
        }

        binding.btnLogout.setOnClickListener(v -> {
            finish();
        });
    }

    private void setupRecyclerView(String classId) {
        ArrayList<Assignment> assignments = sqlHelper.getAllAssignmentOfClass(classId);
        AssignmentAdapter adapter = new AssignmentAdapter(this, new AssignmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(TeacherDetailClassActivity.this, TeacherDetailActivity.class);
                intent.putExtra("classID", assignments.get(position).getIdClass());
                intent.putExtra("roomID", assignments.get(position).getIdRoom());
                intent.putExtra("startTime", assignments.get(position).getStartTime());
                startActivity(intent);
            }
        });
        adapter.setData(assignments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rcvDetailOfClass.setLayoutManager(linearLayoutManager);
        binding.rcvDetailOfClass.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.class_menu, menu);

        menu.clear();
        for (int i = 0; i < classIDs.size(); i++) {
            menu.add(0, Menu.FIRST + i, Menu.NONE, "Lớp " + classIDs.get(i));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId() - Menu.FIRST;
        if (itemId >= 0 && itemId < classIDs.size()) {
            String selectedClassId = classIDs.get(itemId);
            binding.tvTitle.setText("Chi tiết Lớp: " + selectedClassId);
            setupRecyclerView(selectedClassId);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
