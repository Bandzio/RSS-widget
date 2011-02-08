package com.wiki.words;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//TODO : 
// zrob tak zeby wyswietlalo sie w jednym wierszu slowo po pl, slowo po ang i po de 
// trzeba zrobic swoj wlasny list adapter 

public class MainActivity extends Activity {
	
	private String tag = "MainActivity";
	
	Spinner mSpinner;
	ListView mListView;
	MyCustomAdapter mMyCustomAdapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mSpinner = (Spinner) findViewById(R.id.Spinner);
  
        
        ArrayAdapter<CharSequence> spinnerAdapter = 
        	ArrayAdapter.createFromResource(this, R.array.category_spinner,
        			android.R.layout.simple_spinner_item);
        
		mSpinner.setAdapter(spinnerAdapter);
        
		mListView = (ListView) findViewById(R.id.ListView);
		
		String[] words = getResources().getStringArray(R.array.words);
		debugLogShowMeWords(words);
		
		//powiaz words z listView
		//kazde slowo ma byc umieszczone w layoucie wiersza words_row 
		//w textView o id TextViewRow
//		ArrayAdapter<String> listAdapter = 
//			new ArrayAdapter<String>(this, R.layout.words_row, R.id.TextViewRowPL, words); 
//		
//		mListView.setAdapter(listAdapter);
		
		mMyCustomAdapter = new MyCustomAdapter(this, R.layout.words_row, words);
		
		mListView.setAdapter(mMyCustomAdapter);
		
    }

	private void debugLogShowMeWords(String[] words) {
		Log.v(tag , "words");
		StringBuilder sb = new StringBuilder();
		for (String string : words) {
			sb.append("\n").append(string);
		}
		Log.v(tag, sb.toString());
	}
	
	private class MyCustomAdapter extends ArrayAdapter<String>{

		private String tag = "MyCustomAdapter";
		
		private String[] words ;
		private Context context;
		
		public MyCustomAdapter(Context context, int textViewResourceId,	String[] words) {
			super(context, textViewResourceId, words);
			Log.v(tag, "Konstruktor");
			this.words = words;
			this.context = context;
		}

		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.v(tag, "getView call");
			View v = convertView;
			
			if(v == null){
				LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = inflater.inflate(R.layout.words_row, null);
			}
			
			
			TextView textViewPL = (TextView) v.findViewById(R.id.TextViewRowPL);
			TextView textViewEN = (TextView) v.findViewById(R.id.TextViewRowEN);
			TextView textViewDE = (TextView) v.findViewById(R.id.TextViewRowDE);
			
			
			textViewPL.setText(words[position]);
			textViewEN.setText(words[position]+" ang");
			textViewDE.setText(words[position]+" de");
			

			OnClickListener clickListener = new OnClickListener() {
				
				public void onClick(View v) {
					Toast.makeText(context, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
				}
			};
			textViewPL.setOnClickListener(clickListener);
			textViewEN.setOnClickListener(clickListener);
			textViewDE.setOnClickListener(clickListener);
			
			
			return v;
		}
	}
	
}