package com.gy.mydemo.Mvp.ui;

import android.app.ProgressDialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gy.mydemo.Mvp.bean.Movies;
import com.gy.mydemo.Mvp.utils.DividerItemDecoration;
import com.gy.mydemo.Mvp.utils.ToastUtils;
import com.gy.mydemo.R;
import com.gy.mydemo.fragment.FirstFragmen;
import com.gy.mydemo.ui.PluginScrollView;
import com.gy.mydemo.ui.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import com.gy.mydemo.base.BaseActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;


import butterknife.ButterKnife;
import butterknife.InjectView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;


/**
 * Created by 名 on 2017/5/8.
 */

public class GaoYActivity extends BaseActivity  {
    PluginScrollView mPluginScrollView;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    List<View> testList;
    FrameLayout main_frame_layout;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private ArrayList<Fragment> fragmentArrayList;
    MoviePresenter mMoviePresenter;
    private ProgressDialog mProgressDialog;
    private TextView load_more;//加载更多的按钮
    View textView;
    private OneWeekFragment oneWeekFragment;
    private TwoWeekFragment twoWeekFragment;
    FrameLayout content_fragment;
    @Override
    protected void initView(Bundle savedInstanceState) {
        $setToolBar();
        preInit();
        viewPager = (ViewPager) findViewById(R.id.viewpagerLayout);
        viewPagerAdapter = new ViewPagerAdapter();
        viewPagerAdapter.setList(testList);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);

        // mPluginScrollView = new PluginScrollView(this, viewPager, testList);
        mPluginScrollView = (PluginScrollView) findViewById(R.id.horizontalScrollView);
        mPluginScrollView.setTestList(testList);
        mPluginScrollView.setViewPager(viewPager);
        postInit();

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
    protected void $setToolBar() {
        super.$setToolBar();
        setTitle("动画");
        setLeftImage(R.drawable.back);
        setRightTitle("哈");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_scrollview_main;
    }

    private void preInit() {

        TextView textView;
        testList = new ArrayList<View>();
        for (int i = 0; i < 10; i++) {
            textView= new TextView(GaoYActivity.this);
            textView.setText("view"+i);
            testList.add(textView);
        }

//        testList = new ArrayList<View>();
//        LayoutInflater inflater = getLayoutInflater();
//        textView= inflater.inflate(R.layout.content_fragment, null);
//        content_fragment= (FrameLayout) textView.findViewById(R.id.content_fragment);
//
//        for (int i = 0; i < 10; i++) {
//            testList.add(content_fragment);
//        }

    }

    private void postInit() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                Log.d("k", "onPageSelected - " + arg0);
                mPluginScrollView.buttonSelected(arg0);
                viewPager.setCurrentItem(arg0);
//                switch(arg0%3){
//                    case 0:
//                        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
//                        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
//                        if(oneWeekFragment == null){
//                            oneWeekFragment = new OneWeekFragment();
//                            transaction.add(R.id.content_fragment,oneWeekFragment);
//                        }
//                        //显示需要显示的fragment
//                        transaction.show(oneWeekFragment);
//                        transaction.commit();
//                        break;
//                    case 1:
//                        FragmentTransaction transaction2 =getSupportFragmentManager().beginTransaction();
//                        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
//                        if(twoWeekFragment == null){
//                            twoWeekFragment = new TwoWeekFragment();
//                            transaction2.add(R.id.content_fragment,twoWeekFragment);
//                        }
//                        //显示需要显示的fragment
//                        transaction2.show(twoWeekFragment);
//
//                        transaction2.commit();
//                        break;
//                    case 2:
//                        break;
//                }


            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
                Log.d("k", "onPageScrolled - " + arg0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
                Log.d("k", "onPageScrollStateChanged - " + arg0);
                // 状态有三个0空闲，1是增在滑行中，2目标加载完毕
            }
        });

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

//    private void initViews() {
//
//        ButterKnife.inject(this);
//        initPtr();
//        rvMovieList.setLayoutManager(new LinearLayoutManager(this));//设置为listview的布局
//        rvMovieList.setItemAnimator(new DefaultItemAnimator());//设置动画
//        rvMovieList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));//添加分割线
//    }
//    private void initPtr() {
//        //下面是一些基础的配置,直接拿来用就可以 不用深究
//        storeHousePtrFrame.setResistance(1.7f);
//        storeHousePtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
//        storeHousePtrFrame.setDurationToClose(200);
//        storeHousePtrFrame.setDurationToCloseHeader(1000);
//        storeHousePtrFrame.setPullToRefresh(false);
//        storeHousePtrFrame.setKeepHeaderWhenRefresh(true);
//        StoreHouseHeader header = new StoreHouseHeader(this);
//        float scale = getResources().getDisplayMetrics().density;
//        header.setPadding(0, (int) (15 * scale + 0.5f), 0, (int) (15 * scale + 0.5f));
//        header.initWithString("GY");//自定义头显示的字样,设置图片的话看另外的api
////      header.invalidateDrawable();
//        header.setTextColor(Color.RED);
//        header.setBackgroundColor(Color.parseColor("#11000000"));
//        storeHousePtrFrame.setHeaderView(header);//添加头
//        storeHousePtrFrame.addPtrUIHandler(header);//同时也要加上这一句
//        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                mMoviePresenter.loadMoreMovie();//下拉刷新的时候加载更多数据
//                frame.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        storeHousePtrFrame.refreshComplete();
//                    }
//                }, 150);//为了增加用户体验 延迟0.15s再通知刷新结束
//            }
//        });
//
//    }
//
//    @Override
//    public void showBottom(int lastIndex) {
//        load_more.setText("点击加载更多");//设置最底下的加载更多显示的内容    加载中-->点击加载更多
//        rvMovieList.scrollToPosition(lastIndex);
//    }
//
//    @Override
//    public Context getCurContext() {
//        return this;
//    }
//
//    @Override
//    public void showProgress() {
//        mProgressDialog = ProgressDialog.show(this, "提示", "正在获取中,请稍后...");
//    }
//
//    @Override
//    public void hideProgress() {
//        mProgressDialog.hide();
//    }
//
//
//    /**
//     * 显示数据
//     *
//     * @param movies
//     */
//    @Override
//    public void showData(List<Movies.SubjectsBean> movies) {
//        //鸿洋大神的通用适配器(真的很好用哦)
//        CommonAdapter<Movies.SubjectsBean> commonAdapter = new CommonAdapter<Movies.SubjectsBean>(this, R.layout.movie_item, movies) {
//
//            @Override
//            protected void convert(ViewHolder holder, Movies.SubjectsBean subjectsBean, int position) {
//                String title = (position + 1) + "、" + subjectsBean.getTitle() + "/" + subjectsBean.getOriginal_title();
//                holder.setText(R.id.tv_movie_title, title);//设置电影名
//                String doc = "";
//                for (Movies.SubjectsBean.DirectorsBean directorsBean : subjectsBean.getDirectors()) {
//                    doc += directorsBean.getName() + "  ";
//                }
//                holder.setText(R.id.tv_movie_doc, "导演:" + doc);
//                String casts = "";
//                for (Movies.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
//                    casts += castsBean.getName() + "  ";
//                }
//
//                holder.setText(R.id.tv_movie_art, "主演:" + casts);
//                String genres = "";
//                for (String genre : subjectsBean.getGenres()) {
//                    genres += genre + " ";
//                }
//                holder.setText(R.id.tv_movie_type, subjectsBean.getYear() + " / " + genres);//年份+分级
//                holder.setText(R.id.tv_movie_grade, subjectsBean.getRating().getAverage() + "");//评分
//                ImageView iv_pic = holder.getView(R.id.iv_movie_pic);
//                Glide.with(GaoYActivity.this)
//                        .load(subjectsBean.getImages().getSmall())
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)//加快显示速度---缓存在本地磁盘
//                        .into(iv_pic);//图片
//            }
//        };
//        /**
//         * 配置加载更多(通用适配器里面的类哦)
//         */
//        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);//加载更多的包装器(传入通用适配器)
//        View view = View.inflate(this, R.layout.load_more, null);
//        //要设置一下的布局参数,因为布局填充到包装器的时候,自己的一些属性会无效
//        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(mLayoutParams);
//        load_more = (TextView) view.findViewById(R.id.tv_load_more);
//
//        //监听点击加载更多事件
//        load_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                load_more.setText("加载中...");//点击加载更多-->加载中
//                mMoviePresenter.loadMoreMovie();
//            }
//        });
//        mLoadMoreWrapper.setLoadMoreView(view);
//        rvMovieList.setAdapter(mLoadMoreWrapper);//注意  这里添加的是包装器 不是适配器哦
//    }
//
//    @Override
//    public void showInfo(String info) {
//        ToastUtils.showToast(this, info);
//    }

}
