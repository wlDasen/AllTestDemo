package net.george.alltestdemo;

import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);

        test1();
    }

    /**
     * Group的测试Demo
     */
    private void test1() {
//        Group group = (Group)findViewById(R.id.group);
//        group.setVisibility(View.GONE);
    }
}
