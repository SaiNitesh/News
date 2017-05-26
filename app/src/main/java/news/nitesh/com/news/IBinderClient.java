package news.nitesh.com.news;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import news.nitesh.com.news.IBinderService.LocalBinder;

/**
 * Created by nitesh on 1/6/2017.
 */

//http://www.101apps.co.za/articles/binding-to-a-service-a-tutorial.html

public class IBinderClient extends Activity {

    boolean mBounded;
    IBinderService mServer;
    TextView binder_text;
    Button binder_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_ibinder);

        binder_text = (TextView)findViewById(R.id.binder_text);
        binder_button = (Button) findViewById(R.id.binder_button);
        binder_button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                binder_text.setText(mServer.getTime());
            }
        });
    }

    //Called by the system every time a client explicitly starts the service by calling startService(Intent),
    // providing the arguments it supplied and a unique integer token representing the start request. Do not call this method directly

    @Override
    protected void onStart() {
        super.onStart();
        Intent mIntent = new Intent(this, IBinderService.class);
        // this connects us to the Service if it’s running. It starts the Service if it’s not.
        // It returns true if the binding was successful and false if not.
        bindService(mIntent, mConnection, BIND_AUTO_CREATE); // default method
        //Some examples use the bindService() method instead of the startService()
    };

    //We need a ServiceConnection object to be able to bind to the Service.
    // Once bound, onServiceConnected returns an IBinder interface for communicating with the Service
    ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(IBinderClient.this, "Service is disconnected", Toast.LENGTH_SHORT).show();
            mBounded = false;
            mServer = null;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(IBinderClient.this, "Service is connected", Toast.LENGTH_SHORT).show();
            mBounded = true;
            LocalBinder mLocalBinder = (LocalBinder)service;
            mServer = mLocalBinder.getServerInstance(); // after getting this value you can use method "getTime()" in IBinderService
        }


    };

    @Override
    protected void onStop() {
        super.onStop();
        if(mBounded) {
            //disconnects from the Service. The Service is now allowed to die. We pass the connection interface as the parameter
            unbindService(mConnection); // default method
            mBounded = false;
        }
    };
}
