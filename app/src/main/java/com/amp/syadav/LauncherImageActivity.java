package com.amp.syadav;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

import com.amp.helper.Utility;
import com.twostars.syadav.R;

public class LauncherImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher_image);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.launcher_image, menu);
		return true;
	}
	public void gotoNext(){
		Intent mainIntent = new Intent(getApplicationContext(),
				MainActivity.class);
		startActivity(mainIntent);
	}
	
	public void englishBtnClicked(View v)
	{
		Utility.updateLocaleWithLanguage(LauncherImageActivity.this, "en");
		gotoNext();
	}
	public void hindiBtnClicked(View v)
	{
		Utility.updateLocaleWithLanguage(LauncherImageActivity.this, "hi");
		gotoNext();
	}
}
