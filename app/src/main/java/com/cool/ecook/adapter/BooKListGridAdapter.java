package com.cool.ecook.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.CookBookInfo;

import java.util.List;

/**
 * Created by HP on 2016/7/13.
 * 菜谱界面listview里的grid的适配器
 * 里面包含image的数据和适配器
 *
 */
public class BooKListGridAdapter extends BaseAdapter {
    private Context context;
    private List<CookBookInfo.ListBeans.ListBean> list;
    private LayoutInflater inflater;
    public BooKListGridAdapter(Context context, List<CookBookInfo.ListBeans.ListBean> list){
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
            convertView = inflater.inflate(R.layout.item_cookbook_listview_grid_item,parent,false);

            viewHolder = new BookGridViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_cookbook_grid_item);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_cookbook_grid_item_title);

            CookBookInfo.ListBeans.ListBean listBean = list.get(position);
            String imageid = listBean.getImageid();
           int id = Integer.parseInt(imageid);
            String url = "http://pic.ecook.cn/web/"+id+".jpg!s4";
            Log.i("dddddd","url"+url);
          Glide.with(context).load(url).into(viewHolder.imageView);
            viewHolder.textView.setText(listBean.getName());
            convertView.setTag(viewHolder);

        }else {
            convertView.getTag();
        }
        return convertView;
    }


    public class BookGridViewHolder{
        ImageView imageView;
        TextView textView;
    }

}
