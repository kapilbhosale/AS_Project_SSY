package com.amp.syadav;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.view.View;
import android.widget.TextView;

import com.twostars.syadav.R;

public class SocialMediaActivity extends FragmentActivity 
{
    FragmentPagerAdapter adapterViewPager;
    static Context ctx= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        headerSettings();
        ctx = this;
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setOffscreenPageLimit(2);
        vpPager.setOnPageChangeListener(new OnPageChangeListener()
        
        {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) 
            {
            	
            }
            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) 
            {
                // Code goes here
            }

            // Called when the scroll state changes: 
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }
    public static class MyPagerAdapter extends FragmentPagerAdapter {
		    private static int NUM_ITEMS = 3;

		        public MyPagerAdapter(FragmentManager fragmentManager) {
		            super(fragmentManager);
		        }

		        // Returns total number of pages
		        @Override
		        public int getCount() {
		            return NUM_ITEMS;
		        }

		        // Returns the fragment to display for that page
		        @Override
		        public Fragment getItem(int position) {
		            switch (position) {
		            case 0: //Facebook
		       	          return SocialFragment.newInstance("https://www.facebook.com/shivpalsinghyadav");
		            case 1: 
		            	//GooglePlus
		                return SocialFragment.newInstance("https://plus.google.com/+Shivpalsinghyadavorg#+Shivpalsinghyadavorg/posts");
		            case 2:
		            	// Twitter
		                return SocialFragment.newInstance("https://twitter.com/shivpalsinghyad");
//		            case 3:
//		            	// YouTube
//		                return SocialFragment.newInstance("https://www.youtube.com/user/shivpalsinghyad");
		            default:
		                return null;
		            }
		        }

		        // Returns the page title for the top indicator
		        @Override
		        public CharSequence getPageTitle(int position) 
		        {
		        	
		        	switch (position) {
					case 0:
						return ctx.getResources().getString(R.string.FACEBOOK_TITLE);
					case 1:
						return ctx.getResources().getString(R.string.GOOGLE_PLUS_TITLE);
					case 2:
						return ctx.getResources().getString(R.string.TWITTER_TITLE);
//					case 3:
//						return "Youtube";
//						
					default:
						break;
					}
		            return "Page " + position;
		        }

		    }

	
		private void headerSettings() {
			findViewById(R.id.btnBackHeader).setVisibility(View.VISIBLE);
			findViewById(R.id.btnHomeHeader).setVisibility(View.GONE);	
			TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
			headerTitle.setText(R.string.title_activity_social_media);
		}
		public void gotoBack(View v){
			finish();
			
		}
		
	
}
