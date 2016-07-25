package com.cool.ecook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cool.ecook.R;
import com.cool.ecook.bean.Proson;
import com.cool.ecook.thirdlogin.thirdlogin.OnLoginListener;
import com.cool.ecook.thirdlogin.thirdlogin.ThirdPartyLogin;
import com.cool.ecook.thirdlogin.thirdlogin.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by HP on 2016/7/23.
 */
public class LogingsActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    private EditText phone_put;
    private Button button;
    // 填写从短信SDK应用后台注册得到的APPKEY
    private static String APPKEY = "138bef39adba3";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APPSECRET = "992d2a61fb44b19a8ef3a4fbc3078088";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tpl_login_page);



        initView();
        initListener();
        showDemo();
    }
    private void showDemo() {
        ThirdPartyLogin tpl = new ThirdPartyLogin();
        tpl.setSMSSDKAppkey(APPKEY, APPSECRET);
        tpl.setOnLoginListener(new OnLoginListener() {
            public boolean onSignin(String platform, HashMap<String, Object> res) {
                // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
                // 此处全部给回需要注册
                return true;
            }

            public boolean onSignUp(UserInfo info) {
                // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
                return true;
            }
        });
        tpl.show(this);
    }

    private void initListener() {
        textView.setOnClickListener(this);
        phone_put.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
         phone_put = (EditText) findViewById(R.id.editText);
         button = (Button) findViewById(R.id.button);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                onBackPressed();
                break;
            case R.id.button:
                final String phone_num = phone_put.getText().toString();
                BmobQuery<Proson> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("phone_num", phone_num);
                bmobQuery.findObjects(new FindListener<Proson>() {
                    @Override
                    public void done(List<Proson> list, BmobException e) {
                        if (e ==null){
                            Intent intent = new Intent(LogingsActivity.this,LogingPutPasswordActivity.class);
                            intent.putExtra("phone_num",phone_num);
                            startActivity(intent);
                        }else {
                            if (!TextUtils.isEmpty(phone_num)) {
                                BmobSMS.requestSMSCode(phone_num, "本次验证码为：", new QueryListener<Integer>() {
                                    @Override
                                    public void done(Integer smsId, BmobException e) {
                                        if (e == null) {
                                            Log.i("smile", "短信id：" + smsId);//用于后续的查询本次短信发送状态
                                            //跳转第二步
                                            Intent intent = new Intent(LogingsActivity.this, PutVerifyActivity.class);
                                            intent.putExtra("phone_num", phone_num);
                                            startActivity(intent);
                                        }
                                    }
                                });
                            }
                        }
                    }
                });

                break;
        }
    }
    public void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }


}
