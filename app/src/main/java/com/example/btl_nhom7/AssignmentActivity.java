package com.example.btl_nhom7;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AssignmentActivity extends AppCompatActivity {

    private EditText etMSV, etName;
    private ListView lvStudents;
    private ArrayAdapter<String> adapter;
    private String[] students = {
            "01 - 2021802743 - Lý Hải Hưng",
            "02 - 2021802000 - Trần Minh Hiếu"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_cong);

        etMSV = findViewById(R.id.etMSV);
        etName = findViewById(R.id.etName);
        lvStudents = findViewById(R.id.lvStudents);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        lvStudents.setAdapter(adapter);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button
                finish();
            }
        });

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete button
                etMSV.setText("");
                etName.setText("");
            }
        });

        Button btnAssign = findViewById(R.id.btnAssign);
        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle assign button
                String msv = etMSV.getText().toString();
                String name = etName.getText().toString();
                if (!msv.isEmpty() && !name.isEmpty()) {
                    String student = msv + " - " + name;
                    adapter.add(student);
                    adapter.notifyDataSetChanged();
                    etMSV.setText("");
                    etName.setText("");
                }
            }
        });
    }
}