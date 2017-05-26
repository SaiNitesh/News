package news.nitesh.com.news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nitesh on 12/28/2016.
 */

public class SharedPreferencesActivity extends AppCompatActivity {


    EditText ed1,ed2,ed3;
    Button sharedPrefButton;
    TextView sharedPrefName,sharedPrefPhn,sharedPrefEmail;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        sharedPrefName= (TextView) findViewById(R.id.sharedPrefName);
        sharedPrefPhn= (TextView) findViewById(R.id.sharedPrefPhn);
        sharedPrefEmail= (TextView) findViewById(R.id.sharedPrefEmail);

        sharedPrefButton=(Button)findViewById(R.id.sharedPrefButton);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        sharedPrefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n  = ed1.getText().toString();
                String ph  = ed2.getText().toString();
                String e  = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                /*if(n != "" || ph != "" || e != "" || n != null || ph != null || e != null) {
*/
                    editor.putString(Name, n);
                    editor.putString(Phone, ph);
                    editor.putString(Email, e);
                    editor.apply();
                    Toast.makeText(SharedPreferencesActivity.this, "Thanks", Toast.LENGTH_LONG).show();
                /*}else{
                    Toast.makeText(SharedPreferencesActivity.this, "Macha, please enter data", Toast.LENGTH_LONG).show();
                }*/

                startActivity(new Intent(SharedPreferencesActivity.this, SharedPreferencesSecondActivity.class));

            }
        });




        /*editor.remove("key_name3"); // will delete key key_name3
        editor.remove("key_name4"); // will delete key key_name4

        // Save the changes in SharedPreferences
        editor.commit(); // commit changes

*//************ Clear all data from SharedPreferences *****************//*

        editor.clear();
        editor.commit(); // commit changes*/
    }
}
