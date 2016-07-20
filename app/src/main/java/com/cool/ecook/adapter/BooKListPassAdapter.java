package com.cool.ecook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.CookBookGridInfo;
import com.cool.ecook.bean.CookBookInfo;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by HP on 2016/7/13.
 * 菜谱界面listview里的grid点击跳转后的Activity的适配器
 * 里面包含image的数据和适配器
 *
 */
public class BooKListPassAdapter extends BaseAdapter {
    private Context context;
    private List<CookBookGridInfo.ListBean>  list;
    private LayoutInflater inflater;
    public BooKListPassAdapter(Context context, List<CookBookGridInfo.ListBean> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? 0 : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookGridViewHolder viewHolder = null;
        if (convertView ==null){
            convertView = inflater.inflate(R.layout.item_cookbook_list_pass_list,parent,false);

            viewHolder = new BookGridViewHolder();
            viewHolder.imageView = (RoundedImageView) convertView.findViewById(R.id.iv_cookbook_list_pass);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_cookbook_list_psaa_name);
            viewHolder.num = (TextView) convertView.findViewById(R.id.tv_cookbook_list_psaa_num);
            viewHolder.description = (TextView) convertView.findViewById(R.id.tv_cookbook_list_pass_description);


            CookBookGridInfo.ListBean listBean = list.get(position);
            String imageid = listBean.getImageid();

            int id = Integer.parseInt(imageid);
            String url = "http://pic.ecook.cn/web/"+id+".jpg!s4";
            Glide.with(context).load(url).into(viewHolder.imageView);
            viewHolder.name.setText(listBean.getName());
            viewHolder.description.setText(listBean.getDescription());
            viewHolder.num.setText("收藏  "+listBean.getCollectCount()+"/n/n喜欢  "+listBean.getLikeCount());
            convertView.setTag(viewHolder);

        }else {
            convertView.getTag();
        }
        return convertView;
    }


    public class BookGridViewHolder{
        private RoundedImageView imageView;
        private TextView name;
        private TextView num;
        private TextView description;
    }

}
