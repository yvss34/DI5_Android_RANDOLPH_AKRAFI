package com.systemesmobiles.akrafi_randolph_tp3_services.interfaces;

import java.util.ArrayList;

public interface IBackgroundService {

    //Fonction qui nous permet d'ajouter un listener à notre liste
    public void addListener(IBackgroundServiceListener listener);

    //Fonction qui nous permet de retirer un listener de notre liste
    public void removeListener(IBackgroundServiceListener listener);

    //Fonction qui nous permet de recuperer tous les listeners
    public ArrayList<IBackgroundServiceListener> getListeners();
}
