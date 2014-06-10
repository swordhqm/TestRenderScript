package com.test.renderScript;

import android.app.Activity;
import android.os.Bundle;

public class Fall extends Activity {
	private FallView mView; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mView = new FallView(this);
		setContentView(mView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Runtime.getRuntime().exit(0);
	}
	
}
