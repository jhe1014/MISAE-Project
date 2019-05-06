package com.project.misae_project;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 툴바 왼쪽 메뉴 버튼 사용
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_dehaze_black_24);
        setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 클릭된 메뉴 아이템의 아이디로 동작 구분
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home : // 메뉴 버튼
                Toast.makeText(getApplicationContext(), "메뉴 버튼을 누르셨습니다.", Toast.LENGTH_LONG).show();
                //drawerLayout.openDrawer(GravityCompat.START); // 사이드메뉴 뜨도록 하기
                return true;

            case R.id.menu_search :  // 검색 버튼
                // Toast 코드는 임시로 작성한 것이므로 사용하지 않을 시 삭제하기
                Toast.makeText(getApplicationContext(), "검색 버튼을 누르셨습니다.", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_renew : // 새로고침 버튼
                Toast.makeText(getApplicationContext(), "새로고침 버튼을 누르셨습니다.", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
