package news.nitesh.com.news;

/**
 * Created by nitesh on 12/5/2016.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;



public class MyNewsListActivity extends AppCompatActivity {

    private MyNewsListAdapter adapter;
    ListView listView;
    EditText searchTx;
    Button sortB;
    SearchView searchView;

    Toolbar toolbar = null;

    public static final String KEY_HEADLINE = "news_headline";
    public static final String KEY_DETAILS = "news_details";
    public static final String KEY_PUBDATE = "news_pub_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_news_list);


        //searchTx =(EditText) findViewById(R.id.searchTx);

        adapter = new MyNewsListAdapter(this, getData());
        listView = (ListView) findViewById(R.id.newsList);
        listView.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sortB = (Button)findViewById(R.id.sortB);
        sortB.setOnClickListener(new View.OnClickListener() {

            int x=0;
            ArrayList<MyNewsItem> sortData = getData();
            @Override
            public void onClick(View v) {
                if (x==0) {
                    Collections.sort(sortData, new StringAscComparator());
                    adapter = new MyNewsListAdapter(MyNewsListActivity.this, sortData);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    x=1;
                } else if(x==1){
                    ArrayList<MyNewsItem> reverseData = sortData;
                    Collections.reverse(reverseData);

                    //Collections.sort(sortData, new StringAscComparator());
                    adapter = new MyNewsListAdapter(MyNewsListActivity.this, reverseData);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    x=0;
                }


                /*  Collections.sort(listName);
                    for ascending order,and

                    Collections.sort(listName, Collections.reverseOrder());
                    for descending order*/
            }


        });



        /*searchTx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals(""))
                    getData();
                else
                searchText(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
*/


        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                MyNewsItem item = (MyNewsItem) adapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), MyNewsDetailsActivity.class);
                intent.putExtra(KEY_HEADLINE, item.getHeadline());
                intent.putExtra(KEY_PUBDATE, item.getPubDate());
                intent.putExtra(KEY_DETAILS, item.getDetails());

                startActivity(intent);
            }
        });
    }

    private void searchText(String textToSearch) {

        ArrayList<MyNewsItem> searchData =getData();
        int i=0;

        for(int j=0;j<searchData.size();j++){
            MyNewsItem list =searchData.get(j);

            if(!list.getHeadline().toLowerCase().trim().contains(textToSearch))
                searchData.remove(j--);

        }

        adapter = new MyNewsListAdapter(MyNewsListActivity.this, searchData);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    // Comparator for Ascending Order
    /*public static Comparator<MyNewsItem> StringAscComparator = new Comparator<MyNewsItem>() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        public int compare(MyNewsItem app1, MyNewsItem app2) {

            Date date1 = null;
            Date date2= null;
            try {
                date1 = formatter.parse(app1.getPubDate());
                date2 = formatter.parse(app2.getPubDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }


                return date1.compareTo(date2);

        }
    };*/

    public class StringAscComparator implements Comparator<MyNewsItem> {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        @Override
        public int compare(MyNewsItem app1, MyNewsItem app2) {
            Date date1 = null;
            Date date2= null;
            try {
                date1 = formatter.parse(app1.getPubDate());
                date2 = formatter.parse(app2.getPubDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }


            return date1.compareTo(date2);
        }
    }

    private ArrayList<MyNewsItem> getData() {
        ArrayList<MyNewsItem> newsList = new ArrayList<MyNewsItem>();
        String[] headlines = getResources().getStringArray(R.array.news_headlines);// array=> added in res/values/arrays.xml
        String[] pubDate = getResources().getStringArray(R.array.news_pubdate);
        String[] details = getResources().getStringArray(R.array.news_details);

        for (int i = 0; i < headlines.length; i++) {
            MyNewsItem item = new MyNewsItem();
            item.setHeadline(headlines[i]);
            item.setPubDate(pubDate[i]);
            item.setDetails(details[i]);
            newsList.add(item);
        }
        return newsList;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id=item.getItemId();
        Log.i("menuId",id+" menu"+R.id.menuSearch);


        if(id==R.id.menuSearch){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    adapter.getFilter().filter(newText);
                    listView.setAdapter(adapter);

                    return false;
                }
            });

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_list_main, menu); //   R.menu==> added in res/menu/main
        MenuItem menuItem=menu.findItem(R.id.menuSearch);
        //menuItem.expandActionView();    // this is ued to expand the searchview without clicking the search icon
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);  // when you click the search icon (collapsed search view)

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE); // opens the search box to enter data (expanded search view)
        SearchableInfo info = searchManager.getSearchableInfo(getComponentName()); // shows the data you entered on searchbox(expanded search view with Text)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        return true;
    }

}