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
 * Created by HP on 2016/7/14.
 */
public class CookBookHotContentRightFragment extends Fragment {

    private PullLoadMoreRecyclerView mRecyclerView;
    private List<CookBookSpecialInfo.ListBean> mListBean = new ArrayList<>();
    private BookRecyclerSpecialAdapter adapter;

    public static CookBookHotContentRightFragment newInstance(Bundle bundle){
        CookBookHotContentRightFragment fragment = new CookBookHotContentRightFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        OkHttpUtils.post().url(URLConfig.COOKBOOK_HOT)
                .addParams("machine","f7b9d5f586a588130b89aa03fe7264f3")
                .addParams("version","12.4.6")
                .addParams("device","SM-G900F")
                .addParams("type","hotest")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

                Gson gson = new Gson();
                CookBookSpecialInfo info = gson.fromJson(response,CookBookSpecialInfo.class);
                mListBean.addAll(info.getList());
                adapter.notifyDataSetChanged();
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
        initAdapter();
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

        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mListBean.clear();
                initData();


            }

            @Override
            public void onLoadMore() {
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
