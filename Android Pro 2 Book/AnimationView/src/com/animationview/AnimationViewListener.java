package com.animationview;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AnimationViewListener implements AnimationListener {

	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		Log.v("onAnimationEnd", "onAnimationEnd");
	}

	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		Log.v("onAnimationRepeat", "onAnimationRepeat");
	}

	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		Log.v("onAnimationStart", "onAnimationStart");
	}

}
