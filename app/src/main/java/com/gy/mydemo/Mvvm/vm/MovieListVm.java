package com.gy.mydemo.Mvvm.vm;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gy.mydemo.Mvp.utils.ToastUtils;
import com.gy.mydemo.Mvvm.M.MovieModleBiz;
import com.gy.mydemo.Mvvm.adapter.MovieAdapter;
import com.gy.mydemo.Mvvm.base.OnHttpCallBack;
import com.gy.mydemo.Mvvm.bean.Movies;
import com.gy.mydemo.R;
import com.gy.mydemo.databinding.ActivityMovieBinding;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 名 on 2017/7/7.
 */

public class MovieListVm implements OnHttpCallBack<Movies>{
    public  int start = 0;//从第几个开始
    public  int count = 4;//请求多少个
    MovieModleBiz movieModleBiz;
    private TextView load_more;//加载更多的按钮
    List<Movies.SubjectsBean> mMovies = new ArrayList<>();//请求到的电影信息对象集合
    private ActivityMovieBinding binding;
    private Context context;
    MovieAdapter movieAdapter;
    private MovieListVm movieListVm;
    private ProgressDialog mProgressDialog;
    public MovieListVm(Context context,ActivityMovieBinding binding){
        this.binding=binding;
        this.context=context;
        init();
    }
    /**
     * 初始化
     */
    private void init() {
        movieModleBiz = new MovieModleBiz();
        firstLoadData();
    }

    /**
     * 首次加载
     */
    public void firstLoadData() {
         showProgress();//通知V层显示对话框
          movieModleBiz.getMovie(start, count,this);
          start = start + 4;//改变请求的起点
    }



    @Override
    public void onSuccessful(Movies movies) {


        hideProgress();//通知V层隐藏对话框
        mMovies.addAll(movies.getSubjects());//追加数据
        movieAdapter=new MovieAdapter(context,R.layout.movie_item,mMovies);
       showData(mMovies);//将获取到的信息显示到界面之前
       showBottom(start - 5);//实现换页效果
    }

    @Override
    public void onFaild(String errorMsg) {
        hideProgress();//通知V层隐藏对话框
        showInfo(errorMsg);//通知V层显示错误信息
    }

   public void showBottom(int lastIndex){
       binding.rvMovieList.scrollToPosition(lastIndex);
   };

    public void showProgress(){
        mProgressDialog = ProgressDialog.show(context, "提示", "正在获取中,请稍后...");
    };//显示进度条

    public void hideProgress(){

            mProgressDialog.hide();
    };//隐藏进度条

    public  void showData(List<Movies.SubjectsBean> movies){
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(movieAdapter);//加载更多的包装器(传入通用适配器)
        View view = View.inflate(context, R.layout.load_more, null);
        //要设置一下的布局参数,因为布局填充到包装器的时候,自己的一些属性会无效
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(mLayoutParams);
        load_more = (TextView) view.findViewById(R.id.tv_load_more);

        //监听点击加载更多事件
        load_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_more.setText("加载中...");//点击加载更多-->加载中
                firstLoadData();
            }
        });
        mLoadMoreWrapper.setLoadMoreView(view);
       binding. rvMovieList.setAdapter(mLoadMoreWrapper);//注意  这里添加的是包装器 不是适配器哦
    };//显示数据到View上

    public void showInfo(String info){
        ToastUtils.showToast(context, info);
    };//提示用户,提升友好交互
}
