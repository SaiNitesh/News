package news.nitesh.com.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by nitesh on 1/7/2017.
 */

public class DataFromWebsite extends Activity {
    private ImageButton webImageButton;
    public void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.website_data_from);
        webImageButton = (ImageButton) findViewById(R.id.webImageButton);
        webImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, DataFromWebsiteWebView.class);
                startActivity(intent);
            }
        });
    }
}
