package com.flx.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.flx.fts.R;

public class MyAlertDialog extends AlertDialog {

	public MyAlertDialog(Context context) {
		super(context);
	}

	public void showDialog(View view) {
		Window window = getWindow();
		LayoutParams params = window.getAttributes();
		params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
		params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		params.gravity = Gravity.CENTER;
		window.setAttributes(params);
		window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);// 显示一个系统界别的dialog。这种dialog在任何界面下都可以弹出来。但是，这种dialog不相应home键和返回键，即强制用户必须对dialog作出操作后。
		// 使用方法是在dialog.show()语句之前设置dialog的window的type是system alert型
		window.setWindowAnimations(R.style.dlgWinAnim);
		// window.setBackgroundDrawableResource(R.color.dlg_bg_color);
		// window.setBackgroundDrawableResource(R.drawable.bg_dlg_head_view);
		// 默认是true
		setCanceledOnTouchOutside(false);
		show();
		setContentView(view);
	}

}
