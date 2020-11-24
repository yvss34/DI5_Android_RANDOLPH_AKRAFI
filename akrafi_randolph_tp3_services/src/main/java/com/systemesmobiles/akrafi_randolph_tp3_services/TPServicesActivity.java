package com.systemesmobiles.akrafi_randolph_tp3_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.systemesmobiles.akrafi_randolph_tp3_services.interfaces.IBackgroundServiceListener;

import java.util.Date;

public class TPServicesActivity extends AppCompatActivity {

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

        Intent intent = new Intent(this, BackgroundService.class);
        TPServicesActivity activity = this;

        //action sur le boutton Start
        buttonStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) { startService(intent); }
        });

        //action sur le boutton Connexion
        buttonConnexion.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity,BackgroundService.class);
                //Création des listeners
                IBackgroundServiceListener listener = new IBackgroundServiceListener() {
                    public void dataChanged(final Object data) {
                        this.runOnUiThread(new Runnable() {
                            public void run() {
                                // Mise à jour de l'UI
                                final Date date = (Date) data;
                                champDeTexte.setText((String.valueOf(date.getHours()) + ":" + String.valueOf(date.getMinutes()) + ":" + String.valueOf(date.getSeconds())));
                            }
                        });
                    }
                };
            }
        });

        //action sur le boutton Deconnexion
        buttonDeconnexion.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                unbindService(connection);
                service.removeListener(listener);
            }
        });

        //action sur le boutton Stop
        buttonStop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) { stopService(intent); }
        });




    }
}