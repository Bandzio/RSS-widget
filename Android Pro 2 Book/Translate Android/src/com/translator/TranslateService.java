package com.translator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class TranslateService extends Service {

	private static final String tag = "TranslateService"; 
	
	private final ITranslate.Stub mBinder = new ITranslate.Stub() {
		
		@Override
		public String translate(String text, String from, String to) throws RemoteException {
			Log.v(from, "inside ITranslate.stub translate call" );
			

			Log.v("text", ""+text );
			Log.v("from", ""+from);
			Log.v("to", ""+to);
			
			String result = Translator.translate(text,from,to);
			 
			return result;
		}
	};
	
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.v(tag, "onBind call");
		return mBinder;
	}

}
