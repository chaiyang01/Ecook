package com.cool.ecook.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cool.ecook.R;
import com.cool.ecook.activity.AttentionActivity;

/**
 * 厨说
 * A simple {@link Fragment} subclass.
 */
public class CookSpeakFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{


    public CookSpeakFragment() {
        // Required empty public constructor
    }

    public static CookSpeakFragment newInstance() {
        Bundle args = new Bundle();
        CookSpeakFragment fragment = new CookSpeakFragment();
        fragment.setArguments(args);
        return fragment;
    }
    //UI
    private RadioGroup mRadioGroup;
    //Fragment
    //Fragment
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private SquareFragment squareFrament;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cook_speak, container, false);
        //初始化布局
        initView(view);
        manager = getFragmentManager();
        mRadioGroup.setOnCheckedChangeListener(this);
        //选中广场
        RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(0);
        childAt.setChecked(true);
        return view;
    }

    private void initView(View view) {
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg_cookspeak);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        transaction = manager.beginTransaction();
        switch (i){
            case R.id.rb_square:
                if (squareFrament==null){
                    squareFrament = SquareFragment.newInstance();
                    transaction.add(R.id.ll_square,squareFrament,"interCook");
                }else {
                    transaction.show(squareFrament);
                }
                break;
            case R.id.rb_attention:
                Intent intent = new Intent(getActivity(), AttentionActivity.class);
                startActivity(intent);
                RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(1);
                childAt.setChecked(false);
                break;
        }
        transaction.commit();
    }
}
