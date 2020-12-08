package com.systemesmobiles.tp5_part6;

import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class CallWebAPI extends AsyncTask<String, String, String> {
    private TextView mTextView;

    public CallWebAPI(TextView mTextView) {
        this.mTextView = mTextView;
    }

    @Override
    protected String doInBackground(String... param){
        String inputLine = "";
        GeoIP result = new GeoIP();
        String myURL;
        URL url;
        InputStream in = null;
        try {
            myURL = "http://ip-api.com/xml/";
            myURL += param[0];
            HttpClient httpclient = new DefaultHttpClient();

            HttpGet request = new HttpGet();
            URI website = new URI(myURL);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            in = response.getEntity().getContent();

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
            //in.close();
            return result.toString();
        }catch (Exception ignored){
        }
        return "error";
    }

    protected void onPostExecute(String result){
        mTextView.setText(result);
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
