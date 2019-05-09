package com.project.misae_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class SharingActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 툴바 왼쪽 메뉴 버튼 사용
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_black_24);
        setTitle("미세먼지 수치 공유");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 툴바 뒤로가기 버튼
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
