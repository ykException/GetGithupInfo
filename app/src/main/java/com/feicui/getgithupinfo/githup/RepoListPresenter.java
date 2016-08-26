package com.feicui.getgithupinfo.githup;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yukai on 2016/8/25.
 */
public class RepoListPresenter {
    private RepoListView repoListView;
    private List<String> list;

    private int num = 0;

    public RepoListPresenter(RepoListView repoListView) {

        this.repoListView = repoListView;
        list = new ArrayList<>();
    }

    public void refresh(){
        //显示刷新
        repoListView.showRefreshView();
        new Refresh().execute();
    }

    public void load(){
        repoListView.showLoadView();
        new LoadData().execute();
    }

    //异步刷新
    class Refresh extends AsyncTask<Void,Void,Void> {

        @Override protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i = 0; i < 20; i++) {
                list.add("刷新出来的数据"+(num++));
            }
            repoListView.refreshData(list);
            //停止刷新
            repoListView.stopRefresh();
        }
    }

    //异步加载
    class LoadData extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i = 0; i < 20; i++) {
                list.add("新加载的数据"+(num++));
            }
            repoListView.loadData(list);
            repoListView.hideLoadView();
        }
    }
}
