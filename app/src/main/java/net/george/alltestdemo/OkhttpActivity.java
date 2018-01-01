package net.george.alltestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author George
 * @date 2017/12/29
 * @email georgejiapeidi@gmail.com
 * @describe Okhttp测试Activity
 */
public class OkhttpActivity extends AppCompatActivity {
    private static final String TAG = "jpd-OkhttpAc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        asynchronizeTest();
    }

    private void postTest() {
        new MultipartBody.Builder().setType(MultipartBody.FORM).build();
    }

    /**
     * 基本用法－Get同步请求
     */
    private void synchronizeTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 创建方式1－构造器直接创建
                OkHttpClient client = new OkHttpClient();
                // 创建方式2-Builder创建
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(3*1000, TimeUnit.MILLISECONDS);
                OkHttpClient client2 = builder.build();

                Request  request = new Request.Builder()
                        .url("https://www.baidu.com")
                        .build();

                Call call = client2.newCall(request);
                // 同步请求
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        Log.d(TAG, "test1: thread:" + Thread.currentThread().getName());
                        Log.d(TAG, "test1: response:" + response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 基本用法－Get异步请求
     */
    private void asynchronizeTest() {
        // 创建方式1－构造器直接创建
        OkHttpClient client = new OkHttpClient();
        // 创建方式2-Builder创建
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3*1000, TimeUnit.MILLISECONDS);
        OkHttpClient client2 = builder.build();

        Request  request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();

        Call call = client2.newCall(request);
        // 异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: thread:" + Thread.currentThread().getId());
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }
}