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

public class SetTrackingPHP extends Thread {

    int uid;
    String sname, ename;

    public SetTrackingPHP(int uid, String sname, String ename)
    {
        this.uid = uid;
        this.sname = sname;
        this.ename = ename;
    }

    @Override
    public void run()
    {
        URL url = null;
        try
        {
            /*
            $carorapp = $_REQUEST['carorapp'];
            $addTF = $_REQUEST['addtf'];
            $uid = $_REQUEST['uid'];
            $sname = $_REQUEST['sname'];
            $ename = $_REQUEST['ename'];
            $finishedIndicator = $_REQUEST['indi'];
             */
            url = new URL("http://jsn9.ics321.com/MyAssignments/Tracking.php");
            HttpURLConnection cl = (HttpURLConnection)url.openConnection();
            cl.setRequestMethod("POST");
            cl.setDoOutput(true);

            String postReq = "carorapp=app&addtf=t&uid=" + this.uid + "&sname=" + this.sname
                    + "&ename=" + this.ename + "&indi=1";

            DataOutputStream os = new DataOutputStream(cl.getOutputStream());
            os.writeBytes(postReq);
            os.flush();
            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            String ret = br.readLine();
            Log.d("DID SETTRACKING PHP", ret);
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
