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


public class LiveAtmosphere extends AsyncTask<String, String, String> {

    private String str, receiveMsg;
    public static String[] arraysum;


    String  date = null;
    String  pm10 = null;
    String  pm25 = null;
    String  co = null;
    String  no2 = null;
    String  so2 = null;
    String  o3 = null;
    String  mang = null;

    @Override
    protected String doInBackground(String... strings) {

        URL url = null;
        String serviceKey = "oBOYn4oygtbvPGCZWEQu6Sysiao90s3i%2B%2BCUaxguAZ5Pb1tOHI3uuXTH8gKZOd%2FbMXMRm5%2FxVy8Muac3ToEiXA%3D%3D";

        try {
            url = new URL("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName="
                    +stationName+"&dataTerm=month&pageNo=1&numOfRows=1&ServiceKey="+serviceKey+"&ver=1.3&_returnType=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            Log.d("미세먼지 url",url.toString());

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();

                reader.close();
            } else {
                Log.d("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }

    public String[] listjsonParser(String jsonString) {

        String  date = null;
        String  pm10 = null;
        String  pm25 = null;
        String  co = null;
        String  no2 = null;
        String  so2 = null;
        String  o3 = null;
        String  mang = null;


        arraysum = new String[8];
        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("list");
            for (int i = 0; i < jarray.length(); i++) {
                HashMap map = new HashMap<>();
                JSONObject jObject = jarray.getJSONObject(i);

                date = jObject.optString("dataTime");
                pm10 = jObject.optString("pm10Value");
                pm25 = jObject.optString("pm25Value");
                co = jObject.optString("coValue");
                no2 = jObject.optString("no2Value");
                so2 = jObject.optString("so2Value");
                o3 = jObject.optString("o3Value");
                mang = jObject.optString("mangName");

                arraysum[0] = date;
                arraysum[1] = pm10;
                arraysum[2] = pm25;
                arraysum[3] = co;
                arraysum[4] = no2;
                arraysum[5] = so2;
                arraysum[6] = o3;
                arraysum[7] = mang;


            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return arraysum;
    }

    public void setLA() {

        date = LiveAtmosphere.arraysum[0];
        pm10 = LiveAtmosphere.arraysum[1];
        pm25 = LiveAtmosphere.arraysum[2];
        co = LiveAtmosphere.arraysum[3];
        no2 = LiveAtmosphere.arraysum[4];
        so2 = LiveAtmosphere.arraysum[5];
        o3 = LiveAtmosphere.arraysum[6];
        mang = LiveAtmosphere.arraysum[7];


        Log.d("미세먼지 수치","측정소명 :" + stationName + "\n" +
        "date : " + date + "\n" +
                "pm10 : " + pm10 + "\n" +
                "pm2.5 : " + pm25 + "\n" +
                "co : " + co + "\n" +
                "no2 : " + no2 + "\n" +
                "so2 : " + so2 + "\n" +
                "o3 : " + o3 + "\n");







    }

}


