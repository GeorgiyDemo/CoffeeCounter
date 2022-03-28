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
import com.demka.coffeecounter.db.Coffee;

import java.util.List;

public class CoffeeListAdapter extends RecyclerView.Adapter<CoffeeListAdapter.MyViewHolder> {

    public List<Coffee> coffeeList;
    private Context context;


    public CoffeeListAdapter(Context context) {
        this.context = context;

    }

    public void setCoffeeList(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coffee_list_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String coffeeImg = coffeeList.get(position).imagePath;
        int resID = context.getResources().getIdentifier(coffeeImg, "drawable", context.getPackageName());
        if (resID != 0) {
            holder.icon.setImageResource(resID);
        }

        holder.id.setText(String.valueOf(coffeeList.get(position).id));
        holder.title.setText(coffeeList.get(position).name);
        holder.caffeine.setText(String.valueOf(coffeeList.get(position).mg));
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        TextView caffeine;
        TextView id;

        public MyViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.add_icon);
            title = view.findViewById(R.id.add_title);
            caffeine = view.findViewById(R.id.add_caffeine);
            id = view.findViewById(R.id.add_id);
        }
    }
}
