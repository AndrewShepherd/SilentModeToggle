package com.dummies.android.silentmodetoggle;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {
	
	
	public AppWidget() {
		super();
		Log.i(LOG_TAG, "AppWidget constructor invoked");
	}
	
	final static String LOG_TAG = "SilentModeToggle";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(LOG_TAG, "AppWidget.onReceive invoked");
		if(intent.getAction() == null) {
			Log.i(LOG_TAG, "intent.getAction == null. Starting the toggleservice");
			context.startService(new Intent(context, ToggleService.class));

		} else {
			super.onReceive(context, intent);
		}
	}
	
	

	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		context.startService(new Intent(context, ToggleService.class));
		Log.i(LOG_TAG, "AppWidget.onUpdate invoked");
	}
	
	public static class ToggleService extends IntentService {

		public ToggleService() {
			super("AppWidget$ToggleService");
			Log.i(LOG_TAG, "ToggleService constructor invoked");
		}

		@Override
		protected void onHandleIntent(Intent intent) {
			Log.i(LOG_TAG, "ToggleService.onHandleIntent method invoked");
			ComponentName me = new ComponentName(this, AppWidget.class);
			AppWidgetManager mgr = AppWidgetManager.getInstance(this);
			mgr.updateAppWidget(me, buildUpdate(this));
		}
		
		private RemoteViews buildUpdate(Context context) {
			Log.i(LOG_TAG, "ToggleService.buildUpdate method invoked");
			RemoteViews updateViews = new RemoteViews(
														context.getPackageName(),
														R.layout.widget
													);
			AudioManager audioManager = (AudioManager)context.getSystemService(Activity.AUDIO_SERVICE);
			
			if(audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
				updateViews.setImageViewResource(R.id.phoneState, R.drawable.phone_state_normal);
				audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			} else {
				updateViews.setImageViewResource(R.id.phoneState, R.drawable.phone_state_silent);
				audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}
			Intent i = new Intent(this, AppWidget.class);
			PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
			updateViews.setOnClickPendingIntent(R.id.phoneState, pi);
			return updateViews;
		}
		
	}
}
