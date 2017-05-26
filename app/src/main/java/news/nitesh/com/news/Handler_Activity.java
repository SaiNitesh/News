package news.nitesh.com.news;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

/**
 * Created by nitesh on 12/31/2016.
 */

public class Handler_Activity extends AppCompatActivity {

    Thread thread;
    Handler handler; // reference is initialized
    ProgressBar handler_progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Here this code runs in Main Thread
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handler_progress= (ProgressBar) findViewById(R.id.handler_progress);

        thread= new Thread(new MyThread());
        thread.start();
        handler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                handler_progress.setProgress(msg.arg1);
            }
        };
    }

    class MyThread implements  Runnable{

        // The code inside this run method runs in separate thread(say thread2)
        @Override
        public void run() {
            for(int i=0;i<100;i++){ // 10000 you can see in layout file

                Message message= Message.obtain(); // for every iteration we have create new message objec
                message.arg1=i;

                //To communicate from here about the current value, so that we can update
                // the progress bar in "Main Thread" we use handler
                handler.sendMessage(message);
                //after sending message we can use sleep to give the thread some rest
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }
    }
}
