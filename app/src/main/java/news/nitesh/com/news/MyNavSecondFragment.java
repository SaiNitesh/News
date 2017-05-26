package news.nitesh.com.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nitesh on 12/8/2016.
 */
public class MyNavSecondFragment extends Fragment {

    public MyNavSecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.drawer_fragment_second, container, false);

        return rootView;
    }
}
