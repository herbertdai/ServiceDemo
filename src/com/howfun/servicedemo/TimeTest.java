package com.howfun.servicedemo;

import android.util.Log;

public class TimeTest {
	private static long startTime;
	private static long endTime;

	public static void start() {
		startTime = System.currentTimeMillis();
	}

	public static void end() {
		endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		Log.e("TimeTest", "calculateProcessTime is " + time);
	}
}