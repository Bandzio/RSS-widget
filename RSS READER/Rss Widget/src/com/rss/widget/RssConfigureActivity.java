package com.rss.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class RssConfigureActivity extends Activity {

	private String tag = "RssConfigureActivity";
	private int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	
	private Button mButtonOk;
	private Spinner mSpinner;
	TextView mTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.v(tag, "onCreate call");

		mTextView = (TextView) findViewById(R.id.textView);
		
		mButtonOk = (Button) findViewById(R.id.Button01);
		mButtonOk.setOnClickListener(mClickListener);
		
		mSpinner = (Spinner) findViewById(R.id.spinner);
		
		ArrayAdapter<CharSequence> spinnerAdapter  =
			ArrayAdapter.createFromResource(this, R.array.rss_source, android.R.layout.simple_spinner_item);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
		mSpinner.setAdapter(spinnerAdapter);
		
		mSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		//get widget id
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if(extras != null){
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		//if widget id is invalid - finish activity
		if(mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID){
			finish();
		}
	}

	View.OnClickListener mClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Log.v(tag, "onClick");
			
			//udpate view
			AppWidgetManager widgetManager = AppWidgetManager.getInstance(RssConfigureActivity.this);
			RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.rss_layout);
		//	remoteViews.setTextViewText(R.id.bdw_w_name, mTextView.getText());
			
			//widgetManager.updateAppWidget(mAppWidgetId, remoteViews);
			
			//return intent
			Intent returnIntent = new Intent();
			returnIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
			setResult(RESULT_OK,returnIntent);
			finish();
		}
	};
	

	class MyOnItemSelectedListener implements OnItemSelectedListener{
		private String tag = "MyOnItemSelectedListener";
		
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			Log.v(tag, "onItemSelected call");
			String text = parent.getItemAtPosition(pos).toString();
			Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
			
			//set Text View
			mTextView.setText(text);
		}
	
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			Log.v(tag, "onNothingSelected call");
		}
		
	}

}