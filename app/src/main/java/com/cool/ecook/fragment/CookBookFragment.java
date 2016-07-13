package com.cool.ecook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cool.ecook.R;

/**
 * 菜谱
 * A simple {@link Fragment} subclass.
 */
public class CookBookFragment extends Fragment {


    public CookBookFragment() {
        // Required empty public constructor
    }

    public static CookBookFragment newInstance() {
        Bundle args = new Bundle();
        CookBookFragment fragment = new CookBookFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cook_book, container, false);
    }

}
