package com.project.misae_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ViewPager mViewPager;

    // 미세먼지 수치 테스트용 변수
    public static String stationName = "백석동";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.home_navigation_view);
        mViewPager = (ViewPager) findViewById(R.id.container);

        // 미세먼지 수치 openAPI 에 직접 연결하는 방식
        // 추후 데이터베이스에서 받아오는 걸로 변경
        // 파싱된 데이터 log.d tag: 미세먼지 수치
        try {

            String resultText = new LiveAtmosphere().execute().get();
            new LiveAtmosphere().listjsonParser(resultText);
            new LiveAtmosphere().setLA();
            //Log.d("미세먼지 통데이터",resultText);


            //    public static String[] arraysum;
/*
            arraysum[0] = date;
            arraysum[1] = pm10;
            arraysum[2] = pm25;
            arraysum[3] = co;
            arraysum[4] = no2;
            arraysum[5] = so2;
            arraysum[6] = o3;
            arraysum[7] = mang;
*/


        } catch (InterruptedException e) {
        e.printStackTrace();
        } catch (ExecutionException e) {
        e.printStackTrace();
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 툴바 왼쪽 메뉴 버튼 사용
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_dehaze_black_24);
        setTitle("");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setCheckable(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                Intent intent;

                switch (id) {
                    case R.id.menu01 : // 게시판/공유(??)
                        Toast.makeText(getApplicationContext(), "1번 메뉴를 누르셨습니다.", Toast.LENGTH_LONG).show();
                        intent = new Intent(getApplicationContext(), SharingActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu02 : // 식약처 마스크 확인
                        Toast.makeText(getApplicationContext(), "2번 메뉴를 누르셨습니다.", Toast.LENGTH_LONG).show();
                        intent = new Intent(getApplicationContext(), MaskActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.menu03 : // 설정
                        Toast.makeText(getApplicationContext(), "3번 메뉴를 누르셨습니다.", Toast.LENGTH_LONG).show();
                        intent = new Intent(getApplicationContext(), SettingActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        TabLayout tablayout = (TabLayout) findViewById(R.id.tab_layout);
        tablayout.addTab(tablayout.newTab().setText("미세먼지"));
        tablayout.addTab(tablayout.newTab().setText("날씨"));
        tablayout.setTabGravity((TabLayout.GRAVITY_FILL));

        // 페이지 넘기는 이벤트 발생 시
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
        Intent intent;

        switch (id) {
            case android.R.id.home : // 메뉴 버튼
                //Toast.makeText(getApplicationContext(), "메뉴 버튼을 누르셨습니다.", Toast.LENGTH_LONG).show();
                drawerLayout.openDrawer(GravityCompat.START); // 사이드메뉴 뜨도록 하기
                return true;

            case R.id.menu_search :  // 검색 버튼
                // Toast 코드는 임시로 작성한 것이므로 사용하지 않을 시 삭제하기
                Toast.makeText(getApplicationContext(), "검색 버튼을 누르셨습니다.", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_renew : // 지도 버튼
                //Toast.makeText(getApplicationContext(), "지도 버튼을 누르셨습니다.", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
