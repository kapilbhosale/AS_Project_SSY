package com.amp.syadav;



import com.amp.helper.Utility;
import com.twostars.syadav.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VideoGallaryActivity extends Activity 
{
	WebView webview;	
	ProgressBar progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_gallary);
		headerSettings();
		
		progress = (ProgressBar) findViewById(R.id.videoProgressBar);
		progress.setMax(100);
		
		webview = (WebView) findViewById(R.id.videoWebView);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setWebViewClient(new VideoWebViewClient());
		if(Utility.isNetworkStatusAvialable(getApplicationContext()))
		{
			webview.loadUrl("https://www.youtube.com/user/shivpalsinghyad");
		} else 
		{
			Utility.showNetworkConnectionError(this);
		}
		
		
		
	}
	 private class VideoWebViewClient extends WebViewClient{  //HERE IS THE MAIN CHANGE. 

		 @Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
		
				progress.setVisibility(View.VISIBLE);
				view.loadUrl(url);
				return true;
			}
			@Override
			public void onPageFinished(WebView view, String url) {
				progress.setVisibility(View.GONE);
				super.onPageFinished(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				progress.setVisibility(View.VISIBLE);
		
			}
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video_gallary, menu);
		return true;
	}
	private void headerSettings() {
		findViewById(R.id.btnBackHeader).setVisibility(View.VISIBLE);
		findViewById(R.id.btnHomeHeader).setVisibility(View.GONE);	
		TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
		headerTitle.setText(R.string.title_activity_video_gallary);
	}
	public void gotoBack(View v){
		webview.loadUrl("");
    	webview.stopLoading();
		finish();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // TODO Auto-generated method stub
	    if(keyCode==event.KEYCODE_BACK)
	    {
	    	webview.loadUrl("");
	    	webview.stopLoading();

	        finish();

	    }
	    return super.onKeyDown(keyCode, event);
	}
}
