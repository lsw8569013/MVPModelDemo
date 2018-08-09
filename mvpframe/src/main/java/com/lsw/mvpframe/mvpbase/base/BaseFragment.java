package com.lsw.mvpframe.mvpbase.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lsw.mvpframe.MyApplication;
import com.lsw.mvpframe.mvpbase.presenter.BasePresenter;
import com.lsw.mvpframe.mvpbase.view.BaseView;
import com.lsw.mvpframe.utils.DialogUtils;
import com.lsw.mvpframe.utils.LogUtil;
import com.lsw.mvpframe.utils.NetUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;



/**
 * Created by lsw on 2017/11/15.
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment implements BaseView{

    protected Activity mContext;
    protected P mPresenter;
    protected View mView;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mContext = (Activity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        mUnBinder = ButterKnife.bind(this, mView);
        createPresenter();
        if(mPresenter != null){
            mPresenter.attachView((V)this);
        }else{
            LogUtil.e(this.getClass().getName() + "没有创建 mPresenter");
        }


        initData();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mView != null) {
            mView = null;
        }
        if (mContext != null) {
            mContext = null;
        }
    }

    public abstract int getLayoutId();



    public boolean CheckNet(){
        if(!NetUtils.isNetworkAvailable(MyApplication.getInstance().getContext())){
            DialogUtils.makeText("您当前没有网络！");
            return false;
        }
        return true;
    }

    public abstract void initData();

    public abstract void createPresenter();

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public View findView(int id){
        return  mView.findViewById(id);
    }

}
