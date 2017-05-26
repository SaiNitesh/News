package news.nitesh.com.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Network;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.Map;

import news.nitesh.com.news.locationService.CheckLocationService;
import news.nitesh.com.news.locationService.LoctnTrackerUsingFuse;
import news.nitesh.com.news.locationService.LoctnTrackerUsingLocationManger;

/**
 * Created by nitesh on 11/29/2016.
 */
public class MainActivity2 extends Activity {

    Button btn = null;
    Button button21 = null;
    Button button22 = null;

    Button button23 = null;
    Button button24 = null;

    Button button25 = null;
    Button button26 = null;
    Button button27 = null;
    Button button28 = null;
    Button button29 = null;
    Button button30 = null;
    Button button31 = null;
    Button button32 = null;
    Button button33 = null;
    Button button34 = null;
    Button button35 = null;
    Button button36 = null;
    Button button37 = null;
    Button button38 = null;

    // This is MainActivity2 need to be initialized in "AndroidManifest.xml" if you want to
    // redirect 'to and from' another page

    String myUsername = "";
    String myEmail = "";
    int ncounter = 0;

    CheckLocationService locationService;
    LoctnTrackerUsingLocationManger tracker;
    LoctnTrackerUsingFuse loctnTrackerUsingFuse;


    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        button21 = (Button) findViewById(R.id.b21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FloatingActionButtonActivity.class);
                startActivity(i);
            }
        });

        button22 = (Button) findViewById(R.id.b22);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyParcelable.class);
                startActivity(i);
            }
        });

        button23 = (Button) findViewById(R.id.b23);
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TabsSwipe.class);
                startActivity(i);
            }
        });

        button24 = (Button) findViewById(R.id.b24);
        button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ImageSliderActivity.class);
                startActivity(i);
            }
        });


        button25 = (Button) findViewById(R.id.b25);
        button25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RecyclerViewActivity.class);
                startActivity(i);
            }
        });


        button26 = (Button) findViewById(R.id.b26);
        button26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DummyListView.class);
                startActivity(i);
            }
        });

        button27 = (Button) findViewById(R.id.b27);
        button27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CardViewActivity.class);
                startActivity(i);
            }
        });



        button28 = (Button) findViewById(R.id.b28);
        button28.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                Map<String, ?> prefs = sharedpreferences.getAll();
                int totalKeys=prefs.size(); //total login values
                Log.i("tkeys",String.valueOf(totalKeys));

                for (String key : prefs.keySet()) {
                    Object pref = prefs.get(key);
                    ncounter = checkForNull(pref,totalKeys);
                    Log.i("count", String.valueOf(ncounter));
                }
                // if you are already login(have values in SharedPreferemces), then you are directly redirected to next page
                if (ncounter == totalKeys) {
                    count = 0; // setting the key value's

                    Intent intent = new Intent(getApplicationContext(), SharedPreferencesSecondActivity.class);
                    startActivity(intent);

                } else {
                    startActivity(new Intent(getApplicationContext(), SharedPreferencesActivity.class));
                }
            }
        });


        button29 = (Button) findViewById(R.id.b29);
        button29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ShareDataToOtherAppsActivity.class);
                startActivity(i);
            }
        });


        button30 = (Button) findViewById(R.id.b30);
        button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Handler_Activity.class);
                startActivity(i);
            }
        });

        button31 = (Button) findViewById(R.id.b31);
        button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DownloadImageActivity.class);
                startActivity(i);
            }
        });

        button32 = (Button) findViewById(R.id.b32);
        button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ExternalStorageActivityA.class);
                startActivity(i);
            }
        });


        button33 = (Button) findViewById(R.id.b33);
        button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DownloadPDFActivity.class);
                startActivity(i);
            }
        });


        button34 = (Button) findViewById(R.id.b34);
        button34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PDFRendererActivity.class);
                startActivity(i);
            }
        });


        button35 = (Button) findViewById(R.id.b35);
        button35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DownloadManagerActivity.class);
                startActivity(i);
            }
        });

        button36 = (Button) findViewById(R.id.b36);
        button36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DownloadDemo.class);
                startActivity(i);
            }
        });

        button37 = (Button) findViewById(R.id.b37);
        button37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), IBinderClient.class);
                startActivity(i);
            }
        });

        button38 = (Button) findViewById(R.id.b38);
        button38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DataFromWebsite.class);
                startActivity(i);
            }
        });


        if (getIntent().hasExtra("username")) {
            myUsername = getIntent().getStringExtra("username");
        } else {
            //throw new IllegalArgumentException()
        }
        if (getIntent().hasExtra("Email")) {
            myEmail = getIntent().getStringExtra("Email");
        }
        TextView showUname = (TextView) findViewById(R.id.showUname);
        TextView showEmail = (TextView) findViewById(R.id.showEmail);

        showUname.setText(myUsername);
        showEmail.setText(myEmail);
        //Activity.xml==> activity.java===> activity2.java==> actiity2.xml


        String[] myNames = {"Android", "IPhone", "WindowsMobile", "Blackberry",
                "WebOS", "Ubuntu", "Windows7", "Max OS X"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_layout, myNames);
        //*** ArrayAdapter only works if you extend Activity, not AppCompatActivity
        //list_layout is an XML file,  list_layout.xml==> mainActivity2.java ==> activity2_main.xml
        //data ==> ArrayAdapter ==> view
        ListView myFirstLView = (ListView) findViewById(R.id.myFirstLView);
        myFirstLView.setAdapter(adapter);


        locationService = new CheckLocationService(MainActivity2.this);

        boolean deviceLocationEnabled = locationService.isDeviceLocationEnabled(MainActivity2.this);
        Log.i("location", String.valueOf(deviceLocationEnabled));
        if (!locationService.isDeviceLocationEnabled(MainActivity2.this)) {
            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(myIntent);
        }


        Log.i("nloca", String.valueOf(locationService.locationServicesEnabled(MainActivity2.this)));


        locationService.checkInternetConenction();

        tracker = new LoctnTrackerUsingLocationManger(MainActivity2.this);
        if (tracker.canGetLocation()) {

            double latitude = tracker.getLatitude();
            double longitude = tracker.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                    + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            tracker.showSettingsAlert();
        }


        loctnTrackerUsingFuse = new LoctnTrackerUsingFuse(MainActivity2.this, this);
        if (loctnTrackerUsingFuse.isGooglePlayServicesAvailable()) {

            // set up google client
            loctnTrackerUsingFuse.setUpGoogleClient();
            //set Location Request
            loctnTrackerUsingFuse.createLocationRequest();

        }

        loctnTrackerUsingFuse.updateUI();


    }

    int count = 0;

    private int checkForNull(Object pref,int totalKeys) {

        Log.i("mypref",pref.toString());

        if (pref.toString().trim() == null || pref == "" || pref.toString().length()==0) {


        }else{
            if (count < totalKeys )
                count++;
            /*else
                count = 1;*/
        }
        Log.i("countinside", String.valueOf(count));
        return count;

    }

    /*public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, 9000) //PLAY_SERVICES_RESOLUTION_REQUEST=90000
                        .show();
            } else {
                Log.i("LocationActivity", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }*/


    @Override
    protected void onStart() {
        super.onStart();
        if (loctnTrackerUsingFuse.mGoogleApiClient != null) {
            Log.d("LocationActivity", "onStart fired ..............");
            loctnTrackerUsingFuse.mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (loctnTrackerUsingFuse.mGoogleApiClient != null) {
            Log.d("LocationActivity", "onStop fired ..............");
            loctnTrackerUsingFuse.mGoogleApiClient.disconnect();
            Log.d("LocationActivity", "isConnected ...............: " + loctnTrackerUsingFuse.mGoogleApiClient.isConnected());
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (loctnTrackerUsingFuse.mGoogleApiClient.isConnected()) {

            loctnTrackerUsingFuse.stopLocationUpdates();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (loctnTrackerUsingFuse.mGoogleApiClient != null) {
            if (loctnTrackerUsingFuse.mGoogleApiClient.isConnected()) {
                loctnTrackerUsingFuse.startLocationUpdates();
                Log.d("LocationActivity", "Location update resumed .....................");
            }
        }
    }
}

