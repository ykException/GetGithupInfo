package com.feicui.getgithupinfo.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.feicui.getgithupinfo.R;

/**
 * Created by yukai on 2016/8/23.
 */
public class OnePager extends FrameLayout{
    public OnePager(Context context) {
        this(context,null);
    }

    public OnePager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OnePager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0,this,true);
    }
}
