package com.evilgeniuses.skiper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.evilgeniuses.skiper.activities.main.MainActivity;
import com.evilgeniuses.skiper.activities.slider.SliderActivity;
import com.evilgeniuses.skiper.utils.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager preferenceManager = new PreferenceManager(this);
        Intent intent = new Intent(this, MainActivity.class);
        if (preferenceManager.isFirstLaunch()) {
            preferenceManager.setFirstTimeLaunch(false);
            intent = new Intent(this, SliderActivity.class);
        }
        startActivity(intent);
        finish();
    }
}