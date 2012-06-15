package com.howfun.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

public class HelloService extends Service {

	private ServiceHandler mServiceHandler;
	private int works = 0;

	private final class ServiceHandler extends Handler {
		public ServiceHandler(Looper looper) {
			super(looper);
		}

		public void handleMessage(Message msg) {
			long endTime = System.currentTimeMillis() + 1000;
			while (System.currentTimeMillis() < endTime) {
				synchronized (this) {
					try {
						wait(endTime - System.currentTimeMillis());
					} catch (Exception e) {

					}
				}
			}

			dowork(msg.arg1);

			stopSelf(msg.arg1);

		}

	}

	private synchronized void dowork(int id) {
		Log.e("HelloService", "Run service content, id =" + id);
		works++;
		if (works >= ServiceDemoActivity.MAX_WORK) {
			long time = TimeTest.end();
			Toast.makeText(HelloService.this, "Multi service takes time: " + time + " ms", Toast.LENGTH_LONG).show();
		}
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("HelloService", "service starting , id = " + startId);
		
		//? How to stop the thread?

		HandlerThread thread = new HandlerThread("ServiceStartArguments"
				+ startId, Process.THREAD_PRIORITY_BACKGROUND);
		thread.start();
		Looper looper = thread.getLooper();
		mServiceHandler = new ServiceHandler(looper);

		Message msg = mServiceHandler.obtainMessage();
		msg.arg1 = startId;
		mServiceHandler.sendMessage(msg);

		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		Log.e("HelloService", "service onCreate...");

		TimeTest.start();
		works = 0;

		// HandlerThread thread = new HandlerThread("ServiceStartArguments",
		// Process.THREAD_PRIORITY_BACKGROUND);
		// thread.start();
		// Looper looper = thread.getLooper();
		// mServiceHandler = new ServiceHandler(looper);
	}

	@Override
	public void onDestroy() {
		Log.e("HelloService", "service done...");
	}

}
