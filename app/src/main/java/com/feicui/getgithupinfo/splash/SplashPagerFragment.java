package com.feicui.getgithupinfo.splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feicui.getgithupinfo.R;
import com.feicui.getgithupinfo.splash.pager.ThreePager;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by yukai on 2016/8/23.
 */
public class SplashPagerFragment extends Fragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.ivPhoneFont) ImageView ivPhoneFont;
    @BindView(R.id.layoutPhone) FrameLayout layoutPhone;
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.indicator) CircleIndicator indicator;
    @BindView(R.id.content) FrameLayout frameLayout;

    @BindColor(R.color.colorDim) int bim;
    @BindColor(R.color.colorGreen) int green;
    @BindColor(R.color.colorBlue) int blue;

    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private SplashPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);

        //viewpager处理
        viewPager.addOnPageChangeListener(this);
    }


    //背景颜色和动画处理
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position){
            case 0:
                designBackColor(positionOffset,bim,green);//颜色处理
                designScaleAnimation(positionOffset);//动画处理
                break;
            case 1:
                designBackColor(positionOffset,green,blue);
                layoutPhone.setTranslationX(-positionOffsetPixels);//结束平移 绑定在当前的pager上面
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (position==2){
            ThreePager threePager = (ThreePager) adapter.getView(position);
            threePager.setPagerAnimation();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /*
    * 背景颜色的改变处理  参数一 偏移量 参数二 改变前的颜色 参数三 目标颜色
    * */
    private void designBackColor(float positionOffset,int colorF,int colorB){
        int color = (int) argbEvaluator.evaluate(positionOffset,colorF,colorB);
        frameLayout.setBackgroundColor(color);
    }

    /*
    * 切换pager的动画处理 偏移量
    * */
    private void designScaleAnimation(float positionOffset){

        //缩放动画
        float scaleSize = 0.3f + positionOffset * 0.6f;
        layoutPhone.setScaleX(scaleSize);
        layoutPhone.setScaleY(scaleSize);

        //平移动画
        int moveSize = (int) ((positionOffset - 1)*360);
        layoutPhone.setTranslationX(moveSize);

        //字体透明度变化
        ivPhoneFont.setAlpha(positionOffset);
    }
}
