package com.example.btl_nhom7.teacher_view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_nhom7.R;
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
    ArrayList<Student> students = new ArrayList<>();
    String classID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        registerForContextMenu(binding.lvStudents);
        startTimeCalendar = Calendar.getInstance();
        endTimeCalendar = Calendar.getInstance();

        Intent intent = getIntent();
        classID = intent.getStringExtra("classID");
        String roomID = intent.getStringExtra("roomID");
        String startTime = intent.getStringExtra("startTime");

        SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
        DetailedAssignment assignment = sqlHelper.getDetailedAssignment(classID, roomID, startTime);
        students = sqlHelper.getStudentsInClassWithRating(classID, 1);

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
           Intent intent1 = new Intent(this, AssignmentActivity.class);
           intent1.putExtra("classID", classID);
           startActivity(intent1);
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

    @Override
    protected void onStart() {
        refreshData();
        super.onStart();
    }

    private void showTimePicker(Calendar calendar, TextView timeTextView) {
        // Tạo TimePickerDialog với định dạng 24 giờ
        new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            // Cập nhật Calendar với giờ và phút được chọn
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            // Cập nhật TextView với định dạng 24 giờ
            timeTextView.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show(); // `true` để sử dụng định dạng 24 giờ
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId()==R.id.delete){
            confirmDelete(info.position);
        }
        return super.onContextItemSelected(item);
    }

    private void confirmDelete(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có muốn xóa sinh viên này khỏi danh sách trực nhật không?")
                .setPositiveButton("Có", (dialog, which) -> deleteStudent(position))
                .setNegativeButton("Hủy", null)
                .show();
    }
    private void deleteStudent(int position) {
        Student student = students.get(position);
        SqlHelper sqlHelper = new SqlHelper(getApplicationContext());

        // Update the student's rating in the database
        sqlHelper.updateStudentRating(student.getIdStudent(), classID, 0 );

        // Optionally remove the student from the list and update the adapter
        students.remove(position);
refreshData();

        Toast.makeText(this, "Xóa sinh viên thành công", Toast.LENGTH_SHORT).show();
    }


    private void refreshData() {
        SqlHelper sqlHelper = new SqlHelper(getApplicationContext());
        students = sqlHelper.getStudentsInClassWithRating(classID, 1);
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        binding.lvStudents.setAdapter(adapter);
    }

}
