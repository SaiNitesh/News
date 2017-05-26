package news.nitesh.com.news;

/**
 * Created by nitesh on 11/26/2016.
 */
public class NewsItem {
    private String newsHeading;
    private String newsDesc;
    //private String newsDescSmall;
    private String time;
    private String date;
    private String url;
    private int imageId;

    public NewsItem(String newsHeading, String newsDesc,  String time, String date, String url, int imageId) {
        this.newsHeading = newsHeading;
        this.newsDesc = newsDesc;
        this.time = time;
        this.date = date;
        this.url = url;
        this.imageId = imageId;
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public int getImageId() {
        return imageId;
    }
}
