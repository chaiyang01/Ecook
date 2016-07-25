package com.cool.ecook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.cool.ecook.R;
import com.cool.ecook.activity.SearchActivity;
import com.cool.ecook.adapter.MySearchTwoAdapter;
import com.cool.ecook.bean.SearchOneInfo;
import com.cool.ecook.bean.SearchTwoInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ningyachao on 2016/7/22.
 */
public class SearchTwoFragment extends Fragment {

    private Bundle bundle;
    private String text;
    private View view;
    private ListView listView;

    private List<SearchTwoInfo.UserListBean> mlist = new ArrayList<>();
    private MySearchTwoAdapter adapter;

    public static SearchTwoFragment newInstance(Bundle args) {
        SearchTwoFragment fragment = new SearchTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_search_two,container,false);

        listView  = (ListView) view.findViewById(R.id.search_two_list_view);

        adapter = new MySearchTwoAdapter(getActivity(),R.layout.search_two_list_view_item,mlist);

        listView.setAdapter(adapter);

        inintData();

        return view;
    }

    private void inintData() {

        text = ((SearchActivity)getActivity()).getText();

        if (text==null){return;}

        Map<String,String> map = new HashMap<>();
        map.put("machine","868b83db44bafe15546203bbddc50360");
        map.put("version","12.4.6");
        map.put("device","GT-P5210");
        map.put("queryString",text);

        OkHttpTool.newInstance().start("http://api.ecook.cn/public/searchUser.shtml").post(map).callback(
                new IOKCallBack() {
                    @Override
                    public void success( String result) {
                        Gson gson = new Gson();
                        SearchTwoInfo infoTwo = gson.fromJson(result,SearchTwoInfo.class);
                        mlist.clear();
                        mlist.addAll(infoTwo.getUserList());
                        adapter.notifyDataSetChanged();
                    }
                }
        );
    }
}
