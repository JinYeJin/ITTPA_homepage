package com.worldfriends.spectrum.ittpa_homepage;

import android.Manifest;
import android.app.Activity;
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

import java.io.IOException;
import java.util.ArrayList;
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

    private TextView discoveryBoardTitle;
    private ArrayList<ImageView> discoveryBoardImages;
    private ArrayList<TextView> discoveryBoardTexts;


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

        discoveryBoardTitle = (TextView)findViewById(R.id.discoveryBoardTitle);

        //ID value initializing
        for(int i =0; i < 6; i++)
        {
            String imageID = "R.id.discoveryBoardImage" + i;
            String textID = "R.id.discoveryBoardText" + i;
            int TextResID = getResources().getIdentifier(textID, "id", this.getPackageName());
            int ImageResID =  getResources().getIdentifier(imageID, "id", this.getPackageName());
            //discoveryBoardImages[i] =(ImageView)findViewById(resID);
            discoveryBoardTexts.add((TextView)findViewById(TextResID));
            discoveryBoardImages.add((ImageView)findViewById(ImageResID));
        }
    }

    private class GetDiscoveryBoardContentsTitle extends AsyncTask<Void, Void, Map<String, String>>
    {

        @Override
        protected Map<String, String> doInBackground(Void... params) {
            try
            {
                Document document = Jsoup.connect("http://thanhhoatourism.gov.vn").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}