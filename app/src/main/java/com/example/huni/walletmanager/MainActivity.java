package com.example.huni.walletmanager;

import android.app.Dialog;
import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huni.walletmanager.NavigationDrawerActivities.transactionActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.huni.walletmanager.NavigationDrawerActivities.transactionActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Intent transactionIntent;
    private FloatingActionButton plussbutton;


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
                        System.out.println("Halenda");
                        Intent intent = new Intent(MainActivity.this,CalendarActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                plussumButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("minus");
                    }
                });
                minussumButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("plus");
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
    
}
