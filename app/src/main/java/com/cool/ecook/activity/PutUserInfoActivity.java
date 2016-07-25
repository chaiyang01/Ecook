package com.cool.ecook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cool.ecook.R;
import com.cool.ecook.bean.Proson;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class PutUserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private TextView save;
    private EditText name;
    private EditText arge;
    private EditText qm;
    private String objectId;
    private RadioButton boy,gril;
    private Proson proson;
    private String names,arges,qms;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_user_info);
        Intent intent = getIntent();
         objectId = intent.getStringExtra("objectId");
        initView();
        initListener();

    }

    private void initView() {
         back = (ImageView) findViewById(R.id.iv_passwork_back);
         save = (TextView) findViewById(R.id.tv_save_info);
         boy = (RadioButton) findViewById(R.id.but_boy_sel);
         gril = (RadioButton) findViewById(R.id.but_gril_sel);
         name = (EditText) findViewById(R.id.et_putInfo_name);
         arge = (EditText) findViewById(R.id.et_putInfo_arge);
         qm = (EditText) findViewById(R.id.et_putInfo_qianming);
    }

    private void initListener() {
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        onChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_passwork_back:
                break;
            case R.id.tv_save_info:
                 names = this.name.getText().toString();
                 arges = this.arge.getText().toString();
                 qms = this.qm.getText().toString();
               //
                proson = new Proson();
                proson.setSex(sex);
                proson.setAnme(names);
                proson.setAddrss(arges);
                proson.setLog(qms);
                proson.update(objectId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null){
                            Intent intent = new Intent(PutUserInfoActivity.this,UserCenterActivity.class);
                            startActivity(intent);
                        }else {
                            showToast("错误信息");
                        }
                    }
                });

                break;

        }
    }

    private void onChanged() {
        boy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    sex = "男";

                }else {
                    sex = "女";
                }
            }
        });
        gril.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    sex =("女");
                }else {
                    sex=("男");
                }
            }
        });

    }

    public void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}
