package com.cool.ecook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.adapter.FootListViewAdapter;
import com.cool.ecook.adapter.HeadListViewAdapter;
import com.cool.ecook.adapter.MainListViewAdapter;
import com.cool.ecook.bean.ComentInfo;
import com.cool.ecook.bean.MainListViewInfo;
import com.cool.ecook.utlis.MikyouMetricListViewHeightUtils;
import com.cool.ecook.view.MyListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ningyachao on 2016/7/18.
 */
public class InternetCookListViewItemJumpActivity extends AppCompatActivity {
    private String id;
    private ListView mainListView;

    private List<ComentInfo.InfoBean.CommentBean> commentList = new ArrayList<>();

    private List<MainListViewInfo.ListBean.MaterialListBean> headList = new ArrayList<>();

    private List<MainListViewInfo.ListBean.StepListBean> mainList = new ArrayList<>();
    private RelativeLayout headView;
    private MyListView headListview;
    private RelativeLayout footView;
    private MyListView footListView;
    private MainListViewAdapter mainAdapter;
    private HeadListViewAdapter headAdapter;
    private FootListViewAdapter footAdapter;
    private ImageView imageViewHeadMain;
    private TextView textViewTitle;
    private String collectionNum;
    private String userImageId;
    private TextView textViewGood;
    private TextView textViewCollection;
    private TextView textViewUserName;
    private TextView textViewDesc;
    private TextView textViewLittleTip;
    private LinearLayout ll_out;
    private TextView textViewDetail;
    private ImageView imageViewAd;
    private TextView textViewCommentNumber;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private ImageView imageViewPlay;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view_item_jump);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        id = bundle.getString("id");
        collectionNum = bundle.getString("collectionNum");
        userImageId = bundle.getString("userImageId");
        type =bundle.getString("type");

        initView();
        initContentData();
        initMainData();
        initListener();
    }

    private void initListener() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initMainData() {
        Map<String, String> map = new HashMap<>();
        map.put("machine", "868b83db44bafe15546203bbddc50360");
        map.put("version", "12.4.6");
        map.put("device", "GT-P5210");
        map.put("ids", id);

        OkHttpTool.newInstance().start("http://api.ecook.cn/public/getRecipeListByIds.shtml").post(map).callback(
                new IOKCallBack() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        MainListViewInfo mainListViewInfo = gson.fromJson(result, MainListViewInfo.class);

                        headList.addAll(mainListViewInfo.getList().get(0).getMaterialList());

                        mainList.addAll(mainListViewInfo.getList().get(0).getStepList());

                        headAdapter = new HeadListViewAdapter(InternetCookListViewItemJumpActivity.this, R.layout.mian_head_list_view_item, headList);
                        headListview.setAdapter(headAdapter);
//                        MikyouMetricListViewHeightUtils.setListViewHeight(headListview);

                        mainListView.addHeaderView(headView);
                        mainAdapter = new MainListViewAdapter(InternetCookListViewItemJumpActivity.this, R.layout.main_list_view_item, mainList);
                        mainListView.setAdapter(mainAdapter);
                        String url = "http://pic.ecook.cn/web/" + mainListViewInfo.getList().get(0).getImageid() + ".jpg";
                        Glide.with(InternetCookListViewItemJumpActivity.this).load(url).into(imageViewHeadMain);

                        textViewTitle.setText(mainListViewInfo.getList().get(0).getName());

                        textViewUserName.setText(mainListViewInfo.getList().get(0).getAuthorname());

                        textViewDesc.setText(mainListViewInfo.getList().get(0).getDescription());

                        if (mainListViewInfo.getList().get(0) == null
                                || mainListViewInfo.getList().get(0).getTipList().isEmpty() )
                        {
                            return;
                        }
                        if (mainListViewInfo.getList().get(0).getTipList().get(0).getDetails() == "") {
                            textViewLittleTip.setVisibility(View.GONE);
                            ll_out.setVisibility(View.GONE);
                        } else {
                            textViewDetail.setText(mainListViewInfo.getList().get(0).getTipList().get(0).getDetails());
                        }

                    }
                }
        );


    }

    private void initContentData() {
        Map<String, String> map = new HashMap<>();
        map.put("machine", "868b83db44bafe15546203bbddc50360");
        map.put("version", "12.4.6");
        map.put("device", "GT-P5210");
        map.put("id", id);

        OkHttpTool.newInstance().start("http://api.ecook.cn/public/getRecipeInfoById.shtml").post(map).callback(
                new IOKCallBack() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        ComentInfo comentInfo = gson.fromJson(result, ComentInfo.class);

                        commentList.addAll(comentInfo.getInfo().getComment());

                        footAdapter.notifyDataSetChanged();

//                        MikyouMetricListViewHeightUtils.setListViewHeight(footListView);

                        mainListView.addFooterView(footView);

                        textViewCommentNumber.setText("  评论" + "(" + comentInfo.getInfo().getCommentCount() + ")");
                    }
                }
        );

    }

    private void initView() {

        imageView = (ImageView) findViewById(R.id.main_list_view_item_back);

        mainListView = (ListView) findViewById(R.id.main_item_jump_list_view);

        mainListView.setDivider(null);

        headView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.main_list_view_jump_head, null);

        headListview = (MyListView) headView.findViewById(R.id.head_list_view);

        headListview.setDivider(null);

        imageViewHeadMain = (ImageView) headView.findViewById(R.id.main_head_image_view);

        imageViewPlay = (ImageView) headView.findViewById(R.id.play);

        if (type.equals("3")){
            imageViewPlay.setVisibility(View.VISIBLE);
        }else {
            imageViewPlay.setVisibility(View.GONE);
        }

        textViewTitle = (TextView) headView.findViewById(R.id.main_tv_title);

        textViewGood = (TextView) headView.findViewById(R.id.zan_tv);

        textViewCollection = (TextView) headView.findViewById(R.id.collection_tv);

        textViewCollection.setText(collectionNum);
        textViewGood.setText(collectionNum);

        CircleImageView userCircleImageView = (CircleImageView) headView.findViewById(R.id.main_circle_image_view);
        String url = "http://pic.ecook.cn/web/" + userImageId + ".jpg";
        Glide.with(InternetCookListViewItemJumpActivity.this).load(url).into(userCircleImageView);

        textViewUserName = (TextView) headView.findViewById(R.id.tv_username_tv);

        textViewDesc = (TextView) headView.findViewById(R.id.desc);


        footView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.main_list_view_jump_foot, null);


        textViewLittleTip = (TextView) footView.findViewById(R.id.tv_foot);

        ll_out = (LinearLayout) footView.findViewById(R.id.ll_foot);

        textViewDetail = (TextView) footView.findViewById(R.id.tip_content);

        imageViewAd = (ImageView) footView.findViewById(R.id.image_foot_haha);
        String url2 = "http://pic.ecook.cn/web/" + 224475788 + ".jpg";
        Glide.with(InternetCookListViewItemJumpActivity.this).load(url2).into(imageViewAd);

        textViewCommentNumber = (TextView) footView.findViewById(R.id.foot_title);

        footListView = (MyListView) footView.findViewById(R.id.foot_list_view);

        footListView.setDivider(null);

        RelativeLayout relative = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.foot_list_view_foot_view, null);

        footListView.addFooterView(relative);

        linearLayout = (LinearLayout) footView.findViewById(R.id.ll_empty_view);

        footListView.setEmptyView(linearLayout);

        footAdapter = new FootListViewAdapter(InternetCookListViewItemJumpActivity.this, R.layout.main_foot_list_view_item, commentList);

        footListView.setAdapter(footAdapter);

    }
}
