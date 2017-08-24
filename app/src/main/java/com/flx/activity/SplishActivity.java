package com.flx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.flx.fts.R;
import com.flx.service.SerialService;
import com.flx.util.AutoLayoutUtil;

public class SplishActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoLayoutUtil.setSize(this, false, 800, 480);
        setBaseContentView(R.layout.activity_splish,false);
    }

    @Override
    public void initView() {
        btn_opt.setVisibility(View.VISIBLE);
        btn_opt.setText("开始报到");
        title_tv.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        startService(new Intent(this, SerialService.class));
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_opt:
                startActivity(new Intent(this, CheckInActivity.class));
                break;
        }
    }

}
