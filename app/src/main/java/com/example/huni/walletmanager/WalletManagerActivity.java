package com.example.huni.walletmanager;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointD;

import java.util.ArrayList;

public class WalletManagerActivity extends AppCompatActivity {



    private float[]yData = {2.0f, 1.0f, 7.0f, 5.0f, 6.0f, 9.0f, 23.0f};
    private String[] xData = {"General", "Housing", "Finance", "Transport", "Drinks", "Food", "Entertainment"};
    PieChart pieChart;
    private Entry e;
    private EditText sumEditext;
    private Button saveButton;
    private String milyenmuvelet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_manager);
        this.milyenmuvelet = getIntent().getStringExtra("milyen muvelet");
        this.yData = getIntent().getFloatArrayExtra("adatok");
        this.xData = getIntent().getStringArrayExtra("szavak");
        pieChart = (PieChart)  findViewById(R.id.idPieChart);
        this.sumEditext = (EditText) findViewById(R.id.walletmanager_screen_edittext);
        this.saveButton = (Button) findViewById(R.id.walettmanager_screen_modifie_button);

        this.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WalletManagerActivity.this.milyenmuvelet.equalsIgnoreCase("minusz")) {
                    WalletManagerActivity.this.e.setY(WalletManagerActivity.this.e.getY() - Float.parseFloat(WalletManagerActivity.this.sumEditext.getText().toString()));
                }
                if(WalletManagerActivity.this.milyenmuvelet.equalsIgnoreCase("plusz")) {
                    WalletManagerActivity.this.e.setY(WalletManagerActivity.this.e.getY() + Float.parseFloat(WalletManagerActivity.this.sumEditext.getText().toString()));
                }
                WalletManagerActivity.this.pieChart.refreshDrawableState();
            }
        });
        pieChart.setDescription(null);
      //  pieChart.setUsePercentValues(true);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(15f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Monthly expenses");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                System.out.println(e.toString()+"  "+h.toString());
                WalletManagerActivity.this.e = e;

            }

            @Override
            public void onNothingSelected() {
                System.out.println("Semmi nincs kijelolve");
            }
        });



        addDataSet();
    }


    private void addDataSet(){

      // Log.d(TAG, "addDataSet started");
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
         pieDataSet.setSliceSpace(2);
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
