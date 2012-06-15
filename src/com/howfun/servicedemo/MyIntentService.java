package com.howfun.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
	
	private int works = 0;

	public MyIntentService() {
		super("MyIntentService");
	}

	public MyIntentService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		long endTime = System.currentTimeMillis() + 1000;
		while (System.currentTimeMillis()< endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch( Exception e) {
					
				}
			}
		}
		dowork();
		Log.e("MyIntentService", "onHandleIntent()");
		if (works >= ServiceDemoActivity.MAX_WORK) {
			TimeTest.end();
		}
		
	}
	
	private synchronized void dowork() {
		works++;
	}
	
	@Override
	public int onStartCommand (Intent intent, int flags, int startId){
		Log.e("MyIntentService", "on Start command, service starting.......id =" + startId);
		
		TimeTest.start();
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	@Override
	public void onDestroy(){
		Log.e("MyIntentService", "on Destory, service stop........");
		super.onDestroy();
		
	}
}
