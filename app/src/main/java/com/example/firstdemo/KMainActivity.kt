package com.example.firstdemo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class KMainActivity : AppCompatActivity() {

    companion object {
        const val LAYOUT_FLAG_LINER_LAYOUT = 0
        const val LAYOUT_FLAG_GRID_LAYOUT = 1
        const val LAYOUT_FLAG_STAGGERED_GRID_LAYOUT = 2
    }

    private var mLayoutFlag: Int = 0

    private val mDataList = ArrayList<ImgAndTxt>()
    private val mAdapter = KAdapter(mDataList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = mAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        initEvents()
    }

    private fun initEvents() {
        btn_add_data.setOnClickListener {
            if (recycler_view.visibility == View.GONE) {
                recycler_view.visibility = View.VISIBLE
            }
        }

        btn_change_layout.setOnClickListener {
            if (recycler_view.visibility == View.GONE) {
                Toast.makeText(this, "请先添加数据", Toast.LENGTH_SHORT).show()
            } else {
                when (mLayoutFlag) {
                    LAYOUT_FLAG_LINER_LAYOUT -> {
                        mAdapter.mLayoutFlag = false
                        recycler_view.layoutManager = GridLayoutManager(this, 2)
                        mLayoutFlag = LAYOUT_FLAG_GRID_LAYOUT
                    }
                    LAYOUT_FLAG_GRID_LAYOUT -> {
                        mAdapter.mLayoutFlag = true
                        recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        mLayoutFlag = LAYOUT_FLAG_STAGGERED_GRID_LAYOUT
                    }
                    else -> {
                        mAdapter.mLayoutFlag = false
                        recycler_view.layoutManager = LinearLayoutManager(this)
                        mLayoutFlag = LAYOUT_FLAG_LINER_LAYOUT
                    }
                }
            }
        }

        btn_insert_data.setOnClickListener {
            if (recycler_view.visibility == View.GONE) {
                Toast.makeText(this, "请先添加数据", Toast.LENGTH_SHORT).show()
            } else {
                val data = ImgAndTxt().apply {
                    img = R.drawable.wangwang
                    text = "新建item"
                }
                mDataList.add(3, data)
                mAdapter.notifyDataSetChanged()
            }
        }

        btn_del_data.setOnClickListener {
            if (recycler_view.visibility == View.GONE) {
                Toast.makeText(this, "请先添加数据", Toast.LENGTH_SHORT).show()
            } else if (mDataList.size > 3 && mDataList[3].text == "新建item") {
                mDataList.removeAt(3)
                mAdapter.notifyItemRemoved(3)
                mAdapter.notifyItemRangeChanged(3, mDataList.size - 3)
            }
        }
    }

    private fun initData() {
        for (i in 0 until 21) {
            val data = ImgAndTxt()
            if (i % 2 == 0) {
                data.img = R.drawable.tom
            } else {
                data.img = R.drawable.xiaoxin
            }
            data.text = "第" + (i) + "条数据"
            mDataList.add(data)
        }
    }
}