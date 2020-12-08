package com.systemesmobiles.tp5_part6;

import androidx.appcompat.app.AppCompatActivity;

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
        TextView responseTexte = (TextView) findViewById(R.id.textView);

        btaction.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //mise en place de l'url
                try {
                    URL newurl = new URL("http://www.google.com");
                    //apppel à CallWebAPI
                    CallWebAPI c = new CallWebAPI(responseTexte);
                    c.execute(requestTexte.getText().toString().toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}