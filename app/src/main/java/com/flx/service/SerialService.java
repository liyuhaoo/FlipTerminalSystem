package com.flx.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.flx.fts.R;
import com.flx.util.AutoLayoutUtil;
import com.flx.view.MyAlertDialog;
import com.flx.view.RippleView;
import com.flx.view.RippleView.OnRippleCompleteListener;

/**
 *
 * @Description:串口后台服务
 * @date 2017-3-3 下午1:58:24
 * @author liyuhao
 */
public class SerialService extends Service implements OnRippleCompleteListener {

	private MyAlertDialog mAlertDialog;
	private TextView tv_address;
	private RippleView rv_set;
	private RippleView rv_yes;
	private static final String tag="SerialService";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.i(tag,"onCreate()");
		showConfirmAddressDlg();
	}

	@Override
	public int onStartCommand(Intent intent,  int flags, int startId) {
		Log.i(tag,"onStartCommand()");
		return START_NOT_STICKY;//避免通过任务管理器kill掉app后，该service重启
	}

	/**
	 *
	 * @Description:显示设置地址对话框：发两遍0x66指令，避免创建两个对话框
	 */
	private void showConfirmAddressDlg() {
		if (mAlertDialog != null)
			return;
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				mAlertDialog = new MyAlertDialog(SerialService.this);
				View dlg_view = LayoutInflater.from(SerialService.this).inflate(R.layout.dlg_view, null);
				tv_address = (TextView) dlg_view.findViewById(R.id.tv_address);
				rv_set = (RippleView) dlg_view.findViewById(R.id.rv_set);
				rv_yes = (RippleView) dlg_view.findViewById(R.id.rv_yes);
				rv_set.setOnRippleCompleteListener(SerialService.this);
				rv_yes.setOnRippleCompleteListener(SerialService.this);

				mAlertDialog.showDialog(dlg_view);
				AutoLayoutUtil.auto(dlg_view);
			}
		}, 5000);
	}

	@Override
	public void onComplete(RippleView rippleView) {
		switch (rippleView.getId()) {
			case R.id.rv_set:// 设置地址
				rv_set.setVisibility(View.GONE);
				tv_address.setVisibility(View.VISIBLE);
				rv_yes.setVisibility(View.VISIBLE);
				break;
			case R.id.rv_yes:// 确认地址
				mAlertDialog.cancel();
				break;
		}
	}
}
