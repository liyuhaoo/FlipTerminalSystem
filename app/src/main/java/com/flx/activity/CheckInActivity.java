package com.flx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.flx.fts.R;
import com.flx.view.RadarLayout;
import com.flx.view.RevealLayout;
import com.flx.view.RippleView;
import com.flx.view.RippleView.OnRippleCompleteListener;
import android.support.annotation.Nullable;
import butterknife.BindView;

/**
 * @author liyuhao
 * @Description: 报到页面
 * @date 2017-2-22 上午11:11:57
 */
public class CheckInActivity extends BaseActivity implements OnRippleCompleteListener {
    @Nullable
    @BindView(R.id.name_tv)
    TextView name_tv;
    @Nullable
    @BindView(R.id.radarLayout)
    RadarLayout radarLayout;
    @Nullable
    @BindView(R.id.rippleView)
    RippleView rippleView;
    @Nullable
    @BindView(R.id.revealLayout)
    RevealLayout revealLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_check_in,false);
    }

    @Override
    public void initView() {
        btn_opt.setVisibility(View.VISIBLE);
        btn_opt.setText("报到结果");

        radarLayout.setUseRing(true);
        radarLayout.setColor(getResources().getColor(R.color.white));
        radarLayout.setCount(3);
        radarLayout.setDuration(2500);

        if (!radarLayout.isStarted()) {
            radarLayout.start();
        } else {
            radarLayout.stop();
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        rippleView.setOnRippleCompleteListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_opt:
                if (btn_opt.getText().equals("开始议题")) {
                    startActivity(new Intent(this, IssueActivity.class));
                } else {
                    titleBar.setBackgroundColor(getResources().getColor(R.color.light_green));
                    revealLayout.hide();
                    btn_opt.setText("开始议题");
                }
                break;
        }
    }

    @Override
    public void onComplete(final RippleView rippleView) {
        if (!radarLayout.isStarted()) {
            radarLayout.start();
        } else {
            radarLayout.stop();
            ((Button) rippleView.getChildAt(0)).setText("已签到");
        }
    }

}
