package com.cool.ecook.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.InternetActivityJumpInfo;
import com.cool.ecook.bean.VideoButtonJumpInfo;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/18.
 */
public class InternetActivityJumpAdapter extends CommonAdapter<InternetActivityJumpInfo.DataBean.ListBean> {
    private Context context;
    private int layoutId;

    public InternetActivityJumpAdapter(Context context, int layoutId, List<InternetActivityJumpInfo.DataBean.ListBean> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public void convert(ViewHolderM holderM, InternetActivityJumpInfo.DataBean.ListBean bean) {
        ImageView imageViewMain = holderM.getView(R.id.activity_main_pictrue);

        TextView textViewTitle = holderM.getView(R.id.activity_main_text_view);


        String url = "http://pic.ecook.cn/web/"+bean.getImage()+".jpg";
        Glide.with(context).load(url).into(imageViewMain);

        int number =bean.getIsEnd();

        if (number == 2){
            textViewTitle.setText("进行中");
            textViewTitle.setTextColor(Color.WHITE);
            textViewTitle.setBackgroundResource(R.drawable.activity_select_text_bacground);
        }else{
            textViewTitle.setText("已结束");
            textViewTitle.setTextColor(Color.WHITE);
            textViewTitle.setBackgroundResource(R.drawable.activity_select_text_background_two);
        }

    }
}
