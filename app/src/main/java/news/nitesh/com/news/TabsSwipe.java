package news.nitesh.com.news;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitesh on 12/12/2016.
 */
public class TabsSwipe extends FragmentActivity {

    ViewPager viewPager;
    ActionBar actionBar;
    TabHost tabHost;
    List<Fragment> listFragments;
    TabsAdapter tabsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_tabs);


        tabHost =(TabHost) findViewById(R.id.swipeTabHost);
        tabHost.setup();


        String[] tabNames={"Tab1","Tab2","Tab3","Tab4","Tab5","Tab6"};

        for(int i=0; i< tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec= tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }


        // tabHost Listener
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){

            @Override
            public void onTabChanged(String tabId) {

                int selectedPage = tabHost.getCurrentTab();
                viewPager.setCurrentItem(selectedPage);



                //for scrollView on TABS, Tabs bar scroll when you keep moving to end of the tabs by selecting
                HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.hScrollView);
                View tabView = tabHost.getCurrentTabView();
                int scrollPos = tabView.getLeft() - (hScrollView.getWidth()-tabView.getWidth())/2;
                hScrollView.smoothScrollTo(scrollPos,0);
            }});


        listFragments= new ArrayList<>();
        listFragments.add(new TabsSwipeFragmentA());
        listFragments.add(new TabsSwipeFragmentB());

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(tabsAdapter);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer()); // "ZoomOutPageTransformer" will help you add animations to the "LAYOUTS"


        // ViewPager Listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tabHost.setCurrentTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        onRestart();
        /*actionBar=getActionBar();
        actionBar.setNa*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id== R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }

    public class FakeContent implements TabHost.TabContentFactory{

        Context context;
        public FakeContent(Context context){
            this.context=context;
        }

        @Override
        public View createTabContent(String tag) {
           View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumHeight(0);
            return fakeView;

        }
    }
}
