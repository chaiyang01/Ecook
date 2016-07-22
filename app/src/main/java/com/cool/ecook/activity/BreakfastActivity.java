package com.cool.ecook.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.adapter.SquareAdapter;
import com.cool.ecook.bean.AdHeadBean;
import com.cool.ecook.bean.SquareBean;
import com.cool.ecook.config.URLConfig;
import com.cool.ecook.utlis.ShowShareUtils;
import com.cool.ecook.view.CustomProgressDialog;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sharesdk.framework.ShareSDK;
import onekeyshare.OnekeyShare;

/**
 * 第三界面广场 广告早餐
 */
public class BreakfastActivity extends AppCompatActivity {
    //初始化控件
    private PullToRefreshListView mPtlf;
    private ImageView iv;
    private TextView tv;

    private String sid;
    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sid = (String) msg.obj;
        }
    };
    private CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        //Dialog动画
        dialog =new CustomProgressDialog(this,R.drawable.ani_progress);
        dialog.show();

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
        mPtlf.getRefreshableView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                int scrollY = mPtlf.getRefreshableView().getScrollY();
                if (scrollY>20){
                    tv.setVisibility(View.VISIBLE);
                }else if(scrollY<20){
                    tv.setVisibility(View.GONE);
                }
            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
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
                //广告图片和介绍
                AdHeadBean adHeadBean = gson.fromJson(result, AdHeadBean.class);
                View headerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.adhead_shw,null);
                iv  = (ImageView) headerView.findViewById(R.id.iv_pic);
                tv = (TextView) headerView.findViewById(R.id.tv);
                ImageView breakfastiv = (ImageView) headerView.findViewById(R.id.iv_breakfast);
                ImageView ivShare = (ImageView) headerView.findViewById(R.id.iv_share);
                //点回退箭头结束此页面
                breakfastiv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                Glide.with(getApplicationContext()).load(URLConfig.URL_PIC1+adHeadBean.getImageid()+URLConfig.URL_PIC2).into(iv);
                tv.setText(adHeadBean.getDescription());
                //第三方分享
                ivShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ShowShareUtils showShareUtils = new ShowShareUtils(BreakfastActivity.this);
                        showShareUtils.showShare(BreakfastActivity.this);
                    }
                });
                //判断有没有头
                int headerViewsCount = mPtlf.getRefreshableView().getHeaderViewsCount();
                if (headerViewsCount<=1){
                    mPtlf.getRefreshableView().addHeaderView(headerView);
                }
                //广告图片和介绍
                SquareBean squareBean = gson.fromJson(result, SquareBean.class);
                datas.addAll(squareBean.getList());
                Message message = new Message();
                if (datas.size()>=1){
                String id = datas.get(datas.size()-1).getContentId();
                message.obj = id;
                mHandle.sendMessage(message);
                }
                adapter.notifyDataSetChanged();

                //停止动画
                dialog.dismiss();
            }
        });

    }
    private void initView() {
        mPtlf = (PullToRefreshListView) findViewById(R.id.square_ptfl);
        tv = (TextView) findViewById(R.id.tv);
    }
//    //第三方分享
//    private void showShare(){
//        ShareSDK.initSDK(this);
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle("android课堂笔记");
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
//
//// 启动分享GUI
//        oks.show(this);
//    }
}
