package com.demka.coffeecounter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demka.coffeecounter.R;

public class NewCoffeeAdapter extends RecyclerView.Adapter<NewCoffeeAdapter.MyViewHolder> {

    String data1[];
    String data2[];
    Context context;


    public NewCoffeeAdapter(Context ct, String s1[], String s2[]) {
        data1 = s1;
        data2 = s2;
        context = ct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.add_item_list_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.add_title.setText(data1[position]);
        holder.add_caffeine.setText(data2[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView add_title;
        TextView add_caffeine;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            add_title = itemView.findViewById(R.id.add_title);
            add_caffeine = itemView.findViewById(R.id.add_caffeine);

        }
    }
}



