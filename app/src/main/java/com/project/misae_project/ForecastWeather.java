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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static com.project.misae_project.MainActivity.stationName;
import static com.project.misae_project.MainActivity.xLoc;
import static com.project.misae_project.MainActivity.yLoc;


public class ForecastWeather extends AsyncTask<String, String, String> {

    private String str, receiveMsg;
    public static String[] arrayWeatherF;


    private String  TMN = null;
    private String  TMX = null;

    @Override
    protected String doInBackground(String... strings) {

        URL url = null;
        String serviceKey = "oBOYn4oygtbvPGCZWEQu6Sysiao90s3i%2B%2BCUaxguAZ5Pb1tOHI3uuXTH8gKZOd%2FbMXMRm5%2FxVy8Muac3ToEiXA%3D%3D";


        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String getTime = sdf.format(date);

        Log.d("날씨예뽀 일자", getTime);

        Log.d("gps 격자전달확인", xLoc+"\n"+ yLoc);
        try {
            url = new URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?ServiceKey="+serviceKey+"&base_date="+getTime+"&base_time=0200&nx="+xLoc+"&ny="+yLoc+"&pageNo=1&numOfRows=155&_type=json");

            Log.d("날씨예보 url",url.toString());


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.d("날씨예보 통데이터 : ", receiveMsg);

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

    public String[] listjsonParserWeatherF(String jsonString) {

        String  date = null;
        String  t1h = null;
        String  time = null;

        arrayWeatherF = new String[4];
        try{



            // JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
            JSONObject jsonObj = new JSONObject(jsonString);
            // JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.

            JSONObject weatherFinitObj = (JSONObject) jsonObj.get("response");
            JSONObject weatherFbodyObj = (JSONObject) weatherFinitObj.get("body");
            JSONObject weatherFitemsObj = (JSONObject) weatherFbodyObj.get("items");

            JSONArray  weatherArray = (JSONArray) weatherFitemsObj.get("item");
            JSONObject weatherFTMN = (JSONObject) weatherArray.get(7);
            JSONObject weatherFTMX = (JSONObject) weatherArray.get(37);


            TMN = weatherFTMN.optString("fcstValue");
            TMX = weatherFTMX.optString("fcstValue");



                    arrayWeatherF[0] = TMN;
                    arrayWeatherF[1] = TMX;

                    Log.d("날씨예뽀","최저온도" + arrayWeatherF[0] +
                            "최대온도" + arrayWeatherF[1]);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        return arrayWeatherF;
    }

    public void setFW() {

        TMN = ForecastWeather.arrayWeatherF[0];
        TMX = ForecastWeather.arrayWeatherF[1];

        Log.d("날씨예뽀 set","최저온도" + arrayWeatherF[0] +
                "최대온도" + arrayWeatherF[1]);


    }

}

