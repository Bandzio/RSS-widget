package com.animationview;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class ViewAnimation extends Animation {
	
	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		setDuration(2500);
		setFillAfter(true);
		setInterpolator(new BounceInterpolator());
	}
	
	
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		Camera camera = new Camera();
		final Matrix matrix = t.getMatrix();
		camera.save();
//		matrix.setScale(interpolatedTime, interpolatedTime);
		camera.translate(0.0f, 0.0f, (1300-1300.0f*interpolatedTime));
		camera.rotateY(360*interpolatedTime);
		camera.getMatrix(matrix);
		camera.restore();
	}
}
