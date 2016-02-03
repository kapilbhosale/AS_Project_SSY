package com.amp.syadav;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.amp.helper.DepartmentCellAdapter;
import com.amp.helper.ExpandableListVidhanSabhaAdapter;
import com.amp.helper.VidhanSabhaCellAdapter;
import com.twostars.syadav.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;


import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class VidhanSabhaActivity extends Activity
{
	ExpandableListView tableView;
	ArrayList<String>values;
	HashMap<String, List<String>> listDataChild;
	private int lastExpandedPosition = -1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vidhan_sabha);
		headerSettings();

		prepareListData();
		 
		tableView = (ExpandableListView) findViewById(R.id.vidhanSabhaListView);
		ExpandableListVidhanSabhaAdapter adapter  = new ExpandableListVidhanSabhaAdapter(this, values, listDataChild);
		tableView.setAdapter(adapter);
	  tableView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
		  @Override
		  public void onGroupExpand(int groupPosition) {
			  if (lastExpandedPosition != -1
					  && groupPosition != lastExpandedPosition) {
				  tableView.collapseGroup(lastExpandedPosition);
			  }
			  lastExpandedPosition = groupPosition;
		  }
	  });


	}
	private void headerSettings() {
		findViewById(R.id.btnBackHeader).setVisibility(View.VISIBLE);
		findViewById(R.id.btnHomeHeader).setVisibility(View.GONE);	
		TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
		headerTitle.setText(R.string.title_activity_vidhan_sabha);
	}
	public void gotoBack(View v){
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vidhan_sabha, menu);
		return true;
	}
	private void prepareListData()
	{
		String[] array = getResources().getStringArray(R.array.VidhanSabhaPartNames);

		List<String> list = new ArrayList<String>();
		list = Arrays.asList(array);

		values = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		for(String str : array ) {

			String[] tokens = str.split(",");

			List<String> userDetail = new ArrayList<String>();
			if (tokens.length > 1)
			{
				userDetail.add(tokens[1]);
				if(tokens.length >2)
				userDetail.add(tokens[2]);
				if(tokens.length >3)
				userDetail.add(tokens[3]);

			}
			listDataChild.put(tokens[0], userDetail);
			values.add(tokens[0]);
		}
	}


}
