package news.nitesh.com.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by nitesh on 12/30/2016.
 */
public class InternalStorageActivityB extends Activity {


    TextView InternalStTextUser, InternalStTextPass;
    Button internalLoad, internalMoveToA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_internal_storage);

        InternalStTextUser = (TextView) findViewById(R.id.InternalStTextUser);
        InternalStTextPass = (TextView) findViewById(R.id.InternalStTextPass);

        internalLoad = (Button) findViewById(R.id.internalLoad);
        internalMoveToA = (Button) findViewById(R.id.internalMoveToA);

        internalLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FileInputStream fileInput = openFileInput("nitesh.txt");
                    int read = -1; // -1 is the end of the file
                    StringBuffer buffer = new StringBuffer(); // you can append the data(modify)
                    while ((read = fileInput.read()) != -1) { //

                        buffer.append((char) read);
                    }
                    Log.d("nitesh", buffer.toString());
                    String text1= buffer.substring(0, buffer.indexOf(" "));// 0->> start Index buffer.indexOf(" ")-->> last index(which is space)
                    String text2= buffer.substring(buffer.indexOf(" ")+1); //buffer.indexOf(" ")+1 ====> +1 is index after space

                    InternalStTextUser.setText(text1);
                    InternalStTextPass.setText(text2);

                } catch (IOException e) {
                    Log.d("error", e.toString());
                }

            }
        });


        internalMoveToA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InternalStorageActivityB.this, InternalStorageActivityA.class));
            }
        });


    }
}
