package com.rss.widget;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

/*
 * Klasa zajmuje sie parsowanie SAX z XML Stream 
 * Default Hanlder rozpoznaje zdarzenia generowanie przez SAX ( model zdarzeniowy ) 
 * i udospetnia metody do ich obslugi 
 */
public class RssHandler extends DefaultHandler {

	private String tag = "RssHandler";
	private RssFeed rssFeed;
	private RssItem rssItem;
	
	int state = 0;
	final int RSS_TITLE = 1;
	final int RSS_OPIS = 2;
	final int RSS_LINK = 3;
	
	public RssHandler() {
		Log.v(tag, "RssHanlder konstruktor call " ); 
	}
	
	
	@Override
	public void startDocument() throws SAXException {
		Log.v(tag, "start Document call");
		rssFeed = new RssFeed();
		rssItem = new RssItem();
		
		//TODO : ustaw to potem automatycznie
		rssFeed.setRssFeedTitle("Real Madrid RSS ");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//Log.v(tag, "start Element call ");
		
		if (localName.equals("channel")) {
			Log.v(localName, "channel");
			state = 0;
			return;
			
		}
		if (localName.equals("item")){
			Log.v(localName, "item");
			rssItem = new RssItem();
			return;
		}
		if(localName.equals("title")){
			//Log.v(localName, "title");
			state = RSS_TITLE;
			return;
		}
		
		if (localName.equals("description")) {
			state = RSS_OPIS;
			return;
		}
		
		if (localName.equals("link")) {
			state = RSS_LINK;
			return;
		}
		
		state = 0 ; 
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String napis = new String(ch, start, length);
		
		switch (state) {
		case RSS_TITLE:
				Log.v("Title  ", ""+napis);

				rssItem.setRssItemTitle(napis);
				
				state = 0;
			break;
		case RSS_OPIS :
				Log.v("opis = " , ""+napis);
				rssItem.setRssItemOpis(napis);
				state = 0;
			break;
		
		case RSS_LINK:
				Log.v("link = ", ""+napis);
				rssItem.setRssItemLink(napis);
				state = 0;
			break;

		default:
			return;
		}
	
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if (localName.equals("item")) {
			Log.v(localName, "/item");
			rssFeed.addItem(rssItem);
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		Log.v(tag, "endDocument call " );
	}

	public RssFeed getFeed() {
		Log.v(tag, "getFeed call "); 
		return rssFeed;
	}
	
	
}
