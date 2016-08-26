package com.feicui.getgithupinfo.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.feicui.getgithupinfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yukai on 2016/8/23.
 */
public class ThreePager extends FrameLayout{

    @BindView(R.id.ivBubble1)ImageView ivBubble1;
    @BindView(R.id.ivBubble2)ImageView ivBubble2;
    @BindView(R.id.ivBubble3)ImageView ivBubble3;

    public ThreePager(Context context) {
        this(context,null);
    }

    public ThreePager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThreePager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2,this,true);
        ButterKnife.bind(this);
        ivBubble1.setVisibility(GONE);
        ivBubble2.setVisibility(GONE);
        ivBubble3.setVisibility(GONE);
    }


    public void setPagerAnimation(){
        if (ivBubble1.getVisibility() != VISIBLE){
//            setAnimationInfo(ivBubble1,300,100);
//            setAnimationInfo(ivBubble2,300,600);
//            setAnimationInfo(ivBubble3,300,1100);
            installAnimation(ivBubble1,1000,0);
            installAnimation(ivBubble2,1000,600);
            installAnimation(ivBubble3,1000,1200);
        }
    }

    /*
   * 利用第三方框架 YoYo来控制渐变动画效果
   * */
    private void setAnimationInfo(final ImageView img, final int time, int postion){
        postDelayed(new Runnable() {
            @Override
            public void run() {
                img.setVisibility(VISIBLE);
                YoYo.with(Techniques.FadeInLeft).duration(time).playOn(img);//？？
            }
        },postion);
    }

    //当前页面的动画处理 参数一 动画视图 参数二 动画时长 参数三 启动动画前的延时
    private void installAnimation(ImageView inPagerImage, int durationTime, int startTime){
        inPagerImage.setVisibility(VISIBLE);
        //水平移动
        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,-1,
                TranslateAnimation.RELATIVE_TO_SELF,0,
                TranslateAnimation.RELATIVE_TO_SELF,0,
                TranslateAnimation.RELATIVE_TO_SELF,0);
        //透明度变化
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);

        //动画组合
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);//添加动画
        set.addAnimation(alphaAnimation);

        set.setFillAfter(true);//保持当前状态
        set.setInterpolator(new LinearInterpolator());//设置匀速
        set.setDuration(durationTime);//设置延时
        set.setStartOffset(startTime);//设置开始时间

        inPagerImage.startAnimation(set);//开始动画

    }
}
