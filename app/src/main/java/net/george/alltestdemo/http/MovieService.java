package net.george.alltestdemo.http;


import net.george.alltestdemo.entity.MovieEntity;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author George
 * @date 2017/12/27
 * @email georgejiapeidi@gmail.com
 * @describe 基于BaseUrl封装的获取接口fro Retrofit+Rxjava+Okhttp
 */
public interface MovieService {
    /**
     * 只使用Retrofit封装网络请求
     */
    @GET("top250")
    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
    /**
     * Retrofit结合Rxjava封装网络请求
     */
    @GET("top250")
    rx.Observable<MovieEntity> getTopMovie2(@Query("start") int start, @Query("count") int count);
}
