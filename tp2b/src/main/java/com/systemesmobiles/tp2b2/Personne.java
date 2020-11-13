package com.systemesmobiles.tp2b2;

import android.app.Person;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Personne implements Serializable {

    private String nom,prenom;
    private String age;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAge() {
        return age;
    }

    public Personne(){
    }

    public Personne(String pNom, String pPrenom, String pAge){
        nom = pNom;
        prenom = pPrenom;
        age = pAge;
    }
}
