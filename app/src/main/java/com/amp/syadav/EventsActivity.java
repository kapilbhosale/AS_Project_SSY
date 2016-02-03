package com.amp.syadav;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.amp.helper.*;
import com.twostars.syadav.R;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class EventsActivity extends Activity 
{
	ArrayList<EventsData> values;
	EventCellAdaptor adap;
	ListView eventsListView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		headerSettings();


		if(Utility.isNetworkStatusAvialable(getApplicationContext()))
		{
		  new getEventsDataTask().execute("updates");
		} else
		{
			Utility.showNetworkConnectionError(this);
		}
		
		values = new ArrayList<EventsData>();
		
//		EventsData event1 = new EventsData("This is first event", "28 feb 2015");
//		EventsData event2 = new EventsData("This is Second event for Application", "2 march 2015");
//		values.add(event1);
//		values.add(event2);
		
		eventsListView = (ListView) findViewById(R.id.eventsListView);
		adap = new EventCellAdaptor(this,R.layout.customcellview, values);
		eventsListView.setAdapter(adap);
		
		eventsListView.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					int myIntent, long myLng) {
				EventsData selectedFromList = (EventsData) (eventsListView
						.getItemAtPosition(myIntent));

				
					String imageName = selectedFromList.imageURL;
					String detail = selectedFromList.detail;
					String title = selectedFromList.title;
					
					System.out.println("detail" +detail);
					Intent eventDetailIntent = new Intent(getApplicationContext(),
							EventDetailActivity.class);
					
					eventDetailIntent.putExtra("EVENT_DETAIL", detail);
					eventDetailIntent.putExtra("EVENT_TITLE", title);
					eventDetailIntent.putExtra("IMAGE_URL", imageName);
					
					startActivity(eventDetailIntent);
				
			}
		});
		
	}
	private void headerSettings() {
		findViewById(R.id.btnBackHeader).setVisibility(View.VISIBLE);
		findViewById(R.id.btnHomeHeader).setVisibility(View.GONE);	
		TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
		headerTitle.setText(R.string.title_activity_events);
	}
	public void gotoBack(View v){
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.events, menu);
		return true;
	}
	public void refreshEventsUI(String results) throws JSONException 
	{
	
	ArrayList<EventsData> respSchData = getEventsDataList(results);
	this.values.addAll(respSchData);
	adap.notifyDataSetChanged();
	}
	
	private class getEventsDataTask extends AsyncTask<String, Void, String> {
		  ProgressDialog dialog;
    @Override
    protected String doInBackground(String... params)
    {
    	HttpClient httpclient = new DefaultHttpClient();
    	
    	
        HttpGet httppost = new HttpGet(getResources().getString(R.string.GET_EVENTS_DATA_URL));
        try 
        {
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
          
            String content = EntityUtils.toString(response.getEntity());
          
            return content;
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) 
    {
    	 dialog.dismiss();
   
				try {
					refreshEventsUI(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
    	
    }

  
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(EventsActivity.this);
        dialog.setMessage("Please Wait..");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }
}
	 //Following method will parse  Schedule Data
	 ArrayList<EventsData> getEventsDataList(String result) throws JSONException
	 {
		 ArrayList<EventsData> toReturnList = new ArrayList<EventsData>();
		 JSONObject objJSONObject  =  new JSONObject(result);
		 JSONArray jsonObjectArr =  objJSONObject.getJSONArray("events");
	       
		for(int i = 0, count = jsonObjectArr.length(); i< count; i++)
			{
			    try 
			    {
			    	EventsData tempSchData =new EventsData();
			    	JSONObject jsonChildNode = jsonObjectArr.getJSONObject(i);
                 
                 /******* Fetch node values **********/
			    	tempSchData.descStr  = jsonChildNode.optString("desc").toString();
			    	tempSchData.title= jsonChildNode.optString("title").toString();
			    	tempSchData.detail= jsonChildNode.optString("details").toString();
			    	tempSchData.imageURL= jsonChildNode.optString("image").toString();
                 toReturnList.add(tempSchData);
			    }
			    catch (JSONException e) {
			        e.printStackTrace();
			    }
			}
		 return toReturnList;
	 }
}
