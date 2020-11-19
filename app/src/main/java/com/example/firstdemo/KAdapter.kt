package com.example.firstdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class KAdapter(private val list: List<ImgAndTxt>) :
        RecyclerView.Adapter<KAdapter.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_FIRST = 1
        const val ITEM_TYPE_SECOND = 2
    }

    var mLayoutFlag: Boolean = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == ITEM_TYPE_FIRST) {
            ViewHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.recycler_item, parent, false))
        } else {
            ViewHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.recycler_item_sec, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position].img, list[position].text)
        holder.itemView.item_line.setOnClickListener {
            Toast.makeText(holder.itemView.context,
                    "点击了第" + holder.adapterPosition + "个item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (mLayoutFlag && position % 3 == 0) {
            ITEM_TYPE_SECOND
        } else {
            ITEM_TYPE_FIRST
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(img: Int, text: String) {
            itemView.img_view.setImageResource(img)
            itemView.txt_view.text = text
        }
    }

}