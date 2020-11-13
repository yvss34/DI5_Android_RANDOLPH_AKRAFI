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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Si le résultat provient d’une demande de la fenêtre1
        if (requestCode == 0) {
        // le code retour est bon
            if (resultCode == 1) {
                //récupération du text dans le champ de saisie
                final EditText textchampsaisie = (EditText) findViewById(R.id.editText1);
                // récupération de la valeur
                String InfoPasse= data.getStringExtra("passInfoBack");
                textchampsaisie.setText(InfoPasse);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetre_class1);
        //lecontext = this;

        //récupération du text dans le champ de saisie
        final EditText nom = (EditText) findViewById(R.id.editTextNom);
        final EditText prenom = (EditText) findViewById(R.id.editTextPrenom);
        final EditText age = (EditText) findViewById(R.id.editTextAge);
        Button btaction = (Button) findViewById(R.id. btecrire);

        //action sur le bouton click appelle de la nouvelle activité
        btaction.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //création de notre item
                Intent defineIntent = new Intent(FenetreClass1.this, FenetreClass2.class);
                //Creation de l'objet Personne
                Personne personne = new Personne(nom.getText().toString(),prenom.getText().toString(),age.getText().toString());
                Personne person = new Personne();
                // objet qui vas nous permettre de passe des variables ici la variable passInfo
                Bundle objetbunble = new Bundle();
                objetbunble .putSerializable("person",personne);
                // on passe notre objet a notre activities

                defineIntent.putExtras(objetbunble);
                // on appelle notre activité
                startActivityForResult(defineIntent, 0);
            }
        });
    }
}