package com.evilgeniuses.skiper.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.evilgeniuses.skiper.R;
import com.evilgeniuses.skiper.activities.slider.SliderActivity;
import com.evilgeniuses.skiper.fragments.home.HomeFragment;
import com.evilgeniuses.skiper.fragments.scanner.ScannerFragment;
import com.evilgeniuses.skiper.fragments.start.StartFragment;
import com.evilgeniuses.skiper.utils.PreferenceManager;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PreferenceManager preferenceManager = new PreferenceManager(this);
        boolean qrCodeLoaded = preferenceManager.isQrCodeLoaded();
        if (!qrCodeLoaded) {
            setFragment(StartFragment.newInstance());
        } else {
            setFragment(HomeFragment.newInstance());

        }
    }

    @Override
    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        PreferenceManager preferenceManager = new PreferenceManager(this);
        boolean qrCodeLoaded = preferenceManager.isQrCodeLoaded();
        if (qrCodeLoaded) {
            setFragment(HomeFragment.newInstance());
        } else {
            setFragment(StartFragment.newInstance());
        }
    }
}