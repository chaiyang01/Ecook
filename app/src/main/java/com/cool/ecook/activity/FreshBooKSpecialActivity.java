package com.cool.ecook.activity;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.adapter.BookUserInfoCommetRecyAdapter;
import com.cool.ecook.adapter.BookUserInfoRecyAdapter;
import com.cool.ecook.bean.CookBookDetailsInfo;
import com.cool.ecook.config.URLConfig;
import com.cool.ecook.view.MyRecyclerView;
import com.cool.ecook.view.MySrcollView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 最新专辑点击后的跳转界面Activity
 */
public class FreshBooKSpecialActivity extends AppCompatActivity {

    private String id;
    private List<CookBookDetailsInfo.DetailsBean.RecipeListBean> mDetailsList =new ArrayList<>();
    private List<CookBookDetailsInfo.DetailsBean.CommentListBean> mCommetlsList =new ArrayList<>();

    private String names;
    private String nums;
    private String imageId;
    private ImageView book_image;
    private TextView userName;
    private  TextView create_at;
    private TextView userInfo;
    private CircleImageView userPic;
   private MyRecyclerView mRecyclerView;
    private BookUserInfoRecyAdapter adapter;
    private TextView num;
    private TextView name;
    private TextView book_num;
    private CookBookDetailsInfo.DetailsBean details;
    private TextView load_more;
    private TextView load_more_commnet;
    private RecyclerView commentRecycler;
    private ImageView no_comment;
    private TextView comment_num;
    private TextView sofa;
    private BookUserInfoCommetRecyAdapter commentAdapter;
    private MySrcollView scrollView;
    private LinearLayout rl_one;
    private RelativeLayout rl_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_book_special);

        Intent intent = getIntent();
         id = intent.getStringExtra("id");
         names = intent.getStringExtra("name");
         nums = intent.getStringExtra("num");
         imageId= intent.getStringExtra("imageId");
        //准备数据
        initData();
        //实例化视图
        initView();

        //创建适配器
      initAdapter();
        //对每道菜谱进行点击监听
       initListener();
    }

    private void initListener() {
        //菜谱数目详情里的点击事件，跳转到菜谱做法详情
        adapter.setmOnItemClickListener(new BookUserInfoRecyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

            }
        });

        //头部Bar的动画更换监听
        scrollView.setOnScrollListener(new MySrcollView.OnScrollListener() {
            @Override
            public void OnScroll(int scrollY) {
                if (scrollY > 200) {
                rl_one.setVisibility(View.GONE);
                    rl_two.setVisibility(View.VISIBLE);
                    rotateyAnimRun(rl_two);
                }else if (scrollY < 150){
                    rl_one.setVisibility(View.VISIBLE);

                    rl_two.setAlpha(0.0f);
                }
            }
        });

    }

    private void rotateyAnimRun(View view) {
        ObjectAnimator.ofFloat(view,"ddd",0.0f,1.0f)
        .setDuration(3000)
        .start();

    }

    private void initAdapter() {
        //菜谱数目详细列表的适配器
         adapter = new BookUserInfoRecyAdapter(mDetailsList,this);
        mRecyclerView.setAdapter(adapter);
        //评论数目详细列表的适配器
         commentAdapter = new BookUserInfoCommetRecyAdapter(mCommetlsList,this);
        commentRecycler.setAdapter(commentAdapter);

    }

    private void initView() {
         scrollView = (MySrcollView) findViewById(R.id.sv_cookbook_detail);
         rl_one = (LinearLayout) findViewById(R.id.cookbook_bar_plan_a);
         rl_two = (RelativeLayout) findViewById(R.id.cookbook_bar_plan_b);

        //头部图片和名字
        book_image = (ImageView) findViewById(R.id.iv_icon_cookbook);
         name = (TextView) findViewById(R.id.tv_presh_name);
        num = (TextView) findViewById(R.id.tv_presh_num);

        //用户头像和信息
         userPic = (CircleImageView) findViewById(R.id.cir_iv_user);
       userName = (TextView) findViewById(R.id.tv_username_cookbook);
       create_at =  (TextView) findViewById(R.id.tv_create_time);
       userInfo = (TextView) findViewById(R.id.tv_user_info);

        //recycler用来显示用户的菜谱信息
         book_num = (TextView) findViewById(R.id.tv_cookbook_num);//菜谱数量
         load_more = (TextView) findViewById(R.id.tv_cookbooks_load_more);//菜谱加载更多
         mRecyclerView = (MyRecyclerView) findViewById(R.id.rv_cookbook_num_show);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(manager);

        //评论信息
         comment_num = (TextView) findViewById(R.id.tv_cookbook_comment_num);
         commentRecycler = (RecyclerView) findViewById(R.id.rv_commment_content);
        LinearLayoutManager lManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        commentRecycler.setLayoutManager(lManager);
         no_comment = (ImageView) findViewById(R.id.iv_no_comment);
        load_more_commnet = (TextView) findViewById(R.id.tv_rece_load_more);//评论加载更多
         sofa = (TextView) findViewById(R.id.tv_sofa);




        String url = URLConfig.PIC_ADDR+imageId+URLConfig.URL_PIC2;
        Glide.with(this).load(url).into(book_image);
        name.setText(names);
        num.setText("- "+nums+"道菜谱 -");
        book_num.setText("菜谱("+nums+")");



    }

    private void initData() {
        OkHttpUtils.post().url("http://api.ecook.cn/public/getCollectionSortDetailById.shtml")
                .addParams("machine","f7b9d5f586a588130b89aa03fe7264f3")//添加Post请求的参数
                .addParams("version","SM-G900F")
                .addParams("device","GT-P5210")
                .addParams("id",id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Gson gson = new Gson();
                        CookBookDetailsInfo info = gson.fromJson(response,CookBookDetailsInfo.class);
                        details = info.getDetails();
                        CookBookDetailsInfo.DetailsBean details = info.getDetails();

                        mDetailsList.addAll(info.getDetails().getRecipeList());
                        mCommetlsList.addAll(info.getDetails().getCommentList());
                       adapter.notifyDataSetChanged();
                        commentAdapter.notifyDataSetChanged();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String url2 = URLConfig.PIC_ADDR+ FreshBooKSpecialActivity.this.details.getUserimageid()+URLConfig.PIC_ADDR2;
                                Glide.with(FreshBooKSpecialActivity.this).load(url2).into(userPic);
                                userName.setText(FreshBooKSpecialActivity.this.details.getName());
                                create_at.setText(FreshBooKSpecialActivity.this.details.getTime());
                                userInfo.setText(FreshBooKSpecialActivity.this.details.getDescription());
                                //如果评论数大小为0,进行判断
                                judge();

                            }
                        });
                    }
                });

    }

            private void judge() {
                if(details.getCommentList().size() == 0){
                    no_comment.setVisibility(View.VISIBLE);
                    load_more_commnet.setVisibility(View.GONE);
                    sofa.setVisibility(View.VISIBLE);
                    comment_num.setText("评论(0)");
                }else {
                    no_comment.setVisibility(View.GONE);
                    sofa.setVisibility(View.GONE);
                    load_more_commnet.setVisibility(View.VISIBLE);
                    int size = details.getCommentList().size();
                    comment_num.setText("评论("+size+")");
                }
            }
}
