package com.cool.ecook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.cool.ecook.R;
import com.cool.ecook.adapter.InternetActivityJumpAdapter;
import com.cool.ecook.bean.InternetActivityJumpInfo;
import com.cool.ecook.bean.SignJumpInfo;
import com.cool.ecook.config.URLConfig;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ningyachao on 2016/7/18.
 */
public class ActivityJumpActivity extends AppCompatActivity{

    private SwipeRefreshLayout swipeRefresh;
    private ListView listView;
    private List<InternetActivityJumpInfo.DataBean.ListBean> mlist = new ArrayList();
    private InternetActivityJumpAdapter adpter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_jump);
        initView();
        initAdapter();
        initData(null);
        initListener();
    }

    private void initListener() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mlist.clear();
                initData(swipeRefresh);
            }
        });
    }

    private void initAdapter() {

        adpter = new InternetActivityJumpAdapter(ActivityJumpActivity.this,R.layout.activity_jump_item_view,mlist);
        listView.setAdapter(adpter);
    }

    private void initView() {
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.activity_swipe_refresh);

        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        listView = (ListView) findViewById(R.id.activity_list_view);

        listView.setDivider(null);


    }

    private void initData(final SwipeRefreshLayout swipe){
        OkHttpTool.newInstance().start(URLConfig.INTERNET_COOK_ACTIVITY_JUMP).callback(new IOKCallBack() {

            @Override
            public void success(String result) {

                Gson gson = new Gson();
                InternetActivityJumpInfo internetActivityJumpInfo = gson.fromJson(result,InternetActivityJumpInfo.class);

                if (internetActivityJumpInfo == null ||internetActivityJumpInfo.getData() == null|| internetActivityJumpInfo.getData().getList().isEmpty()){
                    return;
                }

                mlist.addAll(internetActivityJumpInfo.getData().getList());

                adpter.notifyDataSetChanged();

                if (swipe != null){
                    swipe.setRefreshing(false);
                }
            }
        });
    }
}
