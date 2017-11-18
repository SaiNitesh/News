package news.nitesh.com.news.Entities;

/**
 * Created by nitesh on 12/27/2016.
 */

public class CardViewBean {
    private String teamName;
    private int teamImage;

    public CardViewBean(String teamName, int teamImage) {
        this.teamName = teamName;
        this.teamImage = teamImage;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(int teamImage) {
        this.teamImage = teamImage;
    }
}
