package news.nitesh.com.news;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitesh on 11/30/2016.
 */
public class JsonParse extends AppCompatActivity {

    private List<CountriesBean> cntries= new ArrayList<CountriesBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parse_json);

        RequestQueue queues = Volley.newRequestQueue(JsonParse.this);
        //http://mysafeinfo.com/api/data?list=englishmonarchs&format=json
        //http://codedamn.com/filesCodedamn/news.php
        //https://github.com/SaiNitesh/REST-Web-services/blob/master/RESTfulWS/json_file.json
        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET,
                "https://raw.githubusercontent.com/SaiNitesh/REST-Web-services/master/RESTfulWS/json_file.json",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Log.i("myCountries**", "response:" +response);
                        JSONArray countryItems = response.getJSONArray("countryItems");

                        Log.i("myTagx", "response:" +countryItems);

                            for(int i=0; i<countryItems.length();i++){
                                JSONObject temp= countryItems.getJSONObject(i);

                                String nm = temp.getString("nm");
                                String cty = temp.getString("cty");
                                String hse = temp.getString("hse");
                                String yrs = temp.getString("yrs");

                                cntries.add(new CountriesBean(nm, cty, hse, yrs));
                            }
                        } catch(JSONException e){
                            Log.i("myTag",e.toString());
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.i("myTag", "Error:"+error.toString());
            }

        });
        // below code is to fix VOlley timout(if you have big json data to retrieve it will take more then 5 sec) for 1000 sec
        // Default time out is 5 secs
        myRequest.setRetryPolicy(new DefaultRetryPolicy(1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // you can find the result in log with name "myTag" in RegEx: EditFilterConfiguration
        queues.add(myRequest);

        ArrayAdapter<CountriesBean> adapter=new customAdapter();

        ListView myFirstListView = (ListView) (findViewById(R.id.myCountriesView));
        myFirstListView.setAdapter(adapter);

        // To make action when you click of each list, you can use below method, which will will be on fianl LIST-VIEW on screen
        myFirstListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //WebView myListViewbrowser = (WebView) findViewById(R.id.myListViewbrowser);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //NewsItem item = (NewsItem) parent.getItemAtPosition(position);

                //myListViewbrowser.loadUrl("https://www."+item.getName()+".com");
                Toast.makeText(JsonParse.this,"My List View Item",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private class customAdapter extends ArrayAdapter<CountriesBean>{
        public customAdapter() {
            super(JsonParse.this, R.layout.parse_json_view, cntries);
            //here CustomLViewAdapter.this, not this, this==> refers to customAdapter
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            /*getView method is like "for loop"
            For First time convertView=null, then it gets 1st list, then it returns(return convertView) the first list,
            then the convertView will be null
            After that it starts from here again for 2nd list and it continues*/
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.parse_json_view, parent,false);
                // for this statement values, "parent, false" are important
            }


            TextView countryName = (TextView) convertView.findViewById(R.id.name);
            TextView countryCity = (TextView) convertView.findViewById(R.id.city);
            TextView countryHouse = (TextView) convertView.findViewById(R.id.house);

            CountriesBean myCurrentctries = cntries.get(position);
            // for each list of data, the position will start from 0
            // and the position automatically increases after "returning convertView" to 2 and  this continues till the end of the list

            countryName.setText(myCurrentctries.getNm());
            countryCity.setText(myCurrentctries.getCty());
            countryHouse.setText( myCurrentctries.getHse() );

            //countryURL.setImageResource(myCurrentctries.getImageId());
            // Instead of below statement, if you want to load images from URL(in JSON file)
            //Picasso.with(NewsActive.this).load(myCurrentNews.getImageURL()).into(newsImg);
            //String-> getImageURL() is method, replaced by getImageId()

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
