package com.feicui.getgithupinfo.githup;

import java.util.List;

/**
 * Created by yukai on 2016/8/25.
 */
public interface RepoPtrView {

    /*
    * 刷新数据
    * */
    void refreshData(List<String> list);

    /*
    * 显示刷新视图
    * */
    void showRefreshView();

    /*
    * 停止刷新
    * */
    void stopRefresh();

    /*
    * 空白视图
    * */
    void emptyView();

    /*
    * 错误的加载数据
    * */
    void errRefreshData(String err);
}
