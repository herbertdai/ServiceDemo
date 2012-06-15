package com.howfun.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

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
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {

				}
			}
		}
		dowork();
		Log.e("MyIntentService", "onHandleIntent()");
		if (works >= ServiceDemoActivity.MAX_WORK) {
			final long time = TimeTest.end();
			// HandlerThread threadToShowToast = new
			// HandlerThread("Thread to show toast!",
			// Process.THREAD_PRIORITY_BACKGROUND) {
			// public void run() {
			//
			Toast.makeText(MyIntentService.this,
					"Multi service takes time: " + time + " ms",
					Toast.LENGTH_LONG).show();
			// }
			// };
			// Looper looper = threadToShowToast.getLooper();
			// looper.prepare();
			//
			// threadToShowToast.start();
			showToastInThread(time);

		}

	}

	private void showToastInThread(final long time) {
		// TODO Auto-generated method stub
	}

	private synchronized void dowork() {
		works++;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("MyIntentService",
				"on Start command, service starting.......id =" + startId);

		TimeTest.start();
		return super.onStartCommand(intent, flags, startId);

	}

	@Override
	public void onDestroy() {
		Log.e("MyIntentService", "on Destory, service stop........");
		super.onDestroy();

	}
}
