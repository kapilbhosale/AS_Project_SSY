package com.amp.helper;

import java.util.ArrayList;

import com.twostars.syadav.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class VidhanSabhaCellAdapter extends ArrayAdapter<String>
{
	 private final Context context;
	 private final  ArrayList<String> values;
	 int layoutResourceId;

	  public VidhanSabhaCellAdapter(Context context, ArrayList<String> values) 
	    {
	        super(context, R.layout.vidhan_sabha_cellview, values);
	        this.context = context;
	        this.values = values;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
	        LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        
	        String areaName = values.get(position);
	        View rowView = inflater.inflate(R.layout.vidhan_sabha_cellview, parent, false);
	        TextView areaSeqNum = (TextView) rowView.findViewById(R.id.areaSeqNum);
	        TextView areaNamea = (TextView) rowView.findViewById(R.id.areaName);
//	        Typeface font = Typeface.createFromAsset(context.getAssets(), "font/K010.TTF");
//	        areaNamea. setTypeface(font);

	        
	        areaSeqNum.setText(Integer.toString((position+1)));
	        areaNamea.setText(areaName);
	        
	         return rowView;
	    }


}
