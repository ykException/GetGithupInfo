package com.feicui.getgithupinfo.githup;

import java.util.List;

/**
 * 执行加载功能的接口
 * Created by yukai on 2016/8/25.
 */
public interface RepoLoadView {

    /*
    * 加载数据
    * */
    void loadData(List<String> list);

    /*
    * 显示加载视图
    * */
    void showLoadView();

    /*
    * 隐藏加载视图
    * */
    void hideLoadView();

    /*
    * 显示加载错误
    * */
    void showLoadErr(String err);

}
