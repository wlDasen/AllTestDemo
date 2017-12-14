package net.george.alltestdemo;

import android.annotation.TargetApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Group;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * ConstraintLayout验证Activity
 * Created at 2017.12.12 By George
 */
public class ConstraintLayoutActivity extends AppCompatActivity {
    private static final String TAG = "jpd-CLAc";
    /**
     * ConstraintLayout布局
     */
    private ConstraintLayout constraintLayout;
    /**
     * 动画Set
     */
    private ConstraintSet applyCS;
    /**
     * 复位Set
     */
    private ConstraintSet resetCS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);

//        test1();
        test2();
    }
    /**
     * APPLY按钮点击处理接口
     * @param view 被点击的控件
     */
    @TargetApi(19)
    public void onApplyClick(View view) {
        Log.d(TAG, "onApplyClick: ");
        //  设置动画特效
        TransitionManager.beginDelayedTransition(constraintLayout);
        // 按钮1与父控件左对齐
//        applyCS.setMargin(R.id.button1, ConstraintSet.START, 8);
        // 所有按钮水平居中对齐于父控件
        /*
        applyCS.setMargin(R.id.button1,ConstraintSet.START,0);
        applyCS.setMargin(R.id.button1,ConstraintSet.END,0);
        applyCS.setMargin(R.id.button2,ConstraintSet.START,0);
        applyCS.setMargin(R.id.button2,ConstraintSet.END,0);
        applyCS.setMargin(R.id.button3,ConstraintSet.START,0);
        applyCS.setMargin(R.id.button3,ConstraintSet.END,0);
        applyCS.centerHorizontally(R.id.button1, R.id.main_layout);
        applyCS.centerHorizontally(R.id.button2, R.id.main_layout);
        applyCS.centerHorizontally(R.id.button3, R.id.main_layout);
        */
        // 按钮3移动到屏幕正中心
        /*
        applyCS.setMargin(R.id.button3, ConstraintSet.START, 0);
        applyCS.setMargin(R.id.button3, ConstraintSet.END, 0);
        applyCS.setMargin(R.id.button3, ConstraintSet.TOP, 0);
        applyCS.setMargin(R.id.button3, ConstraintSet.BOTTOM, 0);
        applyCS.centerHorizontally(R.id.button3, R.id.main_layout);
        applyCS.centerVertically(R.id.button3, R.id.main_layout);
        */
        // 所有按钮的宽度尺寸变为600px
        /*
        applyCS.constrainWidth(R.id.button1, 600);
        applyCS.constrainWidth(R.id.button2, 600);
        applyCS.constrainWidth(R.id.button3, 600);
        */
        // 按钮1宽高充满屏幕，其余按钮隐藏
        /*
        applyCS.setVisibility(R.id.button2,ConstraintSet.GONE);
        applyCS.setVisibility(R.id.button3,ConstraintSet.GONE);
        applyCS.clear(R.id.button1);
        applyCS.connect(R.id.button1,ConstraintSet.LEFT,R.id.main_layout,ConstraintSet.LEFT,0);
        applyCS.connect(R.id.button1,ConstraintSet.RIGHT,R.id.main_layout,ConstraintSet.RIGHT,0);
        applyCS.connect(R.id.button1,ConstraintSet.TOP,R.id.main_layout,ConstraintSet.TOP,0);
        applyCS.connect(R.id.button1,ConstraintSet.BOTTOM,R.id.main_layout,ConstraintSet.BOTTOM,0);
        */
        // 所有按钮实现对齐顶部的Chain效果
        applyCS.clear(R.id.button1);
        applyCS.clear(R.id.button2);
        applyCS.clear(R.id.button3);
        applyCS.connect(R.id.button1, ConstraintSet.LEFT, R.id.main_layout, ConstraintSet.LEFT);
        applyCS.connect(R.id.button1, ConstraintSet.RIGHT, R.id.button2, ConstraintSet.LEFT);
        applyCS.connect(R.id.button2, ConstraintSet.LEFT, R.id.button1, ConstraintSet.RIGHT);
        applyCS.connect(R.id.button2, ConstraintSet.RIGHT, R.id.button3, ConstraintSet.LEFT);
        applyCS.connect(R.id.button3, ConstraintSet.LEFT, R.id.button2, ConstraintSet.RIGHT);
        applyCS.connect(R.id.button3, ConstraintSet.RIGHT, R.id.main_layout, ConstraintSet.RIGHT);
        applyCS.constrainWidth(R.id.button1, ConstraintSet.WRAP_CONTENT);
        applyCS.constrainHeight(R.id.button1, ConstraintSet.WRAP_CONTENT);
        applyCS.constrainWidth(R.id.button2, ConstraintSet.WRAP_CONTENT);
        applyCS.constrainHeight(R.id.button2, ConstraintSet.WRAP_CONTENT);
        applyCS.constrainWidth(R.id.button3, ConstraintSet.WRAP_CONTENT);
        applyCS.constrainHeight(R.id.button3, ConstraintSet.WRAP_CONTENT);
        applyCS.createHorizontalChain(R.id.main_layout, ConstraintSet.LEFT, R.id.main_layout, ConstraintSet.RIGHT, new int[]{R.id.button1, R.id.button2, R.id.button3},
            null, ConstraintWidget.CHAIN_PACKED);
        applyCS.setHorizontalBias(R.id.button1, 0.1f);

        // 开始动画
        applyCS.applyTo(constraintLayout);
    }
    /**
     * RESET按钮点击处理接口
     * @param view 被点击的控件
     */
    @TargetApi(19)
    public void onResetClick(View view) {
        Log.d(TAG, "onResetClick: ");
        TransitionManager.beginDelayedTransition(constraintLayout);
        resetCS.applyTo(constraintLayout);
    }

    /**
     * Group的测试接口
     */
    private void test1() {
//        Group group = (Group)findViewById(R.id.group);
//        group.setVisibility(View.GONE);
    }

    /**
     * ConstraintLayout动画测试接口
     */
    private void test2() {
        constraintLayout = (ConstraintLayout)findViewById(R.id.main_layout);
        applyCS = new ConstraintSet();
        applyCS.clone(constraintLayout);
        resetCS = new ConstraintSet();
        resetCS.clone(constraintLayout);
    }
}
