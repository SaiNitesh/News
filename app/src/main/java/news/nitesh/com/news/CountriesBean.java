package news.nitesh.com.news;

/**
 * Created by nitesh on 11/30/2016.
 */
public class CountriesBean {

    private String nm;
    private String cty;
    private String hse;
    private String yrs;

    public CountriesBean(String nm, String cty, String hse, String yrs) {
        this.nm = nm;
        this.cty = cty;
        this.hse = hse;
        this.yrs = yrs;
    }

    public String getNm() {
        return nm;
    }

    public String getCty() {
        return cty;
    }

    public String getHse() {
        return hse;
    }

    public String getYrs() {
        return yrs;
    }
}
