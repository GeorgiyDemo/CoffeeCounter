package com.demka.coffeecounter;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.demka.coffeecounter.adapters.ChartDataAdapter;
import com.demka.coffeecounter.db.AppDatabase;
import com.demka.coffeecounter.db.GroupCoffeeItem;
import com.demka.coffeecounter.db.relations.RecordWithCoffee;
import com.demka.coffeecounter.statsitems.BarChartItem;
import com.demka.coffeecounter.statsitems.ChartItem;
import com.demka.coffeecounter.statsitems.LineChartItem;
import com.demka.coffeecounter.statsitems.PieChartItem;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        ListView lv = findViewById(R.id.listView1);


        db = AppDatabase.getDbInstance(getApplicationContext());
        ArrayList<ChartItem> list = new ArrayList<>();

        list.add(new PieChartItem(generateDataPie(), getApplicationContext()));
        list.add(new LineChartItem(generateDataLine(1), getApplicationContext()));
        list.add(new BarChartItem(generateDataBar(1), getApplicationContext()));

        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        lv.setAdapter(cda);
    }

    private PieData generateDataPie() {

        List<GroupCoffeeItem> groupedItems = db.recordDao().getCoffeeGroupItems();

        //TODO: Группировка кофе по кол-ву  -> GROUP BY coffeeId
        ArrayList<PieEntry> entries = new ArrayList<>();

        for (GroupCoffeeItem item: groupedItems) {
            entries.add(new PieEntry(item.count, item.name));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.COLORFUL_COLORS);

        return new PieData(d);
    }

    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }

        LineDataSet d1 = new LineDataSet(values1, "New DataSet " + cnt + ", (1)");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values2.add(new Entry(i, values1.get(i).getY() - 30));
        }

        LineDataSet d2 = new LineDataSet(values2, "New DataSet " + cnt + ", (2)");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);

        return new LineData(sets);
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Bar data
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }
}