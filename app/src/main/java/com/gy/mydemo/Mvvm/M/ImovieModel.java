package com.gy.mydemo.Mvvm.M;


import com.gy.mydemo.Mvvm.base.OnHttpCallBack;
import com.gy.mydemo.Mvvm.bean.Movies;

/**
 * Created by 名 on 2017/7/7.
 */

public interface ImovieModel {
    void getMovie(int start, int count, OnHttpCallBack<Movies> callBack);//获取信息

}
