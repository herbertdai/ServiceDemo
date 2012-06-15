package com.howfun.servicedemo;

import android.content.Context;
import android.util.Log;

public class TimeTest {
	private static long startTime;
	private static long endTime;
	
	public static void start() {
		startTime = System.currentTimeMillis();
	}

	/**
	 * Stop time profile. 
	 * @return time in ms
	 */
	public static long end() {
		endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		Log.e("TimeTest", "calculateProcessTime is " + time);
		
		return time;
	}
}