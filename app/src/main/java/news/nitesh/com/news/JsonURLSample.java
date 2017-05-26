package news.nitesh.com.news;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nitesh on 12/6/2016.
 */
public class JsonURLSample extends Activity{

    Button sampleJsonb1;
    TextView jsonSample;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_json_url);


        sampleJsonb1 = (Button) findViewById(R.id.jsonSampleb1);
        jsonSample= (TextView) findViewById(R.id.jsonSample);

        sampleJsonb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new JSONTask().execute("https://raw.githubusercontent.com/SaiNitesh/REST-Web-services/master/RESTfulWS/dummyJson.txt");
                //http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt
                //http://jsonparsing.parseapp.com/jsonData/moviesDemoList.txt
            }
        });



    }
    public class JSONTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {

            //send and receive data over the web
            // can be used to send and receive "streaming" data whose length is not
            // known in advance
            HttpURLConnection connection = null;
            BufferedReader reader= null;

            try{

                URL url= new URL(params[0]);
                connection =(HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream(); // this is used to store
                // the response, response is always stream

                reader = new BufferedReader(new InputStreamReader(stream));
                // BufferedReader reads the stream line by line and then store in the reader

                StringBuffer buffer = new StringBuffer();
                String line ="";
                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString(); // this contails whole JSON data from the URL
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("movies"); // "movies" is the JSON Array object in JSON data URL

               StringBuffer finalBufferedaData = new StringBuffer();
                for(int i=0; i<parentArray.length(); i++){
                    JSONObject finalObject = parentArray.getJSONObject(i);

                    String movieName = finalObject.getString("movie");
                    int year = finalObject.getInt("year");
                    finalBufferedaData.append(movieName +"-"+ year +"\n");
                }
                return finalBufferedaData.toString();

                /*JSONObject finalObject = parentArray.getJSONObject(0); // accessing first element from array object

                String movieName = finalObject.getString("movie");
                int year =  finalObject.getInt("year");

                return movieName + "-" + year;*/

                 /* 2) the above commented code is just for one element in JSON ARRAY,
                 http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt*/

               //1) //return buffer.toString(); // retuned(whole JSON URL) to result in onPostExecute(String result) method on below

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null)
                    connection.disconnect();
                try {
                    if(reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            jsonSample.setText(result);
        }
    }

}