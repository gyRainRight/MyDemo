package com.gy.mydemo.Activity;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gy.mydemo.R;
import com.gy.mydemo.base.BaseActivity;
import com.gy.mydemo.fragment.CenterFragment;
import com.gy.mydemo.fragment.FirstFragmen;
import com.gy.mydemo.fragment.TwoFragment;
import com.gy.mydemo.utils.MessageUtils;


public class LoginActivity extends BaseActivity {
    private long mExitTime = 0;
    private LinearLayout v_first,v_two,v_center;
    private ImageView img_first,img_two,img_center;
    private TextView t_first,t_two,t_center;
    private FrameLayout main_frame_layout;
    private FirstFragmen f1;
    private TwoFragment  f2;
    private CenterFragment f3;
    private LoginActivity mThis=this;
    private boolean isFirst=false,isTwo=false,isCenter=false;


    @Override
    protected void $setToolBar() {
        // 不用父类的样式
        super.$setToolBar();
        setImage(R.drawable.app_icon);
//        setBackGround(R.drawable.uikit_header_bg);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        $setToolBar();
        main_frame_layout = $findViewById(R.id.main_frame_layout);
        v_center=$findViewById(R.id.v_center);
        v_first=$findViewById(R.id.v_first);
        v_two=$findViewById(R.id.v_two);
        img_center=$findViewById(R.id.img_center);
        img_first=$findViewById(R.id.img_first);
        img_two=$findViewById(R.id.img_two);
        t_center=$findViewById(R.id.t_center);
        t_first=$findViewById(R.id.t_first);
        t_two=$findViewById(R.id.t_two);
        setAllImageView(v_first);
        initFragment1();
        isFirst=true;
        isTwo=false;
        isCenter=false;


    }

    @Override
    protected void setListener() {
        v_center.setOnClickListener(this);
        v_first.setOnClickListener(this);
        v_two.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void widgetClick(View v) {
        if(v == v_center){
            if (!isCenter) {
                isFirst=false;
                isTwo=false;
                isCenter=true;
                setTitle("个人中心");
                setAllImageView(v_center);
                initFragment3();
            }
        }else if(v == v_first){
            if (!isFirst) {
                isFirst=true;
                isTwo=false;
                isCenter=false;
                setImage(R.drawable.app_icon);
                setAllImageView(v_first);
                initFragment1();
            }
        }else if(v == v_two){
            if (!isTwo) {
                isFirst=false;
                isTwo=true;
                isCenter=false;
                setTitle("消息");
                setAllImageView(v_two);
                initFragment2();
            }
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                MessageUtils.showMessage(mThis, "再按一次返回桌面");
                mExitTime = System.currentTimeMillis();
            } else {
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
    private void initFragment(Fragment fragment){
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        if(fragment == null){
            if (fragment==f1) {
                fragment = new FirstFragmen();
            }else if (fragment==f2){
                fragment = new TwoFragment();
            }else if (fragment==f3){
                fragment = new CenterFragment();
            }
            transaction.add(R.id.main_frame_layout,fragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(fragment);
        transaction.commit();
    }
    private void initFragment1(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(f1 == null){
            f1 = new FirstFragmen();
           transaction.add(R.id.main_frame_layout,f1);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f1);

        //第二种方式(replace)，初始化fragment
//        if(f1 == null){
//            f1 = new MyFragment("消息");
//        }
//        transaction.replace(R.id.main_frame_layout, f1);

        //提交事务
        transaction.commit();
    }

    //显示第二个fragment
    private void initFragment2(){


        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        if(f2 == null){
            f2 = new TwoFragment();
            transaction.add(R.id.main_frame_layout,f2);
        }
        hideFragment(transaction);
        transaction.show(f2);

//        if(f2 == null) {
//            f2 = new MyFragment("联系人");
//        }
//        transaction.replace(R.id.main_frame_layout, f2);

        transaction.commit();
    }

    //显示第三个fragment
    private void initFragment3(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f3 == null){
            f3 = new CenterFragment();
            transaction.add(R.id.main_frame_layout,f3);
        }
        hideFragment(transaction);
        transaction.show(f3);

//        if(f3 == null) {
//            f3 = new MyFragment("动态");
//        }
//        transaction.replace(R.id.main_frame_layout, f3);

        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(f1 != null){
            transaction.hide(f1);
        }
        if(f2 != null){
            transaction.hide(f2);
        }
        if(f3 != null){
            transaction.hide(f3);
        }
    }
    private void setAllImageView(View view ){
        if (view==v_first){
            img_first.setImageResource(R.drawable.bottom_home_sel);

            img_two.setImageResource(R.drawable.bottom_message);
            img_center.setImageResource(R.drawable.bottom_my);
            t_first.setTextColor(getResources().getColor(R.color.Green));
            t_two.setTextColor(getResources().getColor(R.color.Gray));
            t_center.setTextColor(getResources().getColor(R.color.Gray));
        }else if(view==v_two){
            img_first.setImageResource(R.drawable.bottom_home);
            img_two.setImageResource(R.drawable.bottom_message_sel);
            img_center.setImageResource(R.drawable.bottom_my);
            t_first.setTextColor(getResources().getColor(R.color.Gray));
            t_two.setTextColor(getResources().getColor(R.color.Green));
            t_center.setTextColor(getResources().getColor(R.color.Gray));

        }else{
            img_first.setImageResource(R.drawable.bottom_home);
            img_two.setImageResource(R.drawable.bottom_message);
            img_center.setImageResource(R.drawable.bottom_my_sel);
            t_first.setTextColor(getResources().getColor(R.color.Gray));
            t_two.setTextColor(getResources().getColor(R.color.Gray));
            t_center.setTextColor(getResources().getColor(R.color.Green));
        }


    }
}
