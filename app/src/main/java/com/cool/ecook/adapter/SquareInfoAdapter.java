package com.cool.ecook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.SquareInfoBean;
import com.cool.ecook.config.URLConfig;

import java.util.List;

/**
 * Created by lenovo on 2016/7/16.
 */
public class SquareInfoAdapter extends BaseAdapter{
    private List<SquareInfoBean.DataBean.CollectionSortListBean> datas;
    private LayoutInflater inflater;
    private Context context;

    public SquareInfoAdapter(List<SquareInfoBean.DataBean.CollectionSortListBean> datas, Context context) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.square_collection_item,null);
            holder.iv = (ImageView) view.findViewById(R.id.coll_iv);
            holder.tv = (TextView) view.findViewById(R.id.coll_tv);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        SquareInfoBean.DataBean.CollectionSortListBean listBean = datas.get(i);
        Glide.with(context).load(URLConfig.URL_PIC1+listBean.getImageid()+URLConfig.URL_PIC2).into(holder.iv);
        holder.tv.setText(listBean.getName());
        return view;
    }
    private static class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
