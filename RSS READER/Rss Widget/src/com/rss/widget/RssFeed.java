package com.rss.widget;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/*
 * Klasa reprezentuje Rss Feed. 
 * Kazdy feed zawiera w sobie liste itemow
 * Zawiera tytul feed'a  i lista itemow
 */
public class RssFeed {
	
	private String tag = "RssFeed";
	
	private String rssFeedTitle = null;
	private List<RssItem> rssItemList = null;
	int rssItemCount = 0; // licznik itemow w feedzie
	
	public RssFeed() {
		Log.v(tag, "RssFeed construct call " );
		rssItemList = new ArrayList<RssItem>();
	}
	
	public void addItem(RssItem item ){
		Log.v(tag, "addItem call");
		rssItemList.add(item);
		rssItemCount++;
	}
	
	public void setRssFeedTitle(String rssFeedTitle) {
		this.rssFeedTitle = rssFeedTitle;
	}
	
	public String getRssFeedTitle() {
		return rssFeedTitle;
	}
	
	public List<RssItem> getAllItem() {
		
		return rssItemList;
	}
}
