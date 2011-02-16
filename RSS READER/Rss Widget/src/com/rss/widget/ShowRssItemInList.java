package com.rss.widget;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowRssItemInList extends Activity {

	private static final String URL = "http://feeds.feedburner.com/RealMadrid_pl";

	private String tag = "ShowRssItemInList";
	
	TextView  mTextRssTitle;
	ListView mRssListItem;
	RssFeed mRssFeed;

	private RssFeed rssFeed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(tag, "onCreate call");
		
		setContentView(R.layout.rss_list);
	
		mTextRssTitle = (TextView) findViewById(R.id.TextViewRssTitle);
		mRssListItem = (ListView)findViewById(R.id.listItem);
		
////		RssItem[] allRssItem = 
////	
////		ArrayAdapter<RssItem> listItemAdapter = 
////			new ArrayAdapter<RssItem>(this, android.R.layout.simple_list_item_1, allRssItem);
////		mRssListItem.setAdapter(listItemAdapter );
		
		
		//TODO : to dziala, ale sprobuj narazie zrobic to samo z AsyncTask zeby nie blkkowac UI thread
		//rssFeed = getRSS(URL);
		//showRss();
		
		downloadAndShowRss(URL);
	
	}

	private void downloadAndShowRss(String url) {
		Downloader downloader = new Downloader();
		
		try{
			URL urlConnection = new URL(url);
			downloader.execute(urlConnection);
		}
		catch(Exception e){
			e.printStackTrace();
			Log.v("Error", "Url ");
		}
		
	}

	private RssFeed getRSS(String url2) {
		Log.v(tag, "getRSS call");
		RssHandler rssHandler = null;
		try {
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser newSAXParser = saxParserFactory.newSAXParser();
			XMLReader xmlReader = newSAXParser.getXMLReader();
			
			rssHandler = new RssHandler();
			xmlReader.setContentHandler(rssHandler);

			URL url = new URL(url2);
			InputStream openStream = url.openStream();
			InputSource input = new InputSource(openStream);
			
			xmlReader.parse(input);
		
		
		} catch (ParserConfigurationException e) {
			Log.e("Error", "ParserConfigurationException ");
			e.printStackTrace();
		} catch (SAXException e) {
			Log.e("Error", "SAXException");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			Log.e("Error", "MalformedURLException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Error", "IOException ");
			e.printStackTrace();
		}
		
		Log.v(tag +" RssFeed ()", "OK");
		return rssHandler.getFeed();
	}

	private void showRss() {
		
		List<RssItem> allRssItem = rssFeed.getAllItem();
		
		ArrayAdapter<RssItem> listAdapter = 
			new ArrayAdapter<RssItem>(this, android.R.layout.simple_list_item_1, allRssItem);
		mRssListItem.setAdapter(listAdapter );
	}
	
}
