package com.flx.activity;

import android.app.Application;
import android.content.Context;

public class IApplication extends Application {
	public static Context globalContext;

	@Override
	public void onCreate() {
		super.onCreate();
		globalContext = getApplicationContext();
	}
}
