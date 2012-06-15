package com.howfun.servicedemo;

import com.howfun.servicedemo.LocalService.LocalBinder;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LocalBindServiceActivity extends Activity {
	
	public static final int MAX_WORK = 10;
	private LocalService mLocalService;
	public boolean mIsBound;
	private Button mConnectBtn;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_bind_service);
        
     
        

        mConnectBtn = (Button)findViewById(R.id.connect_btn);
        mConnectBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mIsBound) {
				    int rand = mLocalService.getRandomInt();
				    Log.e("", "get random =" + rand);
				    Toast.makeText(LocalBindServiceActivity.this, "Get Random from service = " + rand, Toast.LENGTH_SHORT).show();
				    
				}
				
			}
        	
        });
        
        
        Intent localSeviceIntent = new Intent(this, LocalService.class);
        this.bindService(localSeviceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        
        
    }
    public void onDestroy() {
    	super.onDestroy();
    	if (mIsBound) {
    		this.unbindService(mServiceConnection);
    		mServiceConnection = null;
    	}
    }
    
    private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			
			Log.e("ServiceDemoActivity", "onServiceConnected");
			
			LocalBinder binder = (LocalBinder) service;
			mLocalService = binder.getService(); 
			mIsBound = true;
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.e("ServiceDemoActivity", "onServiceDisconnected");
			mIsBound = false;
			
		}
    	
    };
}