package com.demka.coffeecounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demka.coffeecounter.adapters.NewCoffeeAdapter;

import java.util.ArrayList;

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

        Intent intent = new Intent();
        intent.putExtra("name", "TEST");
        setResult(RESULT_OK, intent);
        finish();

    }
}