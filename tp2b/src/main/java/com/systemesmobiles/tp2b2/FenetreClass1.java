package com.systemesmobiles.tp2b2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;


public class FenetreClass1 extends AppCompatActivity {

    //Activity lecontext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetre_class1);
        //lecontext = this;

        //récupération du text dans le champ de saisie
        final EditText textchampsaisie = (EditText) findViewById(R.id.editText1);
        Button btaction = (Button) findViewById(R.id. btecrire);

        //action sur le bouton click appelle de la nouvelle activité
        btaction.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //création de notre item
                Intent defineIntent = new Intent(FenetreClass1.this, FenetreClass2.class);
                // objet qui vas nous permettre de passe des variables ici la variable passInfo
                Bundle objetbunble = new Bundle();
                objetbunble .putString("passInfo",textchampsaisie.getText().toString());
                // on passe notre objet a notre activities
                defineIntent.putExtras(objetbunble );
                // on appelle notre activité
                startActivity(defineIntent);
            }
        });
    }
}