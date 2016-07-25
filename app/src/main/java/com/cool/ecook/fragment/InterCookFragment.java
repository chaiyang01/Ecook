package com.cool.ecook.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.activity.ActivityJumpActivity;
import com.cool.ecook.activity.BannerJumpActivity;
import com.cool.ecook.activity.BannerPostJumpActivity;
import com.cool.ecook.activity.SearchActivity;
import com.cool.ecook.activity.ShoppingJumpActivity;
import com.cool.ecook.activity.SignJumpActivity;
import com.cool.ecook.activity.VideoJumpActivity;
import com.cool.ecook.adapter.InternetCookMainListViewAdapter;
import com.cool.ecook.bean.ContentListBean;
import com.cool.ecook.bean.InternetCookMainInfo;
import com.cool.ecook.bean.InternetCookRefreshInfo;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * 网上厨房
 * A simple {@link Fragment} subclass.
 */
public class InterCookFragment extends Fragment {


    private View view;
    private PullToRefreshListView prflv;
    private ListView listView;
    private View header_view;
    private ConvenientBanner convenientBanner;
    private List<InternetCookMainInfo.DataBean.BannerListBean> mlist = new ArrayList<>();
    private List<InternetCookMainInfo.DataBean.ButtonListBean> nlist = new ArrayList<>();
    private List<ContentListBean> plist = new ArrayList<>();
    private ImageView imageViewSign;
    private ImageView imageViewShopping;
    private ImageView imageViewVideo;
    private ImageView imageViewActivity;
    private TextView textViewSign;
    private TextView textViewShopping;
    private TextView textViewVideo;
    private TextView textViewActivity;
    private InternetCookMainListViewAdapter adapter;
    private int numberId;
    private Button button;

    public InterCookFragment() {
        // Required empty public constructor
    }

    public static InterCookFragment newInstance() {
        
        Bundle args = new Bundle();
        
        InterCookFragment fragment = new InterCookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view =inflater.inflate(R.layout.fragment_inter_cook, container, false);
         prflv = (PullToRefreshListView) view.findViewById(R.id.pull_to_fresh_list_view);
         prflv.setMode(PullToRefreshBase.Mode.BOTH);
         listView = prflv.getRefreshableView();

        button = (Button) view.findViewById(R.id.main_search);
        inintHeaderView();

        initAdapter();

        initListener();

        return view;
    }

    private void initListener() {


        prflv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {



                OkHttpTool.newInstance().start("http://api.ecook.cn/public/get124Homedata.shtml").callback(new IOKCallBack() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        InternetCookMainInfo  internetCookMainInfo = gson.fromJson(result,InternetCookMainInfo.class);

                        plist.clear();
                        plist.addAll(internetCookMainInfo.getData().getContentList());
                        numberId= plist.get(plist.size()-1).getSortId();
                        adapter.notifyDataSetChanged();

                        mlist.clear();
                        mlist.addAll(internetCookMainInfo.getData().getBannerList());
                        setupBanner();
                        convenientBanner.getViewPager().getAdapter().notifyDataSetChanged();

                        nlist.clear();
                        nlist.addAll(internetCookMainInfo.getData().getButtonList());
                        buttonListSet(nlist);
                        prflv.onRefreshComplete();
                    }
                });



            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                String id = numberId+"";

                Map<String,String> map = new HashMap<>();
                map.put("machine","868b83db44bafe15546203bbddc50360");
                map.put("version","12.4.6");
                map.put("device","GT-P5210");
                map.put("minSortId",id);
                OkHttpTool.newInstance().start("http://api.ecook.cn/public/get124dataList.shtml").post(map).callback(
                        new IOKCallBack() {
                            @Override
                            public void success( String result) {
                                Gson gson = new Gson();
                        InternetCookRefreshInfo   internetCookRefreshInfo = gson.fromJson(result, InternetCookRefreshInfo.class);
                                if(internetCookRefreshInfo==null){
                                    return;
                                }
                                if (internetCookRefreshInfo.getData()==null || internetCookRefreshInfo.getData().getList()==null){
                                    prflv.onRefreshComplete();
                                    return;
                                }

                        List<ContentListBean> list = internetCookRefreshInfo.getData().getList();

                           plist.addAll(list);
                           numberId= plist.get(plist.size()-1).getSortId();
                           adapter.notifyDataSetChanged();
                           prflv.onRefreshComplete();
                           }
                        }
                );
            }
        });


        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String url = mlist.get(position).getUrl();
                String name = "";
                if (url.indexOf("http") > -1) {
                    Intent intent = new Intent(getActivity(), BannerJumpActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url);
                    bundle.putString("name",name);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }else{
                    String url1 = url.substring(28,url.length());
                    Log.i("kkkkk","jjjjjj"+url1);
                    Intent intent2 = new Intent(getActivity(), BannerPostJumpActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url1);
                    bundle.putString("name",name);
                    intent2.putExtra("bundle", bundle);
                    startActivity(intent2);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initAdapter() {

        listView.setDivider(null);

        adapter = new InternetCookMainListViewAdapter(getActivity(),R.layout.inter_cook_list_item_view,plist);

        listView.setAdapter(adapter);

    }

    /*
     *初始化头部视图
     */
    private void inintHeaderView() {

        header_view =LayoutInflater.from(getActivity()).inflate(R.layout.inter_cook_list_head_view, null);

        convenientBanner = (ConvenientBanner) header_view.findViewById(R.id.internet_cook_ad);

        imageViewSign = (ImageView) header_view.findViewById(R.id.sign);
        imageViewShopping = (ImageView) header_view.findViewById(R.id.shopping);
        imageViewVideo = (ImageView) header_view.findViewById(R.id.video);
        imageViewActivity = (ImageView) header_view.findViewById(R.id.activity);

        textViewSign = (TextView) header_view.findViewById(R.id.sign_tv);
        textViewShopping = (TextView) header_view.findViewById(R.id.shopping_tv);
        textViewVideo = (TextView) header_view.findViewById(R.id.video_tv);
        textViewActivity = (TextView) header_view.findViewById(R.id.activity_tv);
        loadBannerDatas();
        listView.addHeaderView(header_view);
    }
    /*
     *初始化banner数据
     */
    private void loadBannerDatas() {
        OkHttpUtils.get().url("http://api.ecook.cn/public/get124Homedata.shtml")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        InternetCookMainInfo internetCookMainInfo = gson.fromJson(response, InternetCookMainInfo.class);
                        if (!mlist.isEmpty()) {
                            return;
                        }
                        mlist.addAll(internetCookMainInfo.getData().getBannerList());
                        setupBanner();
                        convenientBanner.getViewPager().getAdapter().notifyDataSetChanged();

                        nlist.addAll(internetCookMainInfo.getData().getButtonList());

                        if (nlist.isEmpty()) {
                            return;
                        }

                        buttonListSet(nlist);

                        plist.addAll(internetCookMainInfo.getData().getContentList());

                        numberId = plist.get(plist.size() - 1).getSortId();

                        adapter.notifyDataSetChanged();
                    }
                });
    }
    /*
     *对主界面的四个Button进行设置
     */

    private void buttonListSet(List<InternetCookMainInfo.DataBean.ButtonListBean> nlist) {
        String url = "http://pic.ecook.cn/web/"+nlist.get(0).getImageId()+".jpg!s1";
        if (nlist.get(0).getImageId()==null){
            return;
        }
        Glide.with(getActivity()).load(url).into(imageViewSign);
        textViewSign.setText(nlist.get(0).getTitle());

        imageViewSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignJumpActivity.class);
                startActivity(intent);
            }
        });

        String url1 = "http://pic.ecook.cn/web/"+nlist.get(1).getImageId()+".jpg!s1";
        Glide.with(getActivity()).load(url1).into(imageViewShopping);
        textViewShopping.setText(nlist.get(1).getTitle());

        imageViewShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShoppingJumpActivity.class);
                startActivity(intent);
            }
        });

        String url2 = "http://pic.ecook.cn/web/"+nlist.get(2).getImageId()+".jpg!s1";
        Glide.with(getActivity()).load(url2).into(imageViewVideo);
        textViewVideo.setText(nlist.get(2).getTitle());

        imageViewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VideoJumpActivity.class);
                startActivity(intent);
            }
        });

        String url3 = "http://pic.ecook.cn/web/"+nlist.get(3).getImageId()+".jpg!s1";
        Glide.with(getActivity()).load(url3).into(imageViewActivity);
        textViewActivity.setText(nlist.get(3).getTitle());

        imageViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityJumpActivity.class);
                startActivity(intent);
            }
        });


    }

    /*
     *初始化convenietBanner
     */
    private void setupBanner() {

        convenientBanner.setPages(new CBViewHolderCreator<NetImageViewHolder>() {
            @Override
            public NetImageViewHolder createHolder() {
                return new NetImageViewHolder();
            }
        },mlist).startTurning(5000);


    }

    /*
     *convenietBanner的Holder类
     */
class  NetImageViewHolder implements Holder<InternetCookMainInfo.DataBean.BannerListBean>{

        private ImageView imageview;
        @Override
        public View createView(Context context) {
        imageview = new ImageView(context);
        imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageview;
        }

        @Override
        public void UpdateUI(Context context, int position, InternetCookMainInfo.DataBean.BannerListBean data) {
            String url = "http://pic.ecook.cn/web/"+data.getImage()+".jpg";
            Glide.with(getActivity()).load(url).into(imageview);
        }
    }
}
