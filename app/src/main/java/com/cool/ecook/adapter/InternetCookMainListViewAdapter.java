package com.cool.ecook.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.InternetCookMainInfo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ningyachao on 2016/7/13.
 */
public class InternetCookMainListViewAdapter extends CommonAdapter<InternetCookMainInfo.DataBean.ContentListBean> {
    private Context context;
    private int layoutId;

    public InternetCookMainListViewAdapter(Context context, int layoutId, List<InternetCookMainInfo.DataBean.ContentListBean> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public void convert(ViewHolderM holderM, InternetCookMainInfo.DataBean.ContentListBean bean) {
        final ImageView imageViewMainPictrue = holderM.getView(R.id.internet_cook_list_view_item_image);
        CircleImageView circleImageViewUser = holderM.getView(R.id.author_image_view);
        TextView textViewAuthor = holderM.getView(R.id.author_name_tv);
        TextView textViewCookName = holderM.getView(R.id.cook_name_title);
        TextView textViewCookInfo = holderM.getView(R.id.cook_info);
        TextView textViewPeoplelikes= holderM.getView(R.id.people_likes);
        ImageView imageViewVideo = holderM.getView(R.id.video_play);

        String url = "http://pic.ecook.cn/web/"+bean.getImage()+".jpg!s6";
        Glide.with(context).load(url).into(imageViewMainPictrue);

        String url1 = "http://pic.ecook.cn/web/"+bean.getUserImage()+".jpg!s1";
        Glide.with(context).load(url1).into(circleImageViewUser);

        textViewAuthor.setText(bean.getUserNick());

        textViewCookName.setText(bean.getTitle());

        textViewCookInfo.setText("    "+bean.getDesc());

        textViewPeoplelikes.setText(bean.getCollectionNum()+"  "+"收藏");

        int number = bean.getType();

        if (number == 3){
            imageViewVideo.setVisibility(View.VISIBLE);
        }else{
            imageViewVideo.setVisibility(View.GONE);
        }
        //ning
    }
}

