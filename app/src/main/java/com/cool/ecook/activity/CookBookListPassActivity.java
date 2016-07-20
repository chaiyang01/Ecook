package com.cool.ecook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.cool.ecook.R;
import com.cool.ecook.adapter.BooKListPassAdapter;
import com.cool.ecook.bean.CookBookGridInfo;
import com.cool.ecook.config.URLConfig;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class CookBookListPassActivity extends AppCompatActivity {

    private ImageView back_bar;
    private PullToRefreshListView mListView;
    private String id;
    private List<CookBookGridInfo.ListBean> list = null;
    private BooKListPassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_book_list_pass);
        Intent intent = getIntent();
         id = intent.getStringExtra("id");
        //准备数据
        initData();
        //初始化View
        initview();
        //准备适配器
        initAdapter();
        //监听点击
        initListener();
    }

    private void initListener() {
        back_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initAdapter() {
         adapter = new BooKListPassAdapter(this,list);
        mListView.setAdapter(adapter);
    }

    private void initview() {
         back_bar = (ImageView) findViewById(R.id.iv_cookbook_list_pass_bar_back);
         mListView = (PullToRefreshListView) findViewById(R.id.pulllv_cook_book_list_pass);

    }

    private void initData() {
        list = new ArrayList<>();
        OkHttpUtils.post().url(URLConfig.COOKBOOK_LISTVIEW_ITEM)
                .addParams("machine","f7b9d5f586a588130b89aa03fe7264f3")
                .addParams("version","12.4.6")
                .addParams("device","SM-G900F")
                .addParams("id",id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                       // Log.i("bbbbbbb","dddd"+response);
                        Gson gson = new Gson();
                        CookBookGridInfo info = gson.fromJson(response,CookBookGridInfo.class);
                         list.addAll(info.getList());
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
