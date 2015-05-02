package com.amp.syadav;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import com.twostars.syadav.R;

public class BiographyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_biography);
		headerSettings();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.biography, menu);
		return true;
	}
	private void headerSettings() {
		findViewById(R.id.btnBackHeader).setVisibility(View.VISIBLE);
		findViewById(R.id.btnHomeHeader).setVisibility(View.GONE);	
		TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
		headerTitle.setText(R.string.title_activity_biography);
	}
	public void gotoBack(View v){
		finish();
	}
	
	
	
	public void personalURLClicked(View v)
	{
		TextView txtView= (TextView)v;
		Intent webViewIntent1 = new Intent(getApplicationContext(),
				WebViewActivity.class);
		webViewIntent1.putExtra("SOCIAL_URL", txtView.getText());
		String actTitle1 =  getResources().getString(R.string.title_activity_biography);
		webViewIntent1.putExtra("TITLE", actTitle1);

		startActivity(webViewIntent1);
	}
}
