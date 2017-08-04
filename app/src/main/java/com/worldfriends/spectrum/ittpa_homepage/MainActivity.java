package com.worldfriends.spectrum.ittpa_homepage;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    /*
    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://thanhhoatourism.gov.vn");
        myWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed()
    {
        if (myWebView.canGoBack())
        {
            myWebView.goBack();;
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
    */

    private TextView discoveryBoardTitle = null;
    private ImageView[] discoveryBoardImages = new ImageView[6];
    //private ArrayList<ImageView> discoveryBoardImages = new ArrayList<ImageView>();
    private TextView[] discoveryBoardTexts = new TextView[6];


    //Get the internet permission
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionRequester.Builder request = new PermissionRequester.Builder(this);
        request.create().request(Manifest.permission.INTERNET, 10000, new PermissionRequester.OnClickDenyButtonListener() {
            @Override
            public void onClick(Activity activity) {
                Toast.makeText(activity, "인터넷 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
        });

        //ID value initializing
        discoveryBoardTitle = (TextView)findViewById(R.id.discoveryBoardTitle);

        for(int i =0; i < 6; i++)
        {
            String imageID = "discoveryBoardImage" + i;
            String textID = "discoveryBoardText" + i;
            //String TextResID = getResources().getString();
            int TextResID = getResources().getIdentifier(textID, "id", getPackageName());
            int ImageResID =  getResources().getIdentifier(imageID, "id", getPackageName());
            discoveryBoardTexts[i] = (TextView) findViewById(TextResID);
            //discoveryBoardTexts.add((TextView)findViewById(TextResID));
            discoveryBoardImages[i] = (ImageView)findViewById(ImageResID);
            //discoveryBoardImages.add((ImageView)findViewById(ImageResID));
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        GetDiscoveryBoardContentsText task = new GetDiscoveryBoardContentsText();
        task.execute();
    }

    private class GetDiscoveryBoardContentsText extends AsyncTask<Void, Void, Map<String, String>>
    {

        @Override
        protected Map<String, String> doInBackground(Void... params)
        {
            Map<String, String> result = new HashMap<String, String>();
            try
            {
                Document document = Jsoup.connect("http://thanhhoatourism.gov.vn").get();
                Elements elements = document.select("div .topkhampha div");
                result.put("discoveryBoardTitle", elements.text());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Map<String, String> map)
        {
            String discoveryBoardImages[] = {"vanhoa-lehoi.jpg", "bien.jpg", "langnghe.jpg", "sinhthai.jpg", "dacsan.jpg", "Toan-canh-Nghi-S%C6%A1n.jpg"};
            discoveryBoardTitle.setText(map.get("discoveryBoardTitle"));

            for( int i = 0; i < 6; i++) {
                GetDiscoveryBoardContentsIamges task1 = new GetDiscoveryBoardContentsIamges();
                task1.execute(discoveryBoardImages[i], "discoveryBoardImage" + i);
            }
        }
    }

    private class GetDiscoveryBoardContentsIamges extends AsyncTask<String, Void, Bitmap>
    {
        private String imageType = null;
        private String testParam = null;
        Bitmap bitmap = null;

        @Override
        protected Bitmap doInBackground(String... params) {
            imageType = params[1];
            testParam = params[0];

            try
            {


                URL url = new URL("http://thanhhoatourism.gov.vn/ImageMenu/" + params[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                //conn.setDoOutput(true);

                conn.connect();

                bitmap = BitmapFactory.decodeStream(conn.getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected  void onPostExecute(Bitmap bitmap)
        {
           if(imageType.equals("discoveryBoardImage0"))
           {
               discoveryBoardImages[0].setImageBitmap(bitmap);
           }
           else if(imageType.equals("discoveryBoardImage1"))
           {
               discoveryBoardImages[1].setImageBitmap(bitmap);
           }
           else if(imageType.equals("discoveryBoardImage2"))
           {
               discoveryBoardImages[2].setImageBitmap(bitmap);
           }
           else if(imageType.equals("discoveryBoardImage3"))
           {
               discoveryBoardImages[3].setImageBitmap(bitmap);
           }
           else if(imageType.equals("discoveryBoardImage4"))
           {
               discoveryBoardImages[4].setImageBitmap(bitmap);
           }
           else if(imageType.equals("discoveryBoardImage5"))
           {
               discoveryBoardImages[5].setImageBitmap(bitmap);
           }
        }
    }
}