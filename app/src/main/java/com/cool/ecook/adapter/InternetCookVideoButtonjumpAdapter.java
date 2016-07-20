package com.cool.ecook.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.ContentListBean;
import com.cool.ecook.bean.VideoButtonJumpInfo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ningyachao on 2016/7/16.
 */
public class InternetCookVideoButtonjumpAdapter extends CommonAdapter<VideoButtonJumpInfo.ListBean> {
    private Context context;
    private int layoutId;

    public InternetCookVideoButtonjumpAdapter(Context context, int layoutId, List<VideoButtonJumpInfo.ListBean> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public void convert(ViewHolderM holderM, VideoButtonJumpInfo.ListBean bean) {
        ImageView imageViewMain = holderM.getView(R.id.video_jump_image_view);

        TextView textViewTitle = holderM.getView(R.id.video_title);

        TextView textViewDesc = holderM.getView(R.id.video_desc);

        String url = "http://pic.ecook.cn/web/"+bean.getImageid()+".jpg";
        Glide.with(context).load(url).into(imageViewMain);

        textViewTitle.setText(bean.getName());

        textViewDesc.setText(bean.getLikeCount()+"人赞过     "+bean.getCollectCount()+"人收藏");

    }
}


