package com.feicui.getgithupinfo;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.feicui.getgithupinfo.githup.HotRepoFragment;
import com.feicui.getgithupinfo.githup.HotUserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.drawerLayout)DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)NavigationView navigationView;

    private HotRepoFragment hotRepoFragment;
    private HotUserFragment hotUserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        //系统actionbar的处理
        setSupportActionBar(toolbar);

        //navigation的菜单监听
        navigationView.setNavigationItemSelectedListener(this);
        //设置开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();//同步侧滑
        drawerLayout.addDrawerListener(toggle);

        //设置默认首页fragment 热门仓库
        hotRepoFragment = new HotRepoFragment();
        replaceFragment(hotRepoFragment);
    }

    //侧滑菜单监听
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //将默认选中项“手动”设置为false
        if (item.isChecked()){
            item.setChecked(false);
        }
        String choose = "";
        switch (item.getItemId()){
            case R.id.github_hot_repo://热门仓库
                if (hotRepoFragment.isAdded()){
                    replaceFragment(hotRepoFragment);
                }
                choose = "热门仓库";
                break;
            case R.id.github_hot_coder://热门开发者
                if (hotUserFragment == null){
                    hotUserFragment = new HotUserFragment();
                }
                if (hotUserFragment.isAdded()){
                    replaceFragment(hotUserFragment);
                }
                choose = "热门开发";
                break;
        }
        Snackbar.make(navigationView,choose,Snackbar.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //fragment显示设置
    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }

    /*
    * 返回键 侧滑关闭
    * */
    @Override
    public void onBackPressed() {
        if ( drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
