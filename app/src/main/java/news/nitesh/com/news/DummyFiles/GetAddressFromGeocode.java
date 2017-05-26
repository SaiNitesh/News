package news.nitesh.com.news.DummyFiles;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by nitesh on 12/25/2016.
 */
public class GetAddressFromGeocode extends IntentService {

    protected ResultReceiver resultReceiver;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GetAddressFromGeocode(String name) {
        super(name);
    }



    @Override
    protected void onHandleIntent(Intent intent) {


        // Get the location passed to this service through an extra.
        Location location = intent.getParcelableExtra("curLocation");
        // Check if receiver was properly registered.
        if (location == null) {
            Log.wtf("LocationTag", "No receiver received. There is nowhere to send the results.");
            return;
        }else
        {
            deliverResultToReceiver(location);
        }
    }


    private void deliverResultToReceiver(Location location) {

        Bundle bundle = new Bundle();
        bundle.putDouble("lati",location.getLatitude());
        bundle.putDouble("longo", location.getLatitude());
        resultReceiver.send(0, bundle);

    }


}

