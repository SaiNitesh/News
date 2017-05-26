package news.nitesh.com.news;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

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
import java.util.ArrayList;
import java.util.List;

import news.nitesh.com.news.models.JsonURLFullModel;

/**
 * Created by nitesh on 12/6/2016.
 */

//**********@@@@@ you need to refresh the emulator by clicking menu on right side of screen to ger refresh button.******@@@@@@@@@@
public class JsonURLFull extends Activity {



    private ListView jsonFullL1;
    private ProgressDialog dialog;
    SwipeRefreshLayout swipeRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_json_url);

        dialog = new ProgressDialog(this); // Before the application was loaded, we can use this progressDialog, so that we know something is loading
        dialog.setIndeterminate(true); // we never know how long it runs
        dialog.setCancelable(false); //this dialog cannot be cancelled
        dialog.setMessage("Loading. Please wait...");




        // Create global configuration and initialize ImageLoader with this config
        // UniversalImageLoader was added in gradle, the you can use below code

        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                            .cacheInMemory(true)
                            .cacheOnDisk(true)
                            .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                            .defaultDisplayImageOptions(defaultOptions)
                            .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        jsonFullL1 = (ListView) findViewById(R.id.jsonFullL1);

                // instead of below statemnt we used "onCreateOptionsMenu" method in this class
               // new JSONTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
        //https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt

        swipeRefresh= (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Toast.makeText(JsonURLFull.this,"Refresh",Toast.LENGTH_SHORT).show();
                new JSONTask().execute("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt");
                swipeRefresh.setRefreshing(false);

            }

        });

       /* if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
*/

    }



    public class JSONTask extends AsyncTask<String, String, List<JsonURLFullModel>>  {
                                   //     Url , , return_value_of_doInBackground


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show(); // this is for ProgressDialog
        }

        @Override
        protected List<JsonURLFullModel> doInBackground(String... params) {

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
                JSONArray parentArray = parentObject.getJSONArray("movies"); // "movies" is the JSON Array-object in JSON data URL

                List<JsonURLFullModel> movieModelList = new ArrayList<>();
                //StringBuffer finalBufferedaData = new StringBuffer();

                for(int i=0; i<parentArray.length(); i++){
                    JSONObject finalObject = parentArray.getJSONObject(i); // getting first json object from JSON ARRAY "movies"
                    JsonURLFullModel movieModel = new JsonURLFullModel();

                    movieModel.setMovie(finalObject.getString("movie"));
                    movieModel.setYear(finalObject.getInt("year"));
                    movieModel.setRating((float)finalObject.getDouble("rating"));
                    movieModel.setDirector(finalObject.getString("director"));
                    movieModel.setDuration(finalObject.getString("duration"));
                    movieModel.setTagline(finalObject.getString("tagline"));
                    movieModel.setImage(finalObject.getString("image"));
                    movieModel.setStory(finalObject.getString("story"));

                    List<JsonURLFullModel.Cast> castList = new ArrayList<>();
                    for(int j=0; j<finalObject.getJSONArray("cast").length(); j++){
                        JSONObject castObject = finalObject.getJSONArray("cast").getJSONObject(j);
                        JsonURLFullModel.Cast cast = new JsonURLFullModel.Cast();
                        cast.setName(castObject.getString("name"));
                        castList.add(cast);
                    }
                    movieModel.setCastList(castList); // adding the cast object in cast-list
                    movieModelList.add(movieModel); // adding the final object in list
                    //finalBufferedaData.append(movieName +"-"+ year +"\n");
                }
                return movieModelList;

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
        protected void onPostExecute(List<JsonURLFullModel> result) {
            super.onPostExecute(result);
            //jsonfull.setText(result);
            dialog.dismiss(); // closing dialog, before the content load in ListView(i.e until application lodading it will run, then dialog closes)

            MovieAdapter adapter = new MovieAdapter(getApplicationContext(), R.layout.full_json_row, result);
            jsonFullL1.setAdapter(adapter); // adding the adapter(full_json_row, which contains actual data) into ListView

            /*YoYo.with(Techniques.RubberBand)
                    .duration(700)
                    .playOn(findViewById(R.id.jsonFullL1));*/



            AnimationUtils.animate(jsonFullL1, true);

        }

    }





    public class MovieAdapter extends ArrayAdapter{
        public List<JsonURLFullModel> movieModelListB;
        private int resource;
        private LayoutInflater inflater;
        public MovieAdapter(Context context, int resource, List<JsonURLFullModel> objects) {
            super(context, resource, objects);
            movieModelListB = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }




        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder= null;

            if(convertView == null){ // convertView is null, when first time you load the first item of the list. Next time if it loads then it is
                       //  not null, it hold the "first element reference" becuse we are returning the same "convertView"(at the end of this method)
                convertView = inflater.inflate(resource, null); // resource= R.layout.full_json_row,
                                             // we use hardcoded values(resouece) if adapter class is in same class file  else we mention the value
                              // inflated "full_json_row" in convertView --> this contains all the views of that .xml file lit TextView,ImageView etc

                holder = new ViewHolder();
                holder.jsonMovieImg1 = (ImageView) convertView.findViewById(R.id.jsonMovieImg1);
                holder.txMovieName = (TextView) convertView.findViewById(R.id.txMovieName);
                holder.txTagline = (TextView) convertView.findViewById(R.id.txTagline);
                holder.txMovieYr = (TextView) convertView.findViewById(R.id.txMovieYr);
                holder.txDuration = (TextView) convertView.findViewById(R.id.txDuration);
                holder.txDirect = (TextView) convertView.findViewById(R.id.txDirect);
                holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
                holder.txCast = (TextView) convertView.findViewById(R.id.txCast);
                holder.txStory = (TextView) convertView.findViewById(R.id.txStory);

                convertView.setTag(holder);
            } else {
                holder= (ViewHolder) convertView.getTag(); // this statement helps, if not first element(converview==null)
                                                    // i.e it will loads all the elements from the 2nd element and
                                      // excepts first one because it will load in "if part"
            }




            final ProgressBar progressBar= (ProgressBar) convertView.findViewById(R.id.progressBar); // for this we added new ImageLoaderListener,it is like TextWatcer

            // Then later, when you want to display image
            ImageLoader.getInstance().displayImage(movieModelListB.get(position).getImage(),holder.jsonMovieImg1, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE); // progress bar will visible
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            }); // Default options will be used

            holder.txMovieName.setText(movieModelListB.get(position).getMovie());
            holder.txTagline.setText(movieModelListB.get(position).getTagline());
            holder.txMovieYr.setText("Year: "+movieModelListB.get(position).getYear()); // int is converted to String
            holder.txDuration.setText("Duration: "+movieModelListB.get(position).getDuration());
            holder.txDirect.setText("Director: "+movieModelListB.get(position).getDirector());
            holder.ratingBar.setRating(movieModelListB.get(position).getRating()/2);

            StringBuffer stringBuffer = new StringBuffer();
            for(JsonURLFullModel.Cast cast : movieModelListB.get(position).getCastList()) {
                stringBuffer.append("Cast: "+cast.getName() + ",");
            }
            holder.txCast.setText(stringBuffer);
            holder.txStory.setText(movieModelListB.get(position).getStory());

            return convertView;
        }


        class ViewHolder{
            // we used this class to reduce the load on the application,
            // which will be caused by findById() statement to search for an Id
            ImageView jsonMovieImg1;
            TextView txMovieName;
            TextView txTagline;
            TextView txMovieYr;
            TextView txDuration;
            TextView txDirect;
            RatingBar ratingBar;
            TextView txCast;
            TextView txStory;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.full_json_main, menu); // full_json_main is added in res/menu and in res/values/string
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle action item clicks here. The acion bar will
        * automatically handle clicks on the Home/uop button,
        * so long as you specify a parent activity in android manifest.xml*/

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.jso_full_refresh) { // this "id" will found in full_json_main.xml file
            new JSONTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}