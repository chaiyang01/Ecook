package com.cool.ecook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.activity.InternetCookListViewItemJumpActivity;
import com.cool.ecook.bean.ContentListBean;
import com.cool.ecook.bean.InternetCookMainInfo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ningyachao on 2016/7/13.
 */
public class InternetCookMainListViewAdapter extends CommonAdapter<ContentListBean> {
    private Context context;
    private int layoutId;

    public InternetCookMainListViewAdapter(Context context, int layoutId, List<ContentListBean> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public void convert(ViewHolderM holderM, final ContentListBean bean) {
        final ImageView imageViewMainPictrue = holderM.getView(R.id.internet_cook_list_view_item_image);
        CircleImageView circleImageViewUser = holderM.getView(R.id.author_image_view);
        TextView textViewAuthor = holderM.getView(R.id.author_name_tv);
        TextView textViewCookName = holderM.getView(R.id.cook_name_title);
        TextView textViewCookInfo = holderM.getView(R.id.cook_info);
        TextView textViewPeoplelikes= holderM.getView(R.id.people_likes);
        ImageView imageViewVideo = holderM.getView(R.id.video_play);

        LinearLayout linearLayout = holderM.getView(R.id.ll_layout_another_item);
        TextView textViewOtherItem = holderM.getView(R.id.text_main_recipe);
        TextView textViewOtherTotalNu = holderM.getView(R.id.text_main_totalnum);

        String url = "http://pic.ecook.cn/web/"+bean.getImage()+".jpg!s6";


        int number = bean.getType();

        if (number == 3){
            Glide.with(context).load(url).into(imageViewMainPictrue);

            imageViewMainPictrue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InternetCookListViewItemJumpActivity.class);

                    Bundle bundle = new Bundle();
                    String id =bean.getId()+"";
                    String collectionNum = bean.getCollectionNum()+"";
                    String userImageId =bean.getUserImage();
                    bundle.putString("id",id);
                    bundle.putString("collectionNum",collectionNum);
                    bundle.putString("userImageId",userImageId);
                    intent.putExtra("bundle",bundle);
                    context.startActivity(intent);

                }
            });
            imageViewVideo.setVisibility(View.VISIBLE);
            String url1 = "http://pic.ecook.cn/web/"+bean.getUserImage()+".jpg!s1";
            Glide.with(context).load(url1).into(circleImageViewUser);

            textViewAuthor.setText(bean.getUserNick());

            textViewCookName.setText(bean.getTitle());

            textViewCookInfo.setText("    "+bean.getDesc());

            textViewPeoplelikes.setText(bean.getCollectionNum()+"  "+"收藏");
            linearLayout.setVisibility(View.GONE);
            circleImageViewUser.setVisibility(View.VISIBLE);
            textViewAuthor.setVisibility(View.VISIBLE);
            textViewCookName.setVisibility(View.VISIBLE);
            textViewCookInfo.setVisibility(View.VISIBLE);
            textViewPeoplelikes.setVisibility(View.VISIBLE);
        }
        if (number == 1){
            Glide.with(context).load(url).into(imageViewMainPictrue);
            imageViewVideo.setVisibility(View.GONE);
            String url1 = "http://pic.ecook.cn/web/"+bean.getUserImage()+".jpg!s1";
            Glide.with(context).load(url1).into(circleImageViewUser);

            imageViewMainPictrue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InternetCookListViewItemJumpActivity.class);

                    Bundle bundle = new Bundle();
                    String id =bean.getId()+"";
                    String collectionNum = bean.getCollectionNum()+"";
                    String userImageId =bean.getUserImage();
                    bundle.putString("id",id);
                    bundle.putString("collectionNum",collectionNum);
                    bundle.putString("userImageId",userImageId);
                    intent.putExtra("bundle",bundle);
                    context.startActivity(intent);

                }
            });

            textViewAuthor.setText(bean.getUserNick());

            textViewCookName.setText(bean.getTitle());

            textViewCookInfo.setText("    "+bean.getDesc());

            textViewPeoplelikes.setText(bean.getCollectionNum()+"  "+"收藏");
            linearLayout.setVisibility(View.GONE);
            circleImageViewUser.setVisibility(View.VISIBLE);
            textViewAuthor.setVisibility(View.VISIBLE);
            textViewCookName.setVisibility(View.VISIBLE);
            textViewCookInfo.setVisibility(View.VISIBLE);
            textViewPeoplelikes.setVisibility(View.VISIBLE);
        }

        if (number==2){
            Glide.with(context).load(url).into(imageViewMainPictrue);

            imageViewMainPictrue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InternetCookListViewItemJumpActivity.class);

                    Bundle bundle = new Bundle();
                    String id =bean.getId()+"";
                    String collectionNum = bean.getCollectionNum()+"";
                    String userImageId =bean.getUserImage();
                    bundle.putString("id",id);
                    bundle.putString("collectionNum",collectionNum);
                    bundle.putString("userImageId",userImageId);
                    intent.putExtra("bundle",bundle);
                    context.startActivity(intent);

                }
            });
            imageViewVideo.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);

            textViewOtherItem.setText(bean.getTitle());
            textViewOtherTotalNu.setText(bean.getTotalNum());

            circleImageViewUser.setVisibility(View.GONE);
            textViewAuthor.setVisibility(View.GONE);
            textViewCookName.setVisibility(View.GONE);
            textViewCookInfo.setVisibility(View.GONE);
            textViewPeoplelikes.setVisibility(View.GONE);
        }
    }
}

