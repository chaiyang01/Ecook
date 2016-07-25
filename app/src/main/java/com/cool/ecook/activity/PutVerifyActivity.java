package com.cool.ecook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cool.ecook.R;
import com.cool.ecook.bean.Proson;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class PutVerifyActivity extends AppCompatActivity {

    private String phone_num;
    private EditText verify1,verify2,verify3,verify4,verify5,verify6;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_verify);
        Intent intent = getIntent();
         phone_num = intent.getStringExtra("phone_num");
        initView();
    }


    private void initView() {
        final Button next = (Button) findViewById(R.id.but_step_verify);
         verify1 = (EditText) findViewById(R.id.et_one_verify);
         verify2 = (EditText) findViewById(R.id.et_two_verify);
         verify3= (EditText) findViewById(R.id.et_three_verify);
         verify4 = (EditText) findViewById(R.id.et_four_verify);
         verify5 = (EditText) findViewById(R.id.et_five_verify);
         verify6 = (EditText) findViewById(R.id.et_six_verify);

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final StringBuilder sb = new StringBuilder();
                sb.append(verify1.getText().toString()).append(verify2.getText().toString())
                        .append(verify3.getText().toString()).append(verify4.getText().toString())
                        .append(verify5.getText().toString()).append(verify6.getText().toString());

                BmobSMS.verifySmsCode(phone_num, sb.toString(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null){
                            final Proson p1 = new Proson();
                            p1.setPhone_num(phone_num);
                            p1.setPassword("");
                            p1.setAnme("");
                            p1.setSex("");
                            p1.setAddrss("");

                            p1.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e ==null){
                                        objectId = p1.getObjectId();
                                        Toast.makeText(PutVerifyActivity.this, "ok", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(PutVerifyActivity.this, PutPassWordAvtivity.class);
                                        intent.putExtra("objectId",objectId);
                                        startActivity(intent);
                                    }
                                }
                            });

                        }else {
                            Toast.makeText(PutVerifyActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }


        });
    }


}
