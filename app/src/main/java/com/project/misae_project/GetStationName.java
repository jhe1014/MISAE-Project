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

import static com.project.misae_project.MainActivity.stationName;
import static com.project.misae_project.MainActivity.xTM;
import static com.project.misae_project.MainActivity.yTM;


public class GetStationName extends AsyncTask<String, String, String> {

    private String str, receiveMsg;
    public static String[] arraysum;


    @Override
    protected String doInBackground(String... strings) {

        URL url = null;
        String serviceKey = "oBOYn4oygtbvPGCZWEQu6Sysiao90s3i%2B%2BCUaxguAZ5Pb1tOHI3uuXTH8gKZOd%2FbMXMRm5%2FxVy8Muac3ToEiXA%3D%3D";

        try {
            url = new URL("http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX=" + xTM + "&tmY=" + yTM + "&pageNo=1&numOfRows=10&ServiceKey=" + serviceKey
                    + "&_returnType=json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


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

    public void listjsonParserSt(String jsonString) {

        try {
            JSONArray jarraySt = new JSONObject(jsonString).getJSONArray("list");

            JSONObject jObject = jarraySt.getJSONObject(0);
            stationName = jObject.optString("stationName");
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}


