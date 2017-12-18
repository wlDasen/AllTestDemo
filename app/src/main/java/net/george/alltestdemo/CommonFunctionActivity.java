package net.george.alltestdemo;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

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

//        test1();
        test2();
    }

    /**
     * 各个Android版本设置状态栏颜色的测试接口
     * API 19（Android 4.4 Kitkat）之前的版本无法设置，默认就是黑色的背景颜色（参考微信状态栏）
     * API 19开始可以设置不同颜色的半透明的背景色
     * API 21(Android5.0.1)
     */
    private void test2() {
        // res/mipmap/图片资源
        int resourceId = getResources().getIdentifier("ic_launcher", "mipmap", getPackageName());
        Log.d(TAG, "test2: id:" + resourceId);
        // res/drawable/图片资源
        int resourceId2 = getResources().getIdentifier("place_holder_demo", "drawable", getPackageName());
        // res/values/strings.xml/字符串资源
        int strId = getResources().getIdentifier("app_name", "string", getPackageName());
        Log.d(TAG, "test2: strId:" + strId);
        Log.d(TAG, "test2: package:" + getPackageName());

        Log.d(TAG, "test2: sdk version:" +Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // 增加Window的FLAG_TRANSLUCENT_STATUS才能生效
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            View statusBarView = new View(this);
            int statusBarHeight = getStatusBarHeight();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            params.gravity = Gravity.TOP;
            statusBarView.setLayoutParams(params);
            statusBarView.setBackgroundColor(Color.RED);
            decorView.addView(statusBarView);
        }
    }
    /**
     * 获取状态栏高度
     */
    private int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        Log.d(TAG, "getStatusBarHeight: statusBarHeight:" + statusBarHeight);
        return statusBarHeight;
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