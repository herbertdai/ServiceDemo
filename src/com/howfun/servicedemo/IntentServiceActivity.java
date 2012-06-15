package com.howfun.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class IntentServiceActivity extends Activity {

	public static final int MAX_WORK = 10;
	public boolean mIsBound;
	private Button mLocalBindBtn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent_service);

		Button btn = (Button) findViewById(R.id.connect_btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < MAX_WORK; i++) {
					Intent intent2 = new Intent(IntentServiceActivity.this,
							MyIntentService.class);
					startService(intent2);
				}

			}

		});
		
		Toast.makeText(getApplicationContext(), "Multi service takes time: " + " ms", Toast.LENGTH_LONG).show();

	}

	public void onDestroy() {
		super.onDestroy();
	}

}