package com.cool.ecook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.activity.FreshBooKDetailsActivity;
import com.cool.ecook.bean.CookBookFreshInfo;

import java.util.List;

/**
 * Created by HP on 2016/7/14.
 */
public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.RecyclerViewHolder>{
    private Context context;
    private List<CookBookFreshInfo.ListBean> list;
    private OnItemCilckListener mOnItemCilckListener;

    public interface OnItemCilckListener{
        void OnItemCilck(View view,int position);
    }

    public void setmOnItemCilckListener(OnItemCilckListener mOnItemCilckListener) {
        this.mOnItemCilckListener = mOnItemCilckListener;
    }


    public BookRecyclerAdapter(Context context, List<CookBookFreshInfo.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cookbook_fresh_recycler,null);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        CookBookFreshInfo.ListBean listBean = list.get(position);
        String id = listBean.getImageid();
        String url = "http://pic.ecook.cn/web/"+id+".jpg!s4";
        Glide.with(context).load(url).into(holder.imageView);
        holder.name.setText(listBean.getName());
        holder.like.setText(listBean.getLikeCount());
        holder.collen.setText(listBean.getCollectCount());
        if (mOnItemCilckListener != null){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(context, FreshBooKDetailsActivity.class);
                    mOnItemCilckListener.OnItemCilck(holder.imageView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 :list.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView name;
        private TextView like;
        private TextView collen;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_cookbook_fresh_recycler_item);
            name = (TextView) itemView.findViewById(R.id.tv_cookbook_fresh_recycler_item_name);
            like = (TextView) itemView.findViewById(R.id.tv_cookbook_fresh_recycler_item_like);
            collen = (TextView) itemView.findViewById(R.id.tv_cookbook_fresh_recycler_item_collect);
        }
    }

}
