package com.cool.ecook.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cool.ecook.R;
import com.cool.ecook.bean.FrendringInfo;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 首页布局类似于朋友圈的跳转Activity
 * cy
 */
public class FriendRingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendring);
        //准备数据
        initData();
        //初始化view
        initView();
    }

    private void initView() {

    }

    //machine=f7b9d5f586a588130b89aa03fe7264f3&version=12.4.6&device=SM-G900F&talkid=224512578
    private void initData() {
        OkHttpUtils.post().url("http://api.ecook.cn/public/getTalkById.shtml")
                .addParams("machine","f7b9d5f586a588130b89aa03fe7264f3")
                .addParams("version","12.4.6")
                .addParams("device","SM-G900F")
                .addParams("talkid","224512578")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        FrendringInfo info = gson.fromJson(response,FrendringInfo.class);
                        FrendringInfo.ObjBean obj = info.getObj();

                    }
                });
    }
}
