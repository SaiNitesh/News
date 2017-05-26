package news.nitesh.com.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by nitesh on 11/27/2016.
 */
public class RelativeActivity extends AppCompatActivity {

    Button button4=null; // always declare here before using the object

    private RelativeLayout myLayout =null; // myLayout is in .XML as id for whole layout
    private Button touch1=null; // touch1 is button id
    private float x;
    private float y;

    Button button5=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_relative);


        myLayout= (RelativeLayout) findViewById(R.id.myLayout);
        touch1=(Button)findViewById(R.id.touch1);
        myLayout.setOnTouchListener(new View.OnTouchListener() {
         int i=0;
            @Override
            //onTouch is like if you want coordinates
            // i.e event from finger touch with boolean
            public boolean onTouch(View v, MotionEvent event) {
                /*Toast.makeText(getApplicationContext(),"i was touched in java file", Toast.LENGTH_SHORT).show();
                return true;
                this will popup on screen infinitly after one touch, cuz toast will catch up in memory */
                x= event.getX();   // touch coordinate of finger
                y=event.getY();
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    touch1.setX(x);   // when the finger moves, the button will move to the particular finger touch coordinates(x,y)
                    touch1.setY(y);
                    touch1.setText(""+Math.abs(x)+","+Math.abs(y)+",i="+i);
                    //Log.d("",String.valueOf(x)+","+String.valueOf(y));

                }
                return ++i <= 50;
            }
        });



        button4= (Button) findViewById(R.id.b4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            // event from click
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"i was clicked in java file", Toast.LENGTH_SHORT).show();
                // below first method is same as this onClick() but here event listener is initialized in java file
            }
        });

        button5 = (Button) findViewById(R.id.b5REL);
        button5.setOnClickListener(new View.OnClickListener() {
            int j=0;
            @Override
            public void onClick(View v) {
                // In relativeLayout it doesn't matter how many times you will click, new button will overlap on another one
                // which is different from linearLayout
                Button btn5 = new Button(RelativeActivity.this);  //  should use "RelativeActivity.this" not "this"
                myLayout = (RelativeLayout) findViewById(R.id.myLayout);

                btn5.setLayoutParams(new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT   // horozontal, vertical
                ));
                btn5.setText("I'm a"+(++j) +" button");
                btn5.setId(j);

                myLayout.addView(btn5);

            }
        });
    }



    public void firstMethod(View v){
        Toast.makeText(getApplicationContext(),"i was clicked", Toast.LENGTH_SHORT).show();
        // Toast will will popup when you click on the button
    }

}
