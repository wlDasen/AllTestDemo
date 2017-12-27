package net.george.alltestdemo;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.george.alltestdemo.entity.MovieEntity;
import net.george.alltestdemo.http.HttpMethods;
import net.george.alltestdemo.http.MovieService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author George
 * @date 2017/12/27
 * @email georgejiapeidi@gmail.com
 * @describe Retrofit+Rxjava+Ohttp验证Activity
 */
public class RetrofitRxjavaOkhttpActivity extends AppCompatActivity {
    private static final String TAG = "jpd-RJAc";
    @Bind(R.id.textView1)
    TextView mTextView1;
    @Bind(R.id.btnClickMe)
    Button mBtnClickMe;
    private Subscriber<MovieEntity> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_rxjava_okhttp);

        ButterKnife.bind(this);
    }

    /**
     * 基本用法
     */
    private void test1() {
        // 定义观察者 下面2种形式等同
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: thread:" + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: thread:" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: item:" + s);
            }
        };
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: ");
            }
        };
        // 定义被观察者 如下3种形式等同
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "call: thread:" + Thread.currentThread().getName());
                subscriber.onNext("Hello");
                Log.d(TAG, "call: after Hello..");
                subscriber.onNext("Hi");
                Log.d(TAG, "call: after Hi..");
                subscriber.onNext("Aloha");
                Log.d(TAG, "call: after Aloha..");
                subscriber.onCompleted();
                Log.d(TAG, "call: after completed..");
            }
        });
        Observable observable1 = Observable.just("Hello", "Hi", "Aloha");
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable2 = Observable.from(words);
        observable.subscribe(observer);
        // 自定义Subscriber
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "onNext call: ...");
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG, "onError call: ...");
            }
        };
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                Log.d(TAG, "onCompleted call: ....");
            }
        };
        observable1.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    /**
     * 清晰表达原理的应用场景
     * 应用场景1-打印字符串数组
     */
    private void test2() {
        String[] names = {"George", "Norma", "James", "Guillaume"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "call: item:" + s);
                    }
                });
    }
    /**
     * 应用场景2-由id取得图片并显示
     */
    @TargetApi(21)
    private void test3() {
        final int drawableRes = R.mipmap.ic_launcher;
        Observable.create(new Observable.OnSubscribe<Drawable>() {

            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RetrofitRxjavaOkhttpActivity.this, "Error", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                ImageView imageView = (ImageView)findViewById(R.id.imageView);
                imageView.setImageDrawable(drawable);
            }
        });
    }
    /**
     * 异步实现方式
     */
    private void test4() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: currentThread:" + Thread.currentThread().getName());
                        Log.d(TAG, "call: number:" + integer);
                    }
                });
    }


    @OnClick(R.id.btnClickMe)
    public void onClick() {
//        getTopMovie();
//        getTopMoview2();
        getTopMovie3();
    }

    /**
     * 网络请求接口封装到一个类中实现的请求
     */
    private void getTopMovie3() {
        subscriber = new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(RetrofitRxjavaOkhttpActivity.this, "Get TopMoview completed.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                mTextView1.setText(e.getMessage());
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                mTextView1.setText(movieEntity.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber, 0, 10);
    }

    /**
     * Retrofit结合Rxjava封装网络请求
     */
    private void getTopMoview2() {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        movieService.getTopMovie2(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RetrofitRxjavaOkhttpActivity.this, "Get TopMoview completed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mTextView1.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        mTextView1.setText(movieEntity.toString());
                    }
                });
    }
    /**
     * 只使用Retrofit封装网络请求
     */
    private void getTopMovie() {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieEntity> call = movieService.getTopMovie(0, 10);
        Log.d(TAG, "getTopMovie: 1");
        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                Log.d(TAG, "onResponse: 2");
                Log.d(TAG, "onResponse: " + response.body().toString());
                mTextView1.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                Log.d(TAG, "onFailure: message:" + t.getMessage());
                mTextView1.setText(t.getMessage());
            }
        });
        Log.d(TAG, "getTopMovie: 3");
    }
}
