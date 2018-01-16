package net.george.alltestdemo.binder;


import android.os.Binder;
import android.util.Log;

/**
 * @author George
 * @date 2018/1/16
 * @email georgejiapeidi@gmail.com
 * @describe 自定义MyBinder用于测试Service功能
 */
public class MyBinder extends Binder {
    private static final String TAG = "jpd-MyBinder";
    
    public void startDownload() {
        Log.d(TAG, "startDownload: thread:" + Thread.currentThread().getId());
    }
}
