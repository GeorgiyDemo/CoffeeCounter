package com.demka.coffeecounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.demka.coffeecounter.adapters.MyAdapter;
import com.demka.coffeecounter.databinding.ActivityMainBinding;
import com.demka.coffeecounter.dbhelpers.BaseDbHelper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> addItemActivity;
    BaseDbHelper dbHelper;
    RecyclerView myRecyclerView;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        View.OnClickListener newItemButtonListener = this::newItemButtonClicked;

        binding.newItemButton.setOnClickListener(newItemButtonListener);


        dbHelper = new BaseDbHelper(this);
        myAdapter = new MyAdapter(this, dbHelper);

        addItemActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        //TODO чета делать
                    }
                }
        );

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

    // обработка нажатий на меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getTitle().toString()) {
            case "Stats":
                intent = new Intent(this, StatsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }





}