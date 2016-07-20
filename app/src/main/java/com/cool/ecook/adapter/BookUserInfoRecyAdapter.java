package com.cool.ecook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.CookBookDetailsInfo;
import com.cool.ecook.config.URLConfig;

import java.util.List;

/**
 * Created by HP on 2016/7/19.
 * 最新专辑和最热专辑里菜谱点击后的菜谱集合信息
 *
 */
public class BookUserInfoRecyAdapter extends RecyclerView.Adapter<BookUserInfoRecyAdapter.InfoViewHolde>{
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private List<CookBookDetailsInfo.DetailsBean.RecipeListBean> detailsList;

    public interface OnItemClickListener{
        void OnItemClick(View view ,int position);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public BookUserInfoRecyAdapter( List<CookBookDetailsInfo.DetailsBean.RecipeListBean> detailsList, Context context) {
        this.detailsList = detailsList;
        this.context = context;
    }

    @Override
    public InfoViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cookbook_userinfo_recy,parent,false);
        return new InfoViewHolde(view);
    }

    @Override
    public void onBindViewHolder(InfoViewHolde holder, final int position) {
        CookBookDetailsInfo.DetailsBean.RecipeListBean recipeListBean = detailsList.get(position);
        String imageid = recipeListBean.getImageid();
        String name = recipeListBean.getName();
        String url = URLConfig.PIC_ADDR+imageid+URLConfig.PIC_ADDR2;
        Glide.with(context).load(url).into(holder.imageView);
        holder.name.setText(name);

        if (mOnItemClickListener != null){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(v,position);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (detailsList.size() > 6){
            return 6;
        }else {
            return detailsList.size();
        }
    }

    class InfoViewHolde extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView name;
        public InfoViewHolde(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_item_cookbook_info_recy);
            name = (TextView) itemView.findViewById(R.id.iv_item_cookbook_info_name);
        }
    }
}
