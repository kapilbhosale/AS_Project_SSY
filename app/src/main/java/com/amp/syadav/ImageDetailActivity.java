package com.amp.syadav;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amp.helper.ImageItem;
import com.amp.helper.ViewPagerAdapter;
import com.twostars.syadav.R;

import java.util.ArrayList;

public class ImageDetailActivity extends Activity
{
    private TextView titleTextView;
    private ImageView imageView;

    ArrayList<ImageItem> gridData;
    ArrayList<String> imageUrls;
    ArrayList<String> imageTitles;
    ArrayList<Integer> imageIds;
    ViewPager viewPager;
    PagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        headerSettings();
         imageUrls = getIntent().getStringArrayListExtra("ImageUrls");
        imageTitles = getIntent().getStringArrayListExtra("ImageTitles");
        imageIds = getIntent().getIntegerArrayListExtra("ImageIds");

        int intSelectedIndex = getIntent().getIntExtra("POSITION", 0);

        gridData =new ArrayList<ImageItem>();
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(this, imageUrls,imageTitles,imageIds);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(intSelectedIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_detail, menu);
        return true;
    }


    private void headerSettings()
    {
        findViewById(R.id.btnBackHeader).setVisibility(View.VISIBLE);
        findViewById(R.id.btnHomeHeader).setVisibility(View.GONE);
        TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
        headerTitle.setText(R.string.title_activity_ImageGallary);
    }
    public void gotoBack(View v){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
