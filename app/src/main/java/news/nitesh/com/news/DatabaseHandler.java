package news.nitesh.com.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitesh on 12/2/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = " contactManager",
    TABLE_CONTACTS = " contacts",
    KEY_ID = " id",
    KEY_NAME = " name",
    KEY_PASSWORD = " password",
    KEY_IMAGEURI = " imageUri";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // need to be carefull with the "spaces" in string
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS  + "(" + KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_PASSWORD  + " TEXT, "  + KEY_IMAGEURI  + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }


    public void createContact(ContactDetailsBean contact){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getuName());
        values.put(KEY_PASSWORD, contact.getUPassword());
        values.put(KEY_IMAGEURI, contact.getImgaeUri() != null ? contact.getImgaeUri().toString() : null);

        db.insert(TABLE_CONTACTS, null, values);
        db.close();

    }

    public ContactDetailsBean getContact(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {KEY_ID, KEY_NAME, KEY_PASSWORD, KEY_IMAGEURI}, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null,null);
         //     boolean distinct, String table,                        String[] columns,                   String selection,       String[] selectionArgs,      String groupBy, String having,String  orderBy, String limit
        // Cursor is a temporary buffer area that holds results returned from a database query
        // ,go to each row like select statement
        if(cursor != null)
            cursor.moveToFirst(); // moving to first row or record

            ContactDetailsBean contact = new ContactDetailsBean(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Uri.parse(cursor.getString(3)));
            db.close();
            cursor.close();
        return contact;
    }

    public void deleteContact(ContactDetailsBean contact){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID  + "=?", new String[] {String.valueOf(contact.getId())});
        //        String table, String whereclause,        String where Args
        db.close();
    }

    public int getContactsCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        int count=cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }

    public int updateContact(ContactDetailsBean contact){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getuName());
        values.put(KEY_PASSWORD, contact.getUPassword());
        values.put(KEY_IMAGEURI, contact.getImgaeUri().toString());

        int rowsAffected = db.update(TABLE_CONTACTS, values, KEY_ID  + "=?", new String[] {String.valueOf(contact.getId())});
                                         // table,   values,          where,   String[] whereArgs
        db.close();

        return  rowsAffected;
    }

    public List<ContactDetailsBean> getAllContacts(){
        List<ContactDetailsBean> contacts = new ArrayList<ContactDetailsBean>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM" + TABLE_CONTACTS, null);

        if(cursor.moveToFirst()){
            //ContactDetailsBean contact;    // removed this for optimization
            do{
                //contact = new ContactDetailsBean(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Uri.parse(cursor.getString(3)));
                contacts.add(new ContactDetailsBean(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Uri.parse(cursor.getString(3))));
            }
            while(cursor.moveToNext()); // while moving to next row
        }
        cursor.close();
        db.close();
        return contacts;
    }
    //for loop: for(cursor.moveToFirst(); cusrsor.isAfterLast(); cursor.moveToNext())


}
