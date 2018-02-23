package com.example.nishad.bustrack;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener {
    private TrackGPS gps;
    double lat,lot;
    Button mBreakButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mBreakButton = (Button) findViewById(R.id.breakbt);

        mBreakButton.setOnClickListener(this);

        try {
            gps = new TrackGPS(com.example.nishad.bustrack.LocationActivity.this);
            if (gps.canGetLocation()) {
                lat = gps.getLongitude();
                lot = gps.getLatitude();
                Toast.makeText(LocationActivity.this, lat + "result" + lot,
                        Toast.LENGTH_LONG).show();
            }
        }catch(Exception e){}
        new SendRequest().execute();

    }

    @Override
    public void onClick(View view) {

        class SendRequest1 extends AsyncTask<String, Void, String> {
            protected void onPreExecute(){}

            protected String doInBackground(String... arg0) {


                try{
//                Toast.makeText(Det.this,"Hai",Toast.LENGTH_LONG);

                    //  lat=9.78;
                    //   lot=96.67;
                    SharedPreferences MyPref;
                    SharedPreferences dta =getSharedPreferences("MyPref", MODE_PRIVATE);
                    String id1=dta.getString("idd2",null);
                    //  URL url = new URL("http://10.0.2.2:8080/test/test2.jsp");
                    String urlParameters =
                            "fName=" + URLEncoder.encode("???", "UTF-8") +
                                    "&lName=" + URLEncoder.encode("???", "UTF-8");


                    //URL url = new URL("http://10.0.2.2:8080/women/test.jsp");
                    URL url = new URL("http://192.168.42.86/bus/getstop.php");
                    JSONObject postDataParams = new JSONObject();
                    //    postDataParams.put("rid",id1);
                    postDataParams.put("lat",lat);
                    postDataParams.put("lot",lot);
                    //  postDataParams.put("email", "abhay@gmail.com");

                    Log.e("params",postDataParams.toString());

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    // connection.setRequestProperty("Content-Length", "" +
                    //Integer.toString(urlParameters.getBytes().length));
                    connection.setRequestProperty("Content-Language", "en-US");
                    connection.setUseCaches (false);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
//Send request
                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(getPostDataString(postDataParams));
                    // wr.write(getPostDataString(postDataParams));
                    writer.flush ();
                    writer.close ();
//Get Response
                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');

                        // return str;
                    }
                    return response.toString();
                }
                catch(Exception e){
                    e.printStackTrace();
                    return new String("Exception: " + e.getMessage());


                }
            }

        }

    }

    public class SendRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {


            try{
//                Toast.makeText(Det.this,"Hai",Toast.LENGTH_LONG);

                //  lat=9.78;
                //   lot=96.67;
                SharedPreferences MyPref;
                SharedPreferences dta =getSharedPreferences("MyPref", MODE_PRIVATE);
                String id1=dta.getString("idd2",null);
                //  URL url = new URL("http://10.0.2.2:8080/test/test2.jsp");
                String urlParameters =
                        "fName=" + URLEncoder.encode("???", "UTF-8") +
                                "&lName=" + URLEncoder.encode("???", "UTF-8");


                //URL url = new URL("http://10.0.2.2:8080/women/test.jsp");
                URL url = new URL("http://192.168.42.86/bus/getstop.php");
                JSONObject postDataParams = new JSONObject();
                //    postDataParams.put("rid",id1);
                postDataParams.put("lat",lat);
                postDataParams.put("lot",lot);
                //  postDataParams.put("email", "abhay@gmail.com");

                Log.e("params",postDataParams.toString());

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                // connection.setRequestProperty("Content-Length", "" +
                //Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");
                connection.setUseCaches (false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
//Send request
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                // wr.write(getPostDataString(postDataParams));
                writer.flush ();
                writer.close ();
//Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');

                    // return str;
                }
                return response.toString();
            }
            catch(Exception e){
                e.printStackTrace();
                return new String("Exception: " + e.getMessage());


            }
        }

    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
