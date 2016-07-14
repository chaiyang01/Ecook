package com.cool.ecook.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.adapter.BooKListAdapter;
import com.cool.ecook.bean.CookBookInfo;
import com.cool.ecook.config.URLConfig;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.URL;
import java.util.List;

import okhttp3.Call;

/**
 * 菜谱
 * A simple {@link Fragment} subclass.
 * 第二个界面为Fragment
 * 顶部有最新菜和周最热 两个模块
 * 下部为ListView
 *
 */
public class CookBookFragment extends Fragment{
    private FragmentManager manager;
    private View view;
    private RadioGroup radioGroup;
    private RecyclerView recyclerView;
    private ImageView hot;
    private ImageView fresh;
    private PullToRefreshListView pullListview;
    private View head;

    //构造方法
    public CookBookFragment(){

    }
    //创建fragment实例
    public static CookBookFragment newInstance() {
        Bundle args = new Bundle();
        CookBookFragment fragment = new CookBookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 准备ListView里的数据
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initdata();
    }

    private void initdata() {
        OkHttpUtils.get().url(URLConfig.COOKBOOK_LISTVIEW).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        CookBookInfo info = gson.fromJson(response,CookBookInfo.class);
                        List<CookBookInfo.ListBeans> list = info.getList();
                       // Log.i("fffffff","ddddd"+list.toString());
                        BooKListAdapter listAdapter = new BooKListAdapter(getActivity(),list);
                            pullListview.setAdapter(listAdapter);
                        String urlFresh = "http://pic.ecook.cn/web/"+info.getLatest()+".jpg";
                        String urlHot = "http://pic.ecook.cn/web/"+info.getHotest()+".jpg";
                        Glide.with(getActivity()).load(urlFresh).into(fresh);
                        Glide.with(getActivity()).load(urlHot).into(hot);
                    }
                });
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_cook_book, container, false);
         pullListview = (PullToRefreshListView) view.findViewById(R.id.pull_cookbook_listview);
        //头部视图实例化
         head = inflater.inflate(R.layout.head_cookbook_listview,null);
        fresh = (ImageView) head.findViewById(R.id.iv_cookbook_fresh);
         hot = (ImageView) head.findViewById(R.id.iv_cookbook_hot);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pullListview.getRefreshableView().addHeaderView(head);
    }
}
