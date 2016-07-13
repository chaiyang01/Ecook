package com.cool.ecook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cool.ecook.R;

/**
 * 厨说
 * A simple {@link Fragment} subclass.
 */
public class CookSpeakFragment extends Fragment {


    public CookSpeakFragment() {
        // Required empty public constructor
    }

    public static CookSpeakFragment newInstance() {
        Bundle args = new Bundle();
        CookSpeakFragment fragment = new CookSpeakFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cook_speak, container, false);
    }

}
