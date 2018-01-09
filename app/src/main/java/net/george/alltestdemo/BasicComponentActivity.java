package net.george.alltestdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.george.alltestdemo.adapter.PagerAdapter;

/**
 * @author George
 * @date 2018/1/9
 * @email georgejiapeidi@gmail.com
 * @describe Android各种基本控件的测试Activity
 */
public class BasicComponentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_component);

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        // 必须在ViewPager setAdapter之后调用
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
