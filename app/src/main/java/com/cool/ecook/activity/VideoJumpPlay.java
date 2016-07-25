package com.cool.ecook.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.cool.ecook.R;
import com.cool.ecook.bean.VideoPlayInfo;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by ningyachao on 2016/7/21.
 */
public class VideoJumpPlay extends AppCompatActivity {


    private String id;
    private ImageView imageView;
    private TextView textView;
    private String name;
    private String videoUrl;
    private JCVideoPlayerStandard jcVideoPlayerStandard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_jump_play);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        id = bundle.getString("id");
        name = bundle.getString("name");
        initView();
        initData();
        initListenner();

    }
    private void initListenner() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void initData() {
        Map<String,String> map = new HashMap<>();
        map.put("machine","868b83db44bafe15546203bbddc50360");
        map.put("version","12.4.6");
        map.put("device","GT-P5210");
        map.put("project",id);

        OkHttpTool.newInstance().start("http://api.ecook.cn/public/getVideoInfo.shtml").post(map).callback(
                new IOKCallBack() {
                    @Override
                    public void success( String result) {
                        Gson gson = new Gson();
                        VideoPlayInfo videoPlayInfo  = gson.fromJson(result,VideoPlayInfo.class);

                        videoUrl = videoPlayInfo.getUrlprefix()+videoPlayInfo.getName();

                        jcVideoPlayerStandard.setUp(videoUrl,name);
                    }
                }
        );

    }
    @Override
    protected void onPause() {
        super.onPause();
        jcVideoPlayerStandard.releaseAllVideos();
    }

    private void initView() {


        imageView = (ImageView) findViewById(R.id.play_back);

        textView = (TextView) findViewById(R.id.play_tv);

        textView.setText(name);

        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jiecao_play);
    }
}
