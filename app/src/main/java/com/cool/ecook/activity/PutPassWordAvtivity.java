package com.cool.ecook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cool.ecook.R;
import com.cool.ecook.bean.Proson;

import javax.security.auth.callback.PasswordCallback;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class PutPassWordAvtivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private CheckBox eys;
    private Button step;
    private EditText password;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_pass_word_avtivity);
        Intent intent = getIntent();
         objectId = intent.getStringExtra("objectId");
        initView();
        initListener();
    }

    private void initListener() {
        back.setOnClickListener(this);
        eys.setOnClickListener(this);
        step.setOnClickListener(this);
    }

    private void initView() {
         back = (ImageView) findViewById(R.id.iv_passwork_back);
        eys = (CheckBox) findViewById(R.id.iv_eys);
         step = (Button) findViewById(R.id.but_step_password);
         password = (EditText) findViewById(R.id.et_one_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_eys:
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case R.id.iv_passwork_back:
                onBackPressed();
                break;
            case R.id.but_step_password:
                String s = password.getText().toString();
                Proson proson = new Proson();
                proson.setPassword(s);
                proson.update(objectId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e ==null){
                            Intent intent = new Intent(PutPassWordAvtivity.this,PutUserInfoActivity.class);
                            intent.putExtra("objectId",objectId);
                            startActivity(intent);
                            showToast("ok pass");
                        }else {
                            showToast("网络缓慢");
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
