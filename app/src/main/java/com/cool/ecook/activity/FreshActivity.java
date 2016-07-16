package com.cool.ecook.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.cool.ecook.R;
import com.cool.ecook.adapter.BookFreshPagerAdapter;
import com.cool.ecook.fragment.CookBookFreshContentLeftFragment;
import com.cool.ecook.fragment.CookBookFreshContentRightFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 最新菜点击后跳转的Activity
 * 里面创建了TabLayout和ViewPager
 * 能够在最新菜谱和最新专辑两个节目进行滑动
 */
public class FreshActivity extends AppCompatActivity {
    private List<Fragment> mFragmentList = new ArrayList<>();

    private List<String> title = new ArrayList<>();
    private ViewPager mViewPager;
    private TabLayout mTab;
    private ImageView back_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_fresh);
        initData();
        //添加标题
        title.add("最新菜谱");
        title.add("最新专辑");
        //初始化控件
        initView();
        //创建适配器
        initAdapter();
        //监听点击事件
        initListener();
    }

    private void initListener() {
        back_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initAdapter() {
        BookFreshPagerAdapter adapter = new BookFreshPagerAdapter(getSupportFragmentManager(),title,mFragmentList);
        mViewPager.setAdapter(adapter);
        mTab.setupWithViewPager(mViewPager);
    }
    private void initData() {
        for (int i = 0; i < title.size(); i++) {
            mTab.addTab(mTab.newTab().setText(title.get(i)));

        }

    }

    private void initView() {
        initFragment();
         back_bar = (ImageView) findViewById(R.id.iv_cookbook_fresh_bar_back);
         mTab = (TabLayout) findViewById(R.id.tb_cookbook_fresh_title);
         mViewPager = (ViewPager) findViewById(R.id.vp_cookbook_fresh_content_show);
    }
    //调用newInstance方法实例化Fragnet,然后准备添加到ViewPager
    private void initFragment() {
        mFragmentList.add(CookBookFreshContentLeftFragment.newInstance(null));
        mFragmentList.add(CookBookFreshContentRightFragment.newInstance(null));
    }


}
