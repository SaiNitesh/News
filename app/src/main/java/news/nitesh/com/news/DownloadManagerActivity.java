package news.nitesh.com.news;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import news.nitesh.com.news.DownloadPDFActivity;
import news.nitesh.com.news.FileDownloader;
import news.nitesh.com.news.R;

/**
 * Created by nitesh on 1/5/2017.
 */

public class DownloadManagerActivity  extends Activity{

    Button downloadFile;

    int id = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager);

        downloadFile = (Button) findViewById(R.id.downloadFile);


        downloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    new DownloadFile().execute("http://maven.apache.org/maven-1.x/maven.pdf", "maven.pdf");

                    //"http://download.thinkbroadband.com/50MB.zip"

                    //new DownloadFile().execute("http://maven.apache.org/maven-1.x/maven.pdf", "maven.pdf");

                }else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DownloadManagerActivity.this);
                    alertDialogBuilder.setMessage("Your android version should be greater than GingerBread");

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            }
        });


    }


    private class DownloadFile extends AsyncTask<String, Integer, Long> {


        @Override
        protected Long doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            //String fileName = strings[1];  // -> maven.pdf
            String servicestring = Context.DOWNLOAD_SERVICE;
            DownloadManager downloadmanager;
            downloadmanager = (DownloadManager) getSystemService(servicestring);
            Uri uri = Uri
                    .parse(fileUrl);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setTitle("Data Download");
            //Setting description of request
            request.setDescription("Android Data download using DownloadManager.");
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);


            Long reference = downloadmanager.enqueue(request);





            return reference;
        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);




        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        registerReceiver(downloadReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(downloadReceiver);
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {


            //check if the broadcast message is for our enqueued download
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            String action = intent.getAction();
            Log.v("MY_APP","!!! onReceive !!!");

            Log.d("actionCli",referenceId+"");
            //startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

            Log.i("actionCli",intent.getAction());
            Log.i("actionCli",DownloadManager.ACTION_NOTIFICATION_CLICKED);
            if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(action))
            {
                    /*Intent i = new Intent(context,DownloadManager.class);
                    i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
                    context.startActivity(i);*/
                Intent dm = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                dm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(dm);
                startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
            }


        }
    };


}
