package com.cool.ecook.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.activity.BannerJumpActivity;
import com.cool.ecook.activity.BannerPostJumpActivity;
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
    public void convert(ViewHolderM holderM, final InternetActivityJumpInfo.DataBean.ListBean bean) {
        ImageView imageViewMain = holderM.getView(R.id.activity_main_pictrue);

        TextView textViewTitle = holderM.getView(R.id.activity_main_text_view);


        String url = "http://pic.ecook.cn/web/"+bean.getImage()+".jpg";
        Glide.with(context).load(url).into(imageViewMain);
        imageViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url1 = bean.getUrl();
                String name = bean.getTitle();
                if (url1.indexOf("http") > -1) {
                    Intent intent = new Intent(context, BannerJumpActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url1);
                    bundle.putString("name",name);
                    intent.putExtra("bundle", bundle);
                    context.startActivity(intent);
                }else{
                    String url2 = url1.substring(28,url1.length());
                    Log.i("kkkkk","jjjjjj"+url1);
                    Intent intent2 = new Intent(context, BannerPostJumpActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url2);
                    bundle.putString("name",name);
                    intent2.putExtra("bundle", bundle);
                    context.startActivity(intent2);
                }
            }
        });
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
