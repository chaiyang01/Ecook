package com.cool.ecook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 流式布局
 * Created by hengheng on 2016/6/23.
 * onMeasure测量宽高 -> onLayout排列子控件 -> onDraw绘制
 */
public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //排列子控件
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int cl = 0;
        int ct = 0;
        int width = r - l;//布局的宽
        int maxHeight = 0;//最大的行高
        //排列每个子控件
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int cw = child.getMeasuredWidth();//获得子控件的测量后的宽度
            int ch = child.getMeasuredHeight();//获得子控件的测量后的高度
            int cr = cl + cw;
            int cb = ct + ch;
            //判断是否换行
            if(cr > width){
                cl = 0;
                ct = ct + maxHeight;
                cr = cl + cw;
                cb = ct + ch;
//                int n = 10 / 0;
                maxHeight = ch;
            }else{
                maxHeight = Math.max(maxHeight,ch);//求得行中最大的高度
            }
            //排列子控件
            child.layout(cl,ct,cr,cb);
            //水平排列
            cl = cr;
        }
    }

    //测量布局
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量所有的子控件
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }
}
