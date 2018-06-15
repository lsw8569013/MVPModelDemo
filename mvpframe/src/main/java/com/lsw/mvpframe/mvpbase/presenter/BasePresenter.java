package com.lsw.mvpframe.mvpbase.presenter;

import com.lsw.mvpframe.mvpbase.model.IModule;
import com.lsw.mvpframe.mvpbase.view.BaseView;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;

/**
 *  泛型V:表示与之关联的Activity 或者 fragment
 * @author lsw
 *
 * 默认持有关联的 view module 不需要代码再进行创建
 *
 * @param <V> view 引用
 * @param <M>
 */
public abstract class BasePresenter<V extends BaseView,M> {

	//View 的引用
	protected SoftReference<V> mView;//使用弱引用,避免内存泄漏
	private V mProxyView;
	protected M model;

	/**
	 * 连接上View模型，类型于Activity与Fragment的连接onTachActivity()
	 * @param view
	 */
	public void attachView(V view){
		mView = new SoftReference<V>(view);
		// 动态代理
		mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
				view.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (mView != null && mView.get() != null) {
							return method.invoke(mView, args);
						}
						return null;
					}
				});

//		 注入 Model，怎么注入，获取泛型的类型，也就是 M 的 class，利用反射new 一个对象
		try {
			ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<M> modelClazz = (Class<M>) (parameterizedType.getActualTypeArguments()[1]);
			model = modelClazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 断开与View模型的连接，类型于Activity与Fragment的断开ondeTachActivity()
	 * 防止后面做一些无用的事情
	 * @param
	 */
	public void detachView(){
		if (mView !=null) {
			mView.clear();
		}
	}

	protected V getView(){
		return mProxyView;
	}

}