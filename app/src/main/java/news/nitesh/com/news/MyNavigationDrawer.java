package news.nitesh.com.news;

/**
 * Created by nitesh on 12/7/2016.
 */


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyNavigationDrawer extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerListener;

    private MyAdapter myAdapter;
    Toolbar toolbar = null;
    NavigationView nvDrawer;
    FragmentManager fragmentManager=getSupportFragmentManager();
    FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();

    FragmentActivity activity;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_my_nav);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.drawerList);
        frameLayout=(FrameLayout) findViewById(R.id.mainContent);

        myAdapter= new MyAdapter(this);
        //listView.setMinimumWidth((listView.getWidth()*3)/4);

        /*ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.width = params.width/2;
        listView.setLayoutParams(params);
        listView.requestLayout();*/

       /* DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        int newWidth=width/2;

        listView.setLayoutParams(new DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT,newWidth));*/

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();

        display.getSize(size);
        int width = size.x;    // width = display.getWidth();    for LOWER versions
        listView.getLayoutParams().width=(width*3)/4;


        listView.setAdapter(myAdapter);
        listView.setFocusable(true);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        //listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,planets));
         // "android.R.layout.simple_list_item_1" is default list_item
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // before using the statement ,make sure you changes the AppTheme=NoActionBar in styles.sml

        drawerListener = new ActionBarDrawerToggle(MyNavigationDrawer.this, drawerLayout,
                toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                float xPositionOpenDrawer = listView.getWidth();
                float xPositionWindowContent = (slideOffset * xPositionOpenDrawer);
                Log.i("slide","offset:"+String.valueOf(slideOffset)+" xPositionOpenDrawer:"+xPositionOpenDrawer+" xPositionWindowContent:"+xPositionWindowContent);
                frameLayout.setX(xPositionWindowContent);
                toolbar.setX(xPositionWindowContent);

                int x=Math.abs(frameLayout.getWidth()/2);
                frameLayout.setMinimumWidth(x);
                toolbar.setMinimumWidth(x);
            }

        };

        drawerLayout.addDrawerListener(drawerListener); // drawerLayout receives the events of the "drawerListener"

        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true); // enables the top title space to click(at "news")

        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        //getSupportActionBar().setCustomView(R.layout.abs_layout); // if you want to display action bar in custom changes
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); // shows up arrow symbol for home page navigation


        loadSelection(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(MyNavigationDrawer.this, " was selected ", Toast.LENGTH_SHORT).show();
                if(position>0){
                    selectItem(position-1);   //1) when item was clicked this method will be called
                                             //2) In this method the position will start with  0(header) but we need to start with
                                            //    1(data) for onClick on data
                    loadSelection(position);


                    drawerLayout.closeDrawer(listView);

                }

            }

            public void selectItem(int position) {

                listView.setItemChecked(position, true); // making sure particular position was clicked
                setTitle(myAdapter.getItem(position).toString());  // here title will be changes with the position clicked

            }
            public void setTitle(String title){

                getSupportActionBar().setTitle(title); // here the selected item will be displayed on menu bar
                Log.i("title:",title);

            }
        });


    }


   /* public View getActionBarView() {

        Window window = this.getWindow();
        final View decorView = window.getDecorView();
        final String packageName =  "news.nitesh.com.news";
        final int resId = this.getResources().getIdentifier("action_bar_container", "id", packageName);
        final View actionBarView = decorView.findViewById(resId);
        return actionBarView;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        /*switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }*/

        int id=item.getItemId();

        if (drawerListener.onOptionsItemSelected(item)) {
            return true;
        }

        if(id==R.id.navigate){
            startActivity(new Intent(this,SpinnerActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // this method helps the drawer configuration, like screen orientation changes, the drawer will adjust based in orientation
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // this method will help in handling the item click in the menu, by default it was not enabled
        if(drawerListener.onOptionsItemSelected(item))
            return true; // this "if" condition will enable the thing i.e, if you click on title part then drawer will open
        return super.onOptionsItemSelected(item);

    }*/

    private void loadSelection(int i){
        listView.setItemChecked(i,true); // by default first listView is selected if you get i value=0
        Fragment fragment = null;
        Class fragmentClass = MyNavFirstFragment.class;
        switch(i){
            case 0:
                // "mainContent" is the fragment ID, which we are replacing with the below class firstFragement.java contains view fragment_view

                fragmentClass = MyNavFirstFragment.class;
                //MyNavFirstFragment firstFragment = new MyNavFirstFragment();
                /*fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContent,firstFragment );
                fragmentTransaction.commit();*/

                break;
            case 1:
                fragmentClass = MyNavSecondFragment.class;
                //MyNavSecondFragment secondFragment = new MyNavSecondFragment();
                /*fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContent,secondFragment );
                fragmentTransaction.commit();*/
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                fragmentClass = MyNavFirstFragment.class;


        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainContent, fragment).commit();



    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState(); // this lets what the user see the drawer, i.e the (the horizontal bars)

    }

    class MyAdapter extends BaseAdapter{

        private Context context;
        String[] socialSites;
        int[] images= {R.drawable.ic_fb,R.drawable.ic_twitter,R.drawable.ic_google,R.drawable.ic_youtube};

        public MyAdapter(Context context) {
            // context is to get the resources
            this.context=context;
            this.socialSites = context.getResources().getStringArray(R.array.social);
        }

        @Override
        public int getCount() {
            return socialSites.length+1;  //  length+1==> cuz we have 4 items in "data" but Image was extra added as HEAD,
                                          // total lenth will be 5
        }

        @Override
        public Object getItem(int position) {
            return socialSites[position]; // we dont need to decrease, cuz we need 4 items
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;

            int x=listView.getWidth();
            listView.setMinimumWidth((x*1)/4);

            if (position == 0) {


                convertView = getLayoutInflater().inflate(R.layout.drawer_nav_header_main, parent, false);

                CircleImageView profile_image = (CircleImageView) convertView.findViewById(R.id.profile_image);
                //ImageView imageView = (ImageView) convertView.findViewById(R.id.profile_image);
                TextView headerTextView1 = (TextView)convertView.findViewById(R.id.headerTextView1); // header Text's
                TextView headerTextView2 = (TextView) convertView.findViewById(R.id.headerTextView2);

                profile_image.setImageResource(R.drawable.ic_twitter);
                Log.i("headerT",headerTextView1.getText().toString());
                headerTextView1.setText("Nitesh");
                headerTextView2.setText("CEO of SNReSolutions");

                row=convertView;



            } else if (position > 0) {


                if (convertView == null) { // i.e if it you creating first time of first element
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService
                            (context.LAYOUT_INFLATER_SERVICE);

                    row = inflater.inflate(R.layout.drawer_my_nav_row, parent, false);

                } else {
                    row = convertView;

                }
                TextView navText = (TextView) row.findViewById(R.id.navText);
                ImageView navImage = (ImageView) row.findViewById(R.id.navImage);

                navText.setText(socialSites[position-1]); // we decreased one item, cuz here the position is
                                                          // started with '1'(position>0)
                                                          //  but we need to load the data that starts with "0"
                navImage.setImageResource(images[position-1]);




            }

            return row;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_main, menu);
        return true;
    }

   /* @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
*/
}

