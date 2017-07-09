package com.gy.mydemo.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.gy.mydemo.Mvp.base.BaseFragment;
import com.gy.mydemo.Mvp.ui.MovieActivity;
import com.gy.mydemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 名 on 2017/5/9.
 */

public class TwoFragment extends BaseFragment implements View.OnClickListener{

    LinearLayout mvp,diappear;

    LinearLayout alpha;

    LinearLayout alpha_new;

    LinearLayout filp,filp_vertical;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_two, container, false);
        ButterKnife.inject(getActivity());
        mvp= (LinearLayout) view.findViewById(R.id.mvp);
        alpha= (LinearLayout) view.findViewById(R.id.alpha);
        alpha_new= (LinearLayout) view.findViewById(R.id.alpha_new);
        filp= (LinearLayout) view.findViewById(R.id.filp);
        filp_vertical= (LinearLayout) view.findViewById(R.id.filp_vertical);
        diappear= (LinearLayout) view.findViewById(R.id.diappear);

        mvp.setOnClickListener(this);
        alpha.setOnClickListener(this);
        alpha_new.setOnClickListener(this);
        filp.setOnClickListener(this);
        filp_vertical.setOnClickListener(this);
        diappear.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mvp:
                startActivity(new Intent(getActivity(), MovieActivity.class));
                break;
            case R.id.alpha:
                startActivity(new Intent(getActivity(), MovieActivity.class));
                getActivity().overridePendingTransition(R.anim.in_translate_top,
                        R.anim.out_translate_top);
                break;
            case R.id.alpha_new:
                startActivity(new Intent(getActivity(), MovieActivity.class));
                getActivity().overridePendingTransition(R.anim.unzoom_in,
                        R.anim.unzoom_out);
                break;
            case R.id.filp:
                startActivity(new Intent(getActivity(), MovieActivity.class));
                getActivity().overridePendingTransition(R.anim.flip_horizontal_in,
                        R.anim.flip_horizontal_out);
                break;
            case R.id.filp_vertical:
                startActivity(new Intent(getActivity(), MovieActivity.class));
                getActivity().overridePendingTransition(R.anim.flip_vertical_in,
                        R.anim.flip_vertical_out);
                break;
            case R.id.diappear:
                startActivity(new Intent(getActivity(), MovieActivity.class));
                getActivity().overridePendingTransition(R.anim.disappear_top_left_in,
                        R.anim.disappear_top_left_out);
//                Animation anim = AnimationUtils.loadAnimation(getActivity(),
//                        R.anim.in_scale_x);
//                diappear.startAnimation(anim);
                break;
        }

    }
}
