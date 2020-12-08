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
                try {
                    //apppel à CallWebAPI
                    CallWebAPI c = new CallWebAPI(MainActivity.this);
                    c.execute(requestTexte.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


}