package com.example.firstdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_add_data;
    private Button btn_change_layout;
    private Button btn_insert_data;
    private Button btn_del_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnListener();
    }

    private void btnListener() {
        btn_add_data.setOnClickListener(v -> {
            Toast.makeText(this, "按钮1", Toast.LENGTH_SHORT).show();
        });

        btn_change_layout.setOnClickListener(v -> {
            Toast.makeText(this, "按钮2", Toast.LENGTH_SHORT).show();
        });

        btn_insert_data.setOnClickListener(v -> {
            Toast.makeText(this, "按钮3", Toast.LENGTH_SHORT).show();
        });

        btn_del_data.setOnClickListener(v -> {
            Toast.makeText(this, "按钮4", Toast.LENGTH_SHORT).show();
        });
    }

    private void init() {
        btn_add_data = findViewById(R.id.btn_add_data);
        btn_change_layout = findViewById(R.id.btn_change_layout);
        btn_insert_data = findViewById(R.id.btn_insert_data);
        btn_del_data = findViewById(R.id.btn_del_data);
    }


}