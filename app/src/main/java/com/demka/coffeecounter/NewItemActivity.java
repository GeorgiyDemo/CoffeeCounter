package com.demka.coffeecounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demka.coffeecounter.adapters.CoffeeListAdapter;
import com.demka.coffeecounter.adapters.RecordListAdapter;
import com.demka.coffeecounter.db.AppDatabase;
import com.demka.coffeecounter.db.Coffee;
import com.demka.coffeecounter.db.Record;
import com.demka.coffeecounter.db.relations.RecordWithCoffee;

import java.util.ArrayList;
import java.util.List;

public class NewItemActivity extends AppCompatActivity {

    RecyclerView coffeeRecyclerView;

    private CoffeeListAdapter coffeeListAdapter;
    private Button addItemButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_item);
        View.OnClickListener newButtonListener = this::addItemButtonClicked;


        addItemButton = findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(newButtonListener);

        initRecycleView();
        loadRecordList();
    }


    private void initRecycleView(){
        coffeeRecyclerView = findViewById(R.id.coffeeRecyclerView);
        coffeeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        coffeeRecyclerView.addItemDecoration(dividerItemDecoration);
        coffeeListAdapter = new CoffeeListAdapter(this);
        coffeeRecyclerView.setAdapter(coffeeListAdapter);
    }

    private void loadRecordList(){
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
        List<Coffee> coffeeList = db.coffeeDao().getAllCoffee();
        coffeeListAdapter.setCoffeeList(coffeeList);
    }

    private void addItemButtonClicked(View v) {

        //TODO поменять
        safeNewRecord("ТЕСТ", 1L);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


    private void safeNewRecord(String name, Long amount){

        long currentTime = System.currentTimeMillis() / 1000L;
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());

        //TODO: бувферно добавлем кофи какой-нибудь
        Coffee coffee = new Coffee();
        coffee.mg = 228.134;
        coffee.name = "Cat koffee";
        coffee.imagePath = "raz raz raz";
        db.coffeeDao().insertCoffee(coffee);

        Record record = new Record();

        //TODO: переделать
        record.coffeeId = 1L;
        record.amount = amount;
        record.time = currentTime;

        db.recordDao().insertRecord(record);


    }
}