package com.rss.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RssWidget extends AppWidgetProvider {

	private String tag = "RssWidget";
	
	@Override
	public void onEnabled(Context context) {
		//super.onEnabled(context);
		Log.v(tag, "onEnabled call");
	}
	
	@Override
	public void onDisabled(Context context) {
		//super.onDisabled(context);
		Log.v(tag, "onDisabled call");
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
	//	super.onDeleted(context, appWidgetIds);
		Log.v(tag, "onDeleted call");
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		//super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.v(tag, "onUpdate call v");
		Log.i(tag, "onUpdate call i");
		Log.d(tag, "onUpdate call d");
		
		Intent serviceIntent = new Intent(context,RssService.class );
		context.startService(serviceIntent);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		Log.v(tag, "onReceive call");
	}
	
}
