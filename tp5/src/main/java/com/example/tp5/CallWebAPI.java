package com.example.tp5;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallWebAPI extends AsyncTask<String, String, String> {
    private EditText mEditText;
    private TextView mTextView;

    public CallWebAPI(EditText mEditText,TextView mTextView) {
        this.mEditText = mEditText;
        this.mTextView = mTextView;
    }

    @Override
    protected String doInBackground(String... param){
        String inputLine = "";
        GeoIP result = new GeoIP();
        URL url;
        try {
            url = new URL(param[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            XmlPullParserFactory pullParserFactory;
            try{
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                result = parseXML(parser);
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
        mTextView.setText(result);
    }

        private GeoIP parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
            int eventType = parser.getEventType();
            GeoIP result = new GeoIP();
            while( eventType!= XmlPullParser.END_DOCUMENT) {
                String name = null;
                switch(eventType)
                {
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if( name.equals("Error")) {
                            System.out.println("Web API Error!");
                        }
                        else if( name.equals("countryCode")) {
                            result.countryCode=parser.nextText();
                        }
                        else if(name.equals("query")) {
                            result.query= parser.nextText();
                        }
                        else if(name.equals("country")) {
                            result.country= parser.nextText();
                        }
                        else if(name.equals("status")) {
                            result.status= parser.nextText();
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
