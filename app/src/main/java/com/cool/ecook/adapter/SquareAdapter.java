package com.cool.ecook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.SquareBean;
import com.cool.ecook.config.URLConfig;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 广场的适配器
 * Created by lenovo on 2016/7/13.
 */
public class SquareAdapter extends BaseAdapter{
    private List<SquareBean.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private List<String[]> list;

    public SquareAdapter(List<SquareBean.ListBean> data, Context context) {
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.get(i):null;
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
            view = inflater.inflate(R.layout.item_square,viewGroup,false);
            holder.ivIconurl = (CircleImageView) view.findViewById(R.id.ci_pic_show);
            holder.tvUserName = (TextView) view.findViewById(R.id.tv_username);
            holder.tvDisplaytime = (TextView) view.findViewById(R.id.tv_displaytime);
            holder.tvSplitText = (TextView) view.findViewById(R.id.tv_splitText);
            holder.tvTex = (TextView) view.findViewById(R.id.tv_text);
            holder.gv = (GridView) view.findViewById(R.id.pic_gv);
            holder.tvMessagenum = (TextView) view.findViewById(R.id.messagenum_tv);
            holder.tvGoodnum = (TextView) view.findViewById(R.id.goodnum_tv);
            holder.lv = (ListView) view.findViewById(R.id.comment_lv);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        SquareBean.ListBean squareBean = data.get(i);
        Glide.with(context).load(URLConfig.URL_PIC1+squareBean.getUserimageid()+URLConfig.URL_PIC2).into(holder.ivIconurl);
        holder.tvUserName.setText(squareBean.getUsername());
        holder.tvDisplaytime.setText(squareBean.getDisplaytime());
        holder.tvSplitText.setText(squareBean.getSplitText());
        holder.tvTex.setText(squareBean.getText());
        holder.tvMessagenum.setText(squareBean.getTalkLikeWebPo().getIsLike()+"");
        holder.tvGoodnum.setText(squareBean.getTalkLikeWebPo().getLikenum()+"");
        //将GridView图片ID拆分，装进List里面
        list = new ArrayList<>();
        for (int j =0;j<data.size();j++){
            String str = data.get(i).getImageids();
            String[] obj = str.split(",");
            list.add(obj);
        }
        String[] strings = list.get(i);
        if (strings.length==3||strings.length>4){
            holder.gv.setNumColumns(3);
        }else {
            holder.gv.setNumColumns(2);
        }
        GridAdapter adapter = new GridAdapter(strings);
        adapter.notifyDataSetChanged();
        holder.gv.setAdapter(adapter);
        LvAdapter lvAdapter = new LvAdapter(squareBean);
        holder.lv.setAdapter(lvAdapter);
        return view;
    }
    //PullToRefreshListView里面控件
    public static class ViewHolder
    {
        CircleImageView ivIconurl;
        TextView tvUserName;
        TextView tvDisplaytime;
        TextView tvSplitText;
        TextView tvTex;
        GridView gv;
        TextView tvMessagenum;
        TextView tvGoodnum;
        ListView lv;
    }
    //GridView的适配器
    class GridAdapter extends  BaseAdapter{
        String[] strings;
        public GridAdapter(String[] strings) {
            this.strings = strings;
        }

        @Override
        public int getCount() {
            return strings.length;
        }

        @Override
        public Object getItem(int i) {
            return strings[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view==null){
                viewHolder = new ViewHolder();
                view  = inflater.inflate(R.layout.item_square_gridview,null);
                viewHolder.imageView = (ImageView) view.findViewById(R.id.grid_iv);
                view.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Glide.with(context).load(URLConfig.URL_PIC1+strings[i]+URLConfig.URL_PIC2).into(viewHolder.imageView);
            return view;
        }
        //GridView里面的控件
        class ViewHolder{
            public ImageView imageView;
        }
    }
    //ListView的适配器
    class LvAdapter extends BaseAdapter{
        SquareBean.ListBean listBean;

        public LvAdapter(SquareBean.ListBean listBean) {
            this.listBean = listBean;
        }

        @Override
        public int getCount() {
            return listBean.getCommentPoList().size();
        }

        @Override
        public Object getItem(int i) {
            return listBean.getCommentPoList().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view==null){
                viewHolder = new ViewHolder();
                view = inflater.inflate(R.layout.item_square_listview,viewGroup,false);
                viewHolder.circleImageView = (CircleImageView) view.findViewById(R.id.lv_pic_show);
                viewHolder.tvCommentpolistUsername = (TextView) view.findViewById(R.id.tv_commentpolist_username);
                viewHolder.tvCommentpolistText = (TextView) view.findViewById(R.id.tv_commentpolist_text);
                view.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Glide.with(context).load(URLConfig.URL_PIC1+listBean.getCommentPoList().get(i).getUserimageid()+URLConfig.URL_PIC2).into(viewHolder.circleImageView);
            viewHolder.tvCommentpolistUsername.setText(listBean.getCommentPoList().get(i).getUsername()+":");
            viewHolder.tvCommentpolistText.setText(listBean.getCommentPoList().get(i).getText());
            return view;
        }
        class ViewHolder{
            public CircleImageView circleImageView;
            public TextView tvCommentpolistUsername;
            public TextView tvCommentpolistText;
        }
    }
}
