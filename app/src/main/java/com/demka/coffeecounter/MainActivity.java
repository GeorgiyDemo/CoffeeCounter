package com.demka.coffeecounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demka.coffeecounter.adapters.RecordListAdapter;
import com.demka.coffeecounter.databinding.ActivityMainBinding;
import com.demka.coffeecounter.db.AppDatabase;
import com.demka.coffeecounter.db.relations.RecordWithCoffee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecordListAdapter.OnRecordListener {

    RecyclerView mainRecyclerView;
    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> addItemActivity;
    private RecordListAdapter recordListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        View.OnClickListener newItemButtonListener = this::newItemButtonClicked;

        binding.newItemButton.setOnClickListener(newItemButtonListener);

        addItemActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                        loadRecordList();
                    }
                }
        );

        initRecycleView();
        loadRecordList();

    }

    private void initRecycleView() {
        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, 0));
        recordListAdapter = new RecordListAdapter(this);

        mainRecyclerView.setAdapter(recordListAdapter);

    }

    private void loadRecordList() {
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
        List<RecordWithCoffee> recordList = db.recordDao().getAllRecordsWithCoffee();
        recordListAdapter.setRecordList(recordList);
    }


    public void newItemButtonClicked(View v) {
        Intent intent = new Intent(this, NewItemActivity.class);
        addItemActivity.launch(intent);
    }

    // создание меню из XML
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Обработка нажатий на меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        String statString = getResources().getString(R.string.stat_activity);
        if (item.getTitle().toString().equals(statString)) {
            intent = new Intent(this, StatsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onRecordClick(int position) {
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());
        RecordWithCoffee currentRecord = db.recordDao().getAllRecordsWithCoffee().get(position);
        Intent intent = new Intent(this, RecordInfoActivity.class);
        intent.putExtra("id", String.valueOf(currentRecord.record.id));
        addItemActivity.launch(intent);
    }
}