package com.cool.ecook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.CookBookSpecialInfo;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * 最新菜和周最热里的专辑公用适配器
 *
 * Created by HP on 2016/7/15.
 */
public class BookRecyclerSpecialAdapter extends RecyclerView.Adapter<BookRecyclerSpecialAdapter.SpecialViewHolder>{
    private Context context;
    private List<CookBookSpecialInfo.ListBean> mListBean;
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public BookRecyclerSpecialAdapter(Context context, List<CookBookSpecialInfo.ListBean> mListBean) {
        this.context = context;
        this.mListBean = mListBean;

    }

    @Override
    public SpecialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cookbook_fresh_special_recy,null);
        return new SpecialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SpecialViewHolder holder,final int position) {
        String id = mListBean.get(position).getImageid();
        String url = "http://pic.ecook.cn/web/"+id+".jpg!s4";
        Glide.with(context).load(url).into(holder.imageView);
        holder.name.setText(mListBean.get(position).getName());
        holder.num.setText("菜谱 "+mListBean.get(position).getRecipeCount());
       // String descriptions = mListBean.get(position).getRecipeCount();
        if ("这个人很懒，什么都没写".equals(mListBean.get(position).getDescription())){
            holder.description.setText(" ");
        }else {
            holder.description.setText(mListBean.get(position).getDescription());
        }
        if (mOnItemClickListener != null){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(holder.imageView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mListBean.size() == 0 ? 0 : mListBean.size();
    }
    //继承Recycler的Viewholder
    class SpecialViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;
        private TextView name;
        private TextView num;
        private TextView description;

         public SpecialViewHolder(View itemView) {
             super(itemView);
             imageView = (RoundedImageView) itemView.findViewById(R.id.iv_cookbook_fresh_special);
             name = (TextView) itemView.findViewById(R.id.tv_cookbook_fresh_special_name);
             num = (TextView) itemView.findViewById(R.id.tv_cookbook_fresh_special_num);
             description = (TextView) itemView.findViewById(R.id.tv_cookbook_fresh_special_description);
         }
     }
}
