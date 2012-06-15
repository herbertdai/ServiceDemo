package com.howfun.servicedemo;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class MessengerService extends Service {

	private Handler mServiceHandler = new Handler() {

		public void handleMessage(Message msg) {

			switch (msg.arg1) {
			case MessengerServiceActivity.MSG_CONNECT_SERVICE:
				Log.e("MessengerService", "Receive a msg frome Client........");
				break;
				
			default:
				break;
			}
		}

	};

	@Override
	public void onDestroy() {
		Log.e("MessengerService", "on Destory, service stop........");
		super.onDestroy();

	}

	@Override
	public IBinder onBind(Intent intent) {
		Messenger messenger = new Messenger(mServiceHandler);
		return messenger.getBinder();
	}
}
