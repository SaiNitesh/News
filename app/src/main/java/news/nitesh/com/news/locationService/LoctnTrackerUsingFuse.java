package news.nitesh.com.news.locationService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

import news.nitesh.com.news.Entities.LatLongBean;

/**
 * Created by nitesh on 12/23/2016.
 */

/*
1."getLastLocation(GoogleApiClient)" this API should be used when there is "no need for continuous access"
   to location from an application. Like one shot access or get user location based on some action.
   This is the simplified way to get the device location and also may not provide high accuracy.
2. requestLocationUpdates(GoogleApiClient,LocationRequest, LocationListener) this API should be used when there a
    "need for continuous location updates" and the location is accessed when the application is active in foreground.
3.requestLocationUpdates (GoogleApiClient, LocationRequest, PendingIntent) this API is used to "receive location updates in
    the background" even when the application is not active. So the difference is PendingIntent.
    ---------------------------------------------------------------------------------------
When the GoogleApiClient is connected successfully, onConnected(Bundle) callback will be called. \
There we should register with LocationListener for location updates as
LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
---------------------------------------------------------------------------------------------
"LocationListener" provides call back for location change through onLocationChanged.
"GoogleApiClient.ConnectionCallbacks" provides call back for GoogleApiClient onConnected.
"GoogleApiClient.OnConnectionFailedListener" provides call back for GoogleApiClient onConnectionFailed.
*/


public class LoctnTrackerUsingFuse implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10; // update every (3) minutes, 180
    private static final long FASTEST_INTERVAL = 1000 * 5;

    LocationRequest mLocationRequest;
    public GoogleApiClient mGoogleApiClient;
    static Location mCurrentLocation;
    String mLastUpdateTime;

    private Context context;
    private Activity activity;

    public LoctnTrackerUsingFuse(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        final int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {   //ConnectionResult.SERVICE_MISSING,.SERVICE_VERSION_UPDATE_REQUIRED,.SERVICE_DISABLED
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Dialog dialog = apiAvailability.getErrorDialog(activity, resultCode, 9000); //PLAY_SERVICES_RESOLUTION_REQUEST=90000
                if (dialog != null) {
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        public void onDismiss(DialogInterface dialog) {
                            if (ConnectionResult.SERVICE_INVALID == resultCode)
                                activity.finish();
                        }
                    });
                    return false;
                }
            } else {

                /*new AlertDialog.Builder(context).create().show("Google Play Services Error",
                        "This device is not supported for required Goole Play Services", "OK", new Call() {
                            public void onCall(Object value) {
                                activity.finish();
                            }
                        });*/
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Google Play Services Error");
                alertDialogBuilder.setMessage("This device is not supported for required Goole Play Services");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setNeutralButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface alertDialog, int arg1) {
                                alertDialog.cancel();
                                //Toast.makeText(activity,"You clicked yes button",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog alert11 = alertDialogBuilder.create();
                alert11.show();

                //Toast.makeText(context, "Google Play Services not Avaliable", Toast.LENGTH_LONG).show();
                Log.i("LocationActivity", "This device is not supported.");
                activity.finish();
            }


            return false;
        }
        return true;
    }

    public void setUpGoogleClient() {
        // Create an instance of GoogleAPIClient( Instantiating the GoogleApiClient).
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    public void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);  // update every Interval(time like 4 minutes) // Every interval minutes it triggers onLocationChangedMethod
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);  /*If APP1 has setFastestInterval(1000 * 10)
                                                               and APP2 has setInterval(1000 * 10), both APPS have same request interval.
                                                             But it is the APP1 that will make the first request*/
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    // this will trigger every INTERVAL(minutes/seconds based on mentioned values)
    // LocationListener, interface
    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();

    }

    //GoogleApiClient.ConnectionCallbacks  Interface
    @Override
    public void onConnected(Bundle bundle) {

        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();

    }

    //GoogleApiClient.ConnectionCallbacks Interface
    @Override
    public void onConnectionSuspended(int i) {

    }

    // GoogleApiClient.OnConnectionFailedListener Interface
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());


    }

    public void startLocationUpdates() {
        try {
            PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this); //this==>(com.google.android.gms.location.LocationListener) not android.app.LocationListener
            Log.d(TAG, "Location update started ..............: ");
        } catch (SecurityException e) {
            Log.e("PERMISSION_EXCEPTION", "PERMISSION_NOT_GRANTED");
        }
    }

    static int i = 0;

    public void updateUI() {
        Log.d(TAG, "UI update initiated .............");


        // getting the Location from "onLocationChanged" for first time
        if (mCurrentLocation != null) {

            Location curLocation = mCurrentLocation;
            LatLongBean previousLocation = getLastKnownUserLocation();
            //for First Location, Checking if First location is null
            if (previousLocation == null)
                setNewUserLocation(curLocation);
            else {  // for Next Location's
                Log.i("iValue", "" + i);
                if (i == 0) { //dummy data
                    previousLocation.setLatitude(41.254871);
                    previousLocation.setLongitude(-73.436665);

                }

                if (i == 2) {//dummy data
                    previousLocation.setLatitude(28.704059);
                    previousLocation.setLongitude(77.102490);

                }

                i++;

                float[] results = new float[1];
                Location.distanceBetween(curLocation.getLatitude(), curLocation.getLongitude(), previousLocation.getLatitude(), previousLocation.getLongitude(), results);

                Log.i("distance", "length: " + results.toString() + "result: " + results[0]);
                float distance = (results.length > 0 ? results[0] : 0);
                if ((distance / 1000) >= 5) {
                    //the user has moved quite a distance
                    Log.i(TAG, "User has moved " + (distance / 1000) + "km. It Is Necessary To Refreshe Geofences.");
                    //set as new location
                    setNewUserLocation(curLocation);
                    getAddressFromGoogleApi(curLocation);


                } else {

                    String lat = String.valueOf(previousLocation.getLatitude());
                    String lng = String.valueOf(previousLocation.getLongitude());
                    Toast.makeText(context, "LatLang: " + lat + "," + lng, Toast.LENGTH_LONG).show();
                    Log.i(TAG, "Distance " + (distance / 1000) + "km Too Small To Refreshe Geofence. Ignore");
                }
            }

        } else {
            Log.d(TAG, "location is null ...............");
        }

        /*if (mCurrentLocation  != null) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());
            Toast.makeText(context, "LatLang: " + lat+","+lng,
                    Toast.LENGTH_LONG).show();
            *//*tvLocation.setText("At Time: " + mLastUpdateTime + "\n" +
                    "Latitude: " + lat + "\n" +
                    "Longitude: " + lng + "\n" +
                    "Accuracy: " + mCurrentLocation.getAccuracy() + "\n" +
                    "Provider: " + mCurrentLocation.getProvider());*//*
        } else {
            Log.d(TAG, "location is null ...............");
        }*/

    }

    LatLongBean newLocation;

    private void setNewUserLocation(Location curLocation) {

        newLocation = new LatLongBean(curLocation.getLatitude(), curLocation.getLongitude());

    }

    private LatLongBean getLastKnownUserLocation() {

        return newLocation;

    }


    public void stopLocationUpdates() {
        try {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this); //this==>(com.google.android.gms.location.LocationListener) not android.app.LocationListener
            Log.d(TAG, "Location update stopped .......................");
        } catch (SecurityException e) {
            Log.e("PERMISSION_EXCEPTION", "PERMISSION_NOT_GRANTED");
        }
    }


    public void getAddressFromGoogleApi(Location curLocation) {


        GetAddressFromGoogleApi getAddressFromGoogleApi = new GetAddressFromGoogleApi(context, curLocation);
        getAddressFromGoogleApi.getAddress();

        if(getAddressFromGoogleApi.result != null) {
            Log.i("fuseAddress", " " + getAddressFromGoogleApi.result); // Here we used interface to get the value

            String Address1 = "", Address2 = "", City = "", State = "", Country = "", County = "", PIN = "";


            try {                                                             // from "onPostExecute" in AsyncTask

                for (int i = 0; i < getAddressFromGoogleApi.result.length(); i++) {
                    JSONObject zero2 = getAddressFromGoogleApi.result.getJSONObject(i);
                    String long_name = zero2.getString("long_name");
                    JSONArray mtypes = zero2.getJSONArray("types");
                    String Type = mtypes.getString(0);

                    if (TextUtils.isEmpty(long_name) == false || !long_name.equals(null) || long_name.length() > 0 || long_name != "") {
                        if (Type.equalsIgnoreCase("street_number")) {
                            Address1 = long_name + " ";
                        } else if (Type.equalsIgnoreCase("route")) {
                            Address1 = Address1 + long_name;
                        } else if (Type.equalsIgnoreCase("sublocality")) {
                            Address2 = long_name;
                        } else if (Type.equalsIgnoreCase("locality")) {
                            // Address2 = Address2 + long_name + ", ";
                            City = long_name;
                        } else if (Type.equalsIgnoreCase("administrative_area_level_2")) {
                            County = long_name;
                        } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
                            State = long_name;
                        } else if (Type.equalsIgnoreCase("country")) {
                            Country = long_name;
                        } else if (Type.equalsIgnoreCase("postal_code")) {
                            PIN = long_name;
                        }
                    }

                }

                Log.i("finaladdress: ", Address1 + "," + Address2 + "," + City + "," + County + "," + State + "," + Country + "," + PIN);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
