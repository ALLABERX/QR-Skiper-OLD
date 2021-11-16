package com.evilgeniuses.skiper.fragments.start;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.evilgeniuses.skiper.R;
import com.evilgeniuses.skiper.activities.main.MainView;
import com.evilgeniuses.skiper.activities.slider.SliderActivity;
import com.evilgeniuses.skiper.fragments.scanner.ScannerFragment;

public class StartFragment extends Fragment implements StartView {

    StartPresenter startPresenter;
    MainView mainView;

    Button buttonScan;
    ImageView buttonInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        initiationViewElements(view);
        setOnClickListener();
        return view;
    }

    @Override
    public void initiationViewElements(View view) {
        buttonScan = view.findViewById(R.id.buttonScan);
        buttonInfo = view.findViewById(R.id.buttonInfo);
    }

    @Override
    public void setOnClickListener() {
        buttonScan.setOnClickListener(this);
        buttonInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonScan:
                int MY_PERMISSIONS_REQUEST_CAMERA = 0;
                int permissionStatus = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
                if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
                mainView.setFragment(ScannerFragment.newInstance());
                break;
            case R.id.buttonInfo:
                startActivity(new Intent(getActivity(), SliderActivity.class));
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

    public static StartFragment newInstance() {
        return new StartFragment();
    }
}
