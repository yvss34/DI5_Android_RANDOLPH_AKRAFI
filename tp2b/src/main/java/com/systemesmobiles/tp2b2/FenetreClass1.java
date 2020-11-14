package com.systemesmobiles.tp2b2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;


public class FenetreClass1 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetreclass1);

        //Attribut du layout
        final EditText nom = (EditText) findViewById(R.id.editTextNom);
        final EditText prenom = (EditText) findViewById(R.id.editTextPrenom);
        final EditText age = (EditText) findViewById(R.id.editTextAge);
        final EditText editTextHelloWorld = (EditText) findViewById(R.id.editTextHelloWorld);
        Button btaction = (Button) findViewById(R.id. btecrire);

        //action sur le bouton click appelle de la nouvelle activité
        btaction.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //création de notre item
                Intent defineIntent = new Intent(FenetreClass1.this, FenetreClass2.class);

                //Creation de l'objet Personne
                Personne personne = new Personne(nom.getText().toString(),prenom.getText().toString(),age.getText().toString());

                // Conteneur qui vas nous permettre de passer l'objet personne
                Bundle objetbunble = new Bundle();
                objetbunble .putSerializable("personne",personne);
                objetbunble .putString("passInfo",editTextHelloWorld.getText().toString());

                // on insere le bundle dans l'intent
                defineIntent.putExtras(objetbunble);

                // on appelle notre activité avec intention d'attente d'un resultat
                startActivityForResult(defineIntent, 0);
            }
        });
    }

    /*
    Fonction permettant de capter les informations envoyées par la fenêtre 2
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Si le résultat provient d’une demande de la fenêtre1
        if (requestCode == 0) {
            // le code retour est bon
            if (resultCode == 1) {
                //récupération du text dans le champ de saisie
                final TextView textChampSaisie = (EditText) findViewById(R.id.editTextHelloWorld);
                // récupération de la valeur
                String InfoPasse= data.getStringExtra("passInfoBack");
                textChampSaisie.setText(InfoPasse);
            }
        }
    }
}