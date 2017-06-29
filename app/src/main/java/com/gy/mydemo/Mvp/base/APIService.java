package com.gy.mydemo.Mvp.base;





import com.gy.mydemo.Mvp.bean.Movies;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * API--接口  服务[这里处理的是同一的返回格式 resultCode  resultInfo Data<T> --->这里的data才是返回的结果,T就是泛型 具体返回的been对象或集合]
 * Created by HDL on 2016/8/3.
 */
public interface APIService {

    @GET("v2/movie/top250")
    Observable<Movies> getMovies(@Query("start") int start, @Query("count") int count);
}
