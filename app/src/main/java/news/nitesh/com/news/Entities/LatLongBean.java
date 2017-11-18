package news.nitesh.com.news.Entities;

/**
 * Created by nitesh on 12/24/2016.
 */
public class LatLongBean {

    private double latitude;
    private double longitude;

    public LatLongBean(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LatLongBean{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
