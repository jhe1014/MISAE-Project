package com.project.misae_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class TabFragment1 extends Fragment {

    private RecyclerView air_con_HorizontalView;
    private AirConAdapter air_con_Adapter;

    private RecyclerView air_time_HorizontalView;
    private AirTimeAdapter air_time_Adapter;

    private RecyclerView air_date_HorizontalView;
    private AirDateAdapter air_date_Adapter;

    private LinearLayoutManager mLayoutManager;

    TextView time;
    TextView location;

    TextView con;
    TextView comment;

    int cImg = R.drawable.emoji_sogood;
    String cCon = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.tab_fragment1, container, false);

        // 현재위치 출력
        location = (TextView) rootview.findViewById(R.id.misae_location);
        location.setText(MainActivity.addM1+" "+MainActivity.addM2);

        // 현재시간 출력 (Runnable 사용하지 않았기 때문에 자동 시간 카운트는 하지 않음)
        time = (TextView) rootview.findViewById(R.id.time_now1);

        TimeZone tz;
        Date mDate = new Date();
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        tz = TimeZone.getTimeZone("Asia/Seoul");
        mFormat.setTimeZone(tz);

        String time_now = mFormat.format(mDate);
        time.setText(time_now);

        // 맨위 상태 출력 부분
        con = rootview.findViewById(R.id.big_con1);
        con.setText("좋음");

        // 코멘트 출력 부분
        comment = rootview.findViewById(R.id.misae_comment);
        comment.setText("오늘은 외출해도 좋을 것 같아요!");

        //Log.d("주소", MainActivity.addM1+" "+MainActivity.addM2);

        // 대기 성분들 상태 목록 (가로 RecyclerView)
        View view1 = inflater.inflate(R.layout.air_condition_item, container, false);
        Context context1 = view1.getContext();
        air_con_HorizontalView = (RecyclerView) rootview.findViewById(R.id.air_condition_listview);

        mLayoutManager = new LinearLayoutManager(context1);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 가로 배치

        air_con_HorizontalView.setLayoutManager(mLayoutManager);
        air_con_Adapter = new AirConAdapter();

        // 시간별 미세먼지 상태 (가로 RecyclerView)
        View view2 = inflater.inflate(R.layout.air_time_item, container, false);
        Context context2 = view2.getContext();
        air_time_HorizontalView = (RecyclerView) rootview.findViewById(R.id.air_time_listview);

        mLayoutManager = new LinearLayoutManager(context2);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 가로 배치

        air_time_HorizontalView.setLayoutManager(mLayoutManager);
        air_time_Adapter = new AirTimeAdapter();

        // 일별 미세먼지 상태 (세로 RecyclerView)
        View view3 = inflater.inflate(R.layout.air_date_item, container, false);
        Context context3 = view3.getContext();
        air_date_HorizontalView = (RecyclerView) rootview.findViewById(R.id.air_date_listview);

        mLayoutManager = new LinearLayoutManager(context2);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 가로 배치

        air_date_HorizontalView.setLayoutManager(mLayoutManager);
        air_date_Adapter = new AirDateAdapter();

        // 대기 성분 상태 데이터 삽입부
        ArrayList<AirListData> data1 = new ArrayList<>();

        if (Integer.parseInt(LiveAtmosphere.arraysum[1]) >= 0 || Integer.parseInt(LiveAtmosphere.arraysum[1]) <= 30) {
            cImg = R.drawable.emoji_good;
            cCon = "좋음";
        } else if (Integer.parseInt(LiveAtmosphere.arraysum[1]) >= 31 || Integer.parseInt(LiveAtmosphere.arraysum[1]) <= 50) {
            cImg = R.drawable.emoji_soso;
            cCon = "보통";
        } else if (Integer.parseInt(LiveAtmosphere.arraysum[1]) >= 51 || Integer.parseInt(LiveAtmosphere.arraysum[1]) <= 100) {
            cImg = R.drawable.emoji_bad;
            cCon = "나쁨";
        } else {
            cImg = R.drawable.emoji_sobad;
            cCon = "매우나쁨";
        }

        data1.add(new AirListData("미세먼지", cImg, cCon, LiveAtmosphere.arraysum[1]+"㎍/㎥"));

        if (Integer.parseInt(LiveAtmosphere.arraysum[1]) >= 0 || Integer.parseInt(LiveAtmosphere.arraysum[1]) <= 15) {
            cImg = R.drawable.emoji_good;
            cCon = "좋음";
        } else if (Integer.parseInt(LiveAtmosphere.arraysum[1]) >= 16 || Integer.parseInt(LiveAtmosphere.arraysum[1]) <= 25) {
            cImg = R.drawable.emoji_soso;
            cCon = "보통";
        } else if (Integer.parseInt(LiveAtmosphere.arraysum[1]) >= 26 || Integer.parseInt(LiveAtmosphere.arraysum[1]) <= 50) {
            cImg = R.drawable.emoji_bad;
            cCon = "나쁨";
        } else {
            cImg = R.drawable.emoji_sobad;
            cCon = "매우나쁨";
        }

        data1.add(new AirListData("초미세먼지", cImg, cCon, LiveAtmosphere.arraysum[2]+"㎍/㎥"));


        data1.add(new AirListData("이산화질소", R.drawable.baseline_tag_faces_black_48, "좋음", LiveAtmosphere.arraysum[4]));
        data1.add(new AirListData("오존", R.drawable.baseline_tag_faces_black_48, "좋음", LiveAtmosphere.arraysum[6]));
        data1.add(new AirListData("일산화탄소", R.drawable.baseline_tag_faces_black_48, "좋음", LiveAtmosphere.arraysum[3]));
        data1.add(new AirListData("아황산가스", R.drawable.baseline_tag_faces_black_48, "좋음", LiveAtmosphere.arraysum[5]));



        air_con_Adapter.setData(data1);

        air_con_HorizontalView.setAdapter(air_con_Adapter);


        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("a h");
        String getTime = sdf.format(date);

        Log.d("미세먼지 시간별", getTime);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);




        // 시간별 미세먼지 상태 데이터 삽입부
        ArrayList<AirTimeData> data2 = new ArrayList<>();

        int j = 0;
        while (j < 10) {
            data2.add(new AirTimeData(sdf.format(cal.getTime())+"시", R.drawable.baseline_tag_faces_black_48, "좋음"));
            cal.add(Calendar.HOUR,  1);
            j++;
        }

        air_time_Adapter.setData(data2);

        air_time_HorizontalView.setAdapter(air_time_Adapter);


        // 일별 미세먼지 상태 데이터 삽입부
        ArrayList<AirDateData> data3 = new ArrayList<>();

        long nowT1 = System.currentTimeMillis();
        Date dateT1 = new Date(nowT1);
        SimpleDateFormat sdfT1 = new SimpleDateFormat("E");

        Calendar calT1 = Calendar.getInstance();
        calT1.setTime(dateT1);


        data3.add(new AirDateData(sdfT1.format(calT1.getTime()), R.drawable.baseline_tag_faces_black_48, "좋음"));
        calT1.add(Calendar.DATE, 1);
        data3.add(new AirDateData(sdfT1.format(calT1.getTime()), R.drawable.baseline_tag_faces_black_48, "좋음"));
        calT1.add(Calendar.DATE, 1);
        data3.add(new AirDateData(sdfT1.format(calT1.getTime()), R.drawable.baseline_tag_faces_black_48, "좋음"));



        air_date_Adapter.setData(data3);

        air_date_HorizontalView.setAdapter(air_date_Adapter);


        return rootview;
    }
}

// 대기 성분 상태 목록에서 사용하는 부분 (여기서부터)
class AirConAdapter extends RecyclerView.Adapter<AirConViewHolder> {

    private ArrayList<AirListData> AirConDatas;

    public void setData(ArrayList<AirListData> list) {
        AirConDatas = list;
    }

    public AirConViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.air_condition_item, parent, false);

        AirConViewHolder holder = new AirConViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(AirConViewHolder holder, int position) {
        AirListData data = AirConDatas.get(position);


        //일별예보
        holder.name.setText(data.getName());
        holder.icon.setImageResource(data.getImg());
        holder.condition.setText(data.getCondition());
        holder.value.setText(data.getValue());
    }

    public int getItemCount() {
        return AirConDatas.size();
    }
}

class AirConViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView icon;
    public TextView condition;
    public TextView value;

    public AirConViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.air_con_name);
        icon = (ImageView) itemView.findViewById(R.id.air_con_icon);
        condition = (TextView) itemView.findViewById(R.id.air_con);
        value = (TextView) itemView.findViewById(R.id.air_con_value);
    }
}

class AirListData {
    private String name;
    private int img;
    private String condition;
    private String value;

    public AirListData(String name, int img, String condition, String value) {
        this.name = name;
        this.img = img;
        this.condition = condition;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getImg() {
        return this.img;
    }

    public String getCondition() {
        return this.condition;
    }

    public String getValue() {
        return this.value;
    }
}
// 대기 성분 상태 목록에서 사용하는 부분 (여기까지)

// 시간별 미세먼지 상태 목록에서 사용하는 부분 (여기서부터)
class AirTimeAdapter extends RecyclerView.Adapter<AirTimeViewHolder> {

    private ArrayList<AirTimeData> AirTimeDatas;

    public void setData(ArrayList<AirTimeData> list) {
        AirTimeDatas = list;
    }

    public AirTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.air_time_item, parent, false);

        AirTimeViewHolder holder = new AirTimeViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(AirTimeViewHolder holder, int position) {
        AirTimeData data = AirTimeDatas.get(position);

        holder.time.setText(data.getTime());
        holder.icon.setImageResource(data.getImg());
        holder.condition.setText(data.getCondition());
    }

    public int getItemCount() {
        return AirTimeDatas.size();
    }
}

class AirTimeViewHolder extends RecyclerView.ViewHolder {
    public TextView time;
    public ImageView icon;
    public TextView condition;

    public AirTimeViewHolder(View itemView) {
        super(itemView);

        time = (TextView) itemView.findViewById(R.id.air_time);
        icon = (ImageView) itemView.findViewById(R.id.air_time_icon);
        condition = (TextView) itemView.findViewById(R.id.air_time_con);
    }
}

class AirTimeData {
    private String time;
    private int img;
    private String condition;

    public AirTimeData(String time, int img, String condition) {
        this.time = time;
        this.img = img;
        this.condition = condition;
    }

    public String getTime() {
        return this.time;
    }

    public int getImg() {
        return this.img;
    }

    public String getCondition() {
        return this.condition;
    }
}
// 시간별 미세먼지 상태 목록에서 사용하는 부분 (여기까지)

// 일별 미세먼지 상태 목록에서 사용하는 부분 (여기서부터)
class AirDateAdapter extends RecyclerView.Adapter<AirDateViewHolder> {

    private ArrayList<AirDateData> AirDateDatas;

    public void setData(ArrayList<AirDateData> list) {
        AirDateDatas = list;
    }

    public AirDateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.air_date_item, parent, false);

        AirDateViewHolder holder = new AirDateViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(AirDateViewHolder holder, int position) {
        AirDateData data = AirDateDatas.get(position);

        holder.date.setText(data.getDate());
        holder.icon.setImageResource(data.getImg());
        holder.condition.setText(data.getCondition());
    }

    public int getItemCount() {
        return AirDateDatas.size();
    }
}

class AirDateViewHolder extends RecyclerView.ViewHolder {
    public TextView date;
    public ImageView icon;
    public TextView condition;

    public AirDateViewHolder(View itemView) {
        super(itemView);

        date = (TextView) itemView.findViewById(R.id.air_date);
        icon = (ImageView) itemView.findViewById(R.id.air_date_icon);
        condition = (TextView) itemView.findViewById(R.id.air_date_con);
    }
}

class AirDateData {
    private String date;
    private int img;
    private String condition;

    public AirDateData(String date, int img, String condition) {
        this.date = date;
        this.img = img;
        this.condition = condition;
    }

    public String getDate() {
        return this.date;
    }

    public int getImg() {
        return this.img;
    }

    public String getCondition() {
        return this.condition;
    }
}
// 일별 미세먼지 상태 목록에서 사용하는 부분 (여기까지)