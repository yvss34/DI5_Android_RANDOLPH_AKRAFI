package com.systemesmobiles.tp2b2;

import android.app.Person;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/*
    Classe Personne implemente serialisable pour que l'on puisse l'inserer dans un intent
 */
public class Personne implements Serializable {

    //Attributs
    private String nom,prenom;
    private String age;

    //Getters
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAge() {
        return age;
    }

    //Constructeur de confort
    public Personne(String pNom, String pPrenom, String pAge){
        nom = pNom;
        prenom = pPrenom;
        age = pAge;
    }
}
