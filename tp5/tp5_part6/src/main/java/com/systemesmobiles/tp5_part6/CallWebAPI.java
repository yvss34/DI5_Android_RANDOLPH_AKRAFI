package com.systemesmobiles.tp5_part6;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallWebAPI extends AsyncTask<String, String, String> {
    private GeoIP result;

    public CallWebAPI(GeoIP geoIP) {
        this.result = geoIP;
    }

    @Override
    protected String doInBackground(String... param){
        String inputLine = "";
        String myURL;
        URL url;
        try {
            myURL = "http://ip-api.com/xml/";
            myURL += param[0];
            url = new URL(myURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            XmlPullParserFactory pullParserFactory;
            try{
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                result = parseXML(parser, param[0]);
            }catch(XmlPullParserException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
            in.close();
            return result.toString();
        }catch (Exception ignored){
        }
        return "error";
    }

    protected void onPostExecute(String result){
    }

        private GeoIP parseXML(XmlPullParser parser, String ipuser_adress) throws XmlPullParserException, IOException {
            int eventType = parser.getEventType();
            GeoIP result = new GeoIP();
            while( eventType!= XmlPullParser.END_DOCUMENT) {
                String name;
                switch(eventType)
                {
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        switch (name) {
                            case "Error":
                                System.out.println("Web API Error!");
                                break;
                            case "countryCode":
                                result.countryCode = parser.nextText();
                                break;
                            case "query":
                                result.query = ipuser_adress;
                                break;
                            case "country":
                                result.country = parser.nextText();
                                break;
                            case "status":
                                result.status = parser.nextText();
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                } // end switch
                eventType = parser.next();
            }// end while
            return result;
        }
}
