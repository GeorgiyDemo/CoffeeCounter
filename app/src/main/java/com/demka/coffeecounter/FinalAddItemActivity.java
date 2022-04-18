package com.demka.coffeecounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demka.coffeecounter.db.AppDatabase;
import com.demka.coffeecounter.db.tables.Coffee;
import com.demka.coffeecounter.db.tables.Record;

public class FinalAddItemActivity extends AppCompatActivity {

    ImageView imageView;
    TextView finalNameTextView;
    TextView finalMgTextView;
    TextView amountTextView;
    AppDatabase db;
    Coffee coffee;
    Button incButton;
    Button decButton;
    Button addItemOkButton;
    int currentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_add_item);

        currentAmount = 1;
        db = AppDatabase.getDbInstance(getApplicationContext());
        String currentCoffeeId = getIntent().getStringExtra("coffeeId");
        coffee = db.coffeeDao().getCoffeeById(currentCoffeeId);
        String mgString = coffee.mg + " " + getResources().getString(R.string.mg);

        imageView = findViewById(R.id.finalCoffeeImageView);
        finalNameTextView = findViewById(R.id.finalNameTextView);
        finalMgTextView = findViewById(R.id.finalMgTextView);
        amountTextView = findViewById(R.id.dynamicAmountTextView);
        incButton = findViewById(R.id.incButton);
        decButton = findViewById(R.id.decButton);
        addItemOkButton = findViewById(R.id.addItemOkButton);

        View.OnClickListener addButtonListener = this::addButtonClicked;
        View.OnClickListener incButtonListener = this::incButtonClicked;
        View.OnClickListener decButtonListener = this::decButtonClicked;

        addItemOkButton.setOnClickListener(addButtonListener);
        incButton.setOnClickListener(incButtonListener);
        decButton.setOnClickListener(decButtonListener);

        int resID = getResources().getIdentifier(coffee.imagePath, "drawable", getPackageName());
        if (resID != 0) {
            imageView.setImageResource(resID);
        }
        finalNameTextView.setText(coffee.name);
        finalMgTextView.setText(mgString);

        syncAmountValue();
    }

    private void addButtonClicked(View v) {

        long currentTime = System.currentTimeMillis() / 1000L;
        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());

        Record record = new Record();
        record.coffeeId = coffee.id;
        record.time = currentTime;
        record.amount = Long.parseLong(amountTextView.getText().toString());

        db.recordDao().insertRecord(record);
        String alert = getResources().getString(R.string.item_added);
        Toast.makeText(this, alert, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    private void incButtonClicked(View v) {
        currentAmount++;
        syncAmountValue();
    }

    private void decButtonClicked(View v) {

        if (currentAmount > 1) {
            currentAmount--;
            syncAmountValue();
        }
    }

    private void syncAmountValue() {
        Double newMg = coffee.mg * currentAmount;
        String newMgString = newMg + " " + getResources().getString(R.string.mg);
        finalMgTextView.setText(newMgString);
        amountTextView.setText(String.valueOf(currentAmount));
    }
}