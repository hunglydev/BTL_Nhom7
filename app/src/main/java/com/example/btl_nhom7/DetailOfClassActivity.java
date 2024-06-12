package com.example.btl_nhom7;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.btl_nhom7.adapter.AssignmentAdapter;
import com.example.btl_nhom7.database.SqlHelper;
import com.example.btl_nhom7.databinding.ActivityDetailOfClassBinding;
import com.example.btl_nhom7.model.Assignment;
import java.util.ArrayList;
public class DetailOfClassActivity extends AppCompatActivity {
    ActivityDetailOfClassBinding binding;
    ArrayList<Assignment> assignments = new ArrayList<>();
    ArrayList<String> classIDs = new ArrayList<>();
    SqlHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailOfClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String studentId = intent.getStringExtra("data"); // Ensure this key matches what's being passed from LoginActivity.

        sqlHelper = new SqlHelper(getApplicationContext());
        classIDs = sqlHelper.getClassOfStudent(studentId); // Assuming this method correctly fetches the class IDs.

        if (!classIDs.isEmpty()) {
            setupRecyclerView(classIDs.get(0)); // Setup RecyclerView with the first class ID
            binding.tvTitle.setText("Chi tiết Lớp: " + classIDs.get(0)); // Set the title to the first class
        }
        else{
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
                // Handle the click event with itemView and position
                Intent intent = new Intent(DetailOfClassActivity.this, DetailActivity.class);
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

        // Dynamically add menu items based on class IDs
        menu.clear();
        for (int i = 0; i < classIDs.size(); i++) {
            menu.add(0, Menu.FIRST + i, Menu.NONE, "Lớp " + classIDs.get(i));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection by class IDs
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

