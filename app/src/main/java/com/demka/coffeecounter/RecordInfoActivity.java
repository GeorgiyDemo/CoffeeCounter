package com.demka.coffeecounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.demka.coffeecounter.db.AppDatabase;
import com.demka.coffeecounter.db.relations.RecordWithCoffee;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordInfoActivity extends AppCompatActivity {

    ImageView coffeeImageView;
    TextView nameTextView;
    TextView dateTextView;
    TextView amountTextView;
    TextView mgTextView;
    TextView mgTotalTextView;
    Button okButton;
    Button removeButton;
    AppDatabase db;
    RecordWithCoffee record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        coffeeImageView = findViewById(R.id.coffeeImageView);
        nameTextView = findViewById(R.id.nameTextView);
        dateTextView = findViewById(R.id.dateTextView);
        amountTextView = findViewById(R.id.amountTextView);
        mgTextView = findViewById(R.id.mgTextView);
        mgTotalTextView = findViewById(R.id.mgTotalTextView);
        okButton = findViewById(R.id.okButton);
        removeButton = findViewById(R.id.removeButton);

        View.OnClickListener removeButtonListener = this::removeButtonClicked;
        View.OnClickListener okButtonListener = this::okButtonClicked;

        removeButton.setOnClickListener(removeButtonListener);
        okButton.setOnClickListener(okButtonListener);

        String recordId = getIntent().getStringExtra("id");
        db = AppDatabase.getDbInstance(getApplicationContext());
        record = db.recordDao().getRecordWithCoffeeById(recordId);

        //Устанавилваем изображение кофейка
        String recordImg = record.coffee.imagePath;
        int resID = getResources().getIdentifier(recordImg, "drawable", getPackageName());
        if (resID != 0) {
            coffeeImageView.setImageResource(resID);
        }

        nameTextView.setText(record.coffee.name);
        dateTextView.setText(getFormattedString(record.record.time));

        long amount = record.record.amount;
        double mg = record.coffee.mg;

        String amountString = amount + " " + getResources().getString(R.string.item_count);
        String mgString = mg + " " + getResources().getString(R.string.mg);
        String mgTotalString = mg * amount + " " + getResources().getString(R.string.mg);

        amountTextView.setText(amountString);
        mgTextView.setText(mgString);
        mgTotalTextView.setText(mgTotalString);

    }

    private String getFormattedString(long unixTime) {
        Date date = new Date();
        date.setTime(unixTime * 1000);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, dd MMMM yyyy, hh:mm");
        String formattedString = dateFormatter.format(date);
        return formattedString.substring(0, 1).toUpperCase() + formattedString.substring(1);
    }

    private void removeButtonClicked(View v) {

        //Удаляем с БД
        db.recordDao().deleteRecord(record.record);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    private void okButtonClicked(View v) {
        finish();
    }
}