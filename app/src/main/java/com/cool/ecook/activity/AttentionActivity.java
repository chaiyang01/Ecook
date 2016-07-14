package com.cool.ecook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cool.ecook.R;

/**
 * 关注  YX   LH
 */
public class AttentionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView cancerAttentionTv;
    private Button loginAttentionBtn;
    private ImageView sinaAttentionIv;
    private ImageView qqAttentionIv;
    private ImageView emailAttentionIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        initView();
        initListener();
    }

    private void initView() {
        cancerAttentionTv = (TextView) findViewById(R.id.tv_attention_cancer);
        loginAttentionBtn = (Button) findViewById(R.id.btn_attention_login);
        sinaAttentionIv = (ImageView) findViewById(R.id.iv_attention_sina);
        qqAttentionIv = (ImageView) findViewById(R.id.iv_attention_qq);
        emailAttentionIv = (ImageView) findViewById(R.id.iv_attention_email);
    }

    private void initListener() {
        cancerAttentionTv.setOnClickListener(this);
        loginAttentionBtn.setOnClickListener(this);
        sinaAttentionIv.setOnClickListener(this);
        qqAttentionIv.setOnClickListener(this);
        emailAttentionIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String key = "";
        switch (view.getId()){
            case R.id.tv_attention_cancer:
                key = "cancer";
                break;
            case R.id.btn_attention_login:
                key = "login";
                break;
            case R.id.iv_attention_sina:
                key = "sina";
//                Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
//                authorize(sina);
                break;
            case R.id.iv_attention_qq:
                key = "qq";
                break;
            case R.id.iv_attention_email:
                key = "email";
                break;
        }
        Toast.makeText(this,key,Toast.LENGTH_SHORT).show();
    }

//    //执行授权,获取用户信息
//    //文档：http://wiki.mob.com/Android_%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E8%B5%84%E6%96%99
//    private void authorize(Platform plat) {
//        if (plat == null) {
//            popupOthers();
//            return;
//        }
//
//        plat.setPlatformActionListener(this);
//        //关闭SSO授权
//        plat.SSOSetting(true);
//        plat.showUser(null);
//    }
//
//    @Override
//    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//        if (i == Platform.ACTION_USER_INFOR) {
//            Message msg = new Message();
//            msg.what = MSG_AUTH_COMPLETE;
//            msg.obj = new Object[] {platform.getName(), hashMap};
//            handler.sendMessage(msg);
//        }
//    }
//
//    @Override
//    public void onError(Platform platform, int i, Throwable throwable) {
//
//    }
//
//    @Override
//    public void onCancel(Platform platform, int i) {
//
//    }
//
//    @Override
//    public boolean handleMessage(Message msg) {
//        switch(msg.what) {
//            case MSG_AUTH_CANCEL: {
//                //取消授权
//                Toast.makeText(activity, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
//            } break;
//            case MSG_AUTH_ERROR: {
//                //授权失败
//                Toast.makeText(activity, R.string.auth_error, Toast.LENGTH_SHORT).show();
//            } break;
//            case MSG_AUTH_COMPLETE: {
//                //授权成功
//                Toast.makeText(activity, R.string.auth_complete, Toast.LENGTH_SHORT).show();
//                Object[] objs = (Object[]) msg.obj;
//                String platform = (String) objs[0];
//                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
//                if (signupListener != null && signupListener.onSignin(platform, res)) {
//                    SignupPage signupPage = new SignupPage();
//                    signupPage.setOnLoginListener(signupListener);
//                    signupPage.setPlatform(platform);
//                    signupPage.show(activity, null);
//                }
//            } break;
//            case MSG_SMSSDK_CALLBACK: {
//                if (msg.arg2 == SMSSDK.RESULT_ERROR) {
//                    Toast.makeText(activity, "操作失败", Toast.LENGTH_SHORT).show();
//                } else {
//                    switch (msg.arg1) {
//                        case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE: {
//                            if(msgLoginDlg != null && msgLoginDlg.isShowing()){
//                                msgLoginDlg.dismiss();
//                            }
//                            Toast.makeText(activity, "提交验证码成功", Toast.LENGTH_SHORT).show();
//                            Message m = new Message();
//                            m.what = MSG_AUTH_COMPLETE;
//                            m.obj = new Object[] {"SMSSDK", (HashMap<String, Object>) msg.obj};
//                            handler.sendMessage(m);
//                        } break;
//                        case SMSSDK.EVENT_GET_VERIFICATION_CODE:{
//                            Toast.makeText(activity, "验证码已经发送", Toast.LENGTH_SHORT).show();
//                        } break;
//                    }
//                }
//            } break;
//        }
//        return false;
//    }
}
