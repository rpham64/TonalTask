package com.example.rpham.tonaltask;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class TonalTaskApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
