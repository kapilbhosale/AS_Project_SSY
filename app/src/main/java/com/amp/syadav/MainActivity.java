package com.amp.syadav;



import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.amp.helper.Constants;
import com.amp.helper.Utility;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.twostars.syadav.R;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity 
{
	private Context ctx = null;
	private GridView Gv = null;
	Dialog dialog;
	private String[] HomeMenu ;
	private String[] colorCodes = {"#d0021b", "#4a90e2","#f5a623",
			"#bd10e0","#d0021b","#4a90e2",
			"#f5a623","#bd10e0","#d0021b",
			"#d0021b", "#4a90e2"
	};

	private Integer[] HomeMenuResouce =
			{
			R.drawable.ic_1,
			R.drawable.ic_9,
			R.drawable.ic_5,

			R.drawable.ic_4,
			R.drawable.ic_6,
			R.drawable.ic_7,

			R.drawable.ic_10,
			R.drawable.ic_11,
			R.drawable.ic_3,

			R.drawable.ic_4,
			R.drawable.ic_9,
			};

//-----------------------------------------------

    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
//    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "994936054010";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCM Demo";

    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    Context context;

    String regid;

//-----------------------------------------------



    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ctx = this;
		headerSettings();
		HomeMenu=new String[]{getResources().getString(R.string.title_activity_biography),
				getResources().getString(R.string.title_activity_political_career),
				getResources().getString(R.string.title_activity_samajwadi_party),
				getResources().getString(R.string.title_activity_events),
				getResources().getString(R.string.title_activity_video_gallary),
				getResources().getString(R.string.title_activity_social_media),
				getResources().getString(R.string.title_activity_vidhan_sabha),
				getResources().getString(R.string.title_activity_department),
				getResources().getString(R.string.title_activity_contact),
				getResources().getString(R.string.title_activity_ImageGallary)
				,getResources().getString(R.string.title_activity_OngoingProjects)

		};



        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(ctx);

            if (regid.isEmpty()) {
                registerInBackground();
            }
            else
            {
                registerInBackground();
               // TextView txt_view = (TextView) findViewById(R.id.txt_hellow);
                Log.i(TAG, "REG_ID : " + regid.toString());
                //txt_view.setText("Id is " + regid.toString());
            }
        } else
		{
            Log.i(TAG, "No valid Google Play Services APK found.");
        }





		Gv = (GridView) findViewById(R.id.gridHome);
		Gv.setAdapter(new MenuGridNewAdaptor(MainActivity.this,
				HomeMenuResouce, HomeMenu,colorCodes,R.layout.menu_grid_button));

		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
		Gv.setAnimation(hyperspaceJumpAnimation);
		hyperspaceJumpAnimation.start();

		Gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {

				switch (position) {
				case 0:
					// BioGraphy
					Intent biographyIntent = new Intent(getApplicationContext(),
							BiographyActivity.class);
					startActivity(biographyIntent);

					break;
				case 1:
					// Political Career
					Intent politicalCareerIntent = new Intent(getApplicationContext(),
							PoliticalCareerActivity.class);
					startActivity(politicalCareerIntent);

					break;
				case 2:
					// Samajwadi Party
					Intent samajwadiPartyIntent = new Intent(
							getApplicationContext(),
							SamajwadiPartyActivity.class);
					//sportsListIntent.putExtra("LAUNH_FROM", "ScheduleTile");
					startActivity(samajwadiPartyIntent);
					break;
				case 3:
					// Events
					Intent eventsIntent = new Intent(getApplicationContext(),
							EventsActivity.class);
					startActivity(eventsIntent);
					break;
//				case 4:
//					Intent webViewIntent1 = new Intent(getApplicationContext(),
//							WebViewActivity.class);
//					webViewIntent1.putExtra("SOCIAL_URL", getResources().getString(R.string.IN_MEDIA_URL));
//					String actTitle1 =  getResources().getString(R.string.title_activity_media);
//					webViewIntent1.putExtra("TITLE", actTitle1);
//					startActivity(webViewIntent1);
//					break;
				case 4:
					// Video
					Intent videoGallaryIntent = new Intent(getApplicationContext(),
							VideoGallaryActivity.class);
					startActivity(videoGallaryIntent);

					break;
				case 5:
					// Contact
					Intent socialMediaIntent = new Intent(getApplicationContext(),
							SocialMediaActivity.class);
					startActivity(socialMediaIntent);

					break;

				case 6:
					// Constituency
					Intent vidhanIntent = new Intent(getApplicationContext(),
                            VidhanSabhaActivity.class);
					startActivity(vidhanIntent);

					break;
				case 7:

 //Department
                    Intent departmentIntent = new Intent(getApplicationContext(),
                            DepartmentActivity.class);
                    startActivity(departmentIntent);

					break;
				case 8:
					// Contact
                    // Vidhan Sabha
                    Intent contactIntent = new Intent(getApplicationContext(),
                            ContactActivity.class);
                    startActivity(contactIntent);

					break;

					case 9:
//						// Image Gallary
						Intent imageGallaryIntent = new Intent(getApplicationContext(),ImageGallaryActivity.class);
						startActivity(imageGallaryIntent);


//						Intent imageGallaryIntent = new Intent(getApplicationContext(),FullScreenViewActivity.class);
//						startActivity(imageGallaryIntent);

						break;

					case 10:
						//Ongoing project
						Intent ongoingProjectsIntent = new Intent(getApplicationContext(),
								OngoingProjectsActivity.class);
						startActivity(ongoingProjectsIntent);

						break;

				default:
					break;
				}
			}
		});
	} 
	
	
	public void upgradeBtnClicked(View v)
	{  
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id="+getApplicationContext().getPackageName()));
		startActivity(intent);
		dialog.dismiss();
	}  
	public void cancelBtnClicked(View v)
	{  
		dialog.dismiss();
	} 

	private void headerSettings() {
		findViewById(R.id.btnBackHeader).setVisibility(View.GONE);
		findViewById(R.id.btnHomeHeader).setVisibility(View.VISIBLE);	

		TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
		headerTitle.setText(R.string.app_name);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void gotoHome(View v){
		Intent i=new Intent(android.content.Intent.ACTION_SEND);
		i.setType("text/plain");
		i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Shivpal Singh Yadav");
		i.putExtra(android.content.Intent.EXTRA_TEXT, "Download application from "+"http://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
		startActivity(Intent.createChooser(i,"Share via"));
	}

	private class getUpgradeTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params)
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httppost;
			try
			{
				httppost = new HttpGet(Constants.UPGRADE_URL + "?current_version="+Utility.getAppVersion(getApplicationContext()) + "&device_id=" + regid);

//				httppost = new HttpGet(Constants.BUSINESS_TEMP_URL);


				Log.d("DEVICE_ID", httppost.toString());
				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				String content = EntityUtils.toString(response.getEntity());
				return content;
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
 catch (NameNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			// dialog.dismiss();

			JSONObject objJSONObject;
			try {
				objJSONObject = new JSONObject(result);
				System.out.println("objJSONObject = "+objJSONObject.toString());

				boolean isUpgradeRequired = (Boolean) objJSONObject.get("flag");
				if(isUpgradeRequired)
				{
					if(!MainActivity.this.isFinishing())
					{
						dialog = new  Dialog(MainActivity.this);

						//tell the Dialog to use the dialog.xml as it's layout description
						dialog.setContentView(R.layout.custom_dialog);
						dialog.setTitle("Upgrade Message");


						TextView txt = (TextView) dialog.findViewById(R.id.txt);

						txt.setText("A New Version of Application is available.To download click on Upgrade. Thank you.");

						Button upgradeBtn = (Button) dialog.findViewById(R.id.upgradeButton);
						//		 
						upgradeBtn.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								upgradeBtnClicked(v);
							}
						});
						Button cancelBtn = (Button) dialog.findViewById(R.id.cancelButton);
						//		       		 
						cancelBtn.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.cancel();
							}
						});
						//		 
						dialog.show();
					}
				}	
			} catch (JSONException e) 
			{
				//				// TODO Auto-generated catch block
				//				e.printStackTrace();
			}
			//			 

		}


		@Override
		protected void onPreExecute() {
		
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}



    //-------------------------------------


    @Override
    protected void onResume() {
        super.onResume();
        // Check device for Play Services APK.
        checkPlayServices();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Stores the registration ID and the app versionCode in the application's
     * {@code SharedPreferences}.
     *
     * @param context application's context.
     * @param regId registration ID
     */
    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);
//        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + regId);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
//        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    /**
     * Gets the current registration ID for application on GCM service, if there is one.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     *         registration ID.
     */
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGcmPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
//        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
//        int currentVersion = getAppVersion(context);
//        if (registeredVersion != currentVersion) {
//            Log.i(TAG, "App version changed.");
//            return "";
//        }
        return registrationId;
    }

    /**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and the app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;

                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    sendRegistrationIdToBackend();
                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.
                    // Persist the regID - no need to register again.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
            }
        }.execute(null, null, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGcmPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }
    /**
     * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP or CCS to send
     * messages to your app. Not needed for this demo since the device sends upstream messages
     * to a server that echoes back the message using the 'from' address in the message.
     */
    private void sendRegistrationIdToBackend() {
        if(Utility.isNetworkStatusAvialable(getApplicationContext()))
        {
            new getUpgradeTask().execute("updates");
        }
    }
    //--------------------------------
}
