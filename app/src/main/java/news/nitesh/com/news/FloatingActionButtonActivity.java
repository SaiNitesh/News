package news.nitesh.com.news;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.utils.L;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * Created by nitesh on 12/10/2016.
 */
public class FloatingActionButtonActivity extends AppCompatActivity{



    ImageView floatingImage;
    ImageView subSortImage;
    ImageView subFilterImage;
    ImageView subFilterClearImage;

    private static final String TAG_SORT_DATA = "sortData";
    private static final String TAG_FILTER_DATA = "filterData";
    private static final String TAG_CLEAR_FILTER = "clearFilter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_button_floating);

        floatingImage= new ImageView(this); // REMEMBER**: No need to create ImageView in action_button_floating
        floatingImage.setImageResource(R.drawable.ic_options); // you can also set this using other XML(selector) file(action_button_flot_selctor.xml)

        // the below statement need to be in onCreate Only
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(floatingImage)
                .setBackgroundDrawable(R.drawable.action_button_flot_selctor) //other way of setting immage to button
                .build();


        subSortImage= new ImageView(this);
        subSortImage.setImageResource(R.drawable.ic_sorting);

        subFilterImage= new ImageView(this);
        subFilterImage.setImageResource(R.drawable.ic_filter);

        subFilterClearImage= new ImageView(this);
        subFilterClearImage.setImageResource(R.drawable.ic_filter_clear);


        // For sub floating action button's
        SubActionButton.Builder subSortButton = new SubActionButton.Builder(this);
        SubActionButton.Builder subFilterButton = new SubActionButton.Builder(this);
        SubActionButton.Builder subFilterClearButton = new SubActionButton.Builder(this);

        // for images in Sub floating buttons
        SubActionButton button1 = subSortButton.setContentView(subSortImage).build();
        SubActionButton button2 = subFilterButton.setContentView(subFilterImage).build();
        SubActionButton button3 = subFilterClearButton.setContentView(subFilterClearImage).build();
        // seeting Tag for buttons, you can use ID instead of TAG
        button1.setTag(TAG_SORT_DATA);
        button2.setTag(TAG_FILTER_DATA);
        button3.setTag(TAG_CLEAR_FILTER);
        //Below buttons are for the "common" OnClickListener,  not the "individual"
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);



        // adding button with image to MAIN BUTTON
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(actionButton)
                .build();

    }

    private View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getTag().equals(TAG_SORT_DATA))
                Toast.makeText(FloatingActionButtonActivity.this, "you selected sort", Toast.LENGTH_SHORT).show();
            /* example code(not correct one)
            SomeAdaptr adapter= new SomeAdapter()
            * Fragment fragment=adapter.getItem(viewPager.currentItem()); // if you want to check whether the methos is called, use this,
            *                                                             //Fragment fragment=(Fragment)adapter.instantiateItem(viewPager.viewPager.currentItem())
              * if(fragment instanceOf SortListener)      // SortListener is an interface contains 3 methds(onSort,onFilter,clear) and each fragment implements this interface
              *     ((SortListener) fragment).onSort();
              * */
            if(v.getTag().equals(TAG_FILTER_DATA))
                Toast.makeText(FloatingActionButtonActivity.this, "you selected filter", Toast.LENGTH_SHORT).show();
            if(v.getTag().equals(TAG_CLEAR_FILTER))
                Toast.makeText(FloatingActionButtonActivity.this, "you selected clear filter", Toast.LENGTH_SHORT).show();

        }
    };



}
