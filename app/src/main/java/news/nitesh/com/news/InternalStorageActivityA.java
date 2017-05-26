package news.nitesh.com.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by nitesh on 12/30/2016.
 */

public class InternalStorageActivityA extends Activity {


    EditText InternalStEditUser, InternalStEditPass;
    Button internalSave, internalMoveToB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_internal_storage);

        InternalStEditUser = (EditText) findViewById(R.id.InternalStEditUser);
        InternalStEditPass = (EditText) findViewById(R.id.InternalStEditPass);

        internalSave = (Button) findViewById(R.id.internalSave);
        internalMoveToB = (Button) findViewById(R.id.internalMoveToB);

        internalSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text1 = InternalStEditUser.getText().toString();
                String text2 = InternalStEditPass.getText().toString();

                if (text1 != null && text2 != null) {

                    File location = null;

                    text1 = text1 + " ";
                    FileOutputStream fileOutput = null; // FileOutputStream writed data to files
                    try {
                        location = getFilesDir(); // get the location
                        fileOutput = openFileOutput("nitesh.txt", Context.MODE_PRIVATE);
                        fileOutput.write(text1.getBytes());
                        fileOutput.write(text2.getBytes());

                    } catch (FileNotFoundException e) {
                        Log.d("nitesh", e.toString());
                    } catch (IOException e) { // if your app crashes in between, IOException will help you handle it
                        e.printStackTrace();
                    } finally {
                        try {
                            fileOutput.close(); // make sure we close like closing water TAP else , lot of space will be waste
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    Toast.makeText(InternalStorageActivityA.this, "Saved Successfully" + location + " /nitesh.txt", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(InternalStorageActivityA.this, "macha! please enter data", Toast.LENGTH_LONG).show();
                }
            }
        });


        internalMoveToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InternalStorageActivityA.this, InternalStorageActivityB.class));
            }
        });


    }


}
