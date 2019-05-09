package com.project.misae_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapActivity extends AppCompatActivity {
    Toolbar toolbar;
    WebView webview1;
    WebView webview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        webview1 = (WebView) findViewById(R.id.nullschool1);
        webview2 = (WebView) findViewById(R.id.nullschool2);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 툴바 왼쪽 메뉴 버튼 사용
        webview1.getSettings().setJavaScriptEnabled(true); // 웹뷰 자바스크립트 사용 허용
        webview2.getSettings().setJavaScriptEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_black_24);
        setTitle("미세먼지 지도");

        webview1.loadUrl("https://earth.nullschool.net/ko/#current/particulates/surface/level/overlay=pm10/orthographic=-232.63,37.39,1656");
        webview2.loadUrl("https://earth.nullschool.net/ko/#current/particulates/surface/level/overlay=pm2.5/orthographic=-232.63,37.39,1656");
        //webview1.setWebChromeClient(new WebChromeClient());
        webview1.setWebViewClient(new WebViewClientClass()); // 새 창 띄우지 않고 웹뷰로 띄우기
        webview2.setWebViewClient(new WebViewClientClass());
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

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL", url);
            view.loadUrl(url);
            return true;
        }
    }

}
