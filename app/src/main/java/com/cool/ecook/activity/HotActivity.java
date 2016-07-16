package com.cool.ecook.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.cool.ecook.R;
import com.cool.ecook.adapter.BookFreshPagerAdapter;
import com.cool.ecook.fragment.CookBookFreshContentLeftFragment;
import com.cool.ecook.fragment.CookBookFreshContentRightFragment;
import com.cool.ecook.fragment.CookBookHotContentLeftFragment;
import com.cool.ecook.fragment.CookBookHotContentRightFragment;

import java.util.ArrayList;
import java.util.List;

public class HotActivity extends AppCompatActivity {
    private List<Fragment> mFragmentList = new ArrayList<>();

    private List<String> title = new ArrayList<>();
    private ViewPager mViewPager;
    private TabLayout mTab;
    private ImageView back_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_hot);
        initData();
        title.add("最热菜谱");
        title.add("最热专辑");
        initView();
        initAdapter();
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

    private void initFragment() {
        mFragmentList.add(CookBookHotContentLeftFragment.newInstance(null));
        mFragmentList.add(CookBookHotContentRightFragment.newInstance(null));
    }


}
