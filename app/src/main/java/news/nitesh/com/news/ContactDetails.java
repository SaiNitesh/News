package news.nitesh.com.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitesh on 12/1/2016.
 */
public class ContactDetails extends AppCompatActivity{

    private static final int EDIT = 0, DELETE = 1;

    EditText name;
    EditText pwd;
    Button contactB1;
    TabHost tabHost;
    ListView contactsListView;
    ImageView contactImgView;
    Uri image_Uri/* = Uri.parse("android.resource://news.nitesh.com.news/drawable/no_user_logo.jpeg")*/;
    DatabaseHandler dbHandler;
    //ContactDetailsBean updatedBean;

    private boolean isEditMode;

    int longClickedItemIndex;
    ArrayAdapter<ContactDetailsBean> contactAdapter; // we can access this any where in class


    List<ContactDetailsBean> details = new ArrayList<ContactDetailsBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_contact);

        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
        contactB1 = (Button) findViewById(R.id.contactB1);
        contactsListView = (ListView) findViewById(R.id.contactsListView);
        contactImgView = (ImageView) findViewById(R.id.contactImgView);
        dbHandler = new DatabaseHandler(getApplicationContext());


        tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.ADD);
        tabSpec.setIndicator("Creator");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.View);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab3");
        tabSpec.setContent(R.id.View3);
        tabSpec.setIndicator("Tab3");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab4");
        tabSpec.setContent(R.id.View4);
        tabSpec.setIndicator("Tab4");
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);

        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 80;
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.WHITE);
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height = 80;
        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.WHITE);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.rgb(00, 219, 239));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("creator")) {
                    tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab_selector);
                    tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.WHITE);

                }
                if(tabId.equals("list")) {
                    tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab_selector);
                    tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.WHITE);

                }
            }});





        registerForContextMenu(contactsListView);

        contactsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickedItemIndex = position; // tells where item was clicked
                return false;
            }
        });


        contactB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //dbHandler.getContactsCount()--> if count=5(5 contacts) 5 th contact index=4 ,so next new contact id will be 5(count)
                ContactDetailsBean curentContact =  new ContactDetailsBean(dbHandler.getContactsCount(), String.valueOf(name.getText()), String.valueOf(pwd.getText()), image_Uri);
                if(!isContactExists(curentContact) && !isEditMode ){
                    dbHandler.createContact(curentContact);
                    details.add(curentContact);
                    contactAdapter.notifyDataSetChanged(); // notifing the adapter that list has changes
                    Log.i("arraydata", name.getText().toString() + pwd.getText().toString() + image_Uri);
                    //populateList();
                    Toast.makeText(getApplicationContext(), name.getText() + " has been add to contacts", Toast.LENGTH_SHORT).show();
                    resetContactPanel();
                    tabHost.setCurrentTab(1);
                    return;
                }
                else if(isEditMode){

                    ContactDetailsBean newBean= details.get(longClickedItemIndex);
                    newBean.setuName(name.getText().toString());
                    newBean.setuPassword(pwd.getText().toString());
                    newBean.setImgaeUri(image_Uri);
                    dbHandler.updateContact(newBean);
                    details.set(longClickedItemIndex,newBean);
                    contactAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), name.getText() + " has been updated", Toast.LENGTH_SHORT).show();
                    isEditMode = false;
                    contactB1.setText("ADD CONTACT");
                    resetContactPanel();
                    tabHost.setCurrentTab(1);
                }

                Toast.makeText(getApplicationContext(), name.getText() + " already exists. Please use a different name", Toast.LENGTH_SHORT).show();
            }
        });

        contactImgView.setOnClickListener(new View.OnClickListener() {
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

        if(dbHandler.getContactsCount() != 0)
         details.addAll(dbHandler.getAllContacts());

            populateList();


        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                contactB1.setEnabled(!name.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pwd.addTextChangedListener(mTextEditorWatcher);





    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onCreateContextMenu(ContextMenu menu, View view,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);

        menu.setHeaderIcon(R.drawable.pencil_icon);
        menu.setHeaderTitle("Contact Options");
        menu.add(Menu.NONE, EDIT, Menu.NONE, "Edit Contact");
        menu.add(Menu.NONE, DELETE, Menu.NONE, "Delete Contact");

    }

    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case EDIT:
                // TODO:
                Log.i("indexI",String.valueOf(longClickedItemIndex));
                //updateData(details.get(longClickedItemIndex));
                Intent i= new Intent(ContactDetails.this,Pop.class );
                i.putExtra("reddy",String.valueOf(longClickedItemIndex));
                startActivityForResult(i,2);

                break;
            case DELETE:
                dbHandler.deleteContact(details.get(longClickedItemIndex));
                details.remove(longClickedItemIndex);
                Log.i("indexI",String.valueOf(longClickedItemIndex));
                contactAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }


    private void updateData(ContactDetailsBean bean){

        tabHost.setCurrentTab(0);
        name.setText(bean.getuName());
        pwd.setText(bean.getUPassword());
        contactImgView.setImageURI(bean.getImgaeUri());
        isEditMode=true;
        Button edit=  (Button) findViewById(R.id.contactB1);
        edit.setText("Update");

    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == RESULT_OK) {
            if (reqCode == 1)
                image_Uri = data.getData();
            // getting image URI when you upload(above statement)  and then setting it in view in first tab(below stmt)
            contactImgView.setImageURI(data.getData());
        }
        if(reqCode==2)
        {
            ContactDetailsBean newBean= details.get(longClickedItemIndex);
            newBean.setuName(data.getStringExtra("updName"));
            newBean.setuPassword(data.getStringExtra("updPwd"));
            newBean.setImgaeUri(Uri.parse(data.getStringExtra("updImg")));
            details.set(longClickedItemIndex,newBean);
            //editDetails= getIntent().getParcelableExtra("updatedBean");
            //Log.i("uName","@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+editDetails.getuName());
            dbHandler.updateContact(newBean);
            contactAdapter.notifyDataSetChanged();
            resetContactPanel();
            tabHost.setCurrentTab(1);


        }


    }


    private boolean isContactExists(ContactDetailsBean isDetails){

        String name = isDetails.getuName();
        int contactCount = details.size();

        for(int i=0; i< contactCount; i++){
            if(name.compareToIgnoreCase(details.get(i).getuName())== 0)
                return true;
        }

        return false;
    }


    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s.length() == 0)
                pwd.setError("Not Entered");
            else if (s.length() < 6)
                Toast.makeText(getApplicationContext(), " WEAK", Toast.LENGTH_SHORT).show();
            else if (s.length() < 10)
                pwd.setError("MEDIUM");
            else if (s.length() < 15)
                pwd.setError("STRONG");
            else
                pwd.setError("STRONGEST");

            if (s.length() == 20)
                pwd.setError("Password Max Length Reached");
        }
    };




    private void populateList() {
        contactAdapter = new ContactListAdapter();
        contactsListView.setAdapter(contactAdapter);
    }

    public void resetContactPanel(){
        name.setText("");
        pwd.setText("");
        contactImgView.setImageDrawable(getResources().getDrawable(R.drawable.no_user_logo));
        //contactImgView.setImageURI(Uri.parse("android.resourse://news.nitesh.com.news/drawable/no_user_logo.jpeg"));

    }

    //List<ContactDetailsBean> dbDetails = dbHandler.getAllContacts();

    private class ContactListAdapter extends ArrayAdapter<ContactDetailsBean> {

        public ContactListAdapter() {
            super(ContactDetails.this, R.layout.details_contact_view, details);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null) {

                convertView = getLayoutInflater().inflate(R.layout.details_contact_view, parent, false);
                // for this statement values, "parent, false" are important
            }

            ContactDetailsBean curentContact = details.get(position);

            TextView currentName = (TextView) convertView.findViewById(R.id.nameLayout);
            TextView currentpwd = (TextView) convertView.findViewById(R.id.pwdLayout);
            ImageView contactImgViewLayout = (ImageView) convertView.findViewById(R.id.contactImgViewLayout);

            currentName.setText(curentContact.getuName());
            currentpwd.setText(curentContact.getUPassword());
            Log.i("imageU", curentContact.getImgaeUri().toString());
            contactImgViewLayout.setImageURI(curentContact.getImgaeUri());

            return convertView;

        }

    }



}
