package com.cool.ecook.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.MainListViewInfo;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/18.
 */
public class HeadListViewAdapter extends CommonAdapter<MainListViewInfo.ListBean.MaterialListBean> {
    private Context context;
    private int layoutId;

    private int number=0;

    public HeadListViewAdapter(Context context, int layoutId, List<MainListViewInfo.ListBean.MaterialListBean> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public void convert(ViewHolderM holderM, MainListViewInfo.ListBean.MaterialListBean bean) {
        View view =LayoutInflater.from(context).inflate(layoutId,null);


        TextView textViewNumber = holderM.getView(R.id.tv_one);

        TextView textViewDesc = holderM.getView(R.id.tv_two);

        if (number%2==0){
            textViewNumber.setBackgroundColor(Color.parseColor("#F0F0F0"));
            textViewDesc.setBackgroundColor(Color.parseColor("#F0F0F0"));
        }else {
            textViewNumber.setBackgroundColor(Color.parseColor("#ffffff"));
            textViewDesc.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        number++;

        textViewNumber.setText(bean.getName());

        textViewDesc.setText(bean.getDosage());



    }
}

