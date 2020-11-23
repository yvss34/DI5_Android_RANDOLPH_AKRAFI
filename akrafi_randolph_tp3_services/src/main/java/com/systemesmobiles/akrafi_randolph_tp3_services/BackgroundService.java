package com.systemesmobiles.akrafi_randolph_tp3_services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.systemesmobiles.akrafi_randolph_tp3_services.interfaces.IBackgroundService;
import com.systemesmobiles.akrafi_randolph_tp3_services.interfaces.IBackgroundServiceListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class BackgroundService extends Service implements IBackgroundService {
    private Timer timer;
    private ArrayList<IBackgroundServiceListener> listeners = null;

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

        // vider la liste des listeners
        this.listeners.clear();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Ajout d'un listener
    @Override
    public void addListener(IBackgroundServiceListener listener) {
        if(listeners != null){ listeners.add(listener); }
    }

    // Suppression d'un listener
    @Override
    public void removeListener(IBackgroundServiceListener listener) {
        if(listeners != null){ listeners.remove(listener); }
    }

    // Notification des listeners
    private void fireDataChanged(Object data){
        if(listeners != null){
            for(IBackgroundServiceListener listener: listeners) {
                listener.dataChanged(data);
            }
        }
    }
}