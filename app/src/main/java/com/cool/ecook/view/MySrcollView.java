package com.cool.ecook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by HP on 2016/7/19.
 */
public class MySrcollView extends ScrollView {
    private OnScrollListener onScrollListener;
    public MySrcollView(Context context) {
        this(context,null);
    }

    public MySrcollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySrcollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null){
            onScrollListener.OnScroll(t);
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;

    }

    //监听的回调接口
    public interface OnScrollListener{
        public void OnScroll(int scrollY);
    }

}
