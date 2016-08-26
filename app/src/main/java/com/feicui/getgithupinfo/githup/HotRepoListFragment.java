package com.feicui.getgithupinfo.githup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.feicui.getgithupinfo.R;
import com.feicui.getgithupinfo.compnents.FooterView;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by yukai on 2016/8/25.
 */
public class HotRepoListFragment extends Fragment implements RepoListView{
    @BindView(R.id.lvRepos) ListView lvRepos;
    @BindView(R.id.ptrClassicFrameLayout) PtrClassicFrameLayout ptrFrameLayout;
    @BindView(R.id.emptyView) TextView emptyView;
    @BindView(R.id.errorView) TextView errorView;

    private ArrayList<String> data;
    private ArrayAdapter<String> adapter;

    private RepoListPresenter presenter;

    private FooterView footerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repo_list,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new RepoListPresenter(this);

        //初始添加数据
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("测试数据"+i);
        }
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,data);
        lvRepos.setAdapter(adapter);

        if (adapter.getCount()==0){
            ptrFrameLayout.postDelayed(new Runnable() {
                @Override public void run() {
                    ptrFrameLayout.autoRefresh();
                }
            },200);
        }
        initPullToRefresh();
        initLoadData();
    }

    private void initLoadData() {
        footerView = new FooterView(getContext());

        //上拉加载
        Mugen.with(lvRepos, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                presenter.load();//加载数据
            }

            @Override
            public boolean isLoading() {
                //是否是正在加载
                return lvRepos.getFooterViewsCount() > 0 && footerView.isLoading();
            }

            @Override
            public boolean hasLoadedAllItems() {
                //是否加载完毕
                return lvRepos.getFooterViewsCount() > 0 && footerView.isComplete();
            }
        }).start();
    }

    private void initPullToRefresh() {
        // 如果两次下拉太近，将不会触发新刷新
        ptrFrameLayout.setLastUpdateTimeRelateObject(this);
        ptrFrameLayout.setDurationToCloseHeader(2000);//刷新时长

        //刷新header效果设置
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("I LIKE " + " Android");//设置显示的字体效果
        header.setPadding(0, 60, 0, 60);

        // 修改Ptr的HeaderView效果
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setBackgroundResource(R.color.colorDeepBlue);

        // 下拉刷新监听处理
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            // 当你"下拉时",将触发此方法
            @Override public void onRefreshBegin(PtrFrameLayout frame) {
                // 在业务P中执行异步刷新操作
                presenter.refresh();
            }
        });
    }

    //刷新数据操作
    @Override
    public void refreshData(List<String> list) {
        data.clear();
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showRefreshView() {
        ptrFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void stopRefresh() {
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void emptyView() {
        ptrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void errRefreshData(String err) {
        ptrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    //加载数据操作
    @Override
    public void loadData(List<String> list) {
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadView() {
        if (lvRepos.getFooterViewsCount()==0){
            lvRepos.addFooterView(footerView);
        }
        footerView.showLoading();
    }

    @Override
    public void hideLoadView() {
        lvRepos.removeFooterView(footerView);
    }

    @Override
    public void showLoadErr(String err) {
        if (lvRepos.getFooterViewsCount()==0){
            lvRepos.addFooterView(footerView);
        }
        footerView.showError();
    }
}
