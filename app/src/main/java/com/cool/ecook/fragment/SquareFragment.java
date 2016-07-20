package com.cool.ecook.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.activity.BreakfastActivity;
import com.cool.ecook.activity.DinnerActivity;
import com.cool.ecook.activity.FreeActivity;
import com.cool.ecook.activity.LunchActivity;
import com.cool.ecook.activity.MilkActivity;
import com.cool.ecook.activity.TongueActivity;
import com.cool.ecook.adapter.SquareAdapter;
import com.cool.ecook.bean.SquareBean;
import com.cool.ecook.bean.SquareHeaderBean;
import com.cool.ecook.config.URLConfig;
import com.cool.ecook.view.CustomProgressDialog;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 广场   YX
 * A simple {@link Fragment} subclass.
 */
public class SquareFragment extends Fragment {


    private CustomProgressDialog dialog;

    public SquareFragment() {
        // Required empty public constructor
    }

    private String sid;
    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sid = (String) msg.obj;
        }
    };
    public static SquareFragment newInstance() {
        Bundle args = new Bundle();
        SquareFragment fragment = new SquareFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private PullToRefreshListView mPtlf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);

        //Dialog动画
        dialog =new CustomProgressDialog(getActivity(),R.drawable.ani_progress);
        dialog.show();

        //初始化控件
        initView(view);
        //添加头部数据
        setupHeaderView();
        //创建数据源
        setupListView();
        //创建适配器
        adapter = new SquareAdapter(datas,getContext());
        //绑定适配器
        ListView refreshableView = mPtlf.getRefreshableView();
        refreshableView.setAdapter(adapter);
        //监听事件
        initListener();
        return view;
    }
    //上啦刷新监听
    private void initListener() {
        mPtlf.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
              datas.clear();
              map.put("lastTalkid","");
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
        //广告监听
        headerViewHolder.conventBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                        intent.setClass(getActivity(), BreakfastActivity.class);
                        break;
                    case 1:
                        intent.setClass(getActivity(), LunchActivity.class);
                        break;
                    case 2:
                        intent.setClass(getActivity(), DinnerActivity.class);
                        break;
                    case 3:
                        intent.setClass(getActivity(), FreeActivity.class);
                        break;
                    case 4:
                        intent.setClass(getActivity(), MilkActivity.class);
                        break;
                    case 5:
                        intent.setClass(getActivity(), TongueActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
        //不能这这里设置item里面的控件的监听，应该到适配器里面去设置监听
//        mPtlf.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //自定义的listview的初始化
//                ListView listView = (ListView) view.findViewById(R.id.comment_lv);
//                //自定义listview的监听
//                final List<SquareBean.ListBean.CommentPoListBean> commentPoList = datas.get(i-2).getCommentPoList();
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        String userid = commentPoList.get(i).getUserid();
//                        Intent intent = new Intent(getActivity(), InformationActivity.class);
//                        intent.putExtra("id",userid);
//                        startActivity(intent);
//                    }
//                });
//            }
//        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始自动滚动
        headerViewHolder.conventBanner.startTurning(2000);
    }

    @Override
    public void onStop() {
        super.onStop();
        //停止滚动
        headerViewHolder.conventBanner.stopTurning();
    }

    /**
     * 添加头
     */
    class HeaderViewHolder{
        private ConvenientBanner conventBanner;
    }
    /**
     * 添加头部布局
     */
    HeaderViewHolder headerViewHolder;
    private List<SquareHeaderBean.DataBean.SquareListBean> imageDatas = new ArrayList<>();
    private void setupHeaderView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.square_header_view,null);
        headerViewHolder = new HeaderViewHolder();
        headerViewHolder.conventBanner = (ConvenientBanner) headerView.findViewById(R.id.header_view_cb);
        //加载广告数据
        loadBannerDatas();
        mPtlf.getRefreshableView().addHeaderView(headerView);
    }
    public void setupBanner(HeaderViewHolder headerViewHolder) {
       headerViewHolder.conventBanner.setPages(new CBViewHolderCreator<HeaderBannerViewHolder>() {
           @Override
           public HeaderBannerViewHolder createHolder() {
               return new HeaderBannerViewHolder();
           }
       },imageDatas)
               .setPageIndicator(new int[]{R.drawable.page_now,R.drawable.page})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    class HeaderBannerViewHolder implements Holder<SquareHeaderBean.DataBean.SquareListBean>{
        ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView  = new ImageView(getActivity());
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, SquareHeaderBean.DataBean.SquareListBean data) {
            Glide.with(getActivity()).load(URLConfig.URL_PIC1+data.getImage()+URLConfig.URL_PIC2).into(imageView);
        }
    }
    private Map<String,String> map1 = new HashMap<>();
    private void loadBannerDatas() {
        map1.put("machine","d02c35fb0454e400bba7f3e2882cfd9a");
        map1.put("version","12.4.6");
        map1.put("device","Samsung+Galaxy+S4+-+4.3+-+API+18+-+1080x1920");
        OkHttpTool.newInstance().start(URLConfig.URL_SQUARE2).post(map1).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (result == null) {
                    return;
                }
                Gson gson = new Gson();
                SquareHeaderBean headerBean = gson.fromJson(result,SquareHeaderBean.class);
                imageDatas.addAll(headerBean.getData().getSquareList());
                //不能刷新适配器 刷新就没有点了
              //  headerViewHolder.conventBanner.getViewPager().getAdapter().notifyDataSetChanged();
                setupBanner(headerViewHolder);
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
        map.put("machine","50d2610986be44ca6b24f412cddc47ea");
        map.put("version","12.4.6");
        map.put("device","VPhone");
    }
    private void setupDatas() {
        setupMap();
        OkHttpTool.newInstance().start(URLConfig.URL_SQUARE).post(map).callback(new IOKCallBack() {
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

                //停止动画
                dialog.dismiss();
            }
        });

    }
    private void initView(View view) {
        mPtlf = (PullToRefreshListView) view.findViewById(R.id.square_ptfl);
    }

}
