package com.project.misae_project;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class TabFragment2 extends Fragment {

    private RecyclerView weather_time_HorizontalView;
    private WeatherTimeAdapter weather_time_Adapter;

    private RecyclerView weather_date_HorizontalView;
    private WeatherDateAdapter weather_date_Adapter;

    private LinearLayoutManager mLayoutManager;

    private TextView weather_now;
    private TextView weather_now_comment;
    private TextView weather_max_temp;
    private TextView weather_min_temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.tab_fragment2, container, false);

        // 현재 날씨
        weather_now = (TextView)rootview.findViewById(R.id.weather_text);
        weather_now_comment = (TextView)rootview.findViewById(R.id.weather_comment);
        weather_max_temp = (TextView)rootview.findViewById(R.id.max_temp);
        weather_min_temp = (TextView)rootview.findViewById(R.id.min_temp);

        try {
            new JSONTask().execute("http://192.168.8.1:3000/misae_db");
        } catch (Exception e) {

        }

        // 시간별 날씨 목록 (가로 RecyclerView)
        View view1 = inflater.inflate(R.layout.weather_time_item, container, false);
        Context context1 = view1.getContext();
        weather_time_HorizontalView = (RecyclerView) rootview.findViewById(R.id.weather_time_listview);

        mLayoutManager = new LinearLayoutManager(context1);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 가로 배치

        weather_time_HorizontalView.setLayoutManager(mLayoutManager);
        weather_time_Adapter = new WeatherTimeAdapter();

        // 일별 날씨 목록 (세로 RecyclerView)
        View view2 = inflater.inflate(R.layout.air_date_item, container, false);
        Context context2 = view2.getContext();
        weather_date_HorizontalView = (RecyclerView) rootview.findViewById(R.id.weather_date_listview);

        mLayoutManager = new LinearLayoutManager(context2);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 세로 배치

        weather_date_HorizontalView.setLayoutManager(mLayoutManager);
        weather_date_Adapter = new WeatherDateAdapter();

        // 시간별 날씨 데이터 삽입부
        ArrayList<WeatherTimeData> data1 = new ArrayList<>();

        int i = 0;
        while (i < 10) {
            data1.add(new WeatherTimeData("시간", R.drawable.sun, "온도"));
            i++;
        }

        weather_time_Adapter.setData(data1);

        weather_time_HorizontalView.setAdapter(weather_time_Adapter);

        // 일별 날씨 데이터 삽입부
        ArrayList<WeatherDateData> data2 = new ArrayList<>();

        int j = 0;
        while (j < 10) {
            data2.add(new WeatherDateData("요일", R.drawable.sun, "최고온도", "최저온도"));
            j++;
        }

        weather_date_Adapter.setData(data2);

        weather_date_HorizontalView.setAdapter(weather_date_Adapter);


        return rootview;
    }

    // server.js 파일 실행부
    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                //jsonObject.accumulate("user_id", "androidTest");
                //jsonObject.accumulate("name", "yun");

                HttpURLConnection con = null;
                BufferedReader reader = null;

                try{
                    //URL url = new URL("http://192.168.25.16:3000/users");
                    URL url = new URL(urls[0]);//url을 가져온다.
                    con = (HttpURLConnection) url.openConnection();
                    con.connect();//연결 수행

                    //입력 스트림 생성
                    InputStream stream = con.getInputStream();

                    //속도를 향상시키고 부하를 줄이기 위한 버퍼를 선언한다.
                    reader = new BufferedReader(new InputStreamReader(stream));

                    //실제 데이터를 받는곳
                    StringBuffer buffer = new StringBuffer();

                    //line별 스트링을 받기 위한 temp 변수
                    String line = "";

                    //아래라인은 실제 reader에서 데이터를 가져오는 부분이다. 즉 node.js서버로부터 데이터를 가져온다.
                    while((line = reader.readLine()) != null){
                        buffer.append(line);
                    }

                    //다 가져오면 String 형변환을 수행한다. 이유는 protected String doInBackground(String... urls) 니까
                    return buffer.toString();

                    //아래는 예외처리 부분이다.
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //종료가 되면 disconnect메소드를 호출한다.
                    if(con != null){
                        con.disconnect();
                    }
                    try {
                        //버퍼를 닫아준다.
                        if(reader != null){
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }//finally 부분
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        //doInBackground메소드가 끝나면 여기로 와서 텍스트뷰의 값을 바꿔준다.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            weather_now.setText(result);
        }

    }
}


// 시간별 날씨 목록에서 사용하는 부분 (여기서부터)
class WeatherTimeAdapter extends RecyclerView.Adapter<WeatherTimeViewHolder> {

    private ArrayList<WeatherTimeData> WeatherTimeDatas;

    public void setData(ArrayList<WeatherTimeData> list) {
        WeatherTimeDatas = list;
    }

    public WeatherTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_time_item, parent, false);

        WeatherTimeViewHolder holder = new WeatherTimeViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(WeatherTimeViewHolder holder, int position) {
        WeatherTimeData data = WeatherTimeDatas.get(position);

        holder.time.setText(data.getName());
        holder.icon.setImageResource(data.getImg());
        holder.temp.setText(data.getCondition());
    }

    public int getItemCount() {
        return WeatherTimeDatas.size();
    }
}

class WeatherTimeViewHolder extends RecyclerView.ViewHolder {
    public TextView time;
    public ImageView icon;
    public TextView temp;

    public WeatherTimeViewHolder(View itemView) {
        super(itemView);

        time = (TextView) itemView.findViewById(R.id.weather_time);
        icon = (ImageView) itemView.findViewById(R.id.weather_time_icon);
        temp = (TextView) itemView.findViewById(R.id.weather_time_temp);
    }
}

class WeatherTimeData {
    private String time;
    private int img;
    private String temp;

    public WeatherTimeData(String name, int img, String temp) {
        this.time = name;
        this.img = img;
        this.temp = temp;
    }

    public String getName() {
        return this.time;
    }

    public int getImg() {
        return this.img;
    }

    public String getCondition() {
        return this.temp;
    }
}
// 시간별 날씨 목록에서 사용하는 부분 (여기까지)

// 일별 날씨 목록에서 사용하는 부분 (여기서부터)
class WeatherDateAdapter extends RecyclerView.Adapter<WeatherDateViewHolder> {

    private ArrayList<WeatherDateData> WeatherDateDatas;

    public void setData(ArrayList<WeatherDateData> list) {
        WeatherDateDatas = list;
    }

    public WeatherDateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_date_item, parent, false);

        WeatherDateViewHolder holder = new WeatherDateViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(WeatherDateViewHolder holder, int position) {
        WeatherDateData data = WeatherDateDatas.get(position);

        holder.date.setText(data.getDate());
        holder.icon.setImageResource(data.getImg());
        holder.max_temp.setText(data.getMax());
        holder.min_temp.setText(data.getMin());
    }

    public int getItemCount() {
        return WeatherDateDatas.size();
    }
}

class WeatherDateViewHolder extends RecyclerView.ViewHolder {
    public TextView date;
    public ImageView icon;
    public TextView max_temp;
    public TextView min_temp;

    public WeatherDateViewHolder(View itemView) {
        super(itemView);

        date = (TextView) itemView.findViewById(R.id.weather_date_date);
        icon = (ImageView) itemView.findViewById(R.id.weather_date_icon);
        max_temp = (TextView) itemView.findViewById(R.id.weather_date_max);
        min_temp = (TextView) itemView.findViewById(R.id.weather_date_min);
    }
}

class WeatherDateData {
    private String date;
    private int img;
    private String max_temp;
    private String min_temp;

    public WeatherDateData(String date, int img, String max_temp, String min_temp) {
        this.date = date;
        this.img = img;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
    }

    public String getDate() {
        return this.date;
    }

    public int getImg() {
        return this.img;
    }

    public String getMax() {
        return this.max_temp;
    }

    public String getMin() {
        return this.min_temp;
    }
}
// 일별 날씨 목록에서 사용하는 부분 (여기까지)