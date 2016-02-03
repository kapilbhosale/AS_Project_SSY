package com.amp.helper;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twostars.syadav.R;

import java.util.ArrayList;

/**
 * Created by amolpatil on 02/10/15.
 */
public class ViewPagerAdapter extends PagerAdapter
{
    // Declare Variables
    Context context;
    ArrayList<String>imageUrlsData;
    ArrayList<String>imageTitleData;
    ArrayList<Integer>imageIdsData;

    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, ArrayList<String>urlsData,ArrayList<String>titlesData)
    {
        this.context = context;
        this.imageUrlsData = urlsData;
        this.imageTitleData = titlesData;
    }

    public ViewPagerAdapter(Context context, ArrayList<String>urlsData,ArrayList<String>titlesData,ArrayList<Integer>cImageIdsData)
    {
        this.context = context;
        this.imageUrlsData = urlsData;
        this.imageTitleData = titlesData;
        this.imageIdsData = cImageIdsData;
    }

    @Override
    public int getCount() {
        return imageUrlsData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txTitle;
        ImageView imgView;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        txTitle = (TextView) itemView.findViewById(R.id.imageTitle);
        imgView = (ImageView) itemView.findViewById(R.id.imgView);

        String itemURl =imageUrlsData.get(position);
        String itemTitle =imageTitleData.get(position);
        int itemId =0;
        if(imageIdsData != null &&  imageIdsData.size()>position)
        {
            itemId = imageIdsData.get(position);
        }

        // Locate the TextViews in viewpager_item.xmls
        txTitle.setText(itemTitle);
        if(itemURl.toString().length()>2)
        {
            Picasso.with(context).load(itemURl).into(imgView);
        }
        else if(itemId > 0)
        {
            imgView.setImageResource(itemId);
        }

        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}



