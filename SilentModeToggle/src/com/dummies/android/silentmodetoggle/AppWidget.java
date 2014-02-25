package com.dummies.android.silentmodetoggle;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppWidget extends AppWidgetProvider {
	
	
	final static String LOG_TAG = "AppWidget";

	@Override
	public void onReceive(Context ctx, Intent intent) {
		Log.i(LOG_TAG, "AppWidget.onReceive invoked");
		if(intent.getAction() == null) {
			Log.i(LOG_TAG, "intent.getAction == null");
		} else {
			super.onReceive(ctx, intent);
		}
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.i(LOG_TAG, "AppWidget.onUpdate invoked");
	}
}
