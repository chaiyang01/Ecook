package com.cool.ecook.fragment;


import android.content.Context;
import android.os.Bundle;
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
import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.adapter.SquareAdapter;
import com.cool.ecook.bean.SquareBean;
import com.cool.ecook.bean.SquareHeaderBean;
import com.cool.ecook.config.URLConfig;
import com.google.gson.Gson;
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


    public SquareFragment() {
        // Required empty public constructor
    }

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
        //初始化控件
        initView(view);
        //添加头部数据
        setupHeaderView();
        //创建数据源
        setupListView();
        return view;
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
    private void setupDatas() {
        map.put("machine","868b83db44bafe15546203bbddc50360");
        map.put("version","12.4.6");
        map.put("device","GT-P5210");
        //map.put("id","224339787");
        OkHttpTool.newInstance().start(URLConfig.URL_SQUARE).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (result == null) {
                    return;
                }
                Gson gson = new Gson();
                SquareBean squareBean = gson.fromJson(result, SquareBean.class);
                datas.addAll(squareBean.getList());
                List<String[]>  list = new ArrayList<>();
                //将GridView图片ID拆分，装进List里面
                for (int i =0;i<datas.size();i++){
                    String str = datas.get(i).getImageids();
                    String[] obj = str.split(",");
                    list.add(obj);
                }
                //创建适配器
                adapter = new SquareAdapter(datas,getContext(),list);
                //绑定适配器
                ListView refreshableView = mPtlf.getRefreshableView();
                refreshableView.setAdapter(adapter);
            }
        });

    }

    private void initView(View view) {
        mPtlf = (PullToRefreshListView) view.findViewById(R.id.square_ptfl);
    }

}
