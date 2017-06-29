package com.gy.mydemo.Mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.gy.mydemo.Mvp.Base.utils.ZTLUtils;
import com.gy.mydemo.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.gy.mydemo.utils.TitleBar;

/**
 * Created by Administrator on 2016/7/22.
 */
public class BaseActivity extends Activity {
    public Activity mActivity;
//    @InjectView(R.id.title_bar)
    TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
//        new ZTLUtils(mActivity).setTranslucentStatus();
    }

    protected void $setToolBar() {
        titleBar= (TitleBar) findViewById(R.id.title_bar);
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRightButton(v);
//                overridePendingTransition(R.anim.slide_in_left,
//                        R.anim.slide_out_left);
            }
        });
        titleBar.setLeftlayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLeftButton(v);
            }
        });

    }
    /**
     * @title 左边按钮点击事件
     * @param
     */
    protected void clickLeftButton(View v) {
        setResult(RESULT_OK);
        finish();
    }

    /**
     * @title 右边按钮点击事件
     * @param
     */
    protected void clickRightButton(View v) {
    }
    protected void setTitle(String string) {
        titleBar.setTitle(string);

    }
    protected void setLeftTitle(String string) {
        titleBar.setLeftText(string);
    }
    protected void setRightTitle(String string) {
        titleBar.setRightText(string);
    }
    protected void setImage(int resId) {
        titleBar.setImageResource(resId);
    }
    protected void setLeftImage(int resId) {
        titleBar.setLeftImageResource(resId);
    }
    protected void setRightImage(int resId) {
        titleBar.setRightImageResource(resId);
    }
    protected void setBackGround(int resId) {
        titleBar.setBackgroundColor(resId);
    }

    private long lastClick = 0;

    private boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
