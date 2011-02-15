package com.rss.widget;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowRssItemInList extends Activity {

	private String tag = "ShowRssItemInList";
	
	TextView  mTextRssTitle;
	ListView mRssListItem;
	RssFeed mRssFeed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(tag, "onCreate call");
		
		setContentView(R.layout.rss_list);
	
		mTextRssTitle = (TextView) findViewById(R.id.TextViewRssTitle);
		mRssListItem = (ListView)findViewById(R.id.listItem);
		
//		RssItem[] allRssItem = 
		
//		ArrayAdapter<RssItem> listItemAdapter = 
//			new ArrayAdapter<RssItem>(this, android.R.layout.simple_list_item_1, allRssItem);
//		mRssListItem.setAdapter(listItemAdapter );
	
	}
	
}
