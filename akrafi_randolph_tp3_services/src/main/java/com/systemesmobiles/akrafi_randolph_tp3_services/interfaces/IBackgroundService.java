package com.systemesmobiles.akrafi_randolph_tp3_services.interfaces;

import java.util.ArrayList;

public interface IBackgroundService {

    public void addListener(IBackgroundServiceListener listener);
    public void removeListener(IBackgroundServiceListener listener);
}
