package com.cool.ecook.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cool.ecook.R;
import com.cool.ecook.thirdlogin.androidclassdemo.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnClickListener{


    private AlphaAnimation anim;
    private Button myLoginBtn;

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        LinearLayout myLayoutWord = (LinearLayout) view.findViewById(R.id.ll_my_word);
        myLayoutWord.startAnimation(getAnimation());
        anim.startNow();

        myLoginBtn = (Button) view.findViewById(R.id.btn_my_login);
        myLoginBtn.setOnClickListener(this);

        return view;
    }

    private Animation getAnimation() {
        anim = new AlphaAnimation(0,1);
        anim.setDuration(5000);
        return anim;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
   }
}
