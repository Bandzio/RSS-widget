package com.animationview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AnimationView extends Activity {
	
	ListView mListView;
	Button mButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupListView();
        setupButton();
    }

	private void setupButton() {
		mButton = (Button)findViewById(R.id.button);
		mButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ViewAnimation animation = new ViewAnimation();
				animation.setAnimationListener(new AnimationViewListener());
				mListView.startAnimation(animation);
				
			}
		});
	}

	private void setupListView() {
		mListView = (ListView)findViewById(R.id.listView);
		
		
		String[] items = {"Mama","Tata","Marcin","Karolina","Monika","Anna"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
		
		mListView.setAdapter(adapter);
	}
}