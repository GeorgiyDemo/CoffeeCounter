package com.demka.coffeecounter.statsitems;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.demka.coffeecounter.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.List;

public class LineChartItem extends ChartItem implements OnChartValueSelectedListener {

    private final Typeface mTf;
    private List<String> stringValues;
    private Context context;

    public LineChartItem(ChartData<?> cd, Context context, List<String> stringValues) {
        super(cd);
        mTf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        this.context = context;
        this.stringValues = stringValues;
    }

    @Override
    public int getItemType() {
        return TYPE_LINECHART;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, Context c) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(c).inflate(
                    R.layout.list_item_linechart, null);
            holder.chart = convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        // holder.chart.setValueTypeface(mTf);
        holder.chart.getDescription().setEnabled(false);
        holder.chart.setDrawGridBackground(false);
        holder.chart.setOnChartValueSelectedListener(this);


        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(context, R.layout.custom_marker_view);

        // Set the marker to the chart
        mv.setChartView(holder.chart);
        holder.chart.setMarker(mv);

        // enable scaling and dragging
        holder.chart.setDragEnabled(true);
        holder.chart.setScaleEnabled(true);

        XAxis xAxis = holder.chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(stringValues));


        YAxis leftAxis = holder.chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = holder.chart.getAxisRight();
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(5, false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        // set data
        holder.chart.setData((LineData) mChartData);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        holder.chart.animateX(750);

        return convertView;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        Log.i("Entry selected", e.toString());

    }

    @Override
    public void onNothingSelected() {

    }

    private static class ViewHolder {
        LineChart chart;
    }
}
