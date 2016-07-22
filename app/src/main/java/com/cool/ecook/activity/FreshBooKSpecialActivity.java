package com.cool.ecook.activity;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
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
import com.cool.ecook.utlis.ShowShareUtils;
import com.cool.ecook.view.CustomProgressDialog;
import com.cool.ecook.view.MyRecyclerView;
import com.cool.ecook.view.MySrcollView;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 最新专辑点击后的跳转界面Activity
 *
 * cy
 */
public class FreshBooKSpecialActivity extends AppCompatActivity implements View.OnClickListener{

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
    private float sca  = 0.0f;
    private ImageView b_back;
    private CheckBox b_zan ;
    private CheckBox b_star;
    private ImageView b_share;
    private ImageView w_back;
    private CheckBox w_zan ;
    private CheckBox w_star;
    private ImageView w_share;
    private boolean flag = true;
    private CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_book_special);

        final Intent intent = getIntent();
         id = intent.getStringExtra("id");
         names = intent.getStringExtra("name");
         nums = intent.getStringExtra("num");
         imageId= intent.getStringExtra("imageId");
        //Dialog动画
        dialog =new CustomProgressDialog(this,R.drawable.ani_progress);
        dialog.show();

        //准备数据
        initData();
        //实例化视图
        initView();

        //创建适配器
      initAdapter();
        //头部工具栏点击事件监听
        initBarListener();


    }

    private void initListener() {
        //菜谱数目详情里的点击事件，跳转到菜谱做法详情
        adapter.setmOnItemClickListener(new BookUserInfoRecyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(FreshBooKSpecialActivity.this,InternetCookListViewItemJumpActivity.class);
                Bundle bundle = new Bundle();
                String type = mDetailsList.get(position).getType();
                String id1 = mDetailsList.get(position).getId();
                String imageid = mDetailsList.get(position).getImageid();
                String collectCount = mDetailsList.get(position).getCollectCount();
                bundle.putString("type",type);
                bundle.putString("id",id1);
                bundle.putString("userImageId",imageid);
                bundle.putString("collectionNum",collectCount);
                intent.putExtra("bundle",bundle);
                startActivity(intent);

            }
        });
        //对菜谱集合界面的创建用户头像进行监听
        userPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FreshBooKSpecialActivity.this,InformationActivity.class);
                String userid = details.getUserid();
                intent1.putExtra("id",userid);
                startActivity(intent1);
                Log.i("rrrr","bbbb"+userid);
            }
        });

        //对每个评论用户头像进行点击监听
        commentAdapter.setmOnItemClickListener(new BookUserInfoCommetRecyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position,String id) {
                Intent intent2 = new Intent(FreshBooKSpecialActivity.this,InformationActivity.class);
                intent2.putExtra("id",id);
                startActivity(intent2);
                Log.i("rrrr","ccccc"+id);
            }
        });

        //头部Bar的动画更换监听
        scrollView.setOnScrollListener(new MySrcollView.OnScrollListener() {
            @Override
            public void OnScroll(int scrollY) {
                //对渐隐动画的逻辑判断
                if (scrollY > 200) {
                    int index = scrollY;
                    int je = 200;
                    if (sca == 201){
                        rl_two.setAlpha(0.0f);
                    }
                rl_one.setVisibility(View.GONE);
                    rl_two.setVisibility(View.VISIBLE);
                        while (index > 200  ) {
                            sca += 0.02f;
                            rl_two.setAlpha(sca);
                            break;
                        }
                }else if (scrollY < 150){
                    rl_one.setVisibility(View.VISIBLE);
                    sca = 0.0f;
                    rl_two.setAlpha(0.0f);
                }
            }
        });



    }

    private void initBarListener() {
        b_back.setOnClickListener(this);//头部Bar的返回键
       b_zan .setOnClickListener(this);
       b_star.setOnClickListener(this);
       b_share.setOnClickListener(this);
       w_back.setOnClickListener(this);
       w_zan .setOnClickListener(this);
       w_star.setOnClickListener(this);
       w_share.setOnClickListener(this);
        load_more.setOnClickListener(this);
        load_more_commnet.setOnClickListener(this);

    }


    private void initAdapter() {
        //菜谱数目详细列表的适配器
         adapter = new BookUserInfoRecyAdapter(mDetailsList,this);
        mRecyclerView.setAdapter(adapter);
        //评论数目详细列表的适配器
         commentAdapter = new BookUserInfoCommetRecyAdapter(mCommetlsList,this);

    }

    private void initView() {
        initBar();
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
         mRecyclerView = (MyRecyclerView) findViewById(R.id.rv_cookbook_num_show);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(manager);

        //评论信息
         comment_num = (TextView) findViewById(R.id.tv_cookbook_comment_num);
         commentRecycler = (RecyclerView) findViewById(R.id.rv_commment_content);
        LinearLayoutManager lManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        commentRecycler.setLayoutManager(lManager);
         no_comment = (ImageView) findViewById(R.id.iv_no_comment);
         sofa = (TextView) findViewById(R.id.tv_sofa);

        //加载更多的事件
        load_more = (TextView) findViewById(R.id.tv_cookbooks_load_more);//菜谱加载更多
        load_more_commnet = (TextView) findViewById(R.id.tv_rece_load_more);//评论加载更多

        //头部大图一起显示的名字和菜谱数量
        String url = URLConfig.PIC_ADDR+imageId+URLConfig.URL_PIC2;
        Glide.with(this).load(url).into(book_image);
        name.setText(names);
        num.setText("- "+nums+"道菜谱 -");
        book_num.setText("菜谱("+nums+")");



    }

    private void initBar() {
         b_back = (ImageView) findViewById(R.id.iv_bar_back);
         b_zan = (CheckBox) findViewById(R.id.iv_bar_zan);
         b_star = (CheckBox) findViewById(R.id.iv_bar_star);
         b_share = (ImageView) findViewById(R.id.iv_bar_share);
         w_back = (ImageView) findViewById(R.id.iv_bar_two_back);
         w_zan = (CheckBox) findViewById(R.id.iv_bar_two_zan);
         w_star = (CheckBox) findViewById(R.id.iv_bar_two_star);
         w_share = (ImageView) findViewById(R.id.iv_bar_two_share);

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

                        mDetailsList.addAll(info.getDetails().getRecipeList());
                        mCommetlsList.addAll(info.getDetails().getCommentList());
                       adapter.notifyDataSetChanged();
                        commentAdapter.notifyDataSetChanged();
                        initListener();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //更新主线程里创建用户的头像和信息
                                String url2 = URLConfig.PIC_ADDR+ FreshBooKSpecialActivity.this.details.getUserimageid()+URLConfig.PIC_ADDR2;
                                Glide.with(FreshBooKSpecialActivity.this).load(url2).into(userPic);
                                userName.setText(FreshBooKSpecialActivity.this.details.getUsernickname());
                                create_at.setText(FreshBooKSpecialActivity.this.details.getTime());
                                userInfo.setText(FreshBooKSpecialActivity.this.details.getDescription());
                                //如果评论数大小为0,进行判断
                                judge();
                                //停止动画
                                dialog.dismiss();

                            }
                        });
                    }
                });

    }
            //如果评论数大小为0,进行判断
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


    /**    对头部Bar的功能工具栏进行监听
     *     对菜谱和评论的加载事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_bar_back:
                onBackPressed();
                break;
            case R.id.iv_bar_zan://有背景的赞点击的同时，另外隐藏的赞也被设置点击
                if (flag == true) {
                b_zan.setChecked(flag);
                    w_zan.setChecked(flag);
                    flag = false;
                }else {//点击取消后另外的也设置取消
                    b_zan.setChecked(false);
                    w_zan.setChecked(false);
                    flag = true;
                }
                break;
            case R.id.iv_bar_star:
                if (flag == true) {
                    b_star.setChecked(flag);
                    w_star.setChecked(flag);
                    flag = false;
                }else {
                    b_star.setChecked(false);
                    w_star.setChecked(false);
                    flag = true;
                }
                break;
            case R.id.iv_bar_share:
                ShowShareUtils.showShare(FreshBooKSpecialActivity.this);
                break;
            case R.id.iv_bar_two_back:
               onBackPressed();
                break;
            case R.id.iv_bar_two_zan:
                if (flag == true) {
                    w_zan.setChecked(flag);
                    b_zan.setChecked(flag);
                    flag = false;
                }else {
                    w_zan.setChecked(false);
                    b_zan.setChecked(false);
                    flag = true;
                }
                break;
            case R.id.iv_bar_two_star:
                if (flag == true) {
                    b_star.setChecked(flag);
                    w_star.setChecked(flag);
                    flag = false;
                }else {
                    b_star.setChecked(false);
                    w_star.setChecked(false);
                    flag = true;
                }
                break;
            case R.id.iv_bar_two_share:
                ShowShareUtils.showShare(FreshBooKSpecialActivity.this);//分享工具的点击事件，弹出分享框
                break;
            case R.id.tv_cookbooks_load_more:

                break;
            case R.id.tv_rece_load_more:
                break;

        }

    }


}
