package com.flx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.flx.fts.R;

import butterknife.BindString;
import butterknife.BindView;

/**
 * 议题界面
 */
public class IssueActivity extends BaseActivity {

    @Nullable
    @BindView(R.id.issue_tv)
    TextView issue_tv;
    @BindString(R.string.issue)
    String str_issue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_issue,false);
    }

    @Override
    public void initView() {
        issue_tv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.right_in));
        btn_opt.setVisibility(View.VISIBLE);
        btn_opt.setText("开始表决");
    }

    @Override
    public void initData() {
        issue_tv.setText(str_issue);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_opt:
                if (btn_opt.getText().equals("开始表决")) {
                    startActivity(new Intent(this, VoteActivity.class));
                }
                break;
        }
    }

}
