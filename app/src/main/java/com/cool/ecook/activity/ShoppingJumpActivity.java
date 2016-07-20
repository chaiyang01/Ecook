package com.cool.ecook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.cool.ecook.R;
import com.cool.ecook.config.URLConfig;

/**
 * Created by ningyachao on 2016/7/15.
 */
public class ShoppingJumpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingjump);

        WebView webView = (WebView) findViewById(R.id.shopping_jump_webview);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(URLConfig.INTERNET_COOK_SHOPPING_JUMP);

        TextView textView = (TextView) findViewById(R.id.shopping_tv_back_show);

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

    }
}
