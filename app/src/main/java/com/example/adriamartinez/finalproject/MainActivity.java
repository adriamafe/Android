package com.example.adriamartinez.finalproject;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_person_outline_black_24dp);
        setSupportActionBar(toolbar);
        //Load fragment for first time
        Fragment f = new WelcomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,f)
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.calculadora:
                                CalculatorFragment f = null;
                                f = new CalculatorFragment();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragment_container,f)
                                        .commit();
                                break;
                            case R.id.memory:
                                MemoryFragment mf = null;
                                mf = new MemoryFragment();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragment_container,mf)
                                        .commit();
                                break;
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onClick(View view) {

    }
}
