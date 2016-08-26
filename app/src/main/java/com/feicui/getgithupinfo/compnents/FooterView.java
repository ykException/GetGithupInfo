package com.feicui.getgithupinfo.compnents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicui.getgithupinfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yukai on 2016/8/25.
 */
public class FooterView extends FrameLayout{

    private static final int STATE_LOADING = 0;//加载中
    private static final int STATE_ERROR = 1;//加载出错
    private static final int STATE_COMPLETE = 2;//加载完成

    private int state = STATE_LOADING;

    @BindView(R.id.progressBar)ProgressBar progressbar;
    @BindView(R.id.tv_error)TextView tvError;

    public FooterView(Context context) {
        this(context,null);
    }

    public FooterView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_load_footer,this,true);
        ButterKnife.bind(this);
    }

    /*
    * 是否是加载中
    * */
    public boolean isLoading(){
        return state == STATE_LOADING;
    }

    /*
    * 是否加载完成
    * */
    public boolean isComplete(){
        return state == STATE_COMPLETE;
    }

    /*
    * 进行加载的时候，显示进度条
    * */
    public void showLoading() {
        state=STATE_LOADING;
        progressbar.setVisibility(VISIBLE);
        tvError.setVisibility(GONE);
    }

    /*
    *  出现错误的视图
    * */
    public void showError() {
        state=STATE_ERROR;
        progressbar.setVisibility(GONE);
        tvError.setVisibility(VISIBLE);
    }
}
