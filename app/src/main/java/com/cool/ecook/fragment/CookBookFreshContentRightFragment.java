package com.cool.ecook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cool.ecook.R;
import com.cool.ecook.activity.FreshBooKSpecialActivity;
import com.cool.ecook.adapter.BookRecyclerSpecialAdapter;
import com.cool.ecook.bean.CookBookSpecialInfo;
import com.cool.ecook.config.URLConfig;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 最新菜里最新专辑的界面
 * Created by HP on 2016/7/14.
 */
public class CookBookFreshContentRightFragment extends Fragment {

    private PullLoadMoreRecyclerView mRecyclerView;
    private List<CookBookSpecialInfo.ListBean> mListBean = new ArrayList<>();
    private BookRecyclerSpecialAdapter adapter;
    //创建Fragment实例
    public static CookBookFreshContentRightFragment newInstance(Bundle bundle){
        CookBookFreshContentRightFragment fragment = new CookBookFreshContentRightFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //准备数据
        initData();
    }

    private void initData() {
        mListBean.clear();
        OkHttpUtils.get().url(URLConfig.COOKBOOK_SPECIAL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

                Gson gson = new Gson();
                CookBookSpecialInfo info = gson.fromJson(response,CookBookSpecialInfo.class);
                mListBean.addAll(info.getList());
                //刷新适配器数据
                adapter.notifyDataSetChanged();
                //停止刷新
                mRecyclerView.setPullLoadMoreCompleted();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cookbook_fresh_content_right,null);
         mRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullrecy_cookbook_special_);
        mRecyclerView.setLinearLayout();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建适配器
        initAdapter();
        //监听点击事件
        initListener();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new BookRecyclerSpecialAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), FreshBooKSpecialActivity.class);
                startActivity(intent);
            }
        });
        //下拉刷新方法
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mListBean.clear();
                initData();
            }
            @Override
            public void onLoadMore() {
                //停止刷新
                mRecyclerView.setPullLoadMoreCompleted();
                Toast.makeText(getActivity(),"没有更多了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAdapter() {
        adapter = new BookRecyclerSpecialAdapter(getActivity(),mListBean);
        mRecyclerView.setAdapter(adapter);

    }
}
