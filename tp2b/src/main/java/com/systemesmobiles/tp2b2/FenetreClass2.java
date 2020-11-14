package com.systemesmobiles.tp2b2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FenetreClass2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetreclass2);

        //récupération des attributs du Layout
        TextView textNom = (TextView) findViewById(R.id.TextViewNom);
        TextView textPrenom = (TextView) findViewById(R.id.TextViewPrenom);
        TextView textAge = (TextView) findViewById(R.id.TextViewAge);

        //Recuperation de l'intent
        Intent intent = this.getIntent();
        // Conteneur qui vas nous permettre de recuperer l'objet personne
        Bundle objetbunble = intent.getExtras();
        // récupération de la valeur
        Personne personne = (Personne)objetbunble.getSerializable("personne");

        // on afffiche l'information dans les TextView
        textNom .setText(personne.getNom());
        textPrenom .setText(personne.getPrenom());
        textAge .setText(personne.getAge());

        //action sur le bouton permettant de revenir à la fenêtre 1 et envoi un resultat
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               // Creation d'un conteneur et ajout de la variable HelloWorld
                Bundle objetbunble = new Bundle();
                objetbunble .putString("passInfoBack", "HelloWorld");

                // on insere le bundle dans l'intent
                intent.putExtras(objetbunble);

                //Envoie le résultat suivant l’intent défini.
                setResult(1,intent);
                //Demande la destruction de la fenêtre courante
                finish();
            }
        });

    }
}
