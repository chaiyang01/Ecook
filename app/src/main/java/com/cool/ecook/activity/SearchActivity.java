package com.cool.ecook.activity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.cool.ecook.DaoMaster;
import com.cool.ecook.DaoSession;
import com.cool.ecook.R;
import com.cool.ecook.Search;
import com.cool.ecook.SearchDao;
import com.cool.ecook.adapter.MyadapterViewPager;
import com.cool.ecook.bean.SearchInfoListOne;
import com.cool.ecook.fragment.SearchOneFragment;
import com.cool.ecook.fragment.SearchTwoFragment;
import com.cool.ecook.view.FlowLayoutOne;
import com.cool.ecook.view.XCFlowLayout;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ningyachao on 2016/7/21.
 */
public class SearchActivity extends AppCompatActivity {

    private XCFlowLayout flowLayout;
    private EditText editText;
    private RelativeLayout relativeOne;
    private RelativeLayout relativeTwo;
    private TextView textViewfreind;
    private ImageView imageView;
    private TextView searchTextView;
    private ListView listView;
    private List<String> mlist = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Button button;
    private SearchDao searchDao;
    private RelativeLayout relativeThree;
    private TabLayout tablayout;
    private ViewPager viewPager;

    private List<String> tabNameList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyadapterViewPager viewPagerAdapter;
    private String text;
    private ImageView imageViewBack;
    private TextView textViewSearchFreind;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchDao =getSearchDao();
        inintView();
        setupTitleTabLayout();
        initListener();
        bindAdapter();
        initDataBase();

    }

    private void setupTitleTabLayout() {
        tabNameList.add("菜谱");
        tabNameList.add("厨友");

    }

    private void initDataBase() {

       final SearchDao searchDao = getSearchDao();
        List<Search> list = searchDao.loadAll();

        for (int i = 0;i<list.size();i++){
            Button btn = new Button(this);
            final String name1 =list.get(i).getName();
            btn.setText(name1);
            btn.setBackgroundResource(R.drawable.flowtte_round_background);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10, 10, 10, 10);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    text = name1;
                    editText.setText(text);

                    isExsit(text);

                    Search search = new Search();

                    search.setName(text);

                    searchDao.insert(search);
                    fragmentSwitch();
                }
            });
            flowLayout.addView(btn,lp);
        }

    }

    private void bindAdapter() {

         adapter = new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_list_item_1,mlist);

        listView.setAdapter(adapter);
    }

    private void inintView() {
        flowLayout = (XCFlowLayout) findViewById(R.id.flow_search);

        editText = (EditText) findViewById(R.id.edit_tv);

        relativeOne = (RelativeLayout) findViewById(R.id.fist_one);

        imageView = (ImageView) findViewById(R.id.image_view_clear);

        searchTextView = (TextView) findViewById(R.id.tv_search_change);

        listView = (ListView) findViewById(R.id.seach_list_view);

        relativeTwo = (RelativeLayout) findViewById(R.id.second_one);

        textViewfreind = (TextView) findViewById(R.id.search_friend);

        button = (Button) findViewById(R.id.delete_btn);

        relativeThree = (RelativeLayout) findViewById(R.id.third_one);

        tablayout = (TabLayout) findViewById(R.id.tab_layout);
        
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        imageViewBack = (ImageView) findViewById(R.id.back_search);

        textViewSearchFreind = (TextView) findViewById(R.id.search_friend);
    }

    private void initListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                relativeOne.setVisibility(View.GONE);
                relativeTwo.setVisibility(View.VISIBLE);
                relativeThree.setVisibility(View.GONE);
               searchTextView.setText("搜索");
                searchTextView.setTextColor(Color.BLACK);
            }

            @Override
            public void afterTextChanged(Editable s) {
                  text= editText.getText().toString();
                textViewfreind.setText("搜索名字为【"+text+"】的厨友");
                if (TextUtils.isEmpty(editText.getText().toString())){
                    relativeOne.setVisibility(View.VISIBLE);
                    relativeTwo.setVisibility(View.GONE);
                    relativeThree.setVisibility(View.GONE);
                    searchTextView.setText("取消");
                    searchTextView.setTextColor(Color.YELLOW);
                    return;
                }
                Map<String,String> map = new HashMap<>();
                map.put("machine","868b83db44bafe15546203bbddc50360");
                map.put("version","12.4.6");
                map.put("device","GT-P5210");
                map.put("queryString",text);
                OkHttpTool.newInstance().start("http://api.ecook.cn/public/prompt.shtml").post(map).callback(
                        new IOKCallBack() {
                            @Override
                            public void success(String result) {
                                Gson gson = new Gson();

                                SearchInfoListOne infoOne = gson.fromJson(result,SearchInfoListOne.class);
                               if (infoOne == null ||infoOne.getList().isEmpty()){
                                   return;
                               }
                                mlist.clear();
                                mlist.addAll(infoOne.getList());
                                adapter.notifyDataSetChanged();
                            }
                        });

            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                relativeOne.setVisibility(View.VISIBLE);
                relativeTwo.setVisibility(View.GONE);
                relativeThree.setVisibility(View.GONE);
                searchTextView.setText("取消");
                searchTextView.setTextColor(Color.YELLOW);
                flowLayout.removeAllViews();
                initDataBase();

            }
        });

        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textString = editText.getText().toString();

                if (TextUtils.isEmpty(textString)){
                    onBackPressed();
                    finish();
                    return;
                }

                isExsit(textString);

                Search search = new Search();

                search.setName(textString);

                searchDao.insert(search);

                fragmentSwitch();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.removeAllViews();
                searchDao.deleteAll();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = mlist.get(position);

                text = name;

                editText.setText(text);

                isExsit(text);

                Search search = new Search();

                search.setName(text);

                searchDao.insert(search);
                fragmentSwitch();
            }
        });


        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


        textViewfreind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = editText.getText().toString();

                isExsit(info);

                Search search = new Search();

                search.setName(info);

                searchDao.insert(search);
                fragmentSwitch();
                viewPager.setCurrentItem(1);
            }
        });
    }

    private void fragmentSwitch() {
        relativeOne.setVisibility(View.GONE);
        relativeTwo.setVisibility(View.GONE);
        relativeThree.setVisibility(View.VISIBLE);

        viewPager.removeAllViews();
        fragmentList.clear();

        SearchOneFragment fragment =new  SearchOneFragment();


        SearchTwoFragment fragment1 =new  SearchTwoFragment();

        fragmentList.add(fragment);
        fragmentList.add(fragment1);
        viewPagerAdapter = new MyadapterViewPager(getSupportFragmentManager(),fragmentList,tabNameList);

        viewPager.setAdapter(viewPagerAdapter);
        tablayout.setupWithViewPager(viewPager);
    }

    private void isExsit(String name) {
        List<Search> list = searchDao.loadAll();
        for (int i = 0;i<list.size();i++){
            if (list.get(i).getName().equals(name)){
                searchDao.delete(list.get(i));
            }
        }

    }

    private SearchDao getSearchDao(){

        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this,"searchinfo",null);
        //获得数据库对象
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        //创建一个DaoMaster用来获取UserDao对象，UserDao是用来操作数据库的
        DaoMaster daoMaster = new DaoMaster(readableDatabase);
        DaoSession daoSession = daoMaster.newSession();
        SearchDao searchDao= daoSession.getSearchDao();
        return searchDao;
    }
}
