package com.gy.mydemo.zxing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gy.mydemo.R;
import com.gy.mydemo.base.BaseActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class ThreeActivity extends BaseActivity {

    public EditText editText = null;
    public Button button = null;
    public Button button1 = null;
    public ImageView imageView = null;

    public Bitmap mBitmap = null;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_three;
    }
    @Override
    protected void $setToolBar() {
        // 用父类的样式
        super.$setToolBar();
        setTitle("二维码");
        setLeftImage(R.drawable.back);

    }
    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        $setToolBar();
        editText = $findViewById(R.id.edit_content);
        button = $findViewById(R.id.button_content);
        button1 = $findViewById(R.id.button1_content);
        imageView = $findViewById(R.id.image_content);

//        /**
//         * 生成二维码图片
//         */
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String textContent = editText.getText().toString();
//                if (TextUtils.isEmpty(textContent)) {
//                    Toast.makeText(ThreeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                editText.setText("");
//                mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//                imageView.setImageBitmap(mBitmap);
//            }
//        });
//
//        /**
//         * 生成不带logo的二维码图片
//         */
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String textContent = editText.getText().toString();
//                if (TextUtils.isEmpty(textContent)) {
//                    Toast.makeText(ThreeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                editText.setText("");
//                mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
//                imageView.setImageBitmap(mBitmap);
//            }
//        });
    }

    @Override
    protected void setListener() {
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void widgetClick(View v) {
        if (v==button){
            String textContent = editText.getText().toString();
            if (TextUtils.isEmpty(textContent)) {
                Toast.makeText(ThreeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            editText.setText("");
            mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            imageView.setImageBitmap(mBitmap);
        }else if (v==button1){
            String textContent = editText.getText().toString();
            if (TextUtils.isEmpty(textContent)) {
                Toast.makeText(ThreeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            editText.setText("");
            mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
            imageView.setImageBitmap(mBitmap);
        }

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
}
