package com.cool.ecook.utlis;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by ningyachao on 2016/7/18.
 */
public class MikyouMetricListViewHeightUtils {

    public static void setListViewHeight(ListView lv){
        if (lv==null) {
            return ;
        }
        ListAdapter adapter=lv.getAdapter();
        if (adapter==null) {
            return ;
        }
        int totalHeight=0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem=adapter.getView(i, null, lv);
            listItem.measure(0, 0);
            totalHeight+=listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params=lv.getLayoutParams();
        params.height=totalHeight+(lv.getDividerHeight()*(lv.getCount()-1));//这里还将分割线的高度考虑进去了，统计出所有分割线占有的高度和
        lv.setLayoutParams(params);
    }
}
