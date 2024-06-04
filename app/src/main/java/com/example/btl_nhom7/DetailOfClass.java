package com.example.btl_nhom7;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailOfClass extends AppCompatActivity {

    private Button btnLogout;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_class);

        btnLogout = findViewById(R.id.btn_logout);
        tvTitle = findViewById(R.id.tv_title);

        // Set logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailOfClass.this, "Logged out", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.class_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.class1) {
            tvTitle.setText("Chi tiết Lớp 1");
            return true;
        } else if (itemId == R.id.class2) {
            tvTitle.setText("Chi tiết Lớp 2");
            return true;
        } else if (itemId == R.id.class3) {
            tvTitle.setText("Chi tiết Lớp 3");
            return true;
        } else if (itemId == R.id.class4) {
            tvTitle.setText("Chi tiết Lớp 4");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
