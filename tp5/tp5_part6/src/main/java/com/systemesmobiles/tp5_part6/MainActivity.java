package com.systemesmobiles.tp5_part6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attribut du layout
        Button btaction = (Button) findViewById(R.id.buttonMy);
        EditText requestTexte = (EditText) findViewById(R.id.EditText);

        btaction.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //création de notre item
                Intent defineIntent = new Intent(MainActivity.this, OtherActivity.class);

                GeoIP geoIP = new GeoIP();
                try {
                    //apppel à CallWebAPI
                    CallWebAPI c = new CallWebAPI(geoIP);
                    c.execute(requestTexte.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Conteneur qui vas nous permettre de passer l'objet personne
                Bundle objetbunble = new Bundle();
                objetbunble.putString("passInfo", geoIP.toString());
                // on insere le bundle dans l'intent
                defineIntent.putExtras(objetbunble);
                // on appelle notre activité avec intention d'attente d'un resultat
                startActivity(defineIntent);
            }
        });

    }


}