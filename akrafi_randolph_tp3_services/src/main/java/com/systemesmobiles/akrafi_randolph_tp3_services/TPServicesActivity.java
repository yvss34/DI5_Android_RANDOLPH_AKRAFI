package com.systemesmobiles.akrafi_randolph_tp3_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.systemesmobiles.akrafi_randolph_tp3_services.interfaces.IBackgroundService;
import com.systemesmobiles.akrafi_randolph_tp3_services.interfaces.IBackgroundServiceListener;

import java.util.Date;

public class TPServicesActivity extends AppCompatActivity {

    //Attributs globale
    IBackgroundService monservice = null;
    ServiceConnection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp_services);

        //Attribut du layout
        final EditText champDeTexte = (EditText) findViewById(R.id.editText_main);
        Button buttonStart = (Button) findViewById(R.id. buttonStart);
        Button buttonConnexion = (Button) findViewById(R.id. buttonConnexion);
        Button buttonDeconnexion = (Button) findViewById(R.id. buttonDeconnexion);
        Button buttonStop = (Button) findViewById(R.id. buttonStop);

        //Attributs
        TPServicesActivity context = this;
        Intent intent = new Intent(this, BackgroundService.class);

        //Création des listeners
        IBackgroundServiceListener listener = new IBackgroundServiceListener() {
            public void dataChanged(final Object data) {
                context.runOnUiThread(new Runnable() {
                    public void run() {
                        // Mise à jour de l'UI
                        final Date date = (Date) data;
                        champDeTexte.setText((String.valueOf(date.getHours()) + ":" + String.valueOf(date.getMinutes()) + ":" + String.valueOf(date.getSeconds())));
                    }
                });
            }
        };

        //Création de l’objet Connexion
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("BackgroundService", "Connected!");
                monservice = ((BackgroundServiceBinder)service).getService();
                monservice.addListener(listener);
            }
            public void onServiceDisconnected(ComponentName name) {
                Log.i("BackgroundService", "Disconnected!");
            }
        };

        //action sur le boutton Start
        buttonStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) { startService(intent); }
        });

        //action sur le boutton Connexion
        buttonConnexion.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                bindService(intent,connection,BIND_AUTO_CREATE);
            }
        });

        //action sur le boutton Deconnexion
        buttonDeconnexion.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (monservice != null) {
                    if (monservice.getListeners() != null) {
                        if (!monservice.getListeners().isEmpty()) {
                            unbindService(connection);
                            monservice.removeListener(listener);
                        }
                    }
                }
            }
        });

        //action sur le boutton Stop
        buttonStop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (monservice != null) {
                    if (monservice.getListeners() != null) {
                        if (!monservice.getListeners().isEmpty()) {
                            for (IBackgroundServiceListener listener : monservice.getListeners()) {
                                unbindService(connection);
                                monservice.removeListener(listener);
                            }
                        }
                        stopService(intent);
                    }
                }
            }
        });

    }
}