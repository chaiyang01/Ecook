package com.cool.ecook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.cool.ecook.R;
import com.cool.ecook.adapter.InternetCookVideoButtonjumpAdapter;
import com.cool.ecook.bean.VideoButtonJumpInfo;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ningyachao on 2016/7/16.
 */
public class VideoJumpActivity  extends AppCompatActivity{

    private SwipeRefreshLayout swipeRefreshLayout;
    private PullToRefreshListView prflv;
    private List<VideoButtonJumpInfo.ListBean> mlist = new ArrayList<>();
    private InternetCookVideoButtonjumpAdapter adapter;
    private String idnumber;
    private ListView listView;
    private ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_jump);
        initView();
        initData(null);
        initListenner();
    }

    private void initListenner() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mlist.clear();
                initData(swipeRefreshLayout);
            }
        });

        prflv.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Map<String,String> map = new HashMap<>();
                map.put("machine","868b83db44bafe15546203bbddc50360");
                map.put("version","12.4.6");
                map.put("device","GT-P5210");
                map.put("id",idnumber);
                map.put("type","video_recipe");

                OkHttpTool.newInstance().start("http://api.ecook.cn/public/getRecommendContentsByType.shtml").post(map).callback(
                        new IOKCallBack() {
                            @Override
                            public void success( String result) {
                                Gson gson = new Gson();

                                VideoButtonJumpInfo videoButtonJumpInfo = gson.fromJson(result,VideoButtonJumpInfo.class);
                                if (videoButtonJumpInfo==null){return;}
                                if (videoButtonJumpInfo.getList().isEmpty()){return;}

                                mlist.addAll(videoButtonJumpInfo.getList());

                                adapter.notifyDataSetChanged();

                                idnumber = mlist.get(mlist.size()-1).getId();

                                prflv.onRefreshComplete();
                            }
                        }
                );
            }
        });

        prflv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0)
                    swipeRefreshLayout.setEnabled(true);
                else{
                    swipeRefreshLayout.setEnabled(false);
            }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String ids = mlist.get(position-1).getId()+"";

                String collectionNum = mlist.get(position-1).getCollectCount();

                String userImageId = mlist.get(position-1).getAuthorimageid();

                String type ="3";

                Intent intent = new Intent(VideoJumpActivity.this, InternetCookListViewItemJumpActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("id",ids);
                bundle.putString("type",type);
                bundle.putString("collectionNum",collectionNum);
                bundle.putString("userImageId",userImageId);
                intent.putExtra("bundle",bundle);
                startActivity(intent);

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void initData(final SwipeRefreshLayout swipe ) {

        Map<String,String> map = new HashMap<>();
        map.put("machine","868b83db44bafe15546203bbddc50360");
        map.put("version","12.4.6");
        map.put("device","GT-P5210");
//        map.put("id","27998233");
        map.put("type","video_recipe");

        OkHttpTool.newInstance().start("http://api.ecook.cn/public/getRecommendContentsByType.shtml").post(map).callback(
                new IOKCallBack() {
                    @Override
                    public void success( String result) {
                        Gson gson = new Gson();

                        VideoButtonJumpInfo videoButtonJumpInfo = gson.fromJson(result,VideoButtonJumpInfo.class);
                            if (videoButtonJumpInfo==null){return;}
                            if (videoButtonJumpInfo.getList().isEmpty()){return;}

                            mlist.addAll(videoButtonJumpInfo.getList());
                            adapter.notifyDataSetChanged();

                            idnumber = mlist.get(mlist.size()-1).getId();

                        if (swipe != null){
                            swipe.setRefreshing(false);
                        }
                    }
                }
        );
    }

    private void initView() {

        imageView = (ImageView) findViewById(R.id.video_back);

        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        
        prflv = (PullToRefreshListView) findViewById(R.id.video_list_view);

        prflv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);

        listView = prflv.getRefreshableView();

        listView.setDivider(null);

        adapter= new InternetCookVideoButtonjumpAdapter(VideoJumpActivity.this,R.layout.video_jump_list_view_item,mlist);

        listView.setAdapter(adapter);
    }
}
