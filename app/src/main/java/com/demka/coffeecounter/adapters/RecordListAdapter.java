package com.demka.coffeecounter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demka.coffeecounter.R;
import com.demka.coffeecounter.db.relations.RecordWithCoffee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.MyViewHolder> {

    public List<RecordWithCoffee> recordList;
    private OnRecordListener onRecordListener;
    private Context context;

    public RecordListAdapter(OnRecordListener onRecordListener) {
        this.onRecordListener = onRecordListener;

    }

    public void setRecordList(List<RecordWithCoffee> recordList) {
        this.recordList = recordList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.record_list_view_item, parent, false);
        return new MyViewHolder(view, onRecordListener);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Date date = new Date();

        date.setTime(recordList.get(position).record.time * 1000);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String currentDateString = dateFormatter.format(date);

        Double caffeine = recordList.get(position).record.amount * recordList.get(position).coffee.mg;

        String recordImg = recordList.get(position).coffee.imagePath;
        int resID = context.getResources().getIdentifier(recordImg, "drawable", context.getPackageName());
        if (resID != 0) {
            holder.icon.setImageResource(resID);
        }

        holder.title.setText(recordList.get(position).coffee.name);

        String caffeineString = context.getResources().getString(R.string.caffeine) + ": " + caffeine + " " + context.getResources().getString(R.string.mg);
        holder.caffeine.setText(caffeineString);

        String amountString = context.getResources().getString(R.string.amount) + ": " + recordList.get(position).record.amount;
        holder.amount.setText(amountString);

        holder.timestamp.setText(currentDateString);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public interface OnRecordListener {
        void onRecordClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView icon;
        TextView title;
        TextView caffeine;
        TextView amount;
        TextView timestamp;
        private OnRecordListener onRecordListener;


        public MyViewHolder(View view, OnRecordListener onRecordListener) {
            super(view);
            this.onRecordListener = onRecordListener;
            icon = view.findViewById(R.id.main_icon);
            title = view.findViewById(R.id.main_title);
            caffeine = view.findViewById(R.id.main_caffeine);
            amount = view.findViewById(R.id.main_amount);
            timestamp = view.findViewById(R.id.main_timestamp);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRecordListener.onRecordClick(getAdapterPosition());
        }
    }
}
