package com.demka.coffeecounter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        View.OnClickListener okButtonListener = this::okButtonClicked;
        okButton.setOnClickListener(okButtonListener);

        //Устанавилваем изображение кофейка
        String recordImg = getIntent().getStringExtra("res");
        int resID = getResources().getIdentifier(recordImg, "drawable", getPackageName());
        if (resID != 0) {
            coffeeImageView.setImageResource(resID);
        }

        nameTextView.setText(getIntent().getStringExtra("name"));
        dateTextView.setText(getFormattedString(getIntent().getStringExtra("date")));

        int amount = Integer.parseInt(getIntent().getStringExtra("amount"));
        double mg = Double.parseDouble(getIntent().getStringExtra("mg"));

        String amountString = amount + " " + getResources().getString(R.string.item_count);
        String mgString = mg + " " + getResources().getString(R.string.mg);
        String mgTotalString = mg * amount + " " + getResources().getString(R.string.mg);

        amountTextView.setText(amountString);
        mgTextView.setText(mgString);
        mgTotalTextView.setText(mgTotalString);
    }

    private String getFormattedString(String unixTime) {
        Date date = new Date();
        date.setTime(Long.parseLong(unixTime) * 1000);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, dd MMMM yyyy, hh:mm");
        String formattedString = dateFormatter.format(date);
        return formattedString.substring(0, 1).toUpperCase() + formattedString.substring(1);
    }

    private void okButtonClicked(View v) {
        finish();
    }
}