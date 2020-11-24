package com.systemesmobiles.akrafi_randolph_tp3_services;

import android.os.Binder;

import com.systemesmobiles.akrafi_randolph_tp3_services.interfaces.IBackgroundService;

public class BackgroundServiceBinder extends Binder {

    private IBackgroundService service = null;
    public BackgroundServiceBinder(IBackgroundService service) {
        super();
        this.service = service;
    }
    public IBackgroundService getService(){
        return service;
    }

}
