package com.demka.coffeecounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;

import com.demka.coffeecounter.adapters.NewCoffeeAdapter;
import com.demka.coffeecounter.db.AppDatabase;
import com.demka.coffeecounter.db.Record;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewItemActivity extends AppCompatActivity {

    RecyclerView newCoffeeRecyclerView;
    ArrayList<String> mNameList = new ArrayList<>();
    String s1[], s2[];
    int images[] = {};
    private Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        s1 = getResources().getStringArray(R.array.programming_languages);
        s2 = getResources().getStringArray(R.array.description);

        setContentView(R.layout.activity_new_item);
        View.OnClickListener newButtonListener = this::addItemButtonClicked;

        newCoffeeRecyclerView = findViewById(R.id.newCoffeeRecyclerView);
        addItemButton = findViewById(R.id.addItemButton);

        newCoffeeRecyclerView = findViewById(R.id.newCoffeeRecyclerView);
        NewCoffeeAdapter timetableAdapter = new NewCoffeeAdapter(this, s1, s2);

        newCoffeeRecyclerView.setAdapter(timetableAdapter);
        newCoffeeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addItemButton.setOnClickListener(newButtonListener);

    }

    private void addItemButtonClicked(View v) {

        safeNewRecord("ТЕСТ", 1L);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


    private void safeNewRecord(String name, Long amount){

        long time = System.currentTimeMillis() / 1000L;
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());

        Record record = new Record();
        //TODO: переделать
        record.coffeeId = 1L;
        record.amount = amount;
        record.time = time;

        db.recordDao().insertRecord(record);

    }
}