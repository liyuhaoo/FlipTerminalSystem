package com.flx.bo;

/**
 *
 * @Description: activity/fragment页面初始化接口
 * @date 2017-2-22 上午10:44:54
 * @author liyuhao
 */
public interface IActivityInit {
	/**
	 *
	 * @Description:初始化视图
	 */
	void initView();

	/**
	 *
	 * @Description:初始化数据
	 */
	void initData();

	/**
	 *
	 * @Description:初始化监听
	 */
	void initListener();

}
