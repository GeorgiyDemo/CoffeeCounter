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

    private Context context;
    public List<RecordWithCoffee> recordList;


    public RecordListAdapter(Context context){
        this.context = context;

    }

    public void setRecordList(List<RecordWithCoffee> recordList){
        this.recordList = recordList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.record_list_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Date date = new Date ();

        date.setTime(recordList.get(position).record.time*1000);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String currentDateString = dateFormatter.format(date);

        Double caffeine = recordList.get(position).record.amount * recordList.get(position).coffee.mg;

        //TODO: поменять на id ресурса из SQLite
        holder.icon.setImageResource(R.drawable.affogato);
        holder.title.setText(recordList.get(position).coffee.name);
        holder.caffeine.setText(String.valueOf(caffeine));
        holder.amount.setText(String.valueOf(recordList.get(position).record.amount));
        holder.timestamp.setText(currentDateString);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title;
        TextView caffeine;
        TextView amount;
        TextView timestamp;

        public MyViewHolder(View view){
            super(view);
            icon = view.findViewById(R.id.main_icon);
            title = view.findViewById(R.id.main_title);
            caffeine = view.findViewById(R.id.main_caffeine);
            amount = view.findViewById(R.id.main_amount);
            timestamp = view.findViewById(R.id.main_timestamp);


        }
    }
}
