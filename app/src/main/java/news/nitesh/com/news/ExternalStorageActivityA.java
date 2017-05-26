package news.nitesh.com.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by nitesh on 1/2/2017.
 */
// add permisiions Write_External_Storage

public class ExternalStorageActivityA extends Activity {

    EditText ext_storage_a_edit;
    Button internal_cache,external_cache,external_private,external_public,go_to_ext_storage_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_external_storage);

        ext_storage_a_edit = (EditText) findViewById(R.id.ext_storage_a_edit);

        internal_cache= (Button) findViewById(R.id.internal_cache);
        external_cache= (Button) findViewById(R.id.external_cache);
        external_private= (Button) findViewById(R.id.external_private);
        external_public= (Button) findViewById(R.id.external_public);
        go_to_ext_storage_b= (Button) findViewById(R.id.go_to_ext_storage_b);

// DDMS file accessory====> mnt->shell->emulated->0->Android->

        internal_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data= ext_storage_a_edit.getText().toString();
                // For Internal Cache   /data/data/news.nitesh.com.news/cache
                File folder=getCacheDir();
                Log.d("folder",folder.toString());
                File myFile = new File(folder, "myData1.txt");

                writeData(myFile,data);
            }
        });

        external_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data= ext_storage_a_edit.getText().toString();
                File folder=getExternalCacheDir();  //     /storage/emulated/0/Android/data/news.nitesh.com.news/cache
                Log.d("folder",folder.toString());
                File myFile = new File(folder, "myData1.txt");

                writeData(myFile,data);
            }
        });

        external_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //storing private files on SD CARD

                String data= ext_storage_a_edit.getText().toString();
                // for storing private files and  "nitesh" is the type(ie., folder) under nitesh we have file myData1.txt
                File folder=getExternalFilesDir("nitesh");  //     /storage/emulated/0/Android/data/news.nitesh.com.news/files/nitesh
                Log.d("folder",folder.toString());
                File myFile = new File(folder, "myData1.txt");

                writeData(myFile,data);

            }
        });

        external_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // storing files in public, like in downloads, music, documnets etc

                String data= ext_storage_a_edit.getText().toString();
                // here we store in downloads    /storage/emulated/0/Download
                File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                Log.d("folder",folder.toString());
                File myFile = new File(folder, "myData1.txt");

                writeData(myFile,data);

            }
        });


        go_to_ext_storage_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ExternalStorageActivityA.this,ExternalStorageActivityB.class));
            }
        });

    }

    private void writeData(File myFile,String data){

        FileOutputStream fileOutputStream= null;
        try {
            fileOutputStream = new FileOutputStream(myFile); // new FileOutputStream(myFile,Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}


