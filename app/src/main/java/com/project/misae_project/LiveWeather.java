package com.project.misae_project;


import android.os.AsyncTask;
import android.util.Log;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static com.project.misae_project.MainActivity.stationName;



public class LiveWeather extends AsyncTask<String, String, String> {

    //private DatabaseReference mDatabase;
    private String str, receiveMsg;
    public static String[] arrayWeather;


    private String  date = null;
    private String  time = null;
    private String  t1h = null;


    @Override
    protected String doInBackground(String... strings) {

        URL url = null;
        String serviceKey = "oBOYn4oygtbvPGCZWEQu6Sysiao90s3i%2B%2BCUaxguAZ5Pb1tOHI3uuXTH8gKZOd%2FbMXMRm5%2FxVy8Muac3ToEiXA%3D%3D";

        try {
            url = new URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?ServiceKey="+serviceKey+"&base_date=20190510&base_time=0600&nx=62&ny=111&pageNo=1&numOfRows=4&_type=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }

    public String[] listjsonParserWeather(String jsonString) {

        String  date = null;
        String  t1h = null;
        String  time = null;


        arrayWeather = new String[3];
        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("item");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);

                if (i == 3) {
                    date = jObject.optString("baseDate");
                    time = jObject.optString("baseTime");
                    t1h = jObject.optString("obsrValue");


                    arrayWeather[0] = date;
                    arrayWeather[1] = time;
                    arrayWeather[2] = t1h;

                    Log.d("t1h",arrayWeather[2]);

                }


            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return arrayWeather;
    }

    public void setLW() {

        date = LiveWeather.arrayWeather[0];
        time = LiveWeather.arrayWeather[1];
        t1h = LiveWeather.arrayWeather[2];



//        MainActivity.textView4.setText(
//
//                "측정소명 : " + stationName + "\n" +
//                        "date : " + date + "\n" +
//                        "time : " + time + "\n" +
//                        "temperature : " + t1h + "\n"
//
//        );



    }

}


