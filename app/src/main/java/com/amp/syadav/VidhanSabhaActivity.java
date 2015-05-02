package com.amp.syadav;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amp.helper.DepartmentCellAdapter;
import com.amp.helper.VidhanSabhaCellAdapter;
import com.twostars.syadav.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class VidhanSabhaActivity extends Activity
{
	ListView tableView;
	ArrayList<String>values;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vidhan_sabha);
		headerSettings();
		
	//	values = new ArrayList<String>();
		
		 String[] array = getResources().getStringArray(R.array.VidhanSabhaPartNames);
		 List<String> list = new ArrayList<String>();
		    list = Arrays.asList(array);
		    values = new ArrayList<String>(list);
		 
		 
		tableView = (ListView) findViewById(R.id.vidhanSabhaListView);
		VidhanSabhaCellAdapter adapter  = new VidhanSabhaCellAdapter(this, values);
		tableView.setAdapter(adapter);
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

}
