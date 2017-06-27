package com.gy.mydemo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.gy.mydemo.R;
import com.gy.mydemo.utils.TitleBar;


/**
 * BaseActivity
 * Created by Bill on 2016/8/14.
 * <p/>
 * // 参考 http://www.zhihu.com/question/47045239?sort=created
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected boolean isDebug = true;
    private TitleBar titleBar;
    private ViewPager viewPager;
    protected String mTitle;
    protected int mModuleType;
    private BaseActivity mthis=this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();
        setListener();
        initData();

    }
//    /**
//     * @title 给导航设置样式
//     * @param json
//     */
//    public void setStyle(JSONObject json) {
//        try {
//            mModuleType = JsonUtils.getInt(json, "type");
//            JSONObject config = JsonUtils.getJson(json, "config");
//            if (null == config) {
//                titleBar.setVisibility(View.GONE);
//            } else {
//                JSONObject header = JsonUtils.getJson(config, "header");
//                if (null == header) {
//                    // 设置标题
//                    String title = JsonUtils.getStr(header, "title");
//                    if (null != title) {
//                        TextView headerTextView = (TextView)titleBar
//                                .findViewById(R.id.title);
//                        headerTextView.setText(Utils.subTextString(URLDecoder
//                                .decode(title, "UTF-8"), 24));
//
//                        mTitle = title;
//                        headerTextView.setText(title);
//                    }
//
//                    // 设置标题按钮
//                    JSONObject titleBtn = JsonUtils.getJson(header, "titleBtn");
//                    Button titleButton = (Button)titleBar
//                            .findViewById(R.id.image);
//                    if (null == titleBtn) {
//                        titleButton.setVisibility(View.GONE);
//                    } else {
//                        titleButton.setVisibility(View.VISIBLE);
//                        String strIcon = JsonUtils.getStr(titleBtn, "icon");
//                        if (!CommonUtils.isEmptyString(strIcon)) {
//                            titleButton.setBackgroundResource(Handler_Bitmap
//                                    .getDrawable(this, strIcon));
//                        }
//                    }
//
//                    // 设置左边按钮
//                    JSONObject leftBtn = JsonUtils.getJson(header, "leftBtn");
//                    Button leftButton = (Button) titleBar
//                            .findViewById(R.id.left_image);
//                    if (null == leftBtn) {
//                        leftButton.setVisibility(View.GONE);
//                    } else {
//                        leftButton.setVisibility(View.VISIBLE);
//                        String strIcon = JsonUtils.getStr(leftBtn, "icon");
//                        String strText = JsonUtils.getStr(leftBtn, "text");
//                        if (null != strIcon && strIcon.length() > 0) {
//                            leftButton.setBackgroundResource(Handler_Bitmap
//                                    .getDrawable(this, strIcon));
//                        } else {
//                            leftButton.setText(strText);
//                        }
//
//                        leftButton
//                                .setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        // if (ToolUtils.isFastDoubleClick()) {
//                                        // return;
//                                        // }
//                                        clickLeftButton(v);
//                                    }
//                                });
//                    }
//
//                    // 设置右边按钮
//                    JSONObject rightBtn = JsonUtils.getJson(header, "rightBtn");
//                    Button rightButton = (Button)titleBar
//                            .findViewById(R.id.right_image);
//                    if (null == rightBtn) {
//                        rightButton.setVisibility(View.GONE);
//                    } else {
//                        rightButton.setVisibility(View.VISIBLE);
//                        String strIcon = JsonUtils.getStr(rightBtn, "icon");
//                        String strText = JsonUtils.getStr(rightBtn, "text");
//                        if (null != strIcon && strIcon.length() > 0) {
//                            rightButton.setBackgroundResource(Handler_Bitmap
//                                    .getDrawable(this, strIcon));
//                        } else {
//                            rightButton.setText(strText);
////                            rightButton
////                                    .setBackgroundResource(R.drawable.title_right_button);
//                        }
//
//                        rightButton
//                                .setOnClickListener(new View.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(View v) {
//                                        // if (ToolUtils.isFastDoubleClick()) {
//                                        // return;
//                                        // }
//                                        clickRightButton(v);
//                                    }
//                                });
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    // 通用title
    protected void $setToolBar() {
//        Toolbar toolbar = $findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        titleBar=$findViewById(R.id.title_bar);
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

    // Log
    protected void $Log(String msg) {
        if (isDebug) {
            Log.d(this.getClass().getName(), msg);
        }
    }

    // Toast
    protected void $toast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    // Toast
    protected void $toast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    // startActivity
    protected void $startActivity(Class<?> cls) {
        $startActivity(cls, null);

    }

    // startActivity
    protected void $startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_right);
    }

    // startActivityForResult
    protected void $startActivityForResult(Class<?> cls, int requestCode) {
        $startActivityForResult(cls, null, requestCode);
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_right);
    }

    // startActivityForResult
    protected void $startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_right);
    }

    // getIntent
    protected Bundle $getIntentExtra() {
        Intent intent = getIntent();
        Bundle bundle = null;
        if (null != intent)
            bundle = intent.getExtras();
        return bundle;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
    public void finishWithoutAnimation() {
        super.finish();
    }


    // findViewById
    public <T extends View> T $findViewById(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * 设置ContentView
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * add Listener
     */
    protected abstract void setListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * view点击
     * @param v
     */
    public abstract void widgetClick(View v);
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

    @Override
    public void onClick(View v) {

            widgetClick(v);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
