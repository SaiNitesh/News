package news.nitesh.com.news;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import news.nitesh.com.news.models.MyParcelableBean;

/**
 * Created by nitesh on 12/11/2016.
 */
public class MyParcelable extends Activity {

    List<MyParcelableBean> parcelArray;
    ImageView parcelImage;
    Uri image_Uri;
    Intent intent;
    Bundle bundle;
    Button parcelableB2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parcelable_my);

        parcelImage = (ImageView) findViewById(R.id.parcelImage);



        //savedInstance= new Bundle();

        bundle = new Bundle();

        parcelableB2 = (Button) findViewById(R.id.parcelableB2);

        parcelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this helps you you to upload image from sources(gallery, downloads, etc)
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select contact image"), 1);

            }
        });

        parcelableB2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for(int i=0;i<parcelArray.size();i++)
                {

                    // newList.add(parcelArray.get(i));
                    Log.i("imgLog",parcelArray.get(0).getImage().toString());
                }

                intent=new Intent(MyParcelable.this, MyParcelableSecond.class);
                Log.i("logName:",parcelArray.get(0).getName());
                //intent.putExtra("newList:", (Parcelable) parcelArray);
                intent.putParcelableArrayListExtra("parcelArray", (ArrayList<MyParcelableBean>) parcelArray);
                startActivity(intent);


               /* bundle = new Bundle();
                //Add your data from getFactualResults method to bundle
                bundle.putParcelableArrayList("bundleData",  parcelArray);
                //Add the bundle to the intent
                intent.putExtras(bundle);
                startActivity(intent);*/

            }
        });

    }

    public void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == RESULT_OK) {
            if (reqCode == 1)
                image_Uri = data.getData();
            // getting image URI when you upload(above statement)  and then setting it in view in first tab(below stmt)
            parcelImage.setImageURI(data.getData());
            Log.i("imageChek",data.getData().toString());
        }

         getDataFromUser();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("finalData", (ArrayList<? extends Parcelable>) parcelArray);
    }


    private List<MyParcelableBean> getDataFromUser(){

        parcelArray = new ArrayList<MyParcelableBean>();
        parcelArray.add(new MyParcelableBean(1,"nitesh",image_Uri));
        parcelArray.add(new MyParcelableBean(2,"naren",image_Uri));
        parcelArray.add(new MyParcelableBean(3,"appu",image_Uri));

        return parcelArray;
    }

}
