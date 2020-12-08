package com.example.tp5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URL;

public class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Attribut du layout
        Button btaction = (Button) findViewById(R.id.buttonMy);
        EditText texte = (EditText) findViewById(R.id.EditText);

        btaction.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //mise en place de l'url
                try {
                    URL newurl = new URL("http://www.google.com");
                    //apppel à CallWebAPI
                    CallWebAPI c = new CallWebAPI(texte);
                    c.execute(newurl.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}