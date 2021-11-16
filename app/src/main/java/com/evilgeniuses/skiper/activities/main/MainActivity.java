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
import com.evilgeniuses.skiper.fragments.scanner.ScannerFragment;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int MY_PERMISSIONS_REQUEST_CAMERA=0;

        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }




        Bitmap bitmap;
        ImageView imageView = findViewById(R.id.imageView);

        QRGEncoder qrgEncoder = new QRGEncoder("inputValue", null, QRGContents.Type.TEXT, 25);
        qrgEncoder.setColorBlack(Color.RED);
        qrgEncoder.setColorWhite(Color.BLUE);
        bitmap = qrgEncoder.getBitmap();
        imageView.setImageBitmap(bitmap);


        startActivity(new Intent(this, SliderActivity.class));
        finish();

        //setFragment(ScannerFragment.newInstance());
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}