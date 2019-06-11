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
import java.util.Date;
import java.util.HashMap;

import static com.project.misae_project.MainActivity.stationName;
import static com.project.misae_project.MainActivity.xLoc;
import static com.project.misae_project.MainActivity.yLoc;


public class LiveWeather extends AsyncTask<String, String, String> {

    private String str, receiveMsg;
    public static String[] arrayWeather;


    private String  date = null;
    private String  time = null;
    private String  t1h = null;


    @Override
    protected String doInBackground(String... strings) {

        URL url = null;
        String serviceKey = "Pnj%2BWzSwNtHcFfXTFJ%2F3sYzJcWn23IzwKpn%2BBVeycUTgR0dxqTnSdyAXCcuV3jYBVzBh93wlcCyRC49zyf%2Bc2A%3D%3D";


        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String getTime = sdf.format(date);

        Log.d("날씨 일자", getTime);


        Log.d("gps 격자전달확인", xLoc+"\n"+ yLoc);
        try {
            url = new URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?ServiceKey="+serviceKey+"&base_date="+getTime+"&base_time=0000&nx="+xLoc+"&ny="+yLoc+"&pageNo=1&numOfRows=4&_type=json");

         Log.d("날씨 url",url.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.d("날씨 통데이터 : ", receiveMsg);

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
        try{

            // JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
            JSONObject jsonObj = new JSONObject(jsonString);
            // JSONObject에서 PersonsArray를 get하여 JSONArray에 저장한다.

            JSONObject weatherinitObj = (JSONObject) jsonObj.get("response");
            JSONObject weatherbodyObj = (JSONObject) weatherinitObj.get("body");
            JSONObject weatheritemsObj = (JSONObject) weatherbodyObj.get("items");

            JSONArray  weatherArray = (JSONArray) weatheritemsObj.get("item");

            for (int i = 0; i < weatherArray.length(); i++) {
                JSONObject temperObject = (JSONObject) weatherArray.get(i);

                if (i == 3) {
                    date = temperObject.optString("baseDate");
                    time = temperObject.optString("baseTime");
                    t1h =  temperObject.optString("obsrValue");


                    arrayWeather[0] = date;
                    arrayWeather[1] = time;
                    arrayWeather[2] = t1h;

                    Log.d("날씨","날짜" + arrayWeather[0] +
                                           "발표시각" + arrayWeather[1] +
                                           "온도" + arrayWeather[2]);

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

        Log.d("날씨","날짜" + arrayWeather[0] +
                "발표시각" + arrayWeather[1] +
                "온도" + arrayWeather[2]);


    }

}


