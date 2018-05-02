package com.lsw.mvpframe.mvpbase.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lsw.mvpframe.MyApplication;
import com.lsw.mvpframe.mvpbase.presenter.BasePresenter;
import com.lsw.mvpframe.utils.DialogUtils;
import com.lsw.mvpframe.utils.NetUtils;
import com.lsw.mvpframe.utils.SharedPreferencesUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 *  泛型V ：表示Activity本身，泛型P：表示需要关联的Presenter
 * @author lsw8569013
 *
 * @param <V>
 * @param <P>
 */
public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {

	public  String TAG = this.getClass().getName();

	protected P presenter;
	protected SharedPreferencesUtils spUtils;
	private Unbinder unbinder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (setLayout() != 0) {
			setContentView(setLayout());
			unbinder = ButterKnife.bind(this);

			spUtils = SharedPreferencesUtils.getInstance(getApplicationContext());

        } else {
			throw new IllegalArgumentException("You must return a right contentView layout resource Id");
		}
		presenter = createPresenter();


		presenter.attachView((V)this);
		initData();
	}


	/**
	 * 设置xml布局
	 * @return
	 */
	protected abstract int setLayout();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();



	public boolean CheckNet(){
		if(!NetUtils.isNetworkAvailable(MyApplication.getInstance().getContext())){
			DialogUtils.makeText("您当前没有网络！");
			return  false;
		}
		return true;
	}

	/**
	 * 创建一个与之关联的Presenter
	 * @return
	 */
	protected abstract P  createPresenter();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
		presenter.detachView();
	}
}