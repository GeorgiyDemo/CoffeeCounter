package com.demka.coffeecounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demka.coffeecounter.adapters.CoffeeListAdapter;
import com.demka.coffeecounter.db.AppDatabase;
import com.demka.coffeecounter.db.tables.Coffee;
import com.demka.coffeecounter.db.tables.Record;

import java.util.List;

public class NewItemActivity extends AppCompatActivity {

    RecyclerView coffeeRecyclerView;

    private CoffeeListAdapter coffeeListAdapter;
    private Button addItemButton;
    private EditText searchEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_item);
        View.OnClickListener newButtonListener = this::addItemButtonClicked;

        searchEditText = findViewById(R.id.searchEditText);
        addItemButton = findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(newButtonListener);

        initRecycleView();
        loadRecordList();
    }


    private void initRecycleView() {
        coffeeRecyclerView = findViewById(R.id.coffeeRecyclerView);
        coffeeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        coffeeRecyclerView.addItemDecoration(new DividerItemDecoration(this, 0));
        coffeeListAdapter = new CoffeeListAdapter(this);
        coffeeRecyclerView.setAdapter(coffeeListAdapter);
    }

    private void loadRecordList() {
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
        List<Coffee> coffeeList = db.coffeeDao().getAllCoffee();
        coffeeListAdapter.setCoffeeList(coffeeList);
    }

    private void addItemButtonClicked(View v) {

        if (coffeeListAdapter.getSelected() != null) {

            long currentTime = System.currentTimeMillis() / 1000L;
            AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());

            Record record = new Record();
            record.coffeeId = coffeeListAdapter.getSelected().id;
            record.time = currentTime;

            //TODO: как-то переделать Amount, отдельная активити?
            record.amount = 1L;

            db.recordDao().insertRecord(record);
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();

        } else {
            Toast.makeText(this, "Ничего не выбрано!", Toast.LENGTH_SHORT).show();
        }
    }
}