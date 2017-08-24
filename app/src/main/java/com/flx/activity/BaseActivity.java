package com.flx.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.flx.bo.IActivityInit;
import com.flx.fts.R;
import com.flx.util.AutoLayoutUtil;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author liyuhao
 * @Description: Activity基类
 * @date 2016-7-1 下午6:39:25
 */
public abstract  class BaseActivity extends FragmentActivity implements IActivityInit, OnClickListener {

    /**
     * 管理activity
     */
    public static ArrayList<Activity> activityList = new ArrayList<Activity>();
    /**
     * titleBar
     */
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.btn_opt)
    Button btn_opt;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置屏幕无标题，全屏，子类无需再设置
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 设置屏幕方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 页面切换动画
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
        activityList.add(this);
        //父布局
        initBaseView();
        initBaseListener();
    }
    /**
     * 初始化baseView,加final不希望子类继承重写
     */
    private final void initBaseView() {
        setContentView(R.layout.activity_base);
        // view注解
        ButterKnife.bind(this);
    }

    /**
     * 初始化baseListener
     */
    private final  void initBaseListener(){
        btn_opt.setOnClickListener(this);
    }
    /**
     * @param layoutResId xml布局文件
     * @descreption:子类需要在onCreate的时候调用，代替原来的setContentView
     */
    public void setBaseContentView(int layoutResId,boolean isInitDataBefore) {
        //子布局
        inflateChildView(layoutResId);
        initChildTemplet(isInitDataBefore);
    }

    /**
     * 加载子布局到父布局
     * @param layoutResId
     */
    private void  inflateChildView(int layoutResId)
    {
        ViewGroup baseRoot=(ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        baseRoot.setBackgroundColor(getResources().getColor(R.color.blue));
        getLayoutInflater().inflate(layoutResId,baseRoot);
        // view注解
        unbinder=ButterKnife.bind(this);
    }

    /**
     * @param isInitDataBefore 是否先初始化数据
     * @Description: 模板方法
     */
    protected void initChildTemplet(boolean isInitDataBefore) {
        // view适配
        AutoLayoutUtil.auto(this);
        // 初始化
        if (isInitDataBefore) {
            initData();
           initView();
        } else {
            initView();
            initData();
        }
       initListener();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityList.remove(this);
        unbinder.unbind();
    }

    /**
     * 程序退出
     */
    public static void exit() {
        if (activityList != null && activityList.size() > 0) {
            for (Activity activity : activityList) {
                activity.finish();
            }
            activityList.clear();
        }
        Process.killProcess(Process.myPid());
        System.gc();
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
