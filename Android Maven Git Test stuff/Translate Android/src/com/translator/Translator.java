package com.translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Translator {
	//translate version 1.0 lab
	
	private static final String tag= "Translator";
	private static String URL_BASE = "http://ajax.googleapis.com/ajax/services/language/translate?v=1.0&langpair=";
	private static String INPUT_TEXT = "&q=";
	private static String ENCODING="UTF-8";
	private static String usunToTest = null; 
	private static StringBuilder usunToTestBuf = new StringBuilder();
	
	
	public static String translate(String text, String from, String to) {
		Log.v(tag, "translate call");

		//TODO : open connection
		try {
			StringBuilder url = new StringBuilder();
			//      url base       english       separator     germany
			url.append(URL_BASE).append(from).append("%7C").append(to)
					.append(INPUT_TEXT).append(URLEncoder.encode(text, ENCODING));
			Log.v("string builder ", ""+url.toString());
			
			usunToTest = new String(url);
			
			HttpURLConnection conn = (HttpURLConnection)new URL(url.toString()).openConnection();
			conn.setRequestProperty("REFERER", "http://my.website.com");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader buferReader = new BufferedReader(inputStreamReader);
			
			String line;
			
			while((line = buferReader.readLine())!= null){
				Log.v("line = ", ""+line);
				usunToTestBuf.append(line);
			}
			Log.v(tag, "wynik = "+usunToTestBuf.toString());

		
			//** response
			try {
				JSONObject json = new JSONObject(usunToTestBuf.toString());
				String response = ((JSONObject)json.get("responseData")).getString("translatedText");
				Log.v("respone = ", ""+response);
				
				// clear stringbuilder
				if(usunToTestBuf!=null){
					usunToTestBuf = null;
					usunToTestBuf = new StringBuilder();
				}
					
				
				return response;
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				conn.getInputStream().close();
			}
			
		
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return usunToTestBuf.toString();
	}

}
