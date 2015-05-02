package com.amp.helper;

import java.util.ArrayList;

import com.twostars.syadav.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DepartmentCellAdapter  extends ArrayAdapter<DepartmentsData>
{
    private final Context context;
    private final  ArrayList<DepartmentsData> values;

    public DepartmentCellAdapter(Context context, ArrayList<DepartmentsData> values) 
    {
        super(context, R.layout.departmentcellview, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
        LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        DepartmentsData deptData = values.get(position);
        View rowView = inflater.inflate(R.layout.departmentcellview, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.departmentTitle);
       // textView.setBackgroundColor(Color.parseColor(deptData.colorCode));
        
     LinearLayout  innerLayoutView = (LinearLayout)rowView.findViewById(R.id.innerLayoutView);
     innerLayoutView.setBackgroundColor(Color.parseColor(deptData.colorCode));
        // Customization to your textView here
        textView.setText(deptData.departmentName);
         return rowView;
    }
}

