package news.nitesh.com.news;

import android.content.Context;
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
 * Created by nitesh on 12/29/2016.
 */

public class SharedPreferencesSecondActivity extends AppCompatActivity {

    TextView sharedPrefName, sharedPrefPhn, sharedPrefEmail;
    Button sharedPrefShowButton;


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String DEFAULT = "N/A";


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref_second_activity);


        sharedPrefName = (TextView) findViewById(R.id.sharedPrefName);
        sharedPrefPhn = (TextView) findViewById(R.id.sharedPrefPhn);
        sharedPrefEmail = (TextView) findViewById(R.id.sharedPrefEmail);


        sharedPrefShowButton=(Button)findViewById(R.id.sharedPrefShowButton);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        sharedPrefShowButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context/this);
                String mapTypeString = preferences.getString("map_type_pref_key", "DEFAULT");

                Will provide an access to a preferences file that is global for the whole application package ;
                 any activity can access the preferences (internaly, the xml file holding the preferences will be
                 named your.application.package_preferences.xml).*/

                SharedPreferences   sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                String name=sharedpreferences.getString(Name,DEFAULT);
                String phone=sharedpreferences.getString(Phone,DEFAULT);
                String email=sharedpreferences.getString(Email,DEFAULT);

                if(name.equals(DEFAULT) || phone.equals(DEFAULT) || email.equals(DEFAULT)) {

                    Toast.makeText(getApplicationContext(), "No Data Was Found", Toast.LENGTH_LONG).show();
                }
                else {

                    /* //For Multi-Type values of SINGLE sharedPref files
                    SharedPreferences preference = getSharedPreferences(pref_name, MODE_PRIVATE);
                    Map<String, ?> prefMap = preference.getAll();

                    //For Multi-Type values of all sharedPref files
                     Map<String, ?> prefs = PreferenceManager.getDefaultSharedPreferences(context).getAll();
                     for (String key : prefs.keySet()) {
                        Object pref = prefs.get(key);
                        String printVal = "";
                        if (pref instanceof Boolean) {
                            printVal =  key + " : " + (Boolean) pref;
                        }
                        if (pref instanceof Set<?>) {
                                printVal =  key + " : " + (Set<String>) pref;
                            }
                        }
                   */

                    sharedPrefName.setText(name);
                    sharedPrefPhn.setText(phone);
                    sharedPrefEmail.setText(email);

                    Log.i("sharedpref",sharedPrefName.getText().toString());
                    Toast.makeText(getApplicationContext(), "Data Loaded successfully", Toast.LENGTH_LONG).show();
                }


            }
        });



    }
}