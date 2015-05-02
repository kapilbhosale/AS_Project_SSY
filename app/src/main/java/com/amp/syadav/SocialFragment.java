package com.amp.syadav;

import java.lang.reflect.InvocationTargetException;

import com.amp.helper.Utility;

import com.twostars.syadav.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SocialFragment extends Fragment {
	// Store instance variables

	WebView browser;
	String socialURL,currentTitle;
	ProgressBar progress;


	// newInstance constructor for creating fragment with arguments
	public static SocialFragment newInstance(String socialURL) {
		SocialFragment fragmentFirst = new SocialFragment();
		Bundle args = new Bundle();

		args.putString("SOCIAL_URL", socialURL);
		fragmentFirst.setArguments(args);
		return fragmentFirst;
	}

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		socialURL = getArguments().getString("SOCIAL_URL");
	}

	// Inflate the view for the fragment based on layout XML
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.social_fragment, container, false);
		browser = (WebView) view.findViewById(R.id.socialWebView);
		//tvLabel.setText(page + " -- " + title);
		if(Utility.isNetworkStatusAvialable(getActivity()))
		{
			progress = (ProgressBar) view.findViewById(R.id.progressBar);
			progress.setMax(100);
			browser.setWebViewClient(new CustomWebViewClient());
			browser.getSettings().setJavaScriptEnabled(true);
			if(socialURL != null)
				browser.loadUrl(socialURL);	
		} else 
		{
			Utility.showNetworkConnectionError(getActivity());
		}
		return view;

	}
	@Override
	public void onPause() {
	    super.onPause();
	    browser.onPause();
	    try {
	        Class.forName("android.webkit.WebView")
	                .getMethod("onPause", (Class[]) null)
	                            .invoke(browser, (Object[]) null);

	    } catch(ClassNotFoundException cnfe) {
	        
	    } catch(NoSuchMethodException nsme) {
	        
	    } catch(InvocationTargetException ite) {
	        
	    } catch (IllegalAccessException iae) {
	        
	    }
	}
	

	private class CustomWebViewClient extends WebViewClient{  //HERE IS THE MAIN CHANGE. 

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
	public void setValue(int progress) {
		this.progress.setProgress(progress);		
	}
}