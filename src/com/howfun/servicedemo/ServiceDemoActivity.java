package com.howfun.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceDemoActivity extends Activity {
	
	public static final int MAX_WORK = 10;
	public boolean mIsBound;
	private Button mLocalBindBtn;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button mNormalServiceBtn = (Button)findViewById(R.id.start_service_btn);
        mNormalServiceBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceDemoActivity.this, NormalServiceActivity.class);
				startActivity(i);
				
			}
        	
        });
        
        Button mIntentServiceBtn = (Button)findViewById(R.id.intent_service_btn);
        mIntentServiceBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceDemoActivity.this, IntentServiceActivity.class);
				startActivity(i);
				
			}
        	
        });
        
        mLocalBindBtn = (Button)findViewById(R.id.local_bind_service_btn);
        mLocalBindBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceDemoActivity.this, LocalBindServiceActivity.class);
				startActivity(i);
				
			}
        	
        });
        
        Button mMessengerServiceBtn = (Button)findViewById(R.id.messenger_service_btn);
        mMessengerServiceBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceDemoActivity.this, MessengerServiceActivity.class);
				startActivity(i);
				
			}
        	
        });
        
        
    }
    public void onDestroy() {
    	super.onDestroy();
    }
    
    
    public void onPause() {
    	Log.e("ServiceDemoActivity", "OnPause()");
    	super.onPause();
    }
    
    public void onResume() {
    	Log.e("ServiceDemoActivity", "OnResume()");
    	super.onResume();
    }
    
    
    public void onStart() {
    	Log.e("ServiceDemoActivity", "OnStart()");
    	super.onStart();
    }
    
    public void onStop() {
    	Log.e("ServiceDemoActivity", "OnStop()");
    	super.onStop();
    }
    
}