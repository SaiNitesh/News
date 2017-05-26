package news.nitesh.com.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by nitesh on 1/2/2017.
 */

// Internal cache is not working ...!!!

public class ExternalStorageActivityB  extends Activity{

    EditText ext_storage_b_edit;
    Button b_internal_cache, b_external_cache, b_external_private, b_external_public, go_to_ext_storage_a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_external_storage);

        ext_storage_b_edit= (EditText) findViewById(R.id.ext_storage_b_edit);

        b_internal_cache = (Button) findViewById(R.id.b_internal_cache);
        b_external_cache = (Button) findViewById(R.id.b_external_cache);
        b_external_private = (Button) findViewById(R.id.b_external_private);
        b_external_public = (Button) findViewById(R.id.b_external_public);
        go_to_ext_storage_a = (Button) findViewById(R.id.go_to_ext_storage_a);

        b_internal_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder=getCacheDir();  //If we use getFilesDir() instead of getCacheDir() then it’ll store files in the files directory
                Log.d("folder",folder.toString());
                File myFile= new File(folder,"mydata1.txt"); // full path with file name
                String data= readData(myFile);
                if(data != null){
                    ext_storage_b_edit.setText(data);
                }else{

                    ext_storage_b_edit.setText("No Data was returned");
                }


            }
        });

        b_external_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder=getExternalCacheDir();
                File myFile= new File(folder,"mydata1.txt");
                String data= readData(myFile);
                if(data != null){
                    ext_storage_b_edit.setText(data);
                }else{

                    ext_storage_b_edit.setText("No Data was returned");
                }

            }
        });

        b_external_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder=getExternalFilesDir("nitesh");
                File myFile= new File(folder,"mydata1.txt");
                String data= readData(myFile);
                if(data != null){
                    ext_storage_b_edit.setText(data);
                }else{

                    ext_storage_b_edit.setText("No Data was returned");
                }

            }
        });

        b_external_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File myFile= new File(folder,"mydata1.txt");
                String data= readData(myFile);
                if(data != null){
                    ext_storage_b_edit.setText(data);
                }else{

                    ext_storage_b_edit.setText("No Data was returned");
                }

            }
        });

        go_to_ext_storage_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ExternalStorageActivityB.this,ExternalStorageActivityA.class));

            }
        });

    }

    private String readData(File myFile){

        FileInputStream fileInputStream= null;
        try {
            fileInputStream= new FileInputStream(myFile);
            int read=-1;
            StringBuffer stringBuffer = new StringBuffer();
            while((read = fileInputStream.read()) != -1){

                stringBuffer.append((char) read); // convert binary to string
            }

            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileInputStream != null){
            try {
                fileInputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }

        return null;
    }
}
