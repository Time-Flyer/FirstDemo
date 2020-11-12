package com.example.firstdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_add_data;
    private Button btn_change_layout;
    private Button btn_insert_data;
    private Button btn_del_data;

    private List<ImgAndTxt> dataList;

    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnListener();
    }

    private void btnListener() {
        btn_add_data.setOnClickListener(v -> {
            if (dataList == null) {
                initData();

                recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                adapter = new Adapter(this, dataList);
                recyclerView.setAdapter(adapter)
            }
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

    private void initView() {
        btn_add_data = findViewById(R.id.btn_add_data);
        btn_change_layout = findViewById(R.id.btn_change_layout);
        btn_insert_data = findViewById(R.id.btn_insert_data);
        btn_del_data = findViewById(R.id.btn_del_data);
    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            ImgAndTxt data = new ImgAndTxt();
            data.setImg("");
            data.setText("第" + i + "条数据");
            dataList.add(data);
        }
    }

}