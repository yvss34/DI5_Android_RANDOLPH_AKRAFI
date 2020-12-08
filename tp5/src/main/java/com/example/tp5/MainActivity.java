package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attribut du layout
        Button btaction = (Button) findViewById(R.id. button);
        TextView texte = (TextView) findViewById(R.id.textView);


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
//        //action sur le bouton
//        btaction.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//
//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(policy);
//                HttpURLConnection urlConnection =null;
//                try {
//                    URL url = new URL("http://www.google.com/");
//                    urlConnection = (HttpURLConnection) url.openConnection();
//                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                    readStream(in);
//                    urlConnection.disconnect();
//                } catch (Exception e) {
//    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                    urlConnection.disconnect();
//                }
//            }

//            private void readStream(InputStream in) {
//                String text = null;
//                try (Scanner scanner = new Scanner(in, Charset.forName("UTF-8").name())) {
//                    text = scanner.useDelimiter("\\A").next();
//                }
//
//                texte.setText(text);
//            }

//        });


    }


}