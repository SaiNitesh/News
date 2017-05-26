package news.nitesh.com.news;

/**
 * Created by nitesh on 12/5/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import news.nitesh.com.news.R;
import static news.nitesh.com.news.MyNewsListActivity.KEY_HEADLINE;
import static news.nitesh.com.news.MyNewsListActivity.KEY_PUBDATE;
import static news.nitesh.com.news.MyNewsListActivity.KEY_DETAILS;

public class MyNewsDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_news_details);

        String headline = "";
        String details = "";
        String pubDate = "";

        Intent intent = getIntent();
        if (null != intent) {
            headline = intent.getStringExtra(KEY_HEADLINE);
            details = intent.getStringExtra(KEY_DETAILS);
            pubDate = intent.getStringExtra(KEY_PUBDATE);
        }

        TextView headlineTxt = (TextView) findViewById(R.id.headlines);
        headlineTxt.setText(headline);

        TextView pubdateTxt = (TextView) findViewById(R.id.pub_date);
        pubdateTxt.setText(pubDate);

        TextView descriptionTxt = (TextView) findViewById(R.id.description);
        descriptionTxt.setText(details);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);  //  R.menu==> added in res/menu
        return true;
    }
}
