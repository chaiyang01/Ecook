package com.cool.ecook.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.cool.ecook.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class CookBookListPassActivity extends AppCompatActivity {

    private ImageView back_bar;
    private PullToRefreshListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_book_list_pass);
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

    }

    private void initAdapter() {
    }

    private void initview() {
         back_bar = (ImageView) findViewById(R.id.iv_cookbook_list_pass_bar_back);
         mListView = (PullToRefreshListView) findViewById(R.id.pulllv_cook_book_list_pass);

    }

    private void initData() {

    }
}
