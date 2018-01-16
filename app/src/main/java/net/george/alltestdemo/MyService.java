package net.george.alltestdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.annotations.Expose;

import net.george.alltestdemo.binder.MyBinder;

/**
 * @author George
 * @date 2018/1/16
 * @email georgejiapeidi@gmail.com
 * @describe 自定义MyService用于测试Service功能
 */
public class MyService extends Service {
    private static final String TAG = "jpd-MyService";
    private MyBinder mBinder = new MyBinder();
    
    public MyService() {
        Log.d(TAG, "MyService: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: thread:" + Thread.currentThread().getId());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.d(TAG, "run: .......");
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: thread:" + Thread.currentThread().getId());
        return mBinder;
    }
}
