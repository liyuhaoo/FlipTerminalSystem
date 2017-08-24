package com.flx.activity;

import java.util.ArrayList;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flx.fts.R;
import com.flx.util.AutoLayoutUtil;
import com.flx.util.DisplayUtil;
import com.flx.view.RevealLayout;
import com.flx.view.RippleView;
import com.flx.view.RippleView.OnRippleCompleteListener;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import butterknife.BindString;
import butterknife.BindView;

public class VoteActivity extends BaseActivity implements OnClickListener, OnRippleCompleteListener {

	/**
	 * 表决灯图标
	 */
	@Nullable
	@BindView(R.id.lamp_agree)
	ImageView lamp_agree;
	@Nullable
	@BindView(R.id.lamp_against)
	ImageView lamp_against;
	@Nullable
	@BindView(R.id.lamp_abstain)
	ImageView lamp_abstain;
	/**
	 * 表决灯按钮
	 */
	@Nullable
	@BindView(R.id.btn_agree)
	Button btn_agree;
	@Nullable
	@BindView(R.id.btn_against)
	Button btn_against;
	@Nullable
	@BindView(R.id.btn_abstain)
	Button btn_abstain;

	/**
	 * 表决灯按钮上的波纹
	 */
	@Nullable
	@BindView(R.id.ripple_agree)
	RippleView ripple_agree;
	@Nullable
	@BindView(R.id.ripple_against)
	RippleView ripple_against;
	@Nullable
	@BindView(R.id.ripple_abstain)
	RippleView ripple_abstain;

	@Nullable
	@BindView(R.id.issue_tv)
	TextView issue_tv;
	@Nullable
	@BindView(R.id.revealLayout)
	RevealLayout revealLayout;
	@Nullable
	@BindView(R.id.chart1)
	BarChart mChart;

	@BindString(R.string.issue)
	String str_issue;
	
	private AnimationDrawable lampAgreeDrawable, lampAgainstDrawable, lampAbstainDrawable;
	private boolean isLampOn = true;
	private final String[] xVals = new String[] { "赞成", "反对", "弃权", "未按" };

	private float defaltBtnTextSize;
	private float chartTextSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_vote,false);
	}

	@Override
	public void initView() {
		lampAgreeDrawable = (AnimationDrawable) lamp_agree.getDrawable();
		lampAgainstDrawable = (AnimationDrawable) lamp_against.getDrawable();
		lampAbstainDrawable = (AnimationDrawable) lamp_abstain.getDrawable();

		issue_tv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.right_in));

		btn_opt.setVisibility(View.VISIBLE);
		btn_opt.setText("表决结果");
	}

	@Override
	public void initData() {
		defaltBtnTextSize = btn_agree.getTextSize();
		issue_tv.setText(str_issue);
		int pxValue = (int) getResources().getDimension(R.dimen.defalt_text_size) * 2;
		chartTextSize = DisplayUtil.px2dip(this, pxValue);
		Log.i("hao", "pxValue:" + pxValue + "   chartTextSize:" + chartTextSize);
	}

	private void initBarChart() {
		// step1:基本设置
		mChart.animateY(2000);// 设置Y轴动画
		mChart.getDescription().setEnabled(false);// 隐藏右下角的文字描述
		mChart.setDragEnabled(false);// 不可拖拽
		mChart.setScaleEnabled(false);// 不可缩放
		mChart.setAutoScaleMinMaxEnabled(true);
		Legend leged = mChart.getLegend();// 图例
		// leged.setEnabled(false);
		leged.setPosition(LegendPosition.ABOVE_CHART_LEFT);
		leged.setFormSize(16);
		leged.setTextSize(chartTextSize);
		leged.setTextColor(getResources().getColor(R.color.white));

		// step2:x轴设置
		XAxis xAxis = mChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);// x轴位置
		xAxis.setLabelCount(xVals.length, false);// n个标签
		xAxis.setTextSize(chartTextSize);
		xAxis.setLabelRotationAngle(-40);
		// xAxis.setDrawGridLines(false);
		xAxis.setTextColor(getResources().getColor(R.color.white));
		xAxis.setGridColor(getResources().getColor(R.color.white));
		// xAxis.setGridLineWidth(20);
		xAxis.enableGridDashedLine(10, 10, 0);// 虚线段的长度,线之间的空间
		xAxis.setAxisLineColor(getResources().getColor(R.color.white));
		xAxis.setAxisLineWidth(2);
		// xAxis.setCenterAxisLabels(true);
		xAxis.setValueFormatter(new IAxisValueFormatter() {// x轴标签

			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				return xVals[(int) value];
			}
		});

		// step3:y轴设置
		YAxis leftYAxis = mChart.getAxisLeft();
		leftYAxis.setLabelCount(xVals.length * 2 - 1, false);
		leftYAxis.setTextSize(chartTextSize);
		leftYAxis.setTextColor(getResources().getColor(R.color.white));
		leftYAxis.setGridColor(getResources().getColor(R.color.white));
		leftYAxis.setSpaceTop(15f);
		leftYAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
		leftYAxis.enableGridDashedLine(10, 10, 0);
		leftYAxis.setAxisLineColor(getResources().getColor(R.color.white));
		leftYAxis.setAxisLineWidth(2);
		leftYAxis.addLimitLine(createLimitLine(260f, "Upper Limit：通过", LimitLabelPosition.RIGHT_TOP));
		leftYAxis.addLimitLine(createLimitLine(20f, "Lower Limit：未通过", LimitLabelPosition.RIGHT_TOP));
		mChart.getAxisRight().setEnabled(false);// 隐藏右边的y轴

		// step4:数据
		ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();// Y轴数据
		yVals.add(new BarEntry(0, 300));
		yVals.add(new BarEntry(1, 200));
		yVals.add(new BarEntry(2, 150));
		yVals.add(new BarEntry(3, 120));
		BarDataSet set = new BarDataSet(yVals, "表决结果");
		set.setValueTextSize(chartTextSize);
		set.setValueTextColor(getResources().getColor(R.color.white));
		set.setColors(getResources().getColor(R.color.dark_green), getResources().getColor(R.color.dark_red), getResources().getColor(R.color.dark_yellow), getResources().getColor(R.color.dark_blue));
		set.setValueFormatter(new IValueFormatter() {

			@Override
			public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
				return (int) value + "票";
			}
		});

		ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
		dataSets.add(set);
		BarData data = new BarData(dataSets);
		data.setBarWidth(0.3f);// 柱体的宽度
		mChart.setData(data);

		// 重新适配一下自定义view
		AutoLayoutUtil.auto(mChart);
	}

	private LimitLine createLimitLine(float limit, String label, LimitLabelPosition pos) {
		LimitLine limitLine = new LimitLine(limit, label);
		limitLine.setLineWidth(4f);
		limitLine.enableDashedLine(14f, 10f, 0f);
		limitLine.setLabelPosition(pos);
		limitLine.setTextSize(chartTextSize);
		limitLine.setTextColor(getResources().getColor(R.color.dark_red));
		limitLine.setLineColor(getResources().getColor(R.color.dark_red));
		return limitLine;
	}

	@Override
	public void initListener() {
		btn_agree.setOnClickListener(this);
		btn_against.setOnClickListener(this);
		btn_abstain.setOnClickListener(this);
		ripple_agree.setOnRippleCompleteListener(this);
		ripple_against.setOnRippleCompleteListener(this);
		ripple_abstain.setOnRippleCompleteListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_opt) {
			initBarChart();
			revealLayout.hide();
			titleBar.setBackgroundColor(getResources().getColor(R.color.light_green));
			btn_opt.setVisibility(View.GONE);
		} else {
			if (isLampOn) {
				Log.i("click", "关");
				turnLampOff();
				isLampOn = false;
			} else {
				Log.i("click", "开灯");
				turnLampOn();
				isLampOn = true;
			}
		}
		/*switch (v.getId()) {
		case R.id.btn_agree:
			if (isPause == false) {
				turnLampOn();
				isPause = true;
			} else {
				turnLampOff();
				isPause = false;
			}
			break;
		case R.id.btn_against:
			if (isPause == false) {
				isPause = true;
			} else {
				isPause = false;
			}
			break;
		case R.id.btn_abstain:
			if (isPause == false) {
				isPause = true;
			} else {
				isPause = false;
			}
			break;
		}*/
	}

	/**
	 *
	 * @Description:开灯
	 */
	private void turnLampOn() {
		lampAgreeDrawable.start();
		lampAgainstDrawable.start();
		lampAbstainDrawable.start();
	}

	/**
	 *
	 * @Description:熄灯
	 */
	private void turnLampOff() {
		lampAgreeDrawable.selectDrawable(0);
		lampAgreeDrawable.stop();
		lampAgainstDrawable.selectDrawable(0);
		lampAgainstDrawable.stop();
		lampAbstainDrawable.selectDrawable(0);
		lampAbstainDrawable.stop();
	}

	@Override
	public void onComplete(final RippleView rippleView) {
		final Button childAt = (Button) rippleView.getChildAt(0);
		Button[] btns = new Button[] { btn_agree, btn_against, btn_abstain };
		for (Button button : btns) {
			if (!button.equals(childAt)) {
				button.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaltBtnTextSize);
			}
		}
		if (childAt.getTextSize() != defaltBtnTextSize) {
			childAt.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaltBtnTextSize);
		} else {
			childAt.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaltBtnTextSize + 20);
		}
		/*Rotate3dAnimation.applyRotation(rippleView, 0, 90, new AnimationListener() {
			final Button childAt = (Button) rippleView.getChildAt(0);

			@Override
			public void onAnimationStart(Animation animation) {
				Button[] btns = new Button[] { btn_agree, btn_against, btn_abstain };
				for (Button button : btns) {
					if (!button.equals(childAt)) {
						button.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaltBtnTextSize);
					}
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if (childAt.getTextSize() != defaltBtnTextSize) {
					childAt.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaltBtnTextSize);
				} else {
					childAt.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaltBtnTextSize + 20);
				}
			}
		});*/
	}

}
