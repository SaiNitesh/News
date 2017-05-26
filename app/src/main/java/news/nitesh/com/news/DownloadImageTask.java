package news.nitesh.com.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.InputStream;

/**
 * Created by nitesh on 12/31/2016.
 */

public class DownloadImageTask  extends AsyncTask<String, Void, Bitmap> {

    private Listener mListener;
    //ProgressDialog mProgressDialog;
    //Context context;

    public DownloadImageTask(Listener listener) {
        this.mListener=listener;
        //this.mProgressDialog= mProgressDialog;
        //this.context= context;
    }

    public interface Listener{

        void onImageLoaded(Bitmap bitmap);
        void onError();
        void onPreProgress();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mListener.onPreProgress();

    }

    @Override
    protected Bitmap doInBackground(String... URL) {

        String imageURL = URL[0];

        Bitmap bitmap = null;
        try {

            Thread.sleep(3000);
            // Download Image from URL
            InputStream input = new java.net.URL(imageURL).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {

        if (result != null) {

            mListener.onImageLoaded(result);

        } else {

            mListener.onError();
        }

    }
}
