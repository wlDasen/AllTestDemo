package net.george.alltestdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1: // 启动小Demo验证的Acitivity
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
            default:
                break;
        }
    }
}