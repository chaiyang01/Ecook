package com.cool.ecook.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.cool.ecook.R;
import com.cool.ecook.bean.InternetCookMainInfo;
import com.cool.ecook.bean.SignJumpInfo;
import com.cool.ecook.config.URLConfig;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ningyachao on 2016/7/15.
 */
public class SignJumpActivity extends AppCompatActivity {

    private TextView textViewDesc;
    private TextView textViewOne;
    private TextView textViewOneBelow;
    private TextView textViewTwoBelow;
    private TextView textViewThreeBelow;
    private TextView textViewFour;
    private TextView textViewFourBelow;
    private TextView textViewFive;
    private TextView textViewFiveBelow;
    private TextView textViewSix;
    private TextView textViewSixBelow;
    private TextView textViewSeven;
    private TextView textViewSevenBelow;
    private ConvenientBanner convenientBanner;
    private WebView webView;
    private List<SignJumpInfo.DetailBean.AdListBean> mlist = new ArrayList<>();
    private ImageView imagview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signjump);

        initView();

        ScrollView mscrollView = (ScrollView) findViewById(R.id.sign_scrollview);
        mscrollView.smoothScrollTo(0,45);

        initData();
        
        initListener();
    }

    private void initListener() {
        imagview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {

        textViewDesc = (TextView) findViewById(R.id.sign_seven_day_content);

        textViewOne = (TextView) findViewById(R.id.text_view_sign_one);
        textViewOneBelow = (TextView) findViewById(R.id.text_view_sign_one_below);


        textViewTwoBelow = (TextView) findViewById(R.id.text_view_sign_two_below);


        textViewThreeBelow = (TextView) findViewById(R.id.text_view_sign_three_below);

        textViewFour = (TextView) findViewById(R.id.text_view_sign_four);
        textViewFourBelow= (TextView) findViewById(R.id.text_view_sign_four_below);

        textViewFive = (TextView) findViewById(R.id.text_view_sign_five);
        textViewFiveBelow = (TextView) findViewById(R.id.text_view_sign_five_below);

        textViewSix = (TextView) findViewById(R.id.text_view_sign_six);
        textViewSixBelow = (TextView) findViewById(R.id.text_view_sign_six_below);

        textViewSeven = (TextView) findViewById(R.id.text_view_sign_seven);
        textViewSevenBelow = (TextView) findViewById(R.id.text_view_sign_seven_below);

        convenientBanner = (ConvenientBanner) findViewById(R.id.sign_banner);

        webView = (WebView) findViewById(R.id.sign_webview_last);

        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        imagview = (ImageView) findViewById(R.id.image_view_back);
    }

    private void setBanner(){
        convenientBanner.setPages(new CBViewHolderCreator<NetImageViewHolder>() {
            @Override
            public NetImageViewHolder createHolder() {
                return new NetImageViewHolder();
            }
        },mlist).startTurning(5000);
    }

    private void initData() {
        OkHttpTool.newInstance().start(URLConfig.INTERNET_COOK_SIGN_JUMP).callback(new IOKCallBack() {

            @Override
            public void success(String result) {

                Gson gson = new Gson();

                SignJumpInfo jumpInfo = gson.fromJson(result,SignJumpInfo.class);

                textViewDesc.setText(jumpInfo.getDetail().getDescription());

                List<SignJumpInfo.DetailBean.ListBean> list = jumpInfo.getDetail().getList();

                handlerTime(list);

                mlist.addAll(jumpInfo.getDetail().getAdList());


                setBanner();

                convenientBanner.getViewPager().getAdapter().notifyDataSetChanged();

                String url = jumpInfo.getDetail().getMallUrl();

                webView.getSettings().setJavaScriptEnabled(true);

                webView.loadUrl(url);


            }
        });

    }

    private void handlerTime(List<SignJumpInfo.DetailBean.ListBean> list) {
        String time = format(list.get(0).getDate());
        textViewOne.setText(time);
        String time4 = format(list.get(3).getDate());
        textViewFour.setText(time4);
        String time5 = format(list.get(4).getDate());
        textViewFive.setText(time5);
        String time6 = format(list.get(5).getDate());
        textViewSix.setText(time6);
        String time7 = format(list.get(6).getDate());
        textViewSeven.setText(time7);

        textViewOneBelow.setText(list.get(0).getCoin());
        textViewTwoBelow.setText(list.get(1).getCoin());
        textViewThreeBelow.setText(list.get(2).getCoin());
        textViewFourBelow.setText(list.get(3).getCoin());
        textViewFiveBelow.setText(list.get(4).getCoin());
        textViewSixBelow.setText(list.get(5).getCoin());
        textViewSevenBelow.setText(list.get(6).getCoin());

    }

    private String format(String time) {
       String newtime = time.substring(5,10);
       String date= newtime.replace("-",".");
        return date;
    }


    class  NetImageViewHolder implements Holder<SignJumpInfo.DetailBean.AdListBean> {

        private ImageView imageview;
        @Override
        public View createView(Context context) {
            imageview = new ImageView(context);
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageview;
        }

        @Override
        public void UpdateUI(Context context, int position, SignJumpInfo.DetailBean.AdListBean data) {
            String url = "http://pic.ecook.cn/web/"+data.getImageid()+".jpg!s6";
            Glide.with(SignJumpActivity.this).load(url).into(imageview);
        }
    }
}
