package com.example.huni.walletmanager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huni.walletmanager.NavigationDrawerActivities.transactionActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.huni.walletmanager.NavigationDrawerActivities.transactionActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Intent transactionIntent;
    private FloatingActionButton plussbutton;

    private static String TAG = "MainActivity";

    private float[]yData = {25.3f, 10.25f, 66.90f, 44.15f, 46.50f, 23.99f, 14.01f};
    private String[] xData = {"General", "Housing", "Finance", "Transport", "Drinks", "Food", "Entertainment"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //a floatingactionbutton osszecsatolon a koddal hogy lehesen ra tenni onclick listenert

        this.plussbutton = (FloatingActionButton) findViewById(R.id.fab);

        //rahejezem az onclik listenert a buttonre

        this.plussbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            /** onclick listener a floating buttonre ami elehozza a dialogus dobozt benne a plussmenuoption layouttal */
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.plusbuttonmenuoption);

                // rosszecsatolom a gombokat a dialogus dobozba levo elemekkel

                Button calendarButton = dialog.findViewById(R.id.plusbuttonmenuoption_screen_button_calendar);
                Button plussumButton = dialog.findViewById(R.id.plusbuttonmenuoption_screen_button_sum_plus);
                Button minussumButton = dialog.findViewById(R.id.plusbuttonmenuoption_screen_button_sum_minus);

                /** egyenkent rateszem a buttonkre a gombokra*/

                calendarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,CalendarActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                plussumButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("minus");
                        Intent intent= new Intent(MainActivity.this,WalletManagerActivity.class);
                        intent.putExtra("milyen muvelet","plusz");
                        intent.putExtra("adatok",MainActivity.this.yData);
                        intent.putExtra("szavak",MainActivity.this.xData);
                        startActivityForResult(intent,0);
                        dialog.dismiss();
                    }
                });
                minussumButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("plus");
                        Intent intent= new Intent(MainActivity.this,WalletManagerActivity.class);
                        intent.putExtra("milyen muvelet","minusz");
                        intent.putExtra("adatok",MainActivity.this.yData);
                        intent.putExtra("szavak",MainActivity.this.xData);
                        startActivityForResult(intent,0);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        transactionIntent = new Intent(MainActivity.this, transactionActivity.class);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



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
                Toast.makeText(MainActivity.this, spending + ": " + money + "$", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_languages) {

        } else if (id == R.id.nav_currency) {

        } else if (id == R.id.nav_period) {

        } else if (id == R.id.nav_transaction) {

            startActivity(transactionIntent);

        } else if (id == R.id.nav_summary) {

        } else if (id == R.id.nav_logout) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


