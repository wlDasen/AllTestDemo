package net.george.alltestdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * 此项目用来纪录所有的练习Demo，主要是为了加强和巩固Android各个领域的知识点
 * Created at 2017.12.10 By George
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "jpd-Mac";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(this);
        Button button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(this);
        Button button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(this);
        Button button9 = (Button)findViewById(R.id.button9);
        button9.setOnClickListener(this);
        Button button10 = (Button)findViewById(R.id.button10);
        button10.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1: // 启动小Demo验证的Activity
                Intent intent1 = new Intent(MainActivity.this, LittleDemoActivity.class);
                startActivity(intent1);
                break;
            case R.id.button2: // 启动ConstraintLayout验证的Activity
                Intent intent2 = new Intent(MainActivity.this, ConstraintLayoutActivity.class);
                startActivity(intent2);
                break;
            case R.id.button3: // 启动常用功能验证的Activity
                Intent intent3 = new Intent(MainActivity.this, CommonFunctionActivity.class);
                startActivity(intent3);
                break;
            case R.id.button4: // 启动Layout布局和内部属性验证的Activity
                Intent intent4 = new Intent(MainActivity.this, LayoutDemoActivity.class);
                startActivity(intent4);
                break;
            case R.id.button5: // 启动Retrofit+RxJava+Okhttp的验证Activity
                Intent intent5 = new Intent(MainActivity.this, RetrofitRxjavaOkhttpActivity.class);
                startActivity(intent5);
                break;
            case R.id.button6: // 启动各种自定义Widget的验证Activity
                Intent intent6 = new Intent(MainActivity.this, CustomWidgetActivity.class);
                startActivity(intent6);
                break;
            case R.id.button7: // 启动Gson的验证Activity
                Intent intent7 = new Intent(MainActivity.this, GsonActivity.class);
                startActivity(intent7);
                break;
            case R.id.button8: // 启动Okhttp的验证Activity
                Intent intent8 = new Intent(MainActivity.this, OkhttpActivity.class);
                startActivity(intent8);
                break;
            case R.id.button9: // 启动Java8新增特性的验证Activity
                Intent intent9 = new Intent(MainActivity.this, Java8Activity.class);
                startActivity(intent9);
                break;
            case R.id.button10: // 启动自定义View的验证Activity
                Intent intent10 = new Intent(MainActivity.this, Java8Activity.class);
                startActivity(intent10);
                break;
            default:
                break;
        }
    }
}