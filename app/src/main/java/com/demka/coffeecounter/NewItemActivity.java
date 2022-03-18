package com.demka.coffeecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class NewItemActivity extends AppCompatActivity {

    private Button addItemButton;

    ArrayAdapter<String> mArrayAdapter;
    ListView choiceListView;
    ArrayList<String> mNameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_item);
        View.OnClickListener newButtonListener = this::addItemButtonClicked;

        choiceListView = findViewById(R.id.choiceListView);
        addItemButton = findViewById(R.id.addItemButton);

        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mNameList);
        choiceListView.setAdapter(mArrayAdapter);

        addItemButton.setOnClickListener(newButtonListener);
        choiceListView.setAdapter(mArrayAdapter);


        for (int i = 0; i < 10; i++) {
            mNameList.add(String.valueOf(i));
        }

        mArrayAdapter.notifyDataSetChanged();

    }

    private void addItemButtonClicked(View v){

        //Init
        //mNameList
        //

        Intent intent = new Intent();
        intent.putExtra("name", "TEST");
        setResult(RESULT_OK, intent);
        finish();

    }
}