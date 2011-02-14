package com.rss.widget;

import android.util.Log;


/*
 * Klasa zawiera lista itemow w feedzie. to co bedzie wyswietlane userowi
 * Klasa pelni funkcje pojemnika na informacje 
 * Kazdy item to : 
 * 	tytul 
 * 	opis
 * 	link
 */
public class RssItem {

	private String tag = "RssItem";
	
	private String rssItemTitle = null;
	private String rssItemOpis = null;
	private String rssItemLink = null;
	
	public RssItem() {
		Log.v(tag, "Rss Item konstruktor call " ); 
		
	}
	
	public void setRssItemTitle(String rssItemTitle) {
		this.rssItemTitle = rssItemTitle;
	}
	public void setRssItemOpis(String rssItemOpis) {
		this.rssItemOpis = rssItemOpis;
	}
	public void setRssItemLink(String rssItemLink) {
		this.rssItemLink = rssItemLink;
	}
	
	public String getRssItemTitle() {
		return rssItemTitle;
	}
	public String getRssItemLink() {
		return rssItemLink;
	}
	public String getRssItemOpis() {
		return rssItemOpis;
	}
	
	@Override
	public String toString() {
		String title = rssItemTitle;
		
		return title;
	}
	
}
