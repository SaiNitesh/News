package news.nitesh.com.news.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nitesh on 12/11/2016.
 */
public class MyParcelableBean implements Parcelable {

    private int myId;
    private String name;
    private Uri image;


    public MyParcelableBean(int myId, String name, Uri image) {
        this.myId = myId;
        this.name = name;
        this.image = image;
    }


    //constructor used for parcel
    public MyParcelableBean(Parcel parcel){
        myId = parcel.readInt();
        name = parcel.readString();
        image = parcel.readParcelable(MyParcelableBean.class.getClassLoader());
        // featured = (Boolean) parcel.readValue(null);

    }


    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //write object values to parcel for storage
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(myId);
        dest.writeString(name);
        dest.writeParcelable(image, flags);
    }


    //creator - used when un-parceling our parcle (creating the object)
    public static final Creator<MyParcelableBean> CREATOR = new Creator<MyParcelableBean>() {
        @Override
        public MyParcelableBean createFromParcel(Parcel in) {
            return new MyParcelableBean(in);
        }

        @Override
        public MyParcelableBean[] newArray(int size) {
            return new MyParcelableBean[size];
        }
    };

    @Override
    public String toString() {
        return super.toString();
    }
}


