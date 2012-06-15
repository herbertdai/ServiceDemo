package com.howfun.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NormalServiceActivity extends Activity {

	public static final int MAX_WORK = 10;
	public boolean mIsBound;
	private Button mLocalBindBtn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_service);

		Button start = (Button) findViewById(R.id.start_btn);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Test Service and IntentService.
				for (int i = 0; i < MAX_WORK; i++) {
					Intent intent = new Intent(NormalServiceActivity.this, HelloService.class);
					startService(intent);
				}
			}

		});

	}

	public void onDestroy() {
		super.onDestroy();
	}

}