package com.demka.coffeecounter.statsitems;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;

import com.demka.coffeecounter.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.formatter.PercentFormatter;

public class PieChartItem extends ChartItem {

    private final Typeface mTf;
    private final SpannableString mCenterText;

    public PieChartItem(ChartData<?> cd, Context c) {
        super(cd);

        mTf = Typeface.createFromAsset(c.getAssets(), "OpenSans-Regular.ttf");
        mCenterText = generateCenterText();
    }

    @Override
    public int getItemType() {
        return TYPE_PIECHART;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, Context c) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(c).inflate(
                    R.layout.list_item_piechart, null);
            holder.chart = convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        holder.chart.getDescription().setEnabled(false);
        holder.chart.setHoleRadius(52f);
        holder.chart.setTransparentCircleRadius(57f);
        holder.chart.setCenterText(mCenterText);
        holder.chart.setCenterTextTypeface(mTf);
        holder.chart.setCenterTextSize(9f);
        holder.chart.setUsePercentValues(true);
        holder.chart.setExtraOffsets(5, 10, 50, 10);

        mChartData.setValueFormatter(new PercentFormatter());
        mChartData.setValueTypeface(mTf);
        mChartData.setValueTextSize(11f);
        mChartData.setValueTextColor(Color.WHITE);
        // set data
        holder.chart.setData((PieData) mChartData);

        Legend l = holder.chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        holder.chart.animateY(900);

        return convertView;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Потребление\nкофе\nпо видам");
        s.setSpan(new RelativeSizeSpan(1.4f), 0, s.length(), 0);
        return s;
    }

    private static class ViewHolder {
        PieChart chart;
    }
}
