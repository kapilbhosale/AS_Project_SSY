package com.amp.syadav;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amp.helper.FileDownloader;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class OngoingDetailsActivity extends Activity
{
    private GridView gridView;
    private TextView projectDesc;
    private TextView projectTitle;

    private GridViewAdapter gridAdapter;
    private ProgressBar mProgressBar;
    private ArrayList<ImageItem> mGridData;
//  private String FEED_URL = "https://dummy-class.herokuapp.com/events_list_trial";
    private String FEED_URL = "https://dummy-class.herokuapp.com/get_images_for_mobile";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_details);

        projectTitle = (TextView)findViewById(R.id.projectTitle);
        projectDesc =(TextView)findViewById(R.id.projectDesc);
        gridView = (GridView) findViewById(R.id.gridView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        String projTitleStr =getIntent().getStringExtra("EVENT_TITLE");
        String projDescStr =getIntent().getStringExtra("EVENT_DETAIL");
        String projImgURL =getIntent().getStringExtra("IMAGE_URL");

        headerSettings();
        //Initialize with empty data
        mGridData = new ArrayList<ImageItem>();

        ArrayList<Integer> imageIdsList = new ArrayList<Integer>();
//        Integer[] imageIdsList = new Integer[];

        if(projTitleStr.equalsIgnoreCase(getResources().getString(R.string.title_Bansagar_canal_project)))
        {
            imageIdsList.add(R.drawable.badsagar_1);
            imageIdsList.add(R.drawable.badsagar_2);
            imageIdsList.add(R.drawable.badsagar_3);
            imageIdsList.add(R.drawable.badsagar_4);
            imageIdsList.add(R.drawable.badsagar_5);
            imageIdsList.add(R.drawable.badsagar_6);
            // =new Integer[] {R.drawable.badsagar_01,R.drawable.badsagar_02,R.drawable.badsagar_03};
        }
        else if(projTitleStr.equalsIgnoreCase(getResources().getString(R.string.title_Sarayu_canal_project)))
        {
            imageIdsList.add(R.drawable.saryu_1);
            imageIdsList.add(R.drawable.saryu_2);
            imageIdsList.add(R.drawable.saryu_3);
            imageIdsList.add(R.drawable.saryu_4);
            imageIdsList.add(R.drawable.saryu_5);
            imageIdsList.add(R.drawable.saryu_6);
//            imageIdsList = new Integer[]{R.drawable.badsagar_01,R.drawable.badsagar_02,R.drawable.badsagar_03};
        }
        else if(projTitleStr.equalsIgnoreCase(getResources().getString(R.string.title_Bundelkhand_Irrigation_project)))
        {
            imageIdsList.add(R.drawable.bundelkhand_1);
            imageIdsList.add(R.drawable.bundelkhand_2);
            imageIdsList.add(R.drawable.bundelkhand_3);
            imageIdsList.add(R.drawable.bundelkhand_4);
            imageIdsList.add(R.drawable.bundelkhand_5);
            imageIdsList.add(R.drawable.bundelkhand_6);
//            imageIdsList = new Integer[]{R.drawable.badsagar_01,R.drawable.badsagar_02,R.drawable.badsagar_03};
        }
        for(Integer index = 0 ;index < imageIdsList.size() ;index ++)
        {
            ImageItem item1 = new ImageItem();
            item1.setTitle(projTitleStr);
            item1.setImage("s");
            item1.setImageId(imageIdsList.get(index));
            mGridData.add(item1);
        }
//        ImageItem item1 = new ImageItem();
//        item1.setTitle("Image-1");
//        item1.setImage("s");
//        item1.setImageId(R.drawable.ic_launcher);
//
//        ImageItem item2 = new ImageItem();
//        item2.setTitle("Image-1");
//        item2.setImage("s");
//        item2.setImageId(R.drawable.ic_10);
//
//        ImageItem item3 = new ImageItem();
//        item3.setTitle("Image-3");
//        item3.setImage("s");
//        item3.setImageId(R.drawable.partylogo);
//
//        mGridData.add(item1);
//        mGridData.add(item2);
//        mGridData.add(item3);


        gridAdapter = new GridViewAdapter(this, R.layout.grid_image_item_layout, mGridData);
        gridView.setAdapter(gridAdapter);

        projectTitle.setText(projTitleStr);
        projectDesc.setText(projDescStr);

//        //Start download
//        if(Utility.isNetworkStatusAvialable(getApplicationContext()))
//        {
//            new AsyncHttpTask().execute(FEED_URL);
//            mProgressBar.setVisibility(View.VISIBLE);
//        } else
//        {
//            Utility.showNetworkConnectionError(this);
//        }

        gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                        //Create intent
                        Intent intent = new Intent(getApplicationContext(), ImageDetailActivity.class);
                        ArrayList<String> urlList = new ArrayList<String>();
                        ArrayList<String> titleList = new ArrayList<String>();
                        ArrayList<Integer> imageIdList = new ArrayList<Integer>();
                        for (int i = 0; i < mGridData.size(); i++) {
                            ImageItem ite = mGridData.get(i);
                            urlList.add(ite.getImage());
                            titleList.add(ite.getTitle());
                            imageIdList.add(ite.getImageId());
                        }
                        intent.putStringArrayListExtra("ImageUrls", urlList);
                        intent.putStringArrayListExtra("ImageTitles", titleList);
                        intent.putIntegerArrayListExtra("ImageIds", imageIdList);
                        intent.putExtra("POSITION", position);
                        startActivity(intent);
                    }

                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ongoing_details, menu);
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

    private void headerSettings() {
        findViewById(R.id.btnBackHeader).setVisibility(View.VISIBLE);
        findViewById(R.id.btnHomeHeader).setVisibility(View.GONE);
        TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
        headerTitle.setText(R.string.title_activity_OngoingProjects);
    }
    public void gotoBack(View v){
        finish();
    }


    public void downloadPdfURLClicked(View v)
    {
        new DownloadFile().execute("http://maven.apache.org/maven-1.x/maven.pdf", "maven.pdf");
    }

    private class DownloadFile extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "shivpalpdfdocs");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }
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
                Toast.makeText(OngoingDetailsActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
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
    }

}
