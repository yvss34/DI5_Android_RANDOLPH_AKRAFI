package com.systemesmobiles.tp2b2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FenetreClass2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetre_class_2);
        Activity lecontext = this;
        lecontext.setTitle("Fenetre 2");

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });

//récupération du text dans le champ de saisie
        final EditText textchampsaisie = (EditText) findViewById(R.id.editTextTextPersonName2);
        Bundle objetbunble = this.getIntent().getExtras();
// récupération de la valeur
        String InfoPasse= objetbunble .getString("passInfo");
// on afffiche l'information dans l'edittext
        textchampsaisie .setText(InfoPasse);
    }
}
