package com.systemesmobiles.tp5_part6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity);

        //récupération des attributs du Layout
        TextView text = (TextView) findViewById(R.id.textView);

        //Recuperation de l'intent
        Intent intent = this.getIntent();

        // Conteneur qui vas nous permettre de recuperer l'objet personne
        Bundle objetbunble = intent.getExtras();

        // récupération de la valeur
        String infoPasse = objetbunble.getString("passInfo");

        // on afffiche l'information dans les TextView
        text.setText(infoPasse);
    }
}
