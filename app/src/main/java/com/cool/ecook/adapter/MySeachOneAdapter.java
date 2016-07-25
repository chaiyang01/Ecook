package com.cool.ecook.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.MainListViewInfo;
import com.cool.ecook.bean.SearchOneInfo;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/22.
 */
public class MySeachOneAdapter extends CommonAdapter<SearchOneInfo.ContentListBean> {
    private Context context;
    private int layoutId;

    public MySeachOneAdapter(Context context, int layoutId, List<SearchOneInfo.ContentListBean> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public void convert(ViewHolderM holderM, SearchOneInfo.ContentListBean bean) {
        ImageView imageViewMain = holderM.getView(R.id.search_one_image_view);

        TextView textViewOne = holderM.getView(R.id.search_one_tv_one);

        TextView textViewTwo = holderM.getView(R.id.search_one_tv_two);

        TextView textViewThree = holderM.getView(R.id.search_one_tv_three);

        String url = "http://pic.ecook.cn/web/" + bean.getImageid() + ".jpg";
        Glide.with(context).load(url).into(imageViewMain);

        textViewOne.setText(bean.getName());

        textViewTwo.setText(bean.getAuthorname());

        textViewThree.setText("收藏  "+bean.getCollectionCount()+"   喜欢"+bean.getLikeCount());
    }
}

