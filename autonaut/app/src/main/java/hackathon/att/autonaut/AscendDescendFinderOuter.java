package hackathon.att.autonaut;

import java.util.ArrayList;

/**
 * Created by lichc on 3/11/2017.
 */

public class AscendDescendFinderOuter {

    private boolean sortAlph = true; //If false, it's sorting by time
    private boolean ascDesc = false; //true = ascending
    private ArrayList<RouteInfo> ri = new ArrayList<RouteInfo>();

    public AscendDescendFinderOuter(){}

    public String getCurrentSortMethod()
    {
        return (this.sortAlph) ? "alph" : "date";
    }

    public String getCurrentAscDescMethod()
    {
        return (this.ascDesc) ? "asc" : "desc";
    }

    public ArrayList<RouteInfo> getRouteInfo()
    {
        return this.ri;
    }

    public boolean getCurrentAscDesc()
    {
        return this.ascDesc;
    }

    public void toggleAscDesc()
    {
        this.ascDesc = (this.ascDesc) ? false : true;
    }

    public void toggleAscDesc(boolean bool)
    {
        this.ascDesc = bool;
    }

    public void toggleSortAlph()
    {
        this.sortAlph = (this.sortAlph) ? false : true;
    }
    public void setSortAlph(boolean bool)
    {
        this.sortAlph = bool;
    }
}
