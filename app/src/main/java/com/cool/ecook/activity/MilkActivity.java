package com.cool.ecook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.cool.ecook.R;
import com.cool.ecook.bean.SquareHeaderBean;
import com.cool.ecook.config.URLConfig;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * 广场头部牛奶广告
 */
public class MilkActivity extends AppCompatActivity {
    private WebView wb;
    private Map<String,String> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk);
        wb =(WebView)findViewById(R.id.milk_web);
        map.put("machine","d02c35fb0454e400bba7f3e2882cfd9a");
        map.put("version","12.4.6");
        map.put("device","Samsung+Galaxy+S4+-+4.3+-+API+18+-+1080x1920");
        OkHttpTool.newInstance().start(URLConfig.URL_SQUARE2).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (result == null) {
                    return;
                }
                Gson gson = new Gson();
                SquareHeaderBean headerBean = gson.fromJson(result,SquareHeaderBean.class);
                String url = headerBean.getData().getSquareList().get(4).getUrl();
                wb.loadUrl(url);
                wb.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
            }
        });
    }
}
