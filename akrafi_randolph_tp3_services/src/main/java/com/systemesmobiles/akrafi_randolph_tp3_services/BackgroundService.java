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

    //Attributs
    private Timer timer;
    private BackgroundServiceBinder binder ;
    private ArrayList<IBackgroundServiceListener> listeners = null;
    
    @Override
    public void onCreate() {
        super.onCreate();
        listeners = new ArrayList< IBackgroundServiceListener >();
        timer = new Timer();
        Log.d(this.getClass().getName(), "onCreate");
        binder = new BackgroundServiceBinder(this);

    }

    //Fonction qu'execute le service en boucle
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(this.getClass().getName(), "onStart");
        BackgroundService service = this;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                final Date date = new Date();
                service.fireDataChanged(date);
                Log.i(this.getClass().getName(), String.valueOf(date.getHours()) + ":" + String.valueOf(date.getMinutes()) + ":" + String.valueOf(date.getSeconds()) );
            }
            // Pas de délais d'attente et affichage toute les secondes
        }, 0, 1000);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // vider la liste des listeners
        this.listeners.clear();
        this.timer.cancel();
        Log.d(this.getClass().getName(), "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
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

    //Recupere la liste de listeners
    @Override
    public ArrayList<IBackgroundServiceListener> getListeners() {
        return listeners;
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