package com.example.huni.walletmanager;

import android.os.Bundle;
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

import com.example.huni.walletmanager.Fragments.CurrencyFragment;
import com.example.huni.walletmanager.Fragments.LanguagesFragment;
import com.example.huni.walletmanager.Fragments.LogoutFragment;
import com.example.huni.walletmanager.Fragments.PeriodFragment;
import com.example.huni.walletmanager.Fragments.ProfileFragment;
import com.example.huni.walletmanager.Fragments.SummaryFragment;
import com.example.huni.walletmanager.Fragments.TransactionFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        switch(id) {

            case R.id.nav_profile: {

                fragment = getSupportFragmentManager().findFragmentByTag("ProfileFragment");

                if(fragment == null) {

                    fragment = new ProfileFragment();
                }

                loadFragment(fragment);
                return true;
            }

            case R.id.nav_languages:
                {

                fragment = getSupportFragmentManager().findFragmentByTag("LanguagesFragment");

                if(fragment == null) {

                    fragment = new LanguagesFragment();
                }

                loadFragment(fragment);
                break;
            }

            case R.id.nav_currency: {

                fragment = getSupportFragmentManager().findFragmentByTag("CurrencyFragment");

                if(fragment == null) {

                    fragment = new CurrencyFragment();
                }

                loadFragment(fragment);
                break;
            }

            case R.id.nav_period: {

                fragment = getSupportFragmentManager().findFragmentByTag("PeriodFragment");

                if(fragment == null) {

                    fragment = new PeriodFragment();
                }

                loadFragment(fragment);
                break;
            }


            case R.id.nav_transaction: {

                fragment = getSupportFragmentManager().findFragmentByTag("TransactionFragment");

                if(fragment == null) {

                    fragment = new TransactionFragment();
                }

                loadFragment(fragment);
                break;
            }

            case R.id.nav_summary: {

                fragment = getSupportFragmentManager().findFragmentByTag("SummaryFragment");

                if(fragment == null) {

                    fragment = new SummaryFragment();
                }

                loadFragment(fragment);
                return true;
            }

            case R.id.nav_logout: {

                fragment = getSupportFragmentManager().findFragmentByTag("LogoutFragment");

                if(fragment == null) {

                    fragment = new LogoutFragment();
                }

                loadFragment(fragment);
                break;
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_main, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }
}
