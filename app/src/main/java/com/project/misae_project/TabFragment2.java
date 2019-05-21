package com.project.misae_project;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class TabFragment2 extends Fragment {

    private RecyclerView weather_time_HorizontalView;
    private WeatherTimeAdapter weather_time_Adapter;

    /*private RecyclerView weather_date_HorizontalView;
    private WeatherDateAdapter weather_date_Adapter;*/

    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.tab_fragment2, container, false);

        // 시간별 날씨 목록 (가로 RecyclerView)
        View view1 = inflater.inflate(R.layout.weather_time_item, container, false);
        Context context1 = view1.getContext();
        weather_time_HorizontalView = (RecyclerView) rootview.findViewById(R.id.weather_time_listview);

        mLayoutManager = new LinearLayoutManager(context1);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 가로 배치

        weather_time_HorizontalView.setLayoutManager(mLayoutManager);
        weather_time_Adapter = new WeatherTimeAdapter();

        // 시간별 날씨 데이터 삽입부
        ArrayList<WeatherTimeData> data1 = new ArrayList<>();

        int i = 0;
        while (i < 10) {
            data1.add(new WeatherTimeData("시간", R.drawable.sun, "온도"));
            i++;
        }

        weather_time_Adapter.setData(data1);

        weather_time_HorizontalView.setAdapter(weather_time_Adapter);

        return rootview;
    }
}

// 대기 성분 상태 목록에서 사용하는 부분 (여기서부터)
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
// 대기 성분 상태 목록에서 사용하는 부분 (여기까지)
