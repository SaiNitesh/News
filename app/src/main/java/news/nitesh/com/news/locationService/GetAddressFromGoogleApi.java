package news.nitesh.com.news.locationService;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Locale;

/**
 * Created by nitesh on 12/26/2016.
 */
public class GetAddressFromGoogleApi {

    Context context;
    Location curLocation;
    static JSONArray result;
    public GetAddressFromGoogleApi(Context context, Location curLocation) {
        this.context=context;
        this.curLocation=curLocation;


    }

    public void getAddress(){
        if(curLocation != null) {
            AddressJSONTask addressJSONTask= new AddressJSONTask(new AsyncResponse(){

                @Override
                public void processFinish(JSONArray output){
                    //Here you will receive the result fired from async class
                    //of onPostExecute(result) method.
                    result=output;
                }
            });
            addressJSONTask.execute();
            //new AddressJSONTask().execute();
        }else{
            Log.i("LocationError","Current Location is NULL, please check it ...!!" );
        }
    }

    public interface AsyncResponse {
        void processFinish(JSONArray output);
    }

    public class AddressJSONTask extends AsyncTask<URL,JSONArray,JSONArray>{

        public AsyncResponse delegate = null;
        public AddressJSONTask(AsyncResponse delegate){
            this.delegate = delegate;
        }


        @Override
        protected JSONArray doInBackground(URL... params) {


            String finalAddress = "";
            JSONArray address_components = null;
            Geocoder geocoder;
            List<Address> addresses = null;
            geocoder = new Geocoder(context, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(curLocation.getLatitude(), curLocation.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addresses != null && addresses.size() > 0) {
                finalAddress = addresses.get(0).getAddressLine(0) + ", " + addresses.get(0).getAddressLine(1);
            } else {
                //Get address from Google api
                try {
                /*JSONObject jsonObj = getJSONfromURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + curLocation.getLatitude() + ","
                        + curLocation.getLongitude() + "&sensor=true");*/
                    JSONObject jsonObj=getLocationInfo();
                    String Status = jsonObj.getString("status");
                    if (Status.equalsIgnoreCase("OK")) {
                        JSONArray Results = jsonObj.getJSONArray("results");
                        JSONObject location = Results.getJSONObject(0);
                        finalAddress = location.getString("formatted_address");
                        address_components= location.getJSONArray("address_components");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return address_components;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONArray s) {
            super.onPostExecute(s);

            Log.i("address ",""+s);
            delegate.processFinish(s);
        }
    }


    public JSONObject getLocationInfo() {

        HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?latlng="+curLocation.getLatitude()+","+curLocation.getLongitude()+"&sensor=true");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


}
