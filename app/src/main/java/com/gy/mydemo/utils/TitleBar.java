package com.gy.mydemo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gy.mydemo.R;

/**
 * Created by 名 on 2017/5/8.
 */

public class TitleBar extends RelativeLayout {
    //    标题栏左侧布局
    private RelativeLayout leftlayout;
    //    标题栏左侧图片
    private ImageView leftImage;
    //    标题栏右侧布局
    private RelativeLayout rightLayout;
    //    标题栏右侧图片
    private ImageView rightImage,Image;
    //    标题栏标题
    private TextView titleView,rightView,leftView;
    //    标题栏跟布局
    private RelativeLayout rootLayout;
//    private  Button rightImage,Image,leftImage;

    public TitleBar(Context context) {
        this(context,null);

    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.title_bar,this);
        leftlayout = (RelativeLayout) findViewById(R.id.left_layout);
        leftImage = (ImageView) findViewById(R.id.left_image);
        rightLayout = (RelativeLayout) findViewById(R.id.right_layout);
        rightImage = (ImageView) findViewById(R.id.right_image);
        Image=(ImageView) findViewById(R.id.image);
        titleView = (TextView) findViewById(R.id.title);
        rightView = (TextView) findViewById(R.id.right_text);
        leftView = (TextView) findViewById(R.id.left_text);
        rootLayout = (RelativeLayout) findViewById(R.id.root);
//        通过attrs设置相关属性
        setStyle(context,attrs);
    }

    private void setStyle(Context context, AttributeSet attrs) {
        if (attrs != null){
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
//            获取title
            String title = array.getString(R.styleable.TitleBar_titleBar_title);
            titleView.setText(title);
            rightView.setText(title);
            leftView.setText(title);
////            获取左侧图片
            Drawable lDrawable = array.getDrawable(R.styleable.TitleBar_titleBar_left_image);
            if (lDrawable != null){
                leftImage.setImageDrawable(lDrawable);
            }
//            获取右侧图片
            Drawable rDrawable = array.getDrawable(R.styleable.TitleBar_titleBar_right_image);
            if (rDrawable != null){
                rightImage.setImageDrawable(rDrawable);
            }
            //            获取中间图片
            Drawable cDrawable = array.getDrawable(R.styleable.TitleBar_titleBar_right_image);
            if (cDrawable != null){
                rightImage.setImageDrawable(rDrawable);
            }

//            获取背景图片
            Drawable bgDrawable = array.getDrawable(R.styleable.TitleBar_titleBar_background);
            if (bgDrawable != null){
                rootLayout.setBackgroundDrawable(bgDrawable);
            }

            array.recycle();
        }
    }

    //    左侧布局监听
    public void setLeftlayoutClickListener(OnClickListener listener) {
        if(leftlayout!=null){
            leftlayout.setOnClickListener(listener);
        }
//        leftlayout.setOnClickListener(listener);
    }


    //   右侧布局监听
    public void setRightLayoutClickListener(OnClickListener listener) {
        if(rightLayout!=null){
            rightLayout.setOnClickListener(listener);
        }

    }
//    //   通过资源id设置左侧图片样式
    public void setLeftImageResource(int resId) {
        leftlayout.setVisibility(View.VISIBLE);
        leftView.setVisibility(View.GONE);
        leftImage.setVisibility(View.VISIBLE);
        leftImage.setImageResource(resId);
    }
    //   通过资源id设置右侧图片样式
    public void setRightImageResource(int resId) {
        rightLayout.setVisibility(View.VISIBLE);
        rightView.setVisibility(View.GONE);
        rightImage.setVisibility(View.VISIBLE);
        rightImage.setImageResource(resId);
    }
    public void setImageResource(int resId) {
        titleView.setVisibility(View.GONE);
        Image.setVisibility(View.VISIBLE);
        Image.setImageResource(resId);
    }
    //    设置title
    public void setTitle(String title) {
        titleView.setVisibility(View.VISIBLE);
        Image.setVisibility(View.GONE);
        titleView.setText(title);
    }
    public void setRightText(String title) {
        rightLayout.setVisibility(View.VISIBLE);
        rightView.setVisibility(View.VISIBLE);
        rightImage.setVisibility(View.GONE);
        rightView.setText(title);
    }
    public void setLeftText(String title) {
        leftlayout.setVisibility(View.VISIBLE);
        leftView.setVisibility(View.VISIBLE);
        leftImage.setVisibility(View.GONE);
        leftView.setText(title);
    }

    //    设置跟布局背景颜色
    public void setBackgroundColor(int color){
        rootLayout.setBackgroundColor(color);
    }
}
