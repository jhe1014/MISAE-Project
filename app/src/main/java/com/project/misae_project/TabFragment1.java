package com.project.misae_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class TabFragment1 extends Fragment {

    private RecyclerView air_con_HorizontalView;
    private HorizontalAdapter air_con_Adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.tab_fragment1, container, false);

        View view = inflater.inflate(R.layout.air_condition_item, container, false);
        Context context = view.getContext();
        air_con_HorizontalView = (RecyclerView) rootview.findViewById(R.id.air_condition_listview);

        mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 가로 배치

        air_con_HorizontalView.setLayoutManager(mLayoutManager);

        air_con_Adapter = new HorizontalAdapter();

        ArrayList<AirListData> data = new ArrayList<>();

        int i = 0;
        while (i < 10) {
            data.add(new AirListData("미세먼지", R.drawable.baseline_tag_faces_black_48, "좋음", "수치"));
            i++;
        }

        air_con_Adapter.setData(data);

        air_con_HorizontalView.setAdapter(air_con_Adapter);

        return rootview;
    }
}

class HorizontalAdapter extends RecyclerView.Adapter<HorizontalViewHolder> {

    private ArrayList<AirListData> horizontalDatas;

    public void setData(ArrayList<AirListData> list) {
        horizontalDatas = list;
    }

    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 사용할 아이템의 뷰 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.air_condition_item, parent, false);

        HorizontalViewHolder holder = new HorizontalViewHolder(view);

        return holder;
    }

    public void onBindViewHolder(HorizontalViewHolder holder, int position) {
        AirListData data = horizontalDatas.get(position);

        holder.name.setText(data.getName());
        holder.icon.setImageResource(data.getImg());
        holder.condition.setText(data.getCondition());
        holder.value.setText(data.getValue());
    }

    public int getItemCount() {
        return horizontalDatas.size();
    }
}

class HorizontalViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView icon;
    public TextView condition;
    public TextView value;

    public HorizontalViewHolder(View itemView) {
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
