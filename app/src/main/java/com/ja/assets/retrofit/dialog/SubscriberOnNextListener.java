package com.ja.assets.retrofit.dialog;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/12  14:48
 */

public interface SubscriberOnNextListener<T> {
	/**
	 * 请求成功接口
	 *
	 * @param t
	 */
	void onSuccess(T t);
}
