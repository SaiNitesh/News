package news.nitesh.com.news;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by nitesh on 1/7/2017.
 */
public class DataFromWebsiteWebView extends Activity {
    private WebView dataWebView1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_from_website);

        dataWebView1 = (WebView) findViewById(R.id.dataWebView1);
        dataWebView1.getSettings().setJavaScriptEnabled(true);
        dataWebView1.loadUrl("https://www.uscis.gov/working-united-states/students-and-exchange-visitors/students-and-employment/stem-opt");
    }
}

