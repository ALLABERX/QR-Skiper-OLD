package com.evilgeniuses.skiper.fragments.browser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.evilgeniuses.skiper.R;
import com.evilgeniuses.skiper.activities.main.MainView;
import com.evilgeniuses.skiper.fragments.start.StartFragment;
import com.evilgeniuses.skiper.fragments.start.StartView;
import com.evilgeniuses.skiper.utils.PreferenceManager;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class BrowserFragment extends Fragment implements StartView {

    BrowserPresenter browserPresenter;
    MainView mainView;

    Button buttonReset;
    Button buttonBrowser;
    ImageView imageViewQR;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initiationViewElements(view);
        setOnClickListener();


        PreferenceManager preferenceManager = new PreferenceManager(getContext());
        String qRcode = preferenceManager.getQRcode();
        Bitmap bitmap;
        QRGEncoder qrgEncoder = new QRGEncoder(qRcode, null, QRGContents.Type.TEXT, 600);
        qrgEncoder.setColorBlack(Color.RED);
        qrgEncoder.setColorWhite(Color.BLUE);
        bitmap = qrgEncoder.getBitmap();
        imageViewQR.setImageBitmap(bitmap);
        return view;
    }

    @Override
    public void initiationViewElements(View view) {
        buttonReset = view.findViewById(R.id.buttonReset);
        imageViewQR = view.findViewById(R.id.imageViewQR);
        buttonBrowser = view.findViewById(R.id.buttonBrowser);
    }

    @Override
    public void setOnClickListener() {
        buttonReset.setOnClickListener(this);
        buttonBrowser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        PreferenceManager preferenceManager = new PreferenceManager(getContext());
        switch (view.getId()) {

            case R.id.buttonReset:
                mainView.setFragment(StartFragment.newInstance());

                preferenceManager.setQrCode("");
                break;            
                
                case R.id.buttonBrowser:
                mainView.setFragment(StartFragment.newInstance());
                    String qRcode = preferenceManager.getQRcode();

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(android.net.Uri.parse(qRcode));
                    startActivity(intent);
                    break;
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    public static BrowserFragment newInstance() {
        return new BrowserFragment();
    }
}