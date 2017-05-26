package news.nitesh.com.news;

/**
 * Created by nitesh on 11/29/2016.
 */
public class learnLanguage {

    private String name;
    private int imageId;
    private int tutrialNumber;

    public learnLanguage(String name, int imageId, int tutrialNumber) {
        this.name = name;
        this.imageId = imageId;
        this.tutrialNumber = tutrialNumber;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getTutrialNumber() {
        return tutrialNumber;
    }
}
