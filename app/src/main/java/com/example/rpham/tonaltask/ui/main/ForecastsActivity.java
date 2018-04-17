package com.example.rpham.tonaltask.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rpham.tonaltask.R;
import com.example.rpham.tonaltask.util.ActivityUtils;

public class ForecastsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ForecastsFragment forecastsFragment = (ForecastsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (forecastsFragment == null) {
            forecastsFragment = ForecastsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    forecastsFragment, R.id.fragment_container);
        }
    }
}
