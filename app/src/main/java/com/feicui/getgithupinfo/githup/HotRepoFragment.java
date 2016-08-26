package com.feicui.getgithupinfo.githup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.getgithupinfo.R;

/**
 * Created by yukai on 2016/8/25.
 */
public class HotRepoFragment extends Fragment{


    private ViewPager viewPager;
    private TabLayout tabLayout;

    private HotRepoAdapter adpter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_repo,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View v) {
        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);

        adpter = new HotRepoAdapter(getChildFragmentManager());
        viewPager.setAdapter(adpter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
