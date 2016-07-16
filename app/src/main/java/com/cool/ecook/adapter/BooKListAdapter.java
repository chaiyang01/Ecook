package com.cool.ecook.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.cool.ecook.R;
import com.cool.ecook.activity.FreshBooKSpecialActivity;
import com.cool.ecook.bean.CookBookInfo;
import com.cool.ecook.view.MyGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by HP on 2016/7/13.
 * 菜谱界面listview的适配器
 * 里面包含grid的数据和适配器
 *
 */
public class BooKListAdapter extends BaseAdapter {
    private Context context;
    private List<CookBookInfo.ListBeans> list;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    //创建Itemdd点击接口
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public BooKListAdapter(Context context, List<CookBookInfo.ListBeans> list){
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
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookViewHolder viewHolder = null;
        if (convertView ==null){
            convertView = inflater.inflate(R.layout.item_cookbook_listview_grid,parent,false);

            viewHolder = new BookViewHolder();
            viewHolder.gridView = (MyGridView) convertView.findViewById(R.id.gv_cookbook_list_item);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_cookbook_classfig);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (BookViewHolder) convertView.getTag();
        }
        CookBookInfo.ListBeans listBeans = list.get(position);
        viewHolder.textView.setText(listBeans.getName());
        List<CookBookInfo.ListBeans.ListBean> listBean = listBeans.getList();
        BooKListGridAdapter gridAdapter = new BooKListGridAdapter(context,listBean);
        viewHolder.gridView.setAdapter(gridAdapter);
        viewHolder.gridView.getParent().requestDisallowInterceptTouchEvent(true);
        //如果有设置 了点击接口，则进行方法回调
        if (mOnItemClickListener != null) {
            viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mOnItemClickListener.OnItemClick(view,position);
                }
            });
        }
        return convertView;
    }


    public class BookViewHolder{
        MyGridView gridView;
        TextView textView;
    }

}
