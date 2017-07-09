package com.gy.mydemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.gy.mydemo.Mvp.base.BaseFragment;
import com.gy.mydemo.Mvp.ui.GaoYActivity;

import com.gy.mydemo.Mvp.ui.MovieActivity;
import com.gy.mydemo.Mvvm.ui.MvvmMoviActivity;
import com.gy.mydemo.R;
import com.gy.mydemo.bean.NewData;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 名 on 2017/5/9.
 */

public class FirstFragmen extends BaseFragment {
    private RecyclerView mRecyclerView;

//    private List<Integer> mDatas;
    private List<NewData> mDatas;
    @InjectView(R.id.mvp)
    LinearLayout mvp;
    @InjectView(R.id.alpha)
    LinearLayout alpha;
    @InjectView(R.id.alpha_new)
    LinearLayout alpha_new;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shouye,container,false);
//        initDatas(view);
        //得到控件
        ButterKnife.inject(getActivity());
        mvp= (LinearLayout) view.findViewById(R.id.mvp);
        alpha= (LinearLayout) view.findViewById(R.id.alpha);
        alpha_new= (LinearLayout) view.findViewById(R.id.alpha_new);
        mvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MovieActivity.class));
            }
        });
        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MvvmMoviActivity.class));
                getActivity().overridePendingTransition(R.anim.in_translate_top,
                        R.anim.out_translate_top);
            }
        });
        alpha_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GaoYActivity.class));
                getActivity().overridePendingTransition(R.anim.unzoom_in,
                        R.anim.unzoom_out);
            }
        });
        return view;
    }



















}
