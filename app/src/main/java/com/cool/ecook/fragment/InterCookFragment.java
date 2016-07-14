package com.cool.ecook.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.adapter.InternetCookMainListViewAdapter;
import com.cool.ecook.bean.InternetCookMainInfo;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

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
    private List<InternetCookMainInfo.DataBean.ContentListBean> plist = new ArrayList<>();
    private ImageView imageViewSign;
    private ImageView imageViewShopping;
    private ImageView imageViewVideo;
    private ImageView imageViewActivity;
    private TextView textViewSign;
    private TextView textViewShopping;
    private TextView textViewVideo;
    private TextView textViewActivity;
    private InternetCookMainListViewAdapter adapter;

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
        inintHeaderView();

        initAdapter();

        return view;
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
        setupBanner();
        loadBannerDatas();
        
    }
    /*
     *初始化banner数据
     */
    private void loadBannerDatas() {
        OkHttpTool.newInstance().start("http://api.ecook.cn/public/get124Homedata.shtml").callback(new IOKCallBack() {

            @Override
            public void success(String result) {
                Gson gson = new Gson();
                InternetCookMainInfo  internetCookMainInfo = gson.fromJson(result,InternetCookMainInfo.class);
                if (!mlist.isEmpty()){
                    return;
                }
                mlist.addAll(internetCookMainInfo.getData().getBannerList());
                convenientBanner.getViewPager().getAdapter().notifyDataSetChanged();
                listView.addHeaderView(header_view);

                nlist.addAll(internetCookMainInfo.getData().getButtonList());

                buttonListSet(nlist);

                plist.addAll(internetCookMainInfo.getData().getContentList());

                adapter.notifyDataSetChanged();



            }
        });

    }
    /*
     *对主界面的四个Button进行设置
     */

    private void buttonListSet(List<InternetCookMainInfo.DataBean.ButtonListBean> nlist) {
        String url = "http://pic.ecook.cn/web/"+nlist.get(0).getImageId()+".jpg!s1";
        Glide.with(getActivity()).load(url).into(imageViewSign);
        textViewSign.setText(nlist.get(0).getTitle());

        String url1 = "http://pic.ecook.cn/web/"+nlist.get(1).getImageId()+".jpg!s1";
        Glide.with(getActivity()).load(url1).into(imageViewShopping);
        textViewShopping.setText(nlist.get(1).getTitle());

        String url2 = "http://pic.ecook.cn/web/"+nlist.get(2).getImageId()+".jpg!s1";
        Glide.with(getActivity()).load(url2).into(imageViewVideo);
        textViewVideo.setText(nlist.get(2).getTitle());

        String url3 = "http://pic.ecook.cn/web/"+nlist.get(3).getImageId()+".jpg!s1";
        Glide.with(getActivity()).load(url3).into(imageViewActivity);
        textViewActivity.setText(nlist.get(3).getTitle());


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
