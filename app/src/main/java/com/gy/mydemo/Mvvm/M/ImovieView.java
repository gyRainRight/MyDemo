package com.gy.mydemo.Mvvm.M;

import android.content.Context;


import com.gy.mydemo.Mvvm.bean.Movies;

import java.util.List;

/**
 * Created by 名 on 2017/7/7.
 */

public interface ImovieView {


        void showBottom(int lastIndex);

        Context getCurContext();//获取上下文对象

        void showProgress();//显示进度条

        void hideProgress();//隐藏进度条

        void showData(List<Movies.SubjectsBean> movies);//显示数据到View上

        void showInfo(String info);//提示用户,提升友好交互



}
