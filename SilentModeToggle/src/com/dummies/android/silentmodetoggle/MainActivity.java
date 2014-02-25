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
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        
        checkIfPhoneIsSilent();
        setButtonClickListener();
        
    }

    
    private void checkIfPhoneIsSilent() {
    	int ringerMode = mAudioManager.getRingerMode();
    	mPhoneIsSilent = (ringerMode == AudioManager.RINGER_MODE_SILENT);
    	
    }
    
    private void toggleUi() {
    	ImageView imageView = (ImageView)findViewById(R.id.phone_icon);
    	
    	int drawableId = this.mPhoneIsSilent ? R.drawable.phone_silent : R.drawable.phone_on;
    	
    	Drawable newPhoneImage = getResources().getDrawable(drawableId);
    	imageView.setImageDrawable(newPhoneImage);

    }
    
    
    

	private void setButtonClickListener() {
		Button toggleButton = (Button)findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mPhoneIsSilent) {
					mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				} else {
					mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				}
				mPhoneIsSilent = !mPhoneIsSilent;
				toggleUi();
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
    	toggleUi();
    }
    
    
}
