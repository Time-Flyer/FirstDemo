package com.example.firstdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    //这里使用了枚举，但是后续实际上也是用到它的序数进行判断。直接使用int类型常量即可
    public enum ITEM_TYPE {
        ITEM_TYPE_FIRST,
        ITEM_TYPE_SECOND
    }

    private boolean layoutFlag;

    private Context context;
    private final List<ImgAndTxt> dataList;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_FIRST.ordinal()) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item, parent, false));
        }
        else {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_sec, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(dataList.get(position).getImg());
        holder.textView.setText(dataList.get(position).getText());
        holder.itemLine.setOnClickListener(v -> {
            Log.i("pos", "layout pos " + position);
            Log.i("pos", "old pos " + holder.getOldPosition());
            Log.i("pos", "layout pos " + holder.getAdapterPosition());
            Log.i("pos", "layout pos " + holder.getLayoutPosition());
            listener.onItemClick(v, position);
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (layoutFlag && position % 3 == 0) {
            return ITEM_TYPE.ITEM_TYPE_SECOND.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_FIRST.ordinal();
        }
    }

    public Adapter(Context context, List<ImgAndTxt> list) {
        this.context = context;
        this.dataList = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setLayoutFlag(boolean flag) {
        this.layoutFlag = flag;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemLine;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLine = itemView.findViewById(R.id.item_line);
            imageView = itemView.findViewById(R.id.img_view);
            textView = itemView.findViewById(R.id.txt_view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }
}
