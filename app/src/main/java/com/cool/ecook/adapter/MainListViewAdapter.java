package com.cool.ecook.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.MainListViewInfo;
import com.cool.ecook.bean.VideoButtonJumpInfo;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/18.
 */
public class MainListViewAdapter extends CommonAdapter<MainListViewInfo.ListBean.StepListBean> {
    private Context context;
    private int layoutId;

    public MainListViewAdapter(Context context, int layoutId, List<MainListViewInfo.ListBean.StepListBean> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public void convert(ViewHolderM holderM, MainListViewInfo.ListBean.StepListBean bean) {
        ImageView imageViewMain = holderM.getView(R.id.main_list_view_image);

        TextView textViewNumber = holderM.getView(R.id.tv_number);

        TextView textViewDesc = holderM.getView(R.id.main_list_view_tv);


        if (bean.getImageid()!="") {
            String url = "http://pic.ecook.cn/web/" + bean.getImageid() + ".jpg";
            Glide.with(context).load(url).into(imageViewMain);
        }else{
            imageViewMain.setVisibility(View.GONE);
        }
        int stepNumber = bean.getOrdernum()+1;
        textViewNumber.setText(stepNumber+"");

        textViewDesc.setText(bean.getDetails());


    }
}



