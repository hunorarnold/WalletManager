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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huni.walletmanager.NavigationDrawerActivities.CurrencyPickerActivity;
import com.example.huni.walletmanager.NavigationDrawerActivities.transactionActivity;
import com.example.huni.walletmanager.NavigationDrawerActivities.user_detailsActivity;
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
import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Intent transactionIntent, detailsIntent;
    //log out and firebase declaration
    private Intent intentLogIn;
    private FirebaseAuth firebaseAuth;

    private FloatingActionButton plussbutton;

    private static String TAG = "MainActivity";

    private float[]yData = {2.0f, 1.0f, 7.0f, 5.0f, 6.0f, 9.0f, 23.0f};
    private String[] xData = {"General", "Housing", "Finance", "Transport", "Drinks", "Food", "Entertainment"};
    PieChart pieChart;

    //realtime database
    private DatabaseReference databaseReference;

    private EditText editTextSalary, editTextGeneral, editTextHousing, editTextEntertainment;
    private EditText editTextFinance, editTextTransport, editTextDrinks, editTextFood;
    private TextView textViewRemaining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firebase initializing
        firebaseAuth = FirebaseAuth.getInstance();


        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextGeneral = (EditText)findViewById(R.id.General_editText) ;
        editTextHousing = (EditText)findViewById(R.id.Housing_editText);
        //addButton = (Button)findViewById(R.id.button3);


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
                        //System.out.println("minus");
                        UserCosts();
                        //dialog.dismiss();
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
        detailsIntent = new Intent(MainActivity.this, user_detailsActivity.class);

        //back to log in section after you loged out
        intentLogIn = new Intent(MainActivity.this, loginActivity.class);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    public void UserCosts(){
        String general = editTextGeneral.getText().toString().trim();
        String housing = editTextHousing.getText().toString().trim();

        Costs costs = new Costs(salary, remaining_money, general, housing, finance, transport, drinks, food, entertainment);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(costs);


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
        // Handle navigation view item clicks here
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            startActivity(detailsIntent);

        } else if (id == R.id.nav_languages) {

        } else if (id == R.id.nav_currency) {

            //startActivity(currencyIntent);

        } else if (id == R.id.nav_period) {

        } else if (id == R.id.nav_transaction) {

            startActivity(transactionIntent);

        } else if (id == R.id.nav_summary) {

        } else if (id == R.id.nav_logout) {
            //firebase logout
            Toast.makeText(MainActivity.this, "You logged out!",Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
            finish();
            startActivity(intentLogIn);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}


