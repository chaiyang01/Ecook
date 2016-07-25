package com.cool.ecook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.cool.ecook.R;
import com.cool.ecook.activity.SearchActivity;
import com.cool.ecook.adapter.MySeachOneAdapter;
import com.cool.ecook.bean.SearchOneInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ningyachao on 2016/7/22.
 */
public class SearchOneFragment extends Fragment {

    private View view;
    private ListView listView;
    private String text;
    private List<SearchOneInfo.ContentListBean> mlist = new ArrayList<>();
    private MySeachOneAdapter adapter;

    public static SearchOneFragment newInstance(Bundle args) {
        SearchOneFragment fragment = new SearchOneFragment();
        fragment.setArguments(args);
        Log.i("oooo","........................"+args.toString());
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_search_one,container,false);

         listView = (ListView) view.findViewById(R.id.search_one_list_view);
         adapter = new MySeachOneAdapter(getActivity(),R.layout.search_one_list_view_item,mlist);
        listView.setAdapter(adapter);
        initData();

        return view;
    }

    private void initData() {

       text = ((SearchActivity)getActivity()).getText();

        if (text == null){return;}

        Map<String,String> map = new HashMap<>();
        map.put("machine","868b83db44bafe15546203bbddc50360");
        map.put("version","12.4.6");
        map.put("device","GT-P5210");
        map.put("queryString",text);


        OkHttpTool.newInstance().start("http://api.ecook.cn/public/searchRecipe.shtml").post(map).callback(
                new IOKCallBack() {
                    @Override
                    public void success( String result) {

                       Gson gson = new Gson();

                       SearchOneInfo  oneInfo = gson.fromJson(result,SearchOneInfo.class);
                        mlist.clear();
                        if (oneInfo==null||oneInfo.getContentList().isEmpty()){
                            return;
                        }
                       mlist.addAll(oneInfo.getContentList());
                      adapter.notifyDataSetChanged();
                    }
                }
        );

    }


}
