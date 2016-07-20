package com.cool.ecook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cool.ecook.R;
import com.cool.ecook.activity.FreshBooKDetailsActivity;
import com.cool.ecook.adapter.BookRecyclerAdapter;
import com.cool.ecook.bean.CookBookFreshInfo;
import com.cool.ecook.view.CustomProgressDialog;
import com.cool.ecook.view.DividerGridItemDecoration;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by HP on 2016/7/14.
 */
public class CookBookFreshContentLeftFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BookRecyclerAdapter adapter;
    private List<CookBookFreshInfo.ListBean> list = new ArrayList<>();
    private CustomProgressDialog dialog;

    public static CookBookFreshContentLeftFragment newInstance(Bundle bundle){
        CookBookFreshContentLeftFragment fragment = new CookBookFreshContentLeftFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Dialog动画
        dialog =new CustomProgressDialog(getActivity(),R.drawable.ani_progress);
        dialog.show();

        initData();
    }

    private void initData() {

        OkHttpUtils.post().url("http://api.ecook.cn/public/getRecipeListByType.shtml")
                .addParams("machine","f7b9d5f586a588130b89aa03fe7264f3")
                .addParams("version","12.4.6")
                .addParams("device","SM-G900F")
                .addParams("type","latest")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        CookBookFreshInfo info = gson.fromJson(response,CookBookFreshInfo.class);
                       list.addAll(info.getList());
                        adapter.notifyDataSetChanged();

                        //停止动画
                        dialog.dismiss();
                    }
                });

    }

    private void initListener() {
        adapter.setmOnItemCilckListener(new BookRecyclerAdapter.OnItemCilckListener() {
            @Override
            public void OnItemCilck(View view, int position) {
                Intent intent = new Intent(getActivity(), FreshBooKDetailsActivity.class);


                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cookbook_fresh_content_left,null);
         mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_book_fresh_conten);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建适配器
       initAdapter();
        //设置监听
        initListener();
    }

    private void initAdapter() {
        adapter = new BookRecyclerAdapter(getActivity(),list);
        mRecyclerView.setAdapter(adapter);
    }
}
