package news.nitesh.com.news;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by nitesh on 12/16/2016.
 */
public class DummyListView extends AppCompatActivity {


    ContactListAdapter contactAdapter;
    ListView dummyList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_dummy);

        dummyList= (ListView) findViewById(R.id.dummyList);

        contactAdapter = new ContactListAdapter(this);
        dummyList.setAdapter(contactAdapter);

    }

    private class ContactListAdapter extends BaseAdapter {

        String[] someNames= {"nitesh","reddy","naren"};

        public ContactListAdapter(Context context) {
            super();
        }

        @Override
        public int getCount() {
            return someNames.length+1; //  length+1==> cuz we have 3 items in "data" but Image was extra added as HEAD,
                                        // total lenth will be 4
        }

        @Override
        public Object getItem(int position) {
            return someNames[position];
        }


        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (position == 0) {

                convertView = getLayoutInflater().inflate(R.layout.listview_dummy_row_image, parent, false);

                ImageView contactImgViewLayout = (ImageView) convertView.findViewById(R.id.dummyImage1);
                contactImgViewLayout.setImageResource(R.drawable.ic_filter);



            } else if (position > 0) {

                if (convertView == null) {

                    convertView = getLayoutInflater().inflate(R.layout.listview_dummy_row_text, parent, false);
                    // for this statement values, "parent, false" are important
                }

                TextView currentName = (TextView) convertView.findViewById(R.id.dummyText);
                currentName.setText(someNames[position-1]);  // we decreased one item, cuz here the position is started with '1'(position>0)
                                                              //  but we need to load the data that starts with "0"

            }

            return convertView;
        }

    }
}
