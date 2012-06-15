package com.howfun.servicedemo;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MessengerServiceActivity extends Activity {

	public static final int MAX_WORK = 10;
	public boolean mIsBound;
	private Messenger mMessenger;
	public static final int MSG_CONNECT_SERVICE = 1;

	private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mMessenger = new Messenger(service);
			mIsBound = true;

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			// This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mMessenger = null;
			mIsBound = false;
		}

	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messenger_service);

		Button connectBtn = (Button) findViewById(R.id.connect_btn);
		connectBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message msg = new Message();
				msg.arg1 = MSG_CONNECT_SERVICE;

				try {
					mMessenger.send(msg);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		Log.e("MessengerServiceActivity", "OnCreate()");

	}

	public void onStart() {
		Log.e("MessengerServiceActivity", "OnStart()");
		super.onStart();

		Intent i = new Intent(this, MessengerService.class);
		this.bindService(i, mServiceConnection, Service.BIND_AUTO_CREATE);

	}

	public void onStop() {
		Log.e("MessengerServiceActivity", "OnStop()");
		super.onStop();
		if (mIsBound) {
			this.unbindService(mServiceConnection);
			mIsBound = false;
		}
	}

	public void onDestroy() {
		super.onDestroy();
	}

	public void onPause() {
		Log.e("MessengerServiceActivity", "OnPause()");
		super.onPause();
	}

	public void onResume() {
		Log.e("MessengerServiceActivity", "OnResume()");
		super.onResume();
	}

}