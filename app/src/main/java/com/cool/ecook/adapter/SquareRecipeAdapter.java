package com.cool.ecook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.activity.InternetCookListViewItemJumpActivity;
import com.cool.ecook.bean.SquareInfoBean;
import com.cool.ecook.config.URLConfig;

import java.util.List;

/**
 * Created by lenovo on 2016/7/16.
 */
public class SquareRecipeAdapter extends BaseAdapter {
    private List<SquareInfoBean.DataBean.RecipeListBean> datas;
    private LayoutInflater inflater;
    private Context context;

    public SquareRecipeAdapter(List<SquareInfoBean.DataBean.RecipeListBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.square_recipe_item,viewGroup,false);
            holder.iv = (ImageView) view.findViewById(R.id.recipe_iv);
            holder.tv = (TextView) view.findViewById(R.id.recipe_tv);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        final SquareInfoBean.DataBean.RecipeListBean listBean = datas.get(i);
        Glide.with(context).load(URLConfig.URL_PIC1+listBean.getImageid()+URLConfig.URL_PIC2).into(holder.iv);
        holder.tv.setText(listBean.getName());
        //菜谱点击事件
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,InternetCookListViewItemJumpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                String type  = "1";
                String id1 = listBean.getId();
                String imageid  = listBean.getImageid();
                String collectCount = "5";
                bundle.putString("type",type);
                bundle.putString("id",id1);
                bundle.putString("userImageId",imageid);
                bundle.putString("collectionNum",collectCount);
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }
    private static class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
