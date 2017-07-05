package com.gy.mydemo.Activity;

import android.view.KeyEvent;
import android.view.View;

import com.gy.mydemo.R;
import com.gy.mydemo.base.BaseActivity;

/**
 * Created by 名 on 2017/5/8.
 */

public class GaoYActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.content_main;
    }

    @Override
    protected void initView() {
        $setToolBar();
    }

    @Override
    protected void $setToolBar() {
        super.$setToolBar();
        setTitle("动画");
        setLeftImage(R.drawable.back);
//        setRightTitle("提交");
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void clickLeftButton(View v) {
        super.clickLeftButton(v);
        finish();
    }

    @Override
    protected void clickRightButton(View v) {
        super.clickRightButton(v);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
