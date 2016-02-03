package com.amp.syadav;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amp.helper.EventsData;
import com.amp.helper.GridViewAdapter;
import com.amp.helper.ImageItem;
import com.amp.helper.Utility;
import com.twostars.syadav.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImageGallaryActivity extends Activity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private ProgressBar mProgressBar;
    private ArrayList<ImageItem> mGridData;
//    private String FEED_URL = "https://dummy-class.herokuapp.com/events_list_trial";
    private String FEED_URL = "https://dummy-class.herokuapp.com/get_images_for_mobile";
            //"http://javatechig.com/?json=get_recent_posts&count=45";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallary);

        gridView = (GridView) findViewById(R.id.gridView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        headerSettings();
        //Initialize with empty data
        mGridData = new ArrayList<ImageItem>();

        gridAdapter = new GridViewAdapter(this, R.layout.grid_image_item_layout, mGridData);
        gridView.setAdapter(gridAdapter);

        //Start download
        if(Utility.isNetworkStatusAvialable(getApplicationContext()))
        {
            new AsyncHttpTask().execute(FEED_URL);
            mProgressBar.setVisibility(View.VISIBLE);
        } else
        {
            Utility.showNetworkConnectionError(this);
        }

        gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                //Create intent
                Intent intent = new Intent(getApplicationContext(), ImageDetailActivity.class);
                ArrayList<String> urlList = new ArrayList<String>();
                ArrayList<String> titleList = new ArrayList<String>();
                for(int i=0 ; i <mGridData.size();i++)
                {
                    ImageItem ite =mGridData.get(i);
                    urlList.add(ite.getImage());
                    titleList.add(ite.getTitle());
                }
                intent.putStringArrayListExtra("ImageUrls", urlList);
                intent.putStringArrayListExtra("ImageTitles", titleList);
                intent.putIntegerArrayListExtra("ImageIds", null);
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }

        });


    }


    //Downloading data asynchronously
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            try {
                // Create Apache HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse = httpclient.execute(new HttpGet(params[0]));
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    String response = streamToString(httpResponse.getEntity().getContent());
                    parseResult(response);
                    result = 1; // Successful
//                    Utility.storeImageUrlsArray(mGridData);
                } else {
                    result = 0; //"Failed
                }
            } catch (Exception e) {
//                Log.d(TAG, e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            if (result == 1) {
                gridAdapter.setGridData(mGridData);
            } else {
                Toast.makeText(ImageGallaryActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
            mProgressBar.setVisibility(View.GONE);
        }
    }

    String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        // Close stream
        if (null != stream) {
            stream.close();
        }
        return result;
    }

    /**
     * Parsing the feed results and get the list
     * @param result
     */
    private void parseResult(String result) throws JSONException {


        /*    JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("posts");
            ImageItem item;
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                String title = post.optString("title");
                item = new ImageItem();
                item.setTitle(title);
                JSONArray attachments = post.getJSONArray("attachments");
                if (null != attachments && attachments.length() > 0)
                {
                    JSONObject attachment = attachments.getJSONObject(0);
                    if (attachment != null)
                        item.setImage(attachment.getString("url"));
                }
                mGridData.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            } */


            JSONObject objJSONObject = new JSONObject(result);
            JSONArray posts = objJSONObject.getJSONArray("images");
            ImageItem item;
            for (int i = 0, count = posts.length(); i < count; i++) {
                try {
                    item = new ImageItem();

                    JSONObject jsonChildNode = posts.getJSONObject(i);

                    /******* Fetch node values **********/
                    item.setTitle(jsonChildNode.optString("title").toString());

                    item.setImage(jsonChildNode.optString("image").toString());


                    mGridData.add(item);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }


//        Utility.storeImageUrlsArray(mGridData);


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_gallary, menu);
        return true;
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
