package com.example.firstdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_add_data;
    private Button btn_change_layout;
    private Button btn_insert_data;
    private Button btn_del_data;

    private static int layoutFlag = 0;

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
                adapter.setLayoutFlag(false);
                adapter.setOnItemClickListener((view, pos) -> {
                    Toast.makeText(this, "点击了第" + pos + "个item", Toast.LENGTH_SHORT).show();
                });
                recyclerView.setAdapter(adapter);
            }
        });

        btn_change_layout.setOnClickListener(v -> {
            if (recyclerView == null) {
                Toast.makeText(this, "请先添加数据", Toast.LENGTH_SHORT).show();
            } else {
                if (layoutFlag == 0) {
                    adapter.setLayoutFlag(false);
                    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                    layoutFlag = 1;
                } else if (layoutFlag == 1) {
                    adapter.setLayoutFlag(true);
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    layoutFlag = 2;
                } else {
                    adapter.setLayoutFlag(false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    layoutFlag = 0;
                }
            }
        });

        btn_insert_data.setOnClickListener(v -> {
            ImgAndTxt data = new ImgAndTxt();
            data.setImg(R.drawable.wangwang);
            data.setText("新建item");
            dataList.add(3, data);
            adapter.notifyDataSetChanged();
        });

        btn_del_data.setOnClickListener(v -> {
            if (dataList.get(3).getText().equals("新建item")) {
                dataList.remove(3);
                adapter.notifyDataSetChanged();
            }
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
        for (int i = 0; i <= 20; i++) {
            ImgAndTxt data = new ImgAndTxt();
            if (i % 2 == 0) {
                data.setImg(R.drawable.tom);
            } else {
                data.setImg(R.drawable.xiaoxin);
            }
            data.setText("第" + (i+1) + "条数据");
            dataList.add(data);
        }
    }

}