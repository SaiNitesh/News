package news.nitesh.com.news;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitesh on 11/29/2016.
 */
public class NewsActive extends Activity{

    // This need to be integrated in "AndroidManifest.xml", to get this button on MainActivity

    private List<NewsItem> newsFeed= new ArrayList<NewsItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_news);

        //R.mipmap.ic_launcher==> is an "png" file in mipmap folder
        newsFeed.add(new NewsItem("HTML", "Funny", "12:00 AM", "jan 1 1920", "https://www.html.com", R.mipmap.ic_launcher));
        newsFeed.add(new NewsItem("Java", "Funny", "12:00 AM", "jan 1 1920", "https://www.java.com", R.mipmap.ic_launcher));
        newsFeed.add(new NewsItem("C", "Funny", "12:00 AM", "jan 1 1920", "https://www.c.com", R.mipmap.ic_launcher));
        newsFeed.add(new NewsItem("php", "Funny", "12:00 AM", "jan 1 1920", "https://www.php.com", R.mipmap.ic_launcher));


        ArrayAdapter<NewsItem> adapter=new customAdapter();

        ListView myFirstListView = (ListView) (findViewById(R.id.myNewsView));
        myFirstListView.setAdapter(adapter);

        // To make action when you click of each list, you can use below method, which will will be on fianl LIST-VIEW on screen
        myFirstListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //WebView myListViewbrowser = (WebView) findViewById(R.id.myListViewbrowser);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //NewsItem item = (NewsItem) parent.getItemAtPosition(position);
                //myListViewbrowser.loadUrl("https://www."+item.getName()+".com");
                Toast.makeText(NewsActive.this,"My List View Item",Toast.LENGTH_SHORT).show();
                NewsItem myCurrentNews = newsFeed.get(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(myCurrentNews.getUrl()));
                startActivity(i);
            }
        });


        // Below code is for VOlley,
        //Volley allows us to perform get Request or post request or Json request
        // for this we need to add the dependencey in FloatingActionButtonActivity.gradle
        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queues = Volley.newRequestQueue(NewsActive.this);
                StringRequest myRequest = new StringRequest(Request.Method.GET,
                        "http://google.com",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("myTag", "response:" +response);
                            }
                        }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.i("myTag", "Error:"+error);
                    }
                });

                // below code is to fix VOlley timout(if you have big json data to retrieve it will take more then 5 sec) for 1000 sec
                // Default time out is 5 secs
                myRequest.setRetryPolicy(new DefaultRetryPolicy(1000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                // you can find the result in log with name "myTag" in RegEx: EditFilterConfiguration
                queues.add(myRequest);
            }
        });
    }

    private class customAdapter extends ArrayAdapter<NewsItem>{
        public customAdapter() {
            super(NewsActive.this, R.layout.news_listview, newsFeed);
            //here CustomLViewAdapter.this, not this, this==> refers to customAdapter
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            /*getView method is like "for loop"
            For First time convertView=null, then it gets 1st list, then it returns(return convertView) the first list,
            then the convertView will be null
            After that it starts from here again for 2nd list and it continues*/
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.news_listview, parent,false);
                // for this statement values, "parent, false" are important
            }


            ImageView newsImg = (ImageView) convertView.findViewById(R.id.newsImage);
            TextView newsHeading = (TextView) convertView.findViewById(R.id.newsHeading);
            TextView newsDesc = (TextView) convertView.findViewById(R.id.newsDesc);

            NewsItem myCurrentNews = newsFeed.get(position);
            // for each list of data, the position will start from 0
            // and the position automatically increases after "returning convertView" to 2 and  this continues till the end of the list

            //Instead of below statement, if you want to load images from URL(in JSON file)
            //Picasso.with(NewsActive.this).load(myCurrentNews.getImageURL()).into(newsImg);
            //String-> getImageURL() is method, replaced by getImageId()
            newsImg.setImageResource(myCurrentNews.getImageId());
            newsHeading.setText(myCurrentNews.getNewsHeading());
            newsDesc.setText( myCurrentNews.getNewsDesc() );

            return convertView;
        }

        /*you need to override "getCount" method of the BaseAdapter to return total number of views to diplay.

        public View getView(int position, View convertView, ViewGroup parent)
        position:
        getView going to be called for each position every time it is displayed.

        convertView
        As getView is call many times inflating a new view every time is expensive so list view provides you one of the previously created view to re-use.

        parent
        A reference to the parent view that this view will be a child of.*/
    }

}
