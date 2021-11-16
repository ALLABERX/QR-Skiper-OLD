package com.evilgeniuses.skiper.utils;

import static com.evilgeniuses.skiper.utils.Thesaurus.FIRST_LAUNCH;
import static com.evilgeniuses.skiper.utils.Thesaurus.PREFERENCE;
import static com.evilgeniuses.skiper.utils.Thesaurus.QR_CODE;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;
    Context context;

    int MODE = 0;

    public PreferenceManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE, MODE);
        spEditor = sharedPreferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        spEditor.putBoolean(FIRST_LAUNCH, isFirstTime);
        spEditor.commit();
    }

    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }

    public void setQrCodeLoaded(boolean isLoaded) {
        spEditor.putBoolean(FIRST_LAUNCH, isLoaded);
        spEditor.commit();
    }

    public boolean isQrCodeLoaded(){
        String string = sharedPreferences.getString(QR_CODE, "");
        if(string == null) return false;
        if(string.equals("")) return false;
        return true;
    }

    public void setQrCode(String text) {
        spEditor.putString(QR_CODE, text);
        spEditor.commit();
    }

    public String getQRcode() {
        return sharedPreferences.getString(QR_CODE, "");
    }
}
