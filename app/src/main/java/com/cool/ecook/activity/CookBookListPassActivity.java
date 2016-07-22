package com.cool.ecook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

/**
 * 菜谱下面listView点击后跳转后的界面
 * cy
 *
 */
public class CookBookListPassActivity extends AppCompatActivity {

    private ImageView back_bar;
    private PullToRefreshListView mListView;
    private String id;
    private List<CookBookGridInfo.ListBean> list = null;
    private BooKListPassAdapter adapter;
    private CookBookGridInfo info;

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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //跳转到菜谱的详细做法
                Intent intent = new Intent(CookBookListPassActivity.this,InternetCookListViewItemJumpActivity.class);
                Bundle bundle = new Bundle();
                String type = list.get(position-1).getType();
                String id1 = list.get(position-1).getId();
                String imageid = list.get(position-1).getImageid();
                String collectCount = list.get(position-1).getCollectCount();
                bundle.putString("type",type);
                bundle.putString("id",id1);
                bundle.putString("userImageId",imageid);
                bundle.putString("collectionNum",collectCount);
                intent.putExtra("bundle",bundle);
                startActivity(intent);


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
                         info = gson.fromJson(response,CookBookGridInfo.class);
                         list.addAll(info.getList());
                        adapter.notifyDataSetChanged();
                        initListener();
                    }
                });
    }
}
