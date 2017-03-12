package hackathon.att.autonaut;

/**
 * Created by lichc on 3/11/2017.
 */

public class RouteInfo {

    String date, startName, endName, miles, timeSeconds, timeMinutes, money, gas;

    public RouteInfo(String date, String startName, String endName, String miles,
                     String timeSeconds, String timeMinutes, String money, String gas)
    {
        this.date = date;
        this.startName = startName;
        this.endName = endName;
        this.miles = miles;
        this.timeSeconds = timeSeconds;
        this.timeMinutes = timeMinutes;
        this.money = money;
        this.gas = gas;
    }


}
