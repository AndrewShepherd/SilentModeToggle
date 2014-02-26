package com.dummies.android.silentmodetoggle;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.media.AudioManager;


public class MainActivity extends Activity {

	private AudioManager mAudioManager;
	private boolean mPhoneIsSilent;
	final static String LOG_TAG = "SilentModeToggle";

	public MainActivity() {
		super();
    	Log.i(LOG_TAG, "MainActivity constructor invoked");
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	

    	Log.i(LOG_TAG, "MainActivity.onCreate invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        
        checkIfPhoneIsSilent();

        setButtonClickListener();
    }

    
    private void checkIfPhoneIsSilent() {
    	int ringerMode = mAudioManager.getRingerMode();
    	this.displayRingerState(ringerMode);    	
    }
    
    private void setIconDisplay(int imageResource) {
    	ImageView imageView = (ImageView)findViewById(R.id.phone_icon);
    	imageView.setImageResource(imageResource);
    }
    
    
    private void displayRingerState(int ringerState) {
    	int imageResource = (ringerState == AudioManager.RINGER_MODE_SILENT)  ? R.drawable.phone_silent : R.drawable.phone_on;
    	setIconDisplay(imageResource);
    }

    
    
    

	private void setButtonClickListener() {
		Button toggleButton = (Button)findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
		    	Log.i(LOG_TAG, "toggleButton click event fired");
				
		    	int newPhoneState = new AudioStateToggler(MainActivity.this).Toggle();
		    	MainActivity.this.displayRingerState(newPhoneState);
			}
        	
        });
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onResume() {
    	Log.i("SilentModeToggle", "onResume is being called");
    	super.onResume();
    	checkIfPhoneIsSilent();
    }
    
    
}
