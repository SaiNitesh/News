package news.nitesh.com.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by nitesh on 12/13/2016.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragments;

    public TabsAdapter(FragmentManager fm, List<Fragment> listFragments) {
        super(fm);
        this.listFragments=listFragments;
    }

    @Override
    public Fragment getItem(int position) {
        /*Fragment fragment=null;
        //Class fragmentClass = TabsSwipeFragmentA.class;
        switch(position){
        case 0:
            fragment =  new TabsSwipeFragmentA();
            break;

        case 1:
             fragment =  new TabsSwipeFragmentB();
             break;

        default:
            fragment =  new TabsSwipeFragmentA();
            break;
        }

        *//*Fragment fragment= new TabsSwipeFragmentA();
        Bundle bundle= new Bundle();
        bundle.putInt("count",position+1);
        fragment.setArguments(bundle);

        return fragment;*//*
        return fragment;
*/
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
