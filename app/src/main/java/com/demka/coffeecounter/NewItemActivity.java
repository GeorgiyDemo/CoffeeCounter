package com.demka.coffeecounter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.List;

public class NewItemActivity extends AppCompatActivity {

    RecyclerView coffeeRecyclerView;
    CoffeeListAdapter coffeeListAdapter;
    Button addItemButton;
    EditText searchEditText;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_item);
        View.OnClickListener newButtonListener = this::addItemButtonClicked;

        searchEditText = findViewById(R.id.searchEditText);
        addItemButton = findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(newButtonListener);

        db = AppDatabase.getDbInstance(getApplicationContext());
        initEditText();
        initRecycleView();
        loadRecordList();
    }

    private void initEditText() {
        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Coffee> newCoffeeList = db.coffeeDao().getAllCoffeeByString(s.toString());
                coffeeListAdapter.setCoffeeList(newCoffeeList);
            }
        });
    }

    private void initRecycleView() {
        coffeeRecyclerView = findViewById(R.id.coffeeRecyclerView);
        coffeeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        coffeeRecyclerView.addItemDecoration(new DividerItemDecoration(this, 0));
        coffeeListAdapter = new CoffeeListAdapter(this);
        coffeeRecyclerView.setAdapter(coffeeListAdapter);
    }

    private void loadRecordList() {
        List<Coffee> coffeeList = db.coffeeDao().getAllCoffee();
        coffeeListAdapter.setCoffeeList(coffeeList);
    }

    private void addItemButtonClicked(View v) {

        if (coffeeListAdapter.getSelected() != null) {
            Intent intent = new Intent(this, FinalAddItemActivity.class);
            intent.putExtra("coffeeId", String.valueOf(coffeeListAdapter.getSelected().id));
            startActivity(intent);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "???????????? ???? ??????????????!", Toast.LENGTH_SHORT).show();
        }

    }
}