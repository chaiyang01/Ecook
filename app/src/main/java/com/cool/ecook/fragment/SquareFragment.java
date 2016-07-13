package com.cool.ecook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cool.ecook.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 广场   YX
 * A simple {@link Fragment} subclass.
 */
public class SquareFragment extends Fragment {


    public SquareFragment() {
        // Required empty public constructor
    }

    public static SquareFragment newInstance() {
        Bundle args = new Bundle();
        SquareFragment fragment = new SquareFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private PullToRefreshListView mPtlf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);
        //初始化控件
        initView(view);
        return view;
    }

    private void initView(View view) {
        mPtlf = (PullToRefreshListView) view.findViewById(R.id.square_ptfl);
    }

}
