package com.example.gondolin.moneyutilapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.gondolin.moneyutilapp.Utils.DateUtils.getCurrentDatePosition;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DatePicker datePicker;
    TextView dailyAmount;
    EditText inputAmount;
    TextView prevDayAmount;
    TextView accountBalanceT;
    int lastDay;
    double tempaccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        FloatingActionButton minus = findViewById(R.id.minus);
        FloatingActionButton add = findViewById(R.id.add);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minusTransaction();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTransaction();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void init() {
        inputAmount = findViewById(R.id.inputAmount);
        datePicker = findViewById(R.id.datePicker);
        dailyAmount = findViewById(R.id.DailyAmount);
        prevDayAmount = findViewById(R.id.PrevDayAmount);
        accountBalanceT = findViewById(R.id.AccountBalance);
        initAccountBalance();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setDailyAmount() {

        double dailyamount = accountBalance / getCurrentDatePosition(datePicker);
        double prev = tempaccount / getCurrentDatePosition(datePicker);
        dailyAmount.setText(String.valueOf(dailyamount));
        prevDayAmount.setText(String.valueOf(prev));
        savedailyAmount();
    }

    private Double accountBalance;

    private void addTransaction() {
        //new transaction
        if (!StringUtils.isBlank(inputAmount.getText())) {
            accountBalance = accountBalance + Double.valueOf(inputAmount.getText().toString());
            accountBalanceT.setText(accountBalance.toString());
            setDailyAmount();
        }

    }


    private void minusTransaction() {
        //new transaction
        if (!StringUtils.isBlank(inputAmount.getText())) {
            accountBalance = accountBalance - Double.valueOf(inputAmount.getText().toString());
            accountBalanceT.setText(accountBalance.toString());
            setDailyAmount();
        }
    }

    private void savedailyAmount() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("account", String.valueOf(accountBalance));
        editor.putInt("lastday", datePicker.getDayOfMonth());
        editor.commit();
    }


    private void saveLastDay(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("lastday", datePicker.getDayOfMonth());
        editor.commit();
    }
    private void initAccountBalance() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String ret = settings.getString("account", "0");
        lastDay = settings.getInt("lastday", 0);
        accountBalance = Double.valueOf(ret);
        if(lastDay!=datePicker.getDayOfMonth()){
            tempaccount = accountBalance;
            saveLastDay();
        }
        setDailyAmount();
        accountBalanceT.setText(ret);
    }

    private String getDateSelected() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return sdf.format(calendar.getTime());
    }


}
