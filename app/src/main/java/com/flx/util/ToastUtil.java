package com.flx.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.flx.activity.IApplication;
import com.flx.fts.R;

/**
 *
 * @Description: Toast提示工具类
 * @date 2016-7-1 下午5:56:42
 * @author liyuhao
 */
public class ToastUtil {
	private static Context mContext;
	static {
		mContext = IApplication.globalContext;
	}

	/**
	 * 短时间提示
	 *
	 */
	public static void show(String str) {
		showDuration(str, Toast.LENGTH_SHORT);
	}

	/**
	 * 长时间提示
	 *
	 */
	public static void showLong(String str) {
		showDuration(str, Toast.LENGTH_LONG);
	}

	/**
	 * @category 自定义时间提示
	 * @param duration
	 */
	public static void showDuration(String str, int duration) {
		Toast mToast = Toast.makeText(mContext, str, duration);
		// 屏幕居中
		mToast.setGravity(Gravity.CENTER, 0, 0);
		TextView mTextView = new TextView(mContext);
		mTextView.setPadding(30, 25, 30, 25);
		mTextView.setMinWidth(200);
		// 文字居中
		mTextView.setGravity(Gravity.CENTER);
		mTextView.setText(str);
		mTextView.setTextSize(20);
		mTextView.setTextColor(mContext.getResources().getColor(R.color.white));
		// 背景
		mTextView.setBackgroundResource(R.drawable.shape_rectangle_toast);
		mToast.setView(mTextView);
		mToast.show();
	}
}
