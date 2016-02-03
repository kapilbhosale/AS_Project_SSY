package com.amp.helper;

import httpimage.FileSystemPersistence;
import httpimage.HttpImageManager;

import java.util.ArrayList;

import com.twostars.syadav.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class EventCellAdaptor extends ArrayAdapter<EventsData>
{

	final Context context;
	int layoutResourceId;
	final ArrayList<EventsData> data;
	private HttpImageManager mHttpImageManager;
	public static final String BASEDIR = "/sdcard/httpimage";
	
	
	public EventCellAdaptor(Context context, int textViewResourceId,
			ArrayList<EventsData> data) {
		super(context, textViewResourceId, data);
		this.layoutResourceId = textViewResourceId;
		this.context = context;
		this.data = data;
		mHttpImageManager = new HttpImageManager(HttpImageManager.createDefaultMemoryCache(), 
				new FileSystemPersistence(BASEDIR));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) 
		{
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			final EventsDataHolder holder = new EventsDataHolder();
			holder.eventDesc = (WebView) row.findViewById(R.id.eventDesc);
			holder.eventTitle = (TextView) row.findViewById(R.id.eventTitle);
		    holder.image = (ImageView) row.findViewById(R.id.eventImageView);
			row.setTag(holder);
		} else {
			row = convertView;
			((EventsDataHolder) row.getTag()).eventTitle.setTag(data
					.get(position));
			((EventsDataHolder) row.getTag()).eventDesc.setTag(data
					.get(position));

		}


		EventsDataHolder holder = (EventsDataHolder) row.getTag();
		EventsData weather = data.get(position);

		String eventDescTxt = "<html><font size='2'><i><body>"
		+"<p align=\"justify\">"
		+weather.descStr + "</p> " + "</body></i></font></html>";

		holder.eventDesc.setVerticalScrollBarEnabled(false);
		holder.eventDesc.setOnTouchListener(new WebViewClickListener(holder.eventDesc, parent, position));
		holder.eventDesc.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				return true;
			}
		});

				holder.eventDesc.loadData(eventDescTxt, "text/html; charset=UTF-8", null);
//		holder.eventDesc.setText(Html.fromHtml(eventDescTxt));
		holder.eventTitle.setText(weather.title);

		ImageView imageView = holder.image;
		imageView.setImageResource(R.drawable.ic_launcher);

		if(weather.imageURL.toString().length() > 2)
		{
			final Uri uri = Uri.parse( weather.imageURL);
			if(uri != null)
			{
				Bitmap bitmap = mHttpImageManager.loadImage(new HttpImageManager.LoadRequest(uri, imageView));
				if (bitmap != null)
				{
					imageView.setImageBitmap(bitmap);
				}
			}
		}
		else
		{
			imageView.setImageResource(weather.imageId);
		}
		return row;
	}

	static class EventsDataHolder 
	{
		WebView eventDesc;
		TextView eventTitle;
		ImageView image;
		//TextView eventDetails;
		//TextView eventImageURL;
	}

}
