package com.demka.coffeecounter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.demka.coffeecounter.adapters.ChartDataAdapter;
import com.demka.coffeecounter.db.AppDatabase;
import com.demka.coffeecounter.db.groupitems.CaffeineDateGroupItem;
import com.demka.coffeecounter.db.groupitems.CoffeeTypeCountGroupItem;
import com.demka.coffeecounter.statsitems.ChartItem;
import com.demka.coffeecounter.statsitems.LineChartItem;
import com.demka.coffeecounter.statsitems.PieChartItem;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    AppDatabase db;
    ArrayList<ChartItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        db = AppDatabase.getDbInstance(getApplicationContext());
        list = new ArrayList<>();

        addPieChart();
        addLineChart();
        initList();
    }

    private void addPieChart() {
        PieChartItem pieChartItem = new PieChartItem(generateDataPie(), getApplicationContext());
        list.add(pieChartItem);
    }

    private void addLineChart() {
        List<String> stringValues = new ArrayList<>();

        List<CaffeineDateGroupItem> groupItemsList = db.recordDao().getCaffeineDateGroupItem();
        for (int i = 0; i < groupItemsList.size(); i++) {
            stringValues.add(groupItemsList.get(i).date);
        }

        LineChartItem lineChartItem = new LineChartItem(generateDataLine(), getApplicationContext(), stringValues);
        list.add(lineChartItem);
    }

    private void initList() {
        ListView lv = findViewById(R.id.listView1);
        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        lv.setAdapter(cda);
    }

    private PieData generateDataPie() {

        List<CoffeeTypeCountGroupItem> groupedItems = db.recordDao().getCoffeeTypeCountGroupItems();

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (CoffeeTypeCountGroupItem item : groupedItems) {
            entries.add(new PieEntry(item.count, item.name));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.COLORFUL_COLORS);

        return new PieData(d);
    }

    private LineData generateDataLine() {

        List<Entry> values = new ArrayList<>();
        List<CaffeineDateGroupItem> groupItemsList = db.recordDao().getCaffeineDateGroupItem();

        for (int i = 0; i < groupItemsList.size(); i++) {
            values.add(new Entry(i, groupItemsList.get(i).caffeine));
        }

        LineDataSet d1 = new LineDataSet(values, "");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_brown);
        d1.setFillDrawable(drawable);
        d1.setDrawFilled(true);
        d1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);

        return new LineData(sets);
    }
}