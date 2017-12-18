package net.george.alltestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * RxJava验证Activity
 * Created at 2017.12.18 By George
 */
public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = "jpd-RJAc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

//        test1();
        test2();
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
}
