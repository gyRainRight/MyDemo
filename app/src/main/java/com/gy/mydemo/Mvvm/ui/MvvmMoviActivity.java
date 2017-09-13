package com.gy.mydemo.Mvvm.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gy.mydemo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gy.mydemo.Mvp.ui.MoviePresenter;
import com.gy.mydemo.Mvp.utils.DividerItemDecoration;
import com.gy.mydemo.Mvp.utils.ToastUtils;
import com.gy.mydemo.Mvvm.M.ImovieView;
import com.gy.mydemo.Mvvm.adapter.MovieAdapter;
import com.gy.mydemo.Mvvm.base.BaseMvVmActivity;
import com.gy.mydemo.Mvvm.bean.Movies;
import com.gy.mydemo.Mvvm.vm.MovieListVm;

import com.gy.mydemo.databinding.ActivityMovieBinding;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by 名 on 2017/7/7.
 */

public class MvvmMoviActivity extends BaseMvVmActivity<ActivityMovieBinding>{

    RecyclerView rvMovieList;
    PtrFrameLayout storeHousePtrFrame;
    private MovieListVm moviewListVm;
    @Override
    public int getLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
        initVm();
    }

    private void initVm() {
      moviewListVm= new MovieListVm(this, viewDataBinding);
    }

    private void initView() {
        rvMovieList=viewDataBinding.rvMovieList;
        storeHousePtrFrame=viewDataBinding.storeHousePtrFrame;
        initPtr();
        rvMovieList.setLayoutManager(new LinearLayoutManager(this));//设置为listview的布局
        rvMovieList.setItemAnimator(new DefaultItemAnimator());//设置动画
        rvMovieList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));//添加分割线
    }

    /**
     * 初始化(配置)下拉刷新组件
     */
    private void initPtr() {
        //下面是一些基础的配置,直接拿来用就可以 不用深究
        storeHousePtrFrame.setResistance(1.7f);
        storeHousePtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        storeHousePtrFrame.setDurationToClose(200);
        storeHousePtrFrame.setDurationToCloseHeader(1000);
        storeHousePtrFrame.setPullToRefresh(false);
        storeHousePtrFrame.setKeepHeaderWhenRefresh(true);
        StoreHouseHeader header = new StoreHouseHeader(this);
        float scale = getResources().getDisplayMetrics().density;
        header.setPadding(0, (int) (15 * scale + 0.5f), 0, (int) (15 * scale + 0.5f));
        header.initWithString("GY");//自定义头显示的字样,设置图片的话看另外的api
//      header.invalidateDrawable();
        header.setTextColor(Color.RED);
        header.setBackgroundColor(Color.parseColor("#11000000"));
        storeHousePtrFrame.setHeaderView(header);//添加头
        storeHousePtrFrame.addPtrUIHandler(header);//同时也要加上这一句
        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
               //下拉刷新的时候加载更多数据
                moviewListVm.firstLoadData();
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        storeHousePtrFrame.refreshComplete();
                    }
                }, 150);//为了增加用户体验 延迟0.15s再通知刷新结束
            }
        });

    }



}
