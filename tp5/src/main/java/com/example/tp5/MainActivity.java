package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attribut du layout
        Button btaction = (Button) findViewById(R.id. button);
        TextView texte = (TextView) findViewById(R.id.textView);

        //action sur le bouton click appelle de la nouvelle activité
        btaction.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                HttpURLConnection urlConnection =null;
                try {
                    URL url = new URL("http://www.google.com/");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    readStream(in);
                    urlConnection.disconnect();
                } catch (Exception e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                    urlConnection.disconnect();
                }
            }

            private void readStream(InputStream in) {
                String text = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)))lines().collect(Collectors.joining("\n"));
                texte.setText(text);
            }
        });


    }


}