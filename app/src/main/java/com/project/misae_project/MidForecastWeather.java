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


public class MidForecastWeather extends AsyncTask<String, String, String> {

    private String str, receiveMsg;
    public static String[] arrayWeatherM;



    private String  Min3 = null;
    private String  Min4 = null;
    private String  Min5 = null;
    private String  Min6 = null;
    private String  Min7 = null;
    private String  Min8 = null;
    private String  Min9 = null;
    private String  Min10 = null;

    private String  Max3 = null;
    private String  Max4 = null;
    private String  Max5 = null;
    private String  Max6 = null;
    private String  Max7 = null;
    private String  Max8 = null;
    private String  Max9 = null;
    private String  Max10 = null;



    @Override
    protected String doInBackground(String... strings) {

        URL url = null;
        String serviceKey = "oBOYn4oygtbvPGCZWEQu6Sysiao90s3i%2B%2BCUaxguAZ5Pb1tOHI3uuXTH8gKZOd%2FbMXMRm5%2FxVy8Muac3ToEiXA%3D%3D";


        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String getTime = sdf.format(date);



        Log.d("gps 격자전달확인", xLoc+"\n"+ yLoc);
        try {
            url = new URL("http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/getMiddleTemperature?ServiceKey="+serviceKey+"&regId=11C20302&tmFc="+getTime+"0600&pageNo=1&numOfRows=155&_type=json");

            Log.d("날씨중단기예보 url",url.toString());


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.d("날씨초단기예보 통데이터 : ", receiveMsg);

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

    public String[] listjsonParserWeatherM(String jsonString) {


        arrayWeatherM = new String[16];
        try{



            // JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
            JSONObject jsonObj = new JSONObject(jsonString);
            // JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.

            JSONObject weatherLMinitObj = (JSONObject) jsonObj.get("response");
            JSONObject weatherLMbodyObj = (JSONObject) weatherLMinitObj.get("body");
            JSONObject weatherLMitemsObj = (JSONObject) weatherLMbodyObj.get("items");
            JSONObject weatherLMObjR = (JSONObject) weatherLMitemsObj.get("item");




            Min3 = weatherLMObjR.optString("taMin3");
            Min4 = weatherLMObjR.optString("taMin4");
            Min5 = weatherLMObjR.optString("taMin5");
            Min6 = weatherLMObjR.optString("taMin6");
            Min7 = weatherLMObjR.optString("taMin7");
            Min8 = weatherLMObjR.optString("taMin8");
            Min9 = weatherLMObjR.optString("taMin9");
            Min10 = weatherLMObjR.optString("taMin10");

            Max3 = weatherLMObjR.optString("taMax3");
            Max4 = weatherLMObjR.optString("taMax4");
            Max5 = weatherLMObjR.optString("taMax5");
            Max6 = weatherLMObjR.optString("taMax6");
            Max7 = weatherLMObjR.optString("taMax7");
            Max8 = weatherLMObjR.optString("taMax8");
            Max9 = weatherLMObjR.optString("taMax9");
            Max10 = weatherLMObjR.optString("taMax10");


            arrayWeatherM[0] = Min3+".0"+"°C";
            arrayWeatherM[1] = Min4+".0"+"°C";
            arrayWeatherM[2] = Min5+".0"+"°C";
            arrayWeatherM[3] = Min6+".0"+"°C";
            arrayWeatherM[4] = Min7+".0"+"°C";
            arrayWeatherM[5] = Min8+".0"+"°C";
            arrayWeatherM[6] = Min9+".0"+"°C";
            arrayWeatherM[7] = Min10+".0"+"°C";

            arrayWeatherM[8] = Max3+".0"+"°C";
            arrayWeatherM[9] = Max4+".0"+"°C";
            arrayWeatherM[10] = Max5+".0"+"°C";
            arrayWeatherM[11] = Max6+".0"+"°C";
            arrayWeatherM[12] = Max7+".0"+"°C";
            arrayWeatherM[13] = Max8+".0"+"°C";
            arrayWeatherM[14] = Max9+".0"+"°C";
            arrayWeatherM[15] = Max10+".0"+"°C";


            Log.d("날씨중단기",arrayWeatherM[0]+" "+arrayWeatherM[8]);


        } catch (JSONException e) {
            e.printStackTrace();
        }



        return arrayWeatherM;
    }

    public void setWM() {
        Log.d("날씨중단기",arrayWeatherM[0]+" "+arrayWeatherM[8]);


    }

}


