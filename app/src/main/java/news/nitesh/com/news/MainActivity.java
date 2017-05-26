package news.nitesh.com.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linLayout =null;
    Button button5=null;
    Button button6=null;
    Button button7=null;
    EditText urlText;
    String page="";

    Button button8=null;
    Button signIn=null;
    Button button9=null;
    Button button10=null;
    Button button11=null;
    Button button12=null;
    Button button13=null;
    Button button14=null;
    Button button15=null;
    Button button16=null;
    Button button17=null;
    Button button18=null;
    Button button19=null;
    Button button20 =null;


    private WebView myBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button5 = (Button) findViewById(R.id.b5);
        button5.setOnClickListener(new View.OnClickListener() {
            int j=0;
            @Override
            public void onClick(View v) {

                // In  linearLayout when ever you click button it will create new one in next line, based on this below code
                // which is different from relativeLayout
                Button btn5 = new Button(MainActivity.this);  //  should use "MainActivity.this" not "this"
                linLayout = (LinearLayout) findViewById(R.id.linLayout);

                btn5.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT   // horozontal, vertical
                ));
                btn5.setText("I'm a"+(++j) +" button");
                btn5.setId(j);

                linLayout.addView(btn5);

            }
        });


        button6 = (Button) findViewById(R.id.b6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView myText = (TextView) findViewById(R.id.myText);
                myText.setText("A gun is a normally tubular weapon or other device designed to discharge projectiles or other material.[1] The projectile may be solid, liquid, gas or energy and may be free, as with bullets and artillery shells, or captive as with Taser probes and whaling harpoons. The means of projection varies according to design but is usually effected by the action of gas pressure, either produced through the rapid combustion of a propellant or compressed and stored by mechanical means, operating on the projectile inside an open-ended tube in the fashion of a piston. The confined gas accelerates the movable projectile down the length of the tube, imparting sufficient velocity to sustain the projectile's travel once the action of the gas ceases at the end of the tube or muzzle. Alternatively, acceleration via electromagnetic field generation may be employed in which case the tube may be dispensed with and a guide rail substituted.");

            }
        });

        button6.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                TextView myText = (TextView) findViewById(R.id.myText);
                myText.setText("I'm beaing Looooooong");
                return true;

            }
        });

        /*WebView myBrowser = (WebView) findViewById(R.id.mybrowser);
        myBrowser.loadUrl("https://m.youtube.com/");*/


        button7 = (Button) findViewById(R.id.b7);
        myBrowser = (WebView) findViewById(R.id.mybrowser);

        button7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                urlText = (EditText) findViewById(R.id.urlText);
                page = urlText.getText().toString();
                Log.d("",page);
                //System.out.println(page);
                //myBrowser.loadUrl("https://m.youtube.com/");

                myBrowser.loadUrl("https://"+page);

                // if you want to open the website in new page, you can see this example
                // http://www.java2s.com/Code/Android/UI/UseEditTexttoacceptuserinput.htm

            }
        });


        button8= (Button) findViewById(R.id.b8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), MainActivity2.class);
                // Intent is like redirection to another page
                startActivity(i);
            }
        });

        signIn= (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), MainActivity2.class);
                // Intent is like redirection to another page
                EditText username= (EditText) findViewById(R.id.uname);
                EditText Email= (EditText) findViewById(R.id.mail);

                i.putExtra("username",username.getText().toString() );
                i.putExtra("Email",Email.getText().toString() );

                startActivity(i);
            }
        });

        button9= (Button) findViewById(R.id.b9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), CustomLViewAdapter.class);
                // Intent is like redirection to another page
                startActivity(i);
            }
        });

        button10= (Button) findViewById(R.id.b10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), NewsActive.class);
                //DOnt forget to add NewsActive.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });

        button11= (Button) findViewById(R.id.b11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), JsonParse.class);
                //DOnt forget to add NewsActive.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });

        button12= (Button) findViewById(R.id.b12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), CalculatorActiv.class);
                //DOnt forget to add NewsActive.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });


        button13= (Button) findViewById(R.id.b13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), ContactDetails.class);
                //DOnt forget to add ContactDetails.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });


        button14= (Button) findViewById(R.id.b14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), MyNewsListActivity.class);
                //DOnt forget to add MyNewsListActivity.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });


        button15= (Button) findViewById(R.id.b15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), JsonURLSample.class);
                //DOnt forget to add MyNewsListActivity.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });


        button16= (Button) findViewById(R.id.b16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), JsonURLFull.class);
                //DOnt forget to add MyNewsListActivity.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });

        button17= (Button) findViewById(R.id.b17);
        button17.setText("Gson URL Parse FULL");
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), JsonGsonURLFull.class);
                //DOnt forget to add MyNewsListActivity.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });


        button18= (Button) findViewById(R.id.b18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), MyNavigationDrawer.class);
                //DOnt forget to add MyNewsListActivity.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });

        button19= (Button) findViewById(R.id.b19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), SpinnerActivity.class);
                //DOnt forget to add MyNewsListActivity.class
                // Intent is like redirection to another page
                startActivity(i);
            }
        });


    }



}
