package com.gy.mydemo.Mvvm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.gy.mydemo.Mvvm.bean.Movies;
import com.gy.mydemo.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class MovieAdapter extends CommonAdapter<Movies.SubjectsBean> {
    private Context context;
    List<Movies.SubjectsBean> mMovies = new ArrayList<>();
    private int layoutId;
    public MovieAdapter(Context context, int layoutId, List<Movies.SubjectsBean> mMovies) {
        super(context, layoutId, mMovies);
        this.context=context;
        this.mMovies=mMovies;
        this.layoutId=layoutId;
    }

    @Override
    protected void convert(ViewHolder holder, Movies.SubjectsBean subjectsBean, int position) {
        String title = (position + 1) + "、" + subjectsBean.getTitle() + "/" + subjectsBean.getOriginal_title();
        holder.setText(R.id.tv_movie_title, title);//设置电影名
        String doc = "";
        for (Movies.SubjectsBean.DirectorsBean directorsBean : subjectsBean.getDirectors()) {
            doc += directorsBean.getName() + "  ";
        }
        holder.setText(R.id.tv_movie_doc, "导演:" + doc);
        String casts = "";
        for (Movies.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
            casts += castsBean.getName() + "  ";
        }

        holder.setText(R.id.tv_movie_art, "主演:" + casts);
        String genres = "";
        for (String genre : subjectsBean.getGenres()) {
            genres += genre + " ";
        }
        holder.setText(R.id.tv_movie_type, subjectsBean.getYear() + " / " + genres);//年份+分级
        holder.setText(R.id.tv_movie_grade, subjectsBean.getRating().getAverage() + "");//评分
        ImageView iv_pic = holder.getView(R.id.iv_movie_pic);
        Glide.with(context)
                .load(subjectsBean.getImages().getSmall())
                .diskCacheStrategy(DiskCacheStrategy.ALL)//加快显示速度---缓存在本地磁盘
                .into(iv_pic);//图片
    }


}
