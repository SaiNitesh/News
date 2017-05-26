package news.nitesh.com.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import news.nitesh.com.news.models.MyParcelableBean;

/**
 * Created by nitesh on 12/11/2016.
 */
public class MyParcelableSecond extends Activity{

    MyParcelableAdapter adapter;

    ListView ParceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parcelable_my_second);

        ParceView=(ListView) findViewById(R.id.ParceView);

        Intent intent= getIntent();
        //Bundle bundle= new Bundle();


        ArrayList<MyParcelableBean> finalArray = intent.getParcelableArrayListExtra("parcelArray");

        adapter= new MyParcelableAdapter(this, finalArray);
        ParceView.setAdapter(adapter); // need to extend "BaseAdapter" in MyParcelableAdapter



        Log.i("parcelable",finalArray.get(0).getName());

        for(int i=0; i<finalArray.size();i++){

            Log.i("parcelable",finalArray.get(i).toString());

        }


        ParceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        /* ArrayList<MyParcelableBean> finalbundle = getIntent().getParcelableArrayListExtra("bundleData");

//Extract the data…
        //String venName = bundle.getString("VENUE_NAME");

        Log.i("bundle",finalbundle.toString());*/


    }

}
