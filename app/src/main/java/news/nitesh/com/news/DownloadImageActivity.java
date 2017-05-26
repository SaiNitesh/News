package news.nitesh.com.news;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by nitesh on 12/31/2016.
 */

//http://www.androidbegin.com/tutorial/android-download-image-from-url/
//https://www.learn2crack.com/2014/06/android-load-image-from-internet.html

public class DownloadImageActivity extends Activity {//implements View.OnClickListener, DownloadImageTask.Listener

    // Set your Image URL into a string
    //String URL = "http://www.androidbegin.com/wp-content/uploads/2013/07/HD-Logo.gif";
    String URL ="https://raw.githubusercontent.com/SaiNitesh/Daaripoduguna_gunde_nethurulu/master/Tharpana_Chesthu_padandi_mundku/WebContent/WEB-INF/Images/realmadrid_17.jpg";
    ImageView downloadImage;
    Button downloadImageButton;
    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);

        // Locate the ImageView in activity_main.xml
        downloadImage = (ImageView) findViewById(R.id.downloadImage);

        // Locate the Button in activity_main.xml
        downloadImageButton = (Button) findViewById(R.id.downloadImageButton);


        final DownloadImageTask.Listener listener= new DownloadImageTask.Listener(){

            @Override
            public void onImageLoaded(Bitmap bitmap) {

                // Set the bitmap into ImageView
                downloadImage.setImageBitmap(bitmap);
                // Close progressdialog

                 mProgressDialog.dismiss();
            }
            @Override
            public void onError() {

                Toast.makeText(DownloadImageActivity.this, "Error Loading Image !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPreProgress() {

                // Create a progressdialog
                mProgressDialog = new ProgressDialog(DownloadImageActivity.this);
                // Set progressdialog title
                mProgressDialog.setTitle("Download Image Tutorial");
                // Set progressdialog message
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
               // mProgressDialog.setIndeterminate(false);
                // Show progressdialog
                mProgressDialog.show();

            }
        };


       /* new Runnable() {
            @Override
            public void run() {
                new DownloadImageTask().execute();
            }
        }, 3000;*/
        // Capture button click
        downloadImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Execute DownloadImage AsyncTask
                new DownloadImageTask(listener).execute(URL);
            }
        });

    }



}
