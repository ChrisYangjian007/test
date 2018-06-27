package Models;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-07-25.
 */
public class SortBy implements Serializable {
    private String Property ;
    private Boolean Ascending =true;
    public String getProperty() {
        return Property;
    }
    public void setProperty(String property) {
        Property = property;
    }

    public Boolean getAscending() {
        return Ascending;
    }
    public void setAscending(Boolean ascending) {
        Ascending = ascending;
    }
    public String ToSqlString()
    {
        return " " + Property + (Ascending ? " asc " : " desc ");
    }
    public SortBy(String property)
    {
        Property = property;
    }
    public SortBy()
    { }
    public SortBy(String property, Boolean ascending)
    {
        Property = property;
        Ascending = ascending;
    }
}
