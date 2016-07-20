package com.cool.ecook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.cool.ecook.R;

/**
 * Created by ningyachao on 2016/7/19.
 */
public class BannerJumpActivity extends AppCompatActivity {

    private WebView webView;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_jump);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        url = bundle.getString("url");

        webView = (WebView) findViewById(R.id.banner_jump_activity_webview);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);


    }
}
