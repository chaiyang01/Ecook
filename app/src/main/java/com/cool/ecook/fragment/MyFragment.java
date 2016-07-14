package com.cool.ecook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.cool.ecook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {


    private AlphaAnimation anim;

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
        //jj
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        LinearLayout myLayoutWord = (LinearLayout) view.findViewById(R.id.ll_my_word);
        myLayoutWord.startAnimation(getAnimation());
        anim.startNow();
        return view;
    }

    private Animation getAnimation() {
        anim = new AlphaAnimation(0,1);
        anim.setDuration(10000);
        return anim;
    }

}
