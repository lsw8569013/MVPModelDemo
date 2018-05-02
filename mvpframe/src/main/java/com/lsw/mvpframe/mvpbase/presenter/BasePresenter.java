package com.lsw.mvpframe.mvpbase.presenter;

import java.lang.ref.SoftReference;

/**
 *  泛型V:表示与之关联的Activity fragment
 * @author lsw
 *
 * @param <V>
 */
public class BasePresenter<V> {

	//View 的引用
	protected SoftReference<V> mPresenterReference;//使用弱引用,避免内存泄漏

	/**
	 * 连接上View模型，类型于Activity与Fragment的连接onTachActivity()
	 * @param view
	 */
	public void attachView(V view){
		mPresenterReference = new SoftReference<V>(view);
	}
	/**
	 * 断开与View模型的连接，类型于Activity与Fragment的断开ondeTachActivity()
	 * 防止后面做一些无用的事情
	 * @param
	 */
	public void detachView(){
		if (mPresenterReference!=null) {
			mPresenterReference.clear();
		}
	}
	protected V getView(){
		return mPresenterReference.get();
	}



}