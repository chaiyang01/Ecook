package com.cool.ecook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.cool.ecook.R;

public class LogingPutPasswordActivity extends AppCompatActivity {

    private EditText editText;
    private String phone_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging_put_password);
        Intent intent = getIntent();
         phone_num = intent.getStringExtra("phone_num");
        initView();

    }

    private void initView() {
         editText = (EditText) findViewById(R.id.et_num);
        editText.setText(phone_num);
    }
}
