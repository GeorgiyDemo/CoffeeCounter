package com.demka.coffeecounter.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demka.coffeecounter.dbhelpers.BaseDbHelper;
import com.demka.coffeecounter.R;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Map<String, String>> dataList;
    Context context;
    BaseDbHelper dbHelper;

    public MyAdapter(Context context, BaseDbHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
        dataList = dbHelper.getData();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_item_list_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        dataList.get(position).get("name");
        holder.header.setText(dataList.get(position).get("id"));
        holder.content.setText(dataList.get(position).get("name"));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void remove(String data) {

        for (int i = 0; i < dataList.size(); i++) {
            if (Objects.equals(dataList.get(i).get("name"), data)) {
                remove(i);
                break;
            }
        }

    }

    public void remove(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataList.size());
    }

    public void addItem(Map<String, String> item) {

        int position = dataList.size();
        dataList.add(position, item);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon;
        TextView title;
        TextView caffeine;
        TextView amount;
        TextView timestamp;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.main_coffee_icon);
            title = itemView.findViewById(R.id.main_title);
            caffeine = itemView.findViewById(R.id.main_caffeine);
            amount = itemView.findViewById(R.id.main_amount);
            timestamp = itemView.findViewById(R.id.main_timestamp);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //TODO: тут переход на ItemActivity, дергаем элемент массива через getAdapterPosition();
            //remove(getAdapterPosition());
            //dbHelper.deleteData(content.getText().toString());
        }

    }

}

