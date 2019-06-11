package com.project.misae_project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SharingAdapter extends RecyclerView.Adapter<SharingAdapter.CustomViewHorder> {

    private ArrayList<SharingData> arrayList;

    public SharingAdapter(ArrayList<SharingData> arrayList){this.arrayList=arrayList;}

    @NonNull
    @Override
    public SharingAdapter.CustomViewHorder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shringitem,viewGroup,false);
        CustomViewHorder holder = new CustomViewHorder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SharingAdapter.CustomViewHorder customViewHorder, int i) {
        customViewHorder.img_.setImageResource(arrayList.get(i).getImg_());
        customViewHorder.tv_time.setText(arrayList.get(i).getTv_time());
        customViewHorder.tv_name.setText(arrayList.get(i).getTv_name());
        customViewHorder.tv_pm10.setText(arrayList.get(i).getTv_pm10());
        customViewHorder.tv_pm25.setText(arrayList.get(i).getTv_pm25());

        customViewHorder.itemView.setTag(i);
        customViewHorder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curName = customViewHorder.tv_name.getText().toString();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null!=arrayList?arrayList.size():0);
    }

    public class CustomViewHorder extends RecyclerView.ViewHolder {

        protected ImageView img_;
        protected TextView tv_time;
        protected TextView tv_name;
        protected TextView tv_pm10;
        protected TextView tv_pm25;

        public CustomViewHorder(@NonNull View itemView) {
            super(itemView);
            this.img_=(ImageView) itemView.findViewById(R.id.img_);
            this.tv_time=(TextView) itemView.findViewById(R.id.tv_time);
            this.tv_name=(TextView) itemView.findViewById(R.id.tv_name);
            this.tv_pm10=(TextView) itemView.findViewById(R.id.tv_pm10);
            this.tv_pm25=(TextView) itemView.findViewById(R.id.tv_pm25);
        }
    }

}
