package news.nitesh.com.news;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nitesh on 12/12/2016.
 */
public class TabsSwipeFragmentA extends Fragment {
    public TabsSwipeFragmentA(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.swipe_tabs_fragment_a, container, false);


    }
}
