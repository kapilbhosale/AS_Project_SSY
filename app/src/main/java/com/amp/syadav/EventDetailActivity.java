package com.amp.syadav;

import httpimage.FileSystemPersistence;
import httpimage.HttpImageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.twostars.syadav.R;

public class EventDetailActivity extends Activity {
	WebView detailsTxt;
	WebView eventTitleTxt;
	String detailStr,titleStr;
	String imageName;
	ImageView eventDetailImageView;
	private HttpImageManager mHttpImageManager;
	public static final String BASEDIR = "/sdcard/httpimage";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_detail);

		headerSettings();
		detailsTxt = (WebView)findViewById(R.id.eventDetail);
		eventTitleTxt = (WebView)findViewById(R.id.eventTitle);
		eventDetailImageView = (ImageView)findViewById(R.id.eventDetailImageView);

		
		
		Intent intent = getIntent();
		detailStr = intent.getStringExtra("EVENT_DETAIL");
		imageName = intent.getStringExtra("IMAGE_URL");
		titleStr =  intent.getStringExtra("EVENT_TITLE");	

		mHttpImageManager = new HttpImageManager(HttpImageManager.createDefaultMemoryCache(), 
				new FileSystemPersistence(BASEDIR));
		
		final Uri uri = Uri.parse( imageName);
		eventDetailImageView.setImageResource(R.drawable.ic_launcher);
		if(uri != null)
		{
			Bitmap bitmap = mHttpImageManager.loadImage(new HttpImageManager.LoadRequest(uri, eventDetailImageView));
			if (bitmap != null) 
			{
				eventDetailImageView.setImageBitmap(bitmap);
			}
		}
		//<html><font size='3'><i><body>"
		//+ "</body></i></font></html>"
		String eventDescTxt = "<p align=\"justify\">"
				+detailStr + "</p>";

		detailsTxt.setVerticalScrollBarEnabled(false);
		detailsTxt.loadData(eventDescTxt, "text/html; charset=UTF-8", null);
//		detailsTxt.setText (Html.fromHtml(detailStr));


		String eventTitlTxt = "<html><font size='3'><b><body>"
				+"<p align=\"justify\">"
				+titleStr + "</p> " + "</body></b></font></html>";

		eventTitleTxt.setVerticalScrollBarEnabled(false);
		eventTitleTxt.loadData(eventTitlTxt, "text/html; charset=UTF-8", null);

//		eventTitleTxt.setText (titleStr);
	}

	private void headerSettings() {
		findViewById(R.id.btnBackHeader).setVisibility(View.VISIBLE);
		findViewById(R.id.btnHomeHeader).setVisibility(View.GONE);	
		TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
		headerTitle.setText(R.string.title_activity_events);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_detail, menu);
		return true;
	}
	public void gotoBack(View v){
		finish();
	}
}
