package news.nitesh.com.news;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by nitesh on 12/1/2016.
 */
public class ContactDetailsBean {

    private int id;
    private String uName;
    private String uPassword;
    private Uri imgaeUri;



    public ContactDetailsBean(int id,String uName, String uPassword, Uri imgaeUri ) {
        this.id = id;
        this.uName = uName;
        this.uPassword = uPassword;
        this.imgaeUri=imgaeUri;
    }



    public int getId() {
        return id;
    }

    public String getuName() {
        return uName;
    }

    public String getUPassword() {
        return uPassword;
    }

    public Uri getImgaeUri() {
        return imgaeUri;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public void setImgaeUri(Uri imgaeUri) {
        this.imgaeUri = imgaeUri;
    }

    //write object values to parcel for storage
   /* public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(id);
        dest.writeString(uName);
        dest.writeString(uPassword);
        dest.writeString(imgaeUri.toString());
    }

    //constructor used for parcel
    public ContactDetailsBean(Parcel parcel){
        id = parcel.readInt();
        uName = parcel.readString();
        uPassword = parcel.readString();
        imgaeUri = Uri.parse(parcel.readString());
       // featured = (Boolean) parcel.readValue(null);

    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<ContactDetailsBean> CREATOR = new Parcelable.Creator<ContactDetailsBean>(){

        @Override
        public ContactDetailsBean createFromParcel(Parcel parcel) {
            return new ContactDetailsBean(parcel);
        }

        @Override
        public ContactDetailsBean[] newArray(int size) {
            return new ContactDetailsBean[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }

*/
}
