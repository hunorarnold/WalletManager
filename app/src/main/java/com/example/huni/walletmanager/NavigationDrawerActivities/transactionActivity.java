package com.example.huni.walletmanager.NavigationDrawerActivities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.huni.walletmanager.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class transactionActivity extends AppCompatActivity {

    private static String TAG = "transactionActivity";

    private float[]yData = {25.3f, 10.25f, 66.90f, 44.15f, 46.50f, 23.99f, 14.01f};
    private String[] xData = {"General", "Housing", "Finance", "Transport", "Drinks", "Food", "Entertainment"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = findViewById(R.id.idPieChart);

        pieChart.setDescription(null);
        pieChart.setUsePercentValues(true);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(15f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Monthly expenses");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);



        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int positionOne = e.toString().indexOf("y: ");
                String money = e.toString().substring(positionOne + 3);

                for(int i = 0; i < yData.length; i++){

                    if(yData[i] == Float.parseFloat(money)){

                        positionOne = i;
                        break;
                    }
                }

                String spending = xData[positionOne];
                Toast.makeText(transactionActivity.this, spending + ": " + money + "$", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void addDataSet(){

        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){

            yEntrys.add(new PieEntry(yData[i], i));
        }

        for(int i = 0; i < xData.length; i++){

            xEntrys.add(xData[i]);
        }

        //create data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "What are you spending?");
        //pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to datasel
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(255, 141, 51  ));
        colors.add(Color.rgb(213, 159, 152   ));
        colors.add(Color.rgb(13, 144, 179  ));
        colors.add(Color.rgb(83, 13, 179  ));
        colors.add(Color.rgb(179, 57, 46  ));
        colors.add(Color.rgb(146, 179, 13  ));
        colors.add(Color.rgb(179, 116, 13  ));

        pieDataSet.setColors(colors);

        //add legend to chart

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }
}
