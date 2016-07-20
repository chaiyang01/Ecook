package com.cool.ecook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.config.URLConfig;

/**
 * 放大图片
 */
public class SpaceImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_image_detail);
        String images = getIntent().getStringExtra("images");
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        Glide.with(this).load(URLConfig.URL_PIC1+images+URLConfig.URL_PIC2).into(imageView);
    }
}
