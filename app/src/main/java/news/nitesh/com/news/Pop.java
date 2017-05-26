package news.nitesh.com.news;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitesh on 12/3/2016.
 */
public class Pop extends Activity {

    ImageView popContactImgView;
    EditText popName;
    EditText popPwd;
    Button updateB1;


    Uri image_Uri /*= Uri.parse("android.resource://news.nitesh.com.news/drawable/no_user_logo.jpeg")*/;

    ContactDetailsBean eDetails;
    DatabaseHandler dbHandler;
    List<ContactDetailsBean> bean;

    int longClickedItemIndex;

    public Pop() {
        super();
    }

   /* private ContactDetails c;
    public Pop(ContactDetails c){
        this.c=c;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_view);

        popName = (EditText) findViewById(R.id.popName);
        popPwd = (EditText) findViewById(R.id.popPwd);
        popContactImgView = (ImageView) findViewById(R.id.popContactImgView);
        updateB1 = (Button) findViewById(R.id.updateB1);
        //dbHandler = new DatabaseHandler(getApplicationContext());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));

        //Log.i("imageK", getIntent().getStringExtra("image") );

        dbHandler= new DatabaseHandler(this);
        bean= dbHandler.getAllContacts();



        //eDetails= getIntent().getParcelableExtra("eDetails");
       // DatabaseHandler x=(DatabaseHandler) getIntent().getStringExtra("dbHan");

        //Log.i("yoyo",eDetails.toString());
        popContactImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this helps you you to upload image from sources(gallery, downloads, etc)
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select contact image"), 1);

            }
        });


        int x=Integer.parseInt(getIntent().getStringExtra("reddy"));
        ContactDetailsBean c= bean.get(x);
        Log.i("databean",c.getImgaeUri().toString());

        image_Uri = c.getImgaeUri();
        Picasso.with(Pop.this).load(image_Uri).into(popContactImgView);
        //popContactImgView.setImageURI(parsed);
        popName.setText(c.getuName());
        popPwd.setText(c.getUPassword());



        updateB1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                /*eDetails.setImgaeUri(parsed);
                eDetails.setuName(String.valueOf(popName.getText()));
                eDetails.setuPassword(String.valueOf(popPwd.getText()));

                Log.i("heyyyy",eDetails.getuName());*/

                Intent i= new Intent(/*Pop.this, ContactDetails.class*/);
                i.putExtra("updName",popName.getText().toString());
                i.putExtra("updPwd",popPwd.getText().toString());
                Log.i("fluffy",image_Uri.toString());
                i.putExtra("updImg",image_Uri.toString());
                Log.i("after",image_Uri.toString());

                //i.putExtra("updatedBean",eDetails);
                setResult(2,i);
                finish();

                //startActivity(i);
                /*int x=Integer.parseInt(getIntent().getStringExtra("reddy"));
                ContactDetailsBean c= bean.get(x);

                parsed = c.getImgaeUri();
                Picasso.with(Pop.this).load(parsed).into(popContactImgView);
                //popContactImgView.setImageURI(parsed);
                popName.setText(c.getuName());
                popPwd.setText(c.getUPassword());



                c.setuName(popName.getText().toString());
                c.setuPassword(popPwd.getText().toString());
                c.setImgaeUri(parsed);
                dbHandler.updateContact(c);*/



            }

        });




    }


    public void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == RESULT_OK) {
            if (reqCode == 1)
                image_Uri = data.getData();

                popContactImgView.setImageURI(data.getData());
        }
    }


}
