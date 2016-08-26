package com.feicui.getgithupinfo.splash;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.getgithupinfo.splash.pager.OnePager;
import com.feicui.getgithupinfo.splash.pager.ThreePager;
import com.feicui.getgithupinfo.splash.pager.TwoPager;

/**
 * Created by yukai on 2016/8/23.
 */
public class SplashPagerAdapter extends PagerAdapter {

    private View[] views;

    public SplashPagerAdapter(Context context) {
        views = new View[]{new OnePager(context),new TwoPager(context), new ThreePager(context)};
    }

    public View getView(int position){
        return views[position];
    }

    @Override
    public int getCount() {
        return views == null ? 0 : 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position]);
        return views[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views[position]);
    }
}
