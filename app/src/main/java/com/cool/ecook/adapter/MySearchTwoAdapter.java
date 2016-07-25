package com.cool.ecook.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.SearchOneInfo;
import com.cool.ecook.bean.SearchTwoInfo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ningyachao on 2016/7/22.
 */
public class MySearchTwoAdapter extends CommonAdapter<SearchTwoInfo.UserListBean> {
    private Context context;
    private int layoutId;

    public MySearchTwoAdapter(Context context, int layoutId, List<SearchTwoInfo.UserListBean> list) {
        super(context, layoutId, list);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public void convert(ViewHolderM holderM, SearchTwoInfo.UserListBean bean) {
        CircleImageView imageViewMain = holderM.getView(R.id.search_two_image);

        TextView textViewOne = holderM.getView(R.id.search_two_tv_one);

        TextView textViewTwo = holderM.getView(R.id.search_two_tv_two);


        String url = "http://pic.ecook.cn/web/" + bean.getPic() + ".jpg";
        Glide.with(context).load(url).into(imageViewMain);

        textViewOne.setText(bean.getTitle());

        textViewTwo.setText("粉丝"+bean.getFansCount()+"   菜谱"+bean.getRecipeCount());

    }
}

