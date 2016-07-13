package com.cool.ecook;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cool.ecook.fragment.AddFragment;
import com.cool.ecook.fragment.CookBookFragment;
import com.cool.ecook.fragment.CookSpeakFragment;
import com.cool.ecook.fragment.InterCookFragment;
import com.cool.ecook.fragment.MyFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    //UI
    private RadioGroup radioGroup;
    //Fragment
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private InterCookFragment interCookFragment;
    private CookBookFragment cookBookFragment;
    private AddFragment addFragment;
    private CookSpeakFragment cookSpeakFragment;
    private MyFragment myFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图
        initView();
        manager = getSupportFragmentManager();
        radioGroup.setOnCheckedChangeListener(this);
        //选中网上厨房
        RadioButton childAt = (RadioButton) radioGroup.getChildAt(0);
        childAt.setChecked(true);
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_buttom);
    }
    //Fragment之间的切换
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        transaction = manager.beginTransaction();
        switch (i){
            case R.id.rb_internetcook:
                if (interCookFragment==null){
                    interCookFragment = InterCookFragment.newInstance();
                    transaction.add(R.id.ll_main,interCookFragment,"interCook");
                    if (cookBookFragment!=null){
                        transaction.hide(cookBookFragment);
                    }
                    if (addFragment!=null){
                        transaction.hide(addFragment);
                    }
                    if (cookSpeakFragment!=null){
                        transaction.hide(cookSpeakFragment);
                    }
                    if (myFragment!=null){
                        transaction.hide(myFragment);
                    }

                }else {
                    transaction.show(interCookFragment);
                    if (cookBookFragment!=null){
                        transaction.hide(cookBookFragment);
                    }
                    if (addFragment!=null){
                        transaction.hide(addFragment);
                    }
                    if (cookSpeakFragment!=null){
                        transaction.hide(cookSpeakFragment);
                    }
                    if (myFragment!=null){
                        transaction.hide(myFragment);
                    }
                }
                break;
            case R.id.rb_cookbook:
                if (cookBookFragment==null){
                    cookBookFragment = CookBookFragment.newInstance();
                    transaction.add(R.id.ll_main,cookBookFragment,"cookBook");
                    if (interCookFragment!=null){
                        transaction.hide(interCookFragment);
                    }
                    if (addFragment!=null){
                        transaction.hide(addFragment);
                    }
                    if (cookSpeakFragment!=null){
                        transaction.hide(cookSpeakFragment);
                    }
                    if (myFragment!=null){
                        transaction.hide(myFragment);
                    }

                }else {
                    transaction.show(cookBookFragment);
                    if (interCookFragment!=null){
                        transaction.hide(interCookFragment);
                    }
                    if (addFragment!=null){
                        transaction.hide(addFragment);
                    }
                    if (cookSpeakFragment!=null){
                        transaction.hide(cookSpeakFragment);
                    }
                    if (myFragment!=null){
                        transaction.hide(myFragment);
                    }
                }
                break;
            case R.id.rb_add:
                if (addFragment==null){
                    addFragment = AddFragment.newInstance();
                    transaction.add(R.id.ll_main,addFragment,"add");
                    if (cookBookFragment!=null){
                        transaction.hide(cookBookFragment);
                    }
                    if (interCookFragment!=null){
                        transaction.hide(interCookFragment);
                    }
                    if (cookSpeakFragment!=null){
                        transaction.hide(cookSpeakFragment);
                    }
                    if (myFragment!=null){
                        transaction.hide(myFragment);
                    }

                }else {
                    transaction.show(addFragment);
                    if (cookBookFragment!=null){
                        transaction.hide(cookBookFragment);
                    }
                    if (interCookFragment!=null){
                        transaction.hide(interCookFragment);
                    }
                    if (cookSpeakFragment!=null){
                        transaction.hide(cookSpeakFragment);
                    }
                    if (myFragment!=null){
                        transaction.hide(myFragment);
                    }
                }
                break;
            case R.id.rb_cookspeak:
                if (cookSpeakFragment==null){
                    cookSpeakFragment = CookSpeakFragment.newInstance();
                    transaction.add(R.id.ll_main,cookSpeakFragment,"cookSpeak");
                    if (cookBookFragment!=null){
                        transaction.hide(cookBookFragment);
                    }
                    if (addFragment!=null){
                        transaction.hide(addFragment);
                    }
                    if (interCookFragment!=null){
                        transaction.hide(interCookFragment);
                    }
                    if (myFragment!=null){
                        transaction.hide(myFragment);
                    }

                }else {
                    transaction.show(cookSpeakFragment);
                    if (cookBookFragment!=null){
                        transaction.hide(cookBookFragment);
                    }
                    if (addFragment!=null){
                        transaction.hide(addFragment);
                    }
                    if (interCookFragment!=null){
                        transaction.hide(interCookFragment);
                    }
                    if (myFragment!=null){
                        transaction.hide(myFragment);
                    }
                }
                break;
            case R.id.rb_my:
                if (myFragment==null){
                    myFragment = MyFragment.newInstance();
                    transaction.add(R.id.ll_main,myFragment,"my");
                    if (cookBookFragment!=null){
                        transaction.hide(cookBookFragment);
                    }
                    if (addFragment!=null){
                        transaction.hide(addFragment);
                    }
                    if (cookSpeakFragment!=null){
                        transaction.hide(cookSpeakFragment);
                    }
                    if (interCookFragment!=null){
                        transaction.hide(interCookFragment);
                    }

                }else {
                    transaction.show(myFragment);
                    if (cookBookFragment!=null){
                        transaction.hide(cookBookFragment);
                    }
                    if (addFragment!=null){
                        transaction.hide(addFragment);
                    }
                    if (cookSpeakFragment!=null){
                        transaction.hide(cookSpeakFragment);
                    }
                    if (interCookFragment!=null){
                        transaction.hide(interCookFragment);
                    }
                }
                break;
        }
        transaction.commit();
    }


}
