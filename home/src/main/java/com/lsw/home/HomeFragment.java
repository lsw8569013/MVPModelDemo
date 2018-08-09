package com.lsw.home;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lsw.mvpframe.mvpbase.base.BaseFragment;

/**
 * @author : lsw
 * @date 2018/8/8
 */
@Route(path = "/home/HomeFragment")
public class HomeFragment extends BaseFragment{


    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void initData() {

    }

    @Override
    public void createPresenter() {

    }

    @Override
    public void loading() {

    }

    @Override
    public void loadError() {

    }
}
