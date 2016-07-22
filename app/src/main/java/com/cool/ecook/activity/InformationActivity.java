package com.cool.ecook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.adapter.SquareInfoAdapter;
import com.cool.ecook.adapter.SquareRecipeAdapter;
import com.cool.ecook.bean.SquareInfoBean;
import com.cool.ecook.config.URLConfig;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

public class InformationActivity extends AppCompatActivity {
    //UI初始化
    private CircleImageView civ;
    private TextView tvName;
    private ImageView ivSex;
    private ImageView imageView;
    private TextView tvTitle;
    private TextView tvFan;
    private TextView tvAcction;
    private TextView tvSpecial;
    private TextView tvMenu;
    private TextView tvCook;
    private String id;
    private GridView gvColl;
    private GridView gvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Intent intent = getIntent();
        //获得上个页面传过来的ID
        id = intent.getStringExtra("id");
        //初始化视图
        initView();
        //加载数据
        setupDatas();
    }
    //
    private Map<String,String> map = new HashMap<>();
    private SquareInfoAdapter adapter ;
    private SquareRecipeAdapter reAdapter;
    private void setupDatas() {
        OkHttpUtils.post().url(URLConfig.URL_SQUARE4)
                .addParams("machine","d02c35fb0454e400bba7f3e2882cfd9a")
                .addParams("version","12.4.6")
                .addParams("device","Samsung+Galaxy+S4+-+4.3+-+API+18+-+1080x1920")
                .addParams("id",id)
                .build()
                .execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if (response==null){
                    return;
                }
                Gson gson = new Gson();
                SquareInfoBean squareInfoBean = gson.fromJson(response, SquareInfoBean.class);
                Glide.with(getApplicationContext()).load(URLConfig.URL_PIC1+squareInfoBean.getData().getImageid()+URLConfig.URL_PIC2).into(civ);
                        tvName.setText(squareInfoBean.getData().getTitle());
                if (squareInfoBean.getData().getSex().equals("0")){
                    ivSex.setImageResource(R.drawable.me_gir);
                }else if (squareInfoBean.getData().getSex().equals("1")){
                    ivSex.setImageResource(R.drawable.me_boy);
                }else {
                    ivSex.setImageResource(R.drawable.me_gir);
                }
                if (squareInfoBean.getData().getMedal().equals("gold")){
                    imageView.setImageResource(R.drawable.medal_a);
                }else if(squareInfoBean.getData().getMedal().equals("copper")){
                    imageView.setImageResource(R.drawable.medal_c);
                }else if (squareInfoBean.getData().getMedal().equals("silver")){
                    imageView.setImageResource(R.drawable.medal_b);
                }else {
                    imageView.setImageResource(R.drawable.medal_d);
                }
                if(squareInfoBean.getData().getProfile()==null){
                    tvTitle.setText("这家伙很懒，什么都没写～");
                }else {
                    tvTitle.setText(squareInfoBean.getData().getProfile());
                }
                tvFan.setText("粉丝"+squareInfoBean.getData().getFansCount());
                tvAcction.setText("关注"+squareInfoBean.getData().getFollowsCount());
                tvSpecial.setText(squareInfoBean.getData().getCollectionSortCount()+"");
                tvMenu.setText(squareInfoBean.getData().getRecipeCount()+"");
                tvCook.setText(squareInfoBean.getData().getTalkCount()+"");
                //创建专辑的适配器
                adapter = new SquareInfoAdapter(squareInfoBean.getData().getCollectionSortList(),getApplicationContext());
                //绑定适配器
                gvColl.setAdapter(adapter);
                //创建菜谱的适配器
                reAdapter = new SquareRecipeAdapter(squareInfoBean.getData().getRecipeList(),getApplicationContext());
                //绑定适配器
                gvMenu.setAdapter(reAdapter);
            }
        });
    }

    private void initView() {
        civ = (CircleImageView) findViewById(R.id.ci_pic_show);
        tvName = (TextView) findViewById(R.id.info_name_tv);
        ivSex = (ImageView) findViewById(R.id.info_sex_iv);
        imageView = (ImageView) findViewById(R.id.imageView);
        tvTitle = (TextView) findViewById(R.id.info_title_tv);
        tvFan = (TextView) findViewById(R.id.info_fan_tv);
        tvAcction = (TextView) findViewById(R.id.info_attion_tv);
        tvSpecial = (TextView) findViewById(R.id.info_specialnum_tv);
        tvMenu = (TextView) findViewById(R.id.info_menunum_tv);
        tvCook = (TextView) findViewById(R.id.info_talknum_tv);
        gvColl = (GridView) findViewById(R.id.gv_coll);
        gvMenu = (GridView) findViewById(R.id.gv_menu);
    }

}
