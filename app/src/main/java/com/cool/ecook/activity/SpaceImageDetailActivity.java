package com.cool.ecook.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.adapter.ViewPagePicAdapter;
import com.cool.ecook.config.URLConfig;
import com.cool.ecook.view.SmoothImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 放大图片
 */
public class SpaceImageDetailActivity extends AppCompatActivity {

    private String[] recvData;
    private int i;
    private SmoothImageView imageView;
    private List<View> list = new ArrayList<>();
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_image_detail);
        vp = (ViewPager) findViewById(R.id.vp);
        //接收传来的数组
        recvData = getIntent().getStringArrayExtra("DATA") ;
        //接收传来的数组下标
        String id = getIntent().getStringExtra("id");
        i = Integer.parseInt(id);
        //接收自定义ImageView需要的一些属性
        int mLocationX = getIntent().getIntExtra("locationX", 0);
        int mLocationY = getIntent().getIntExtra("locationY", 0);
        int mWidth = getIntent().getIntExtra("width", 0);
        int mHeight = getIntent().getIntExtra("height", 0);
        for (int j  = 0;j<recvData.length;j++){
            View view = LayoutInflater.from(this).inflate(R.layout.item_pic_show,null);
            SmoothImageView imageView = (SmoothImageView) view.findViewById(R.id.siv);
            imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
            imageView.transformIn();
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(this).load(URLConfig.URL_PIC1 + recvData[j] + URLConfig.URL_PIC2).asBitmap().into(imageView);
            //点击图片退出
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            list.add(view);
        }
        //viewpager的适配器创建
        ViewPagePicAdapter adapter = new ViewPagePicAdapter(list);
        //绑定适配器
        vp.setAdapter(adapter);
        //默认选择第i张
        vp.setCurrentItem(i);

//        点击图片换图片
//        imageView = new SmoothImageView(this);
//        imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
//        imageView.transformIn();
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        setContentView(imageView);
//        overridePendingTransition(0, 0);
//        Glide.with(this).load(URLConfig.URL_PIC1 + recvData[i] + URLConfig.URL_PIC2).asBitmap().into(imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (i<recvData.length-1){
//                    i = i+1;
//                Glide.with(getApplication()).load(URLConfig.URL_PIC1 + recvData[i] + URLConfig.URL_PIC2).asBitmap().into(imageView);
//                }else {
//                    i = 0;
//                    Glide.with(getApplication()).load(URLConfig.URL_PIC1 + recvData[i] + URLConfig.URL_PIC2).asBitmap().into(imageView);
//                }
//            }
//        });
//        imageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                finish();
//                return false;
//            }
//        });
    }

}
