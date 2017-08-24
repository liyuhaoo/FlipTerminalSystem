package com.flx.util;

import android.view.animation.Animation;

/**
 *
 * @Description: 3D翻转效果，使用说明：http://www.cnblogs.com/loonggg/archive/2013/01/20/2868458.html
 * @date 2017-2-23 上午11:14:40
 * @author liyuhao
 */
public class Rotate3dAnimation extends Animation {
	/*// 开始角度
	private final float mFromDegrees;
	// 结束角度
	private final float mToDegrees;
	// 中心点
	private final float mCenterX;
	private final float mCenterY;
	private final float mDepthZ;
	// 是否需要扭曲
	private final boolean mReverse;
	// 摄像头
	private Camera mCamera;

	public Rotate3dAnimation(float fromDegrees, float toDegrees, float centerX, float centerY, float depthZ, boolean reverse) {
		mFromDegrees = fromDegrees;
		mToDegrees = toDegrees;
		mCenterX = centerX;
		mCenterY = centerY;
		mDepthZ = depthZ;
		mReverse = reverse;
	}

	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
	}

	// 生成Transformation
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		final float fromDegrees = mFromDegrees;
		// 生成中间角度
		float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);

		final float centerX = mCenterX;
		final float centerY = mCenterY;
		final Camera camera = mCamera;

		final Matrix matrix = t.getMatrix();

		camera.save();
		if (mReverse) {
			camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
		} else {
			camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
		}
		camera.rotateY(degrees);
		// 取得变换后的矩阵
		camera.getMatrix(matrix);
		camera.restore();

		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
	}

	*//**
	 *
	 * @Description: 翻转
	 * @param v
	 *            要翻转的view
	 * @param start
	 *            起始角度
	 * @param end
	 */
	/*
	public static void applyRotation(View v, float start, float end, AnimationListener animationListener) {
	// 计算中心点
	final float centerX = v.getWidth() / 2.0f;
	final float centerY = v.getHeight() / 2.0f;

	final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
	rotation.setDuration(500);
	rotation.setFillAfter(true);
	rotation.setInterpolator(new AccelerateInterpolator());
	// 设置监听
	rotation.setAnimationListener(new DisplayNextView(v, animationListener));

	v.startAnimation(rotation);
	}

	private static class DisplayNextView implements Animation.AnimationListener {

	private View v;
	private AnimationListener animationListener;

	public DisplayNextView(View v, AnimationListener animationListener) {
		this.v = v;
		this.animationListener = animationListener;
	}

	public void onAnimationStart(Animation animation) {
	}

	// 动画结束
	public void onAnimationEnd(Animation animation) {
		v.post(new Runnable() {

			@Override
			public void run() {
				final float centerX = v.getWidth() / 2.0f;
				final float centerY = v.getHeight() / 2.0f;
				Rotate3dAnimation rotation = null;

				v.requestFocus();

				rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
				rotation.setDuration(500);
				rotation.setFillAfter(true);
				rotation.setInterpolator(new DecelerateInterpolator());
				// 开始动画
				v.startAnimation(rotation);
				rotation.setAnimationListener(animationListener);
			}
		});
	}

	public void onAnimationRepeat(Animation animation) {
	}
	}*/
}