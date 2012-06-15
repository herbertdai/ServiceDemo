package com.howfun.servicedemo;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService extends Service {

	private IBinder mBinder = new LocalBinder();
	private Random mRandom = new Random();

	public class LocalBinder extends Binder {

		public LocalService getService() {
			return LocalService.this;
		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	public int getRandomInt() {
		return mRandom.nextInt(1000);
	}

}
