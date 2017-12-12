package net.george.alltestdemo;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * 常用功能验证Activity
 * Created at 2017.12.12 By George
 */
public class CommonFunctionActivity extends AppCompatActivity {
    private static final String TAG = "jpd-CFAc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_function);

        test1();
    }

    /**
     * 获取屏幕的宽度、高度和密度
     */
    private void test1() {
        // 方法1
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        // 如下2个接口已经废弃
        int width1 = wm.getDefaultDisplay().getWidth();
        int height1 = wm.getDefaultDisplay().getHeight();

        // 方法2
        WindowManager wm2 = getWindowManager();
        int width2 = wm.getDefaultDisplay().getWidth();
        int height2 = wm.getDefaultDisplay().getHeight();

        // 方法3
        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width3 = outMetrics.widthPixels;
        int height3 = outMetrics.heightPixels;

        // 方法4
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width4 = dm.widthPixels;
        int height4 = dm.heightPixels;

        Log.d(TAG, "test1: w1:" + width1 + ",h1:" + height1);
        Log.d(TAG, "test1: w2:" + width2 + ",h2:" + height2);
        Log.d(TAG, "test1: w3:" + width3 + ",h3:" + height3);
        Log.d(TAG, "test1: w4:" + width4 + ",h4:" + height4);
        Log.d(TAG, "test1: density:" + density);
    }
}