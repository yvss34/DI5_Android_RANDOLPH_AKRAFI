package com.systemesmobiles.akrafi_randolph_tp3_services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class BackgroundService extends Service {
    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        Log.d(this.getClass().getName(), "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(this.getClass().getName(), "onStart");
        timer.scheduleAtFixedRate(new TimerTask() {
            final Date date = new Date();
            public void run() {
                Log.i(this.getClass().getName(), String.valueOf(date.getTime()));
            }
        }, 0, 1000);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(this.getClass().getName(), "onDestroy");
        this.timer.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}