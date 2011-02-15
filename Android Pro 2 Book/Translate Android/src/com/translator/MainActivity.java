package com.translator;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 
 * @author Marcin
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	private static final String tag = "MainActivity";
	
	EditText mInput, mOutput;
	Spinner mFromLang, mToLang;
	Button mTranslateButton;
	
	private String[] languageValues = null;
	
	//translate interface service object
	private ITranslate mITranslateInterface = null;

	private ServiceConnection mTranslateConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v("Service Connection", "onServiceDisconnected component name :"+name.toShortString());
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v("Service Connection", "onServiceConnected component name :"+name.toShortString());			
			
			mITranslateInterface = ITranslate.Stub.asInterface(service);
			Log.v(tag, "mITranslateInterface.equals(null) ="+mITranslateInterface.equals(null));
			mTranslateButton.setEnabled(true);
		}
	};;;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(tag, "onCreate call");
        
        setContentView(R.layout.main);
        
        mInput = (EditText)findViewById(R.id.input);
        mOutput = (EditText)findViewById(R.id.translation);
        
        mFromLang = (Spinner)findViewById(R.id.from);
        mToLang = (Spinner)findViewById(R.id.to);
        
        mTranslateButton = (Button)findViewById(R.id.translateBtn);
        mTranslateButton.setOnClickListener(this);

        languageValues = getResources().getStringArray(R.array.language_values);
        
        /*
         * populate spinner's
         */
        
        ArrayAdapter<?> fromLangAdapter = 
        			ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item); 
        mFromLang.setAdapter(fromLangAdapter);
        mFromLang.setSelection(1);
        
        ArrayAdapter<?> toLangAdapter = 
        	ArrayAdapter.createFromResource(this, R.array.languages,android.R.layout.simple_spinner_item);
		mToLang.setAdapter(toLangAdapter );
		toLangAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		mToLang.setSelection(3);
		
		mInput.selectAll();
		
		Intent service = new Intent(Intent.ACTION_VIEW);
		//mTranslateConnection - Receives information as the service is started and stopped.
		bindService(service, mTranslateConnection, Service.BIND_AUTO_CREATE);
		
    }

	@Override
	public void onClick(View v) {
		Log.v(tag, "onClick call ");
		if(mInput.getText().toString().length()>0)
			doTranslate();
		else
			Toast.makeText(getApplicationContext(), "Empty input", Toast.LENGTH_SHORT).show();
	}

	private void doTranslate() {
		//TODO : in => edit  - > mInput; spinner -> mFromLang, mToLang
		Log.v(tag, "doTranslate call");
		String text = mInput.getText().toString();
		int selectedItemPositionFromLang = mFromLang.getSelectedItemPosition();
		int selectedItemPositionToLang = mToLang.getSelectedItemPosition();
		
//		String from = mFromLang.getSelectedItem().toString();
		String from = languageValues[selectedItemPositionFromLang];
		
//		String to = mToLang.getSelectedItem().toString();
		String to = languageValues[selectedItemPositionToLang];
		String result = null;
		Log.v("text", ""+text );
		Log.v("from", ""+from);
		Log.v("to", ""+to);
		
		
		try {
			result = mITranslateInterface.translate(text, from, to);
			Log.v("doTranslate result", ""+result);
		} catch (RemoteException e) {
			e.printStackTrace();
			Log.v("doTranslate Exception", ""+e.toString());
		}
		
		mOutput.setText(result);
		
	}
}