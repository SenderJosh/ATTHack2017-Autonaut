package hackathon.att.autonaut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RouteView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_view);
        RouteInfo info = ScrollRoutesActivity.ascDescFO.getRouteInfo().get(Main.instance.userInfo.getCurrentViewedItem());
        String start = info.startName;
        String end = info.endName;
        ((TextView)findViewById(R.id.name)).setText(start + " to " + end);
        ((TextView)findViewById(R.id.moneyNumber)).setText(info.money.substring(0, info.gas.length()-2));
        ((TextView)findViewById(R.id.gasNumber)).setText(info.gas.substring(0, info.gas.length()-2));
        ((TextView)findViewById(R.id.milesNumberTotal)).setText(info.miles);
        ((TextView)findViewById(R.id.timeNumber)).setText(info.timeMinutes + ":" + (Integer.parseInt(info.timeSeconds)%60));
    }
}
