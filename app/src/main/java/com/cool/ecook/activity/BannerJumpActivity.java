package com.cool.ecook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cool.ecook.R;

/**
 * Created by ningyachao on 2016/7/19.
 */
public class BannerJumpActivity extends AppCompatActivity {

    private WebView webView;
    private String url;
    private String name;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_jump);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        url = bundle.getString("url");

        name = bundle.getString("name");
        
        textView = (TextView) findViewById(R.id.tv_show_banner);

        textView.setText(name);
        
        imageView = (ImageView) findViewById(R.id.banner_jump_image);

        webView = (WebView) findViewById(R.id.banner_jump_activity_webview);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
