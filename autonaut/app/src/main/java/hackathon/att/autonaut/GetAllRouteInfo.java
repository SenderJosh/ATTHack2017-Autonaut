package hackathon.att.autonaut;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by lichc on 3/11/2017.
 */

public class GetAllRouteInfo extends Thread {

    private int uid;
    private AscendDescendFinderOuter ascDescFO;

    public GetAllRouteInfo(int uid, AscendDescendFinderOuter ascDescFO)
    {
        this.uid = uid;
        this.ascDescFO = ascDescFO;
    }

    @Override
    public void run()
    {
        URL url = null;
        try
        {
            url = new URL("http://jsn9.ics321.com/MyAssignments/GetRoutes.php");
            HttpURLConnection cl = (HttpURLConnection)url.openConnection();
            cl.setRequestMethod("POST");
            cl.setDoOutput(true);

            String postReq = "uid=" + uid + "&SortBy=" + ascDescFO.getCurrentSortMethod() + "&AscDesc=" + ascDescFO.getCurrentAscDescMethod();

            DataOutputStream os = new DataOutputStream(cl.getOutputStream());
            os.writeBytes(postReq);
            os.flush();
            os.close();

            int responseCode = cl.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            String startName = br.readLine(), endName = "", date = "",
                    miles = "", timeSeconds = "", timeMinutes = "", money = "", gas = "";
            if(startName != null)
            {
                if(!startName.contains("ERROR"))
                {
                    endName = br.readLine();
                    date = br.readLine();
                    miles = br.readLine();
                    timeSeconds = br.readLine();
                    timeMinutes = br.readLine();
                    money = br.readLine();
                    gas = br.readLine();
                    ascDescFO.getRouteInfo().add(new RouteInfo(date, startName, endName, miles, timeSeconds, timeMinutes, money, gas));
                    startName = br.readLine();
                    while(startName != null && !startName.isEmpty())
                    {
                        endName = br.readLine();
                        date = br.readLine();
                        miles = br.readLine();
                        timeSeconds = br.readLine();
                        timeMinutes = br.readLine();
                        money = br.readLine();
                        gas = br.readLine();
                        ascDescFO.getRouteInfo().add(new RouteInfo(date, startName, endName, miles, timeSeconds, timeMinutes, money, gas));
                        startName = br.readLine();
                    }
                    Log.d("TESTSIZE OF ALL SIZE", ascDescFO.getRouteInfo().size() + "");
                }
            }
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (ProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
