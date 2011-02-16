package com.rss.widget;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.os.AsyncTask;
import android.util.Log;

public class Downloader extends AsyncTask<URL, Void, RssFeed> {

	private static String tag = "Downloader";
	private RssFeed rssFeed;
	

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.v(tag, "onPreExecute call");
	
	}
	

	
	@Override
	protected RssFeed doInBackground(URL... params) {
		Log.v(tag, "doInBackground call");
		
		URL url = params[0];
		
		rssFeed = getRssFeed(url);
		
		
		return rssFeed;
	}


	private RssFeed getRssFeed(URL url) {
		Log.v(tag, "getRSS call");
		RssHandler rssHandler = null;
		try {
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser newSAXParser = saxParserFactory.newSAXParser();
			XMLReader xmlReader = newSAXParser.getXMLReader();
			
			rssHandler = new RssHandler();
			xmlReader.setContentHandler(rssHandler);

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
		
		RssFeed feed = rssHandler.getFeed();
		Log.v("FEED IS NULL ? ", ""+feed.equals(null));
		
		return rssHandler.getFeed();
	}

	@Override
	protected void onPostExecute(RssFeed result) {
		super.onPostExecute(result);
		Log.v(tag, "onPostExecute call");
		
		Log.v("feed is null " , ""+rssFeed.getRssFeedTitle());
	
		
		Log.v("feed result is null", ""+result.equals(null));
	}
	
	

}
