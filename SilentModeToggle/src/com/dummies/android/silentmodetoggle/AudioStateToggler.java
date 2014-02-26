package com.dummies.android.silentmodetoggle;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

public class AudioStateToggler {
	private final AudioManager mAudioManager;
	
	public AudioStateToggler(Context context) {
		this.mAudioManager = (AudioManager)context.getSystemService(Activity.AUDIO_SERVICE);
	}
	
	// Toggles the volume state, and returns the new state
	public int Toggle() {
		int newRingerMode = (mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) ? AudioManager.RINGER_MODE_NORMAL : AudioManager.RINGER_MODE_SILENT;
		mAudioManager.setRingerMode(newRingerMode);
		return newRingerMode;
	}
}
