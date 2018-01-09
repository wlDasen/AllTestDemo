package net.george.alltestdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import net.george.alltestdemo.fragment.PageFragment;

/**
 * @author George
 * @date 2018/1/9
 * @email georgejiapeidi@gmail.com
 * @describe TabLayout适配器
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "jpd-PagerAdap";
    private String[] titles = {"Tab1", "Tab2", "Tab3"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        Log.d(TAG, "PagerAdapter: ");
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: ");
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: ");
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAG, "getPageTitle: ");
        return titles[position];
    }
}
