package com.cool.ecook.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.ComentInfo;
import com.cool.ecook.bean.MainListViewInfo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ningyachao on 2016/7/18.
 */
public class FootListViewAdapter extends CommonAdapter<ComentInfo.InfoBean.CommentBean> {
    private Context context;
    private int layoutId;

    public FootListViewAdapter(Context context, int layoutId, List<ComentInfo.InfoBean.CommentBean> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public void convert(ViewHolderM holderM,ComentInfo.InfoBean.CommentBean bean) {

        CircleImageView circleImageView = holderM.getView(R.id.foot_circle_image_view);

        TextView textViewName = holderM.getView(R.id.tv_name);

        TextView textView = holderM.getView(R.id.text_view_tv);

        TextView textViewComment = holderM.getView(R.id.text_view_comment);

        String url = "http://pic.ecook.cn/web/"+bean.getUserimageid()+".jpg";
        Glide.with(context).load(url).into(circleImageView);

        textViewName.setText(bean.getUsername());

        textView.setText(bean.getDisplaytime());

        textViewComment.setText(bean.getText());




    }
}

