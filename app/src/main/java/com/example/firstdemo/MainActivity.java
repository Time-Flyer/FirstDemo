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

    private final int LAYOUT_FLAG_LINER_LAYOUT = 0;
    private final int LAYOUT_FLAG_GRID_LAYOUT = 1;
    private final int LAYOUT_FLAG_STAGGERED_GRID_LAYOUT = 2;

    private static int layoutFlag = 0;

    private Button btn_add_data;
    private Button btn_change_layout;
    private Button btn_insert_data;
    private Button btn_del_data;

    //todo 全局变量可以使用mDataList、mAdapter、mRecyclerView等
    //todo 局部变量可以使用dataList、adapter等
    private final List<ImgAndTxt> mDataList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private KAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
        initEvents();
    }

    private void initEvents() {
        btn_add_data.setOnClickListener(v -> {
            if (mAdapter == null) {
                mAdapter = new KAdapter(mDataList);
                mAdapter.setMLayoutFlag(false);
//                mAdapter.setOnItemClickListener((view, pos) -> {
//                    Toast.makeText(this, "点击了第" + pos + "个item", Toast.LENGTH_SHORT).show();
//                });
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        btn_change_layout.setOnClickListener(v -> {
            if (mAdapter == null) {
                Toast.makeText(this, "请先添加数据", Toast.LENGTH_SHORT).show();
            } else {
                //todo 尽量少用魔术数，用具体化的变量代表，比如LAYOUT_FLAG_GRIDLAYOUT等，方便阅读理解
                if (layoutFlag == LAYOUT_FLAG_LINER_LAYOUT) {
                    mAdapter.setMLayoutFlag(false);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                    layoutFlag = LAYOUT_FLAG_GRID_LAYOUT;
                } else if (layoutFlag == LAYOUT_FLAG_GRID_LAYOUT) {
                    mAdapter.setMLayoutFlag(true);
                    mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    layoutFlag = LAYOUT_FLAG_STAGGERED_GRID_LAYOUT;
                } else {
                    mAdapter.setMLayoutFlag(false);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    layoutFlag = LAYOUT_FLAG_LINER_LAYOUT;
                }
            }
        });

        btn_insert_data.setOnClickListener(v -> {
            if (mAdapter == null) {
                Toast.makeText(this, "请先添加数据", Toast.LENGTH_SHORT).show();
            } else {
                ImgAndTxt data = new ImgAndTxt();
                data.setImg(R.drawable.wangwang);
                data.setText("新建item");
                //todo 这里直接add，但是你前面的dataList没有初始化，会发生NPE
                mDataList.add(3, data);
                mAdapter.notifyDataSetChanged();
            }
        });

        btn_del_data.setOnClickListener(v -> {
            //todo 数据使用的时候，不进行判断直接进行crud操作，容易出现错误
            //这里直接下标操作，发生越界错误，崩溃
            if (mAdapter == null) {
                Toast.makeText(this, "请先添加数据", Toast.LENGTH_SHORT).show();
            } else if (mDataList.size() > 3 && mDataList.get(3).getText().equals("新建item")) {
                mDataList.remove(3);
                mAdapter.notifyItemRemoved(3); // TODO 如果没有这句，在 GridLayout 中会闪退，但其他两个又没问题
                mAdapter.notifyItemRangeChanged(3, mDataList.size() - 3);
            }
        });
    }

    private void initViews() {
        btn_add_data = findViewById(R.id.btn_add_data);
        btn_change_layout = findViewById(R.id.btn_change_layout);
        btn_insert_data = findViewById(R.id.btn_insert_data);
        btn_del_data = findViewById(R.id.btn_del_data);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
//        dataList = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            //todo 这里可以也考虑直接传递构造参数
            ImgAndTxt data = new ImgAndTxt();
            if (i % 2 == 0) {
                data.setImg(R.drawable.tom);
            } else {
                data.setImg(R.drawable.xiaoxin);
            }
            data.setText("第" + (i) + "条数据");
            mDataList.add(data);
        }
    }

}