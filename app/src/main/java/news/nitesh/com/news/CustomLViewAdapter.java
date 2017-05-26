package news.nitesh.com.news;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitesh on 11/29/2016.
 */
public class CustomLViewAdapter extends Activity {

    // the button to customLViewAdapater is in "MainActivity"

    private List<learnLanguage> availLang= new ArrayList<learnLanguage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_custom_listview);

        //R.mipmap.ic_launcher==> is an "png" file in mipmap folder
        availLang.add(new learnLanguage("Html", R.mipmap.ic_launcher, 40));
        availLang.add(new learnLanguage("c++", R.mipmap.ic_launcher, 25));
        availLang.add(new learnLanguage("java", R.mipmap.ic_launcher, 10));
        availLang.add(new learnLanguage("c", R.mipmap.ic_launcher, 30));

        ArrayAdapter<learnLanguage> adapter=new customAdapter();

        ListView myFirstListView = (ListView) (findViewById(R.id.myFirstLView));
        myFirstListView.setAdapter(adapter);

        // To make action when you click of each list, you can use below method, which will will be on fianl LIST-VIEW on screen
        myFirstListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            WebView myListViewbrowser = (WebView) findViewById(R.id.myListViewbrowser);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                learnLanguage item = (learnLanguage) parent.getItemAtPosition(position);
                //learnLanguage myCurrentLang = availLang.get(position);
                /*if (item.getName()=="java"){
                    myListViewbrowser.setVisibility(view.VISIBLE);
                    myListViewbrowser.refreshDrawableState();
                    myListViewbrowser.loadUrl("https://www."+item.getName()+".com");
                }*/
                Log.i("myLang",item.getName());
                myListViewbrowser.loadUrl("https://www."+item.getName()+".com");
                Toast.makeText(CustomLViewAdapter.this,"My List View Item",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class customAdapter extends ArrayAdapter<learnLanguage>{
        public customAdapter() {
            super(CustomLViewAdapter.this, R.layout.my_layout, availLang);
            //here CustomLViewAdapter.this, not this, this==> refers to customAdapter
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            /*getView method is like "for loop"
            For First time convertView=null, then it gets 1st list, then it returns(return convertView) the first list,
            then the convertView will be null
            After that it starts from here again for 2nd list and it continues*/
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.my_layout, parent,false);
                // for this statement values, "parent, false" are important
            }


            ImageView myImg = (ImageView) convertView.findViewById(R.id.imageView);
            TextView myHeading = (TextView) convertView.findViewById(R.id.heading);
            TextView myDesc = (TextView) convertView.findViewById(R.id.desc);

            learnLanguage myCurrentLang = availLang.get(position);
            // for each list of data, the position will start from 0
            // and the position automatically increases after "returning convertView" to 2 and  this continues till the end of the list

            myImg.setImageResource(myCurrentLang.getImageId());
            myHeading.setText(myCurrentLang.getName());
            myDesc.setText( String.valueOf(myCurrentLang.getTutrialNumber()) );

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
