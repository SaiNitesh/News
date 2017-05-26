package news.nitesh.com.news;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.squareup.picasso.Downloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static android.icu.util.MeasureUnit.MEGABYTE;

/**
 * Created by nitesh on 1/3/2017.
 */

public class DownloadPDFActivity extends Activity {


    Button downloadPDF,viewPDF;
    WebView webView1;
    private static final int  MEGABYTE = 1024 * 1024;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_pdf);

        downloadPDF = (Button) findViewById(R.id.downloadPDF);
        viewPDF = (Button) findViewById(R.id.viewPDF);

        webView1 = (WebView) findViewById(R.id.webView1);
        /*webView1.setWebViewClient(new WebViewClient() {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request,
                                    WebResourceError error) {
            Toast.makeText(DownloadPDFActivity.this, error+"",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onReceivedSslError(WebView view,
                                       SslErrorHandler handler, SslError error) {
            // TODO Auto-generated method stub
            // this method will proceed your url however if certification issues are there or not
            handler.proceed();
        }

    });
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl("https://docs.google.com/viewer?url=http://maven.apache.org/maven-1.x/maven.pdf");
*/

        downloadPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DownloadFile().execute("http://maven.apache.org/maven-1.x/maven.pdf", "maven.pdf");

            }
        });

        //https://github.com/barteksc/AndroidPdfViewer
        PDFView pdfViewer = (PDFView) findViewById(R.id.pdfViewer);

        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/testthreepdf/" + "maven.pdf");
        if(pdfFile.canRead()){
        pdfViewer.fromFile(pdfFile).defaultPage(1).onLoad(new OnLoadCompleteListener() {
            @Override
            public void loadComplete(int nbPages) {
                Toast.makeText(getApplicationContext(), String.valueOf(nbPages), Toast.LENGTH_LONG).show();
            }
        });
        }

        viewPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File pdfFile = new File(Environment.getExternalStorageDirectory() + "/testthreepdf/" + "maven.pdf");  // -> filename = maven.pdf
                Log.d("pdflog",pdfFile.toString());
                Log.d("pdflog",String.valueOf(pdfFile.exists()));
                Uri path = Uri.fromFile(pdfFile);
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setDataAndType(path, "application/pdf");
                //pdfIntent.setType("application/pdf");
                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                try{
                    startActivity(pdfIntent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(DownloadPDFActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(DownloadPDFActivity.this);
                    builder.setTitle("No Application Found");
                    builder.setMessage("Download one from Android Market?");
                    builder.setPositiveButton("Yes, Please",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                                    marketIntent.setData(Uri.parse("market://details?id=com.adobe.reader"));
                                    startActivity(marketIntent);
                                }
                            });
                    builder.setNegativeButton("No, Thanks", null);
                    builder.create().show();
                }

            }
        });

    }




    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            Log.d("pdflog",extStorageDirectory);
            File folder = new File(extStorageDirectory, "testthreepdf");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }
    }



}



