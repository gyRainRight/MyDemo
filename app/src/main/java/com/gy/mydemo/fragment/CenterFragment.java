package com.gy.mydemo.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gy.mydemo.Mvp.base.BaseFragment;
import com.gy.mydemo.R;
import com.gy.mydemo.zxing.MApplication;
import com.gy.mydemo.zxing.MainActivity;
import com.gy.mydemo.zxing.SecondActivity;

import static com.gy.mydemo.zxing.MainActivity.REQUEST_CODE;


/**
 * Created by 名 on 2017/5/9.
 */

@SuppressLint("ValidFragment")
public class CenterFragment extends BaseFragment {


    public Button button1 = null;
    public Button button2 = null;
    public Button button3 = null;
    public Button button4 = null;
    private static final String THREE_FRAGMENT = "THREE_FRAGMENT";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(THREE_FRAGMENT);

            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putBoolean(THREE_FRAGMENT, isHidden());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_center,container,false);
        button2=(Button) view.findViewById(R.id.button2);
         final  LinearLayout login=(LinearLayout) view.findViewById(R.id.ll_login);
        button3=(Button) view.findViewById(R.id.button3);
     if ( MApplication.isLogion){
         button2.setVisibility(View.VISIBLE);
         login.setVisibility(View.GONE);

     }else {
         login.setVisibility(View.VISIBLE);
         button2.setVisibility(View.GONE);

     }
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MApplication.isLogion=false;
                login.setVisibility(View.VISIBLE);
                button2.setVisibility(View.GONE);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MApplication.isLogion=true;
                button2.setVisibility(View.VISIBLE);
                login.setVisibility(View.GONE);
            }
        });
        button1= (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_right);
            }
        });
        return view;
    }


}
