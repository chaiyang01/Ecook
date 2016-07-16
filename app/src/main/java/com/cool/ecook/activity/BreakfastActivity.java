package com.cool.ecook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.cool.ecook.R;
import com.cool.ecook.adapter.SquareAdapter;
import com.cool.ecook.bean.SquareBean;
import com.cool.ecook.config.URLConfig;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 第三界面广场 广告早餐
 */
public class BreakfastActivity extends AppCompatActivity {
    //初始化控件
    private PullToRefreshListView mPtlf;
    private String sid;
    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sid = (String) msg.obj;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        //初始化控件
        initView();
        //创建数据源
        setupListView();
        //创建适配器
        adapter = new SquareAdapter(datas,this);
        //绑定适配器
        ListView refreshableView = mPtlf.getRefreshableView();
        refreshableView.setAdapter(adapter);
        //上啦刷新
        initListener();
    }
    private void initListener() {
        mPtlf.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                datas.clear();
                map.put("lastTalkid","0");
                setupDatas();
                adapter.notifyDataSetChanged();
                mPtlf.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                map.put("lastTalkid",sid);
                setupDatas();
                adapter.notifyDataSetChanged();
                mPtlf.onRefreshComplete();
            }
        });
        //如果使用上啦
        mPtlf.setMode(PullToRefreshBase.Mode.BOTH);
        //点击头像查看详情
        mPtlf.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public String contentId;
            public CircleImageView ci;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ci =(CircleImageView) view.findViewById(R.id.ci_pic_show);
                contentId = datas.get(i-1).getUserid();
                ci.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplication(), InformationActivity.class);
                        intent.putExtra("id",contentId);
                        startActivity(intent);
                    }
                });
            }
        });

    }
    private void setupListView() {
        //主界面下拉刷新的数据源
        setupDatas();
    }
    //添加PullToRefreshListView的数据
    private Map<String,String> map = new HashMap<>();
    private List<SquareBean.ListBean> datas = new ArrayList<>();
    private SquareAdapter adapter;
    private void setupMap(){
        map.put("topicname","早餐");
        map.put("machine","50d2610986be44ca6b24f412cddc47ea");
        map.put("version","12.4.6");
        map.put("device","VPhone");
    }
    private void setupDatas() {
        setupMap();
        OkHttpTool.newInstance().start(URLConfig.URL_SQUARE3).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (result == null) {
                    return;
                }
                Gson gson = new Gson();
                SquareBean squareBean = gson.fromJson(result, SquareBean.class);
                datas.addAll(squareBean.getList());
                Message message = new Message();
                String id = datas.get(datas.size()-1).getContentId();
                message.obj = id;
                mHandle.sendMessage(message);
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void initView() {
        mPtlf = (PullToRefreshListView) findViewById(R.id.square_ptfl);
    }
}
