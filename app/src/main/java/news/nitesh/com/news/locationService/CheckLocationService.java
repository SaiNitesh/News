package news.nitesh.com.news.locationService;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by nitesh on 12/23/2016.
 */
public class CheckLocationService {


    private final Context context;

    public CheckLocationService(Context context) {
        this.context=context;
    }


    public static boolean isDeviceLocationEnabled(Context mContext) {
        int locMode = 0;
        String locProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){ // api 19- kit kat
            try {
                locMode = Settings.Secure.getInt(mContext.getContentResolver(), Settings.Secure.LOCATION_MODE); // 3 location modes(high,battery,device)
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            Log.i("locM",String.valueOf(locMode));
            return locMode != Settings.Secure.LOCATION_MODE_OFF; // checks if  LOCATION_MODE(0n/off)!=LOCATION_MODE_OFF
        }else{
            locProviders = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locProviders);
        }
    }

    public static boolean locationServicesEnabled(Context context) {
        //gps –> (GPS, AGPS)
        //network –> (AGPS, CellID, WiFi MACID):
        //passive –> (CellID, WiFi MACID):
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean net_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER); //gps
            Log.i("gps",String.valueOf(gps_enabled));
        } catch (Exception ex) {
            Log.e("TAG","Exception gps_enabled");
        }

        try {
            net_enabled = lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER); // wifi, cellular
            Log.i("wifi",String.valueOf(net_enabled));
        } catch (Exception ex) {
            Log.e("TAG","Exception network_enabled");
        }
        return gps_enabled || net_enabled;
    }


    private void CheckEnableGPS(){
        String provider = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.equals("")){
            //GPS Enabled
            Toast.makeText(context, "GPS Enabled: " + provider,
                    Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
            context.startActivity(intent);
        }
    }


    public boolean checkInternetConenction() {
        // getAllNetworkInfo() This method was deprecated in API level 23
        // Use getAllNetworks() and getNetworkInfo(android.net.Network) instead.

        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //api 22- lollipop
            Toast.makeText(context, " >=Lollipop ", Toast.LENGTH_LONG).show();

           /* NetworkInfo netInfo = connec.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting())
                return true;*/


            //boolean result = Settings.Global.GetInt(context.ContentResolver, "mobile_data", 1) ==1 // to check mobile data
            boolean wifi=isConnected(connec, ConnectivityManager.TYPE_WIFI);
            boolean data=isConnected(connec, ConnectivityManager.TYPE_MOBILE);
            if(wifi || data) {
                Toast.makeText(context, " Connected ", Toast.LENGTH_LONG).show();
                return true;
            }


           /* Network[] networks = connec.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connec.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }*/


        }else {
            Toast.makeText(context, " < Lollipop ", Toast.LENGTH_LONG).show();
            // Check for network connections
            // if connected
            if (connec.getNetworkInfo(0).getState() ==
                    NetworkInfo.State.CONNECTED ||
                    connec.getNetworkInfo(0).getState() ==
                            NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() ==
                            NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
                Toast.makeText(context, " Connected ", Toast.LENGTH_LONG).show();
                return true;

                /*NetworkInfo[] info = connec.getAllNetworkInfo();
            if (info != null)
                for (NetworkInfo anInfo : info)
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }*/

                // if not connected
            } else if (
                    connec.getNetworkInfo(0).getState() ==
                            NetworkInfo.State.DISCONNECTED ||
                            connec.getNetworkInfo(1).getState() ==
                                    NetworkInfo.State.DISCONNECTED) {
                Toast.makeText(context, " Not Connected ", Toast.LENGTH_LONG).show();
                return false;
            }

        }
        Toast.makeText(context, " Please connect to Internet ", Toast.LENGTH_LONG).show();
        return false;
    }


    private static boolean isConnected(@NonNull ConnectivityManager connMgr, int type) {
        Network[] networks = connMgr.getAllNetworks();
        NetworkInfo networkInfo;
        for (Network mNetwork : networks) {
            networkInfo = connMgr.getNetworkInfo(mNetwork);
            if (networkInfo != null && networkInfo.getType() == type && networkInfo.isConnected()) {
                Log.i("type ",String.valueOf(type));
                return true;
            }
        }
        return false;
    }

}
