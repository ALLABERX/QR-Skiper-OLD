package com.evilgeniuses.skiper.fragments.scanner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.evilgeniuses.skiper.R;
import com.evilgeniuses.skiper.activities.main.MainView;
import com.evilgeniuses.skiper.fragments.home.HomeFragment;
import com.evilgeniuses.skiper.fragments.start.StartFragment;
import com.evilgeniuses.skiper.utils.PreferenceManager;

public class ScannerFragment extends Fragment {
    private CodeScanner mCodeScanner;
    MainView mainView;

    Button buttonBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.fragment_scanner, container, false);
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(result -> activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setTest(result.getText());
            }
        }));
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());

        buttonBack = root.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.setFragment(StartFragment.newInstance());
            }
        });
        return root;
    }

    public void setTest(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

        PreferenceManager preferenceManager = new PreferenceManager(getContext());
        preferenceManager.setQrCode(text);
        mainView.setFragment(HomeFragment.newInstance());
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    public static ScannerFragment newInstance() {
        return new ScannerFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }
}