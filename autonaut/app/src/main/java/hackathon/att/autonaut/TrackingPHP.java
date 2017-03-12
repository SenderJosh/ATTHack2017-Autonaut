package hackathon.att.autonaut;

import android.support.v4.app.NotificationCompatSideChannelService;
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

public class TrackingPHP extends Thread {

    public static boolean done = false;
    public static boolean working = true;

    int uid;
    String sname, ename;

    public TrackingPHP(int uid, String sname, String ename)
    {
        this.uid = uid;
        this.sname = sname;
        this.ename = ename;
    }

    @Override
    public void run()
    {
        while(!done)
        {
           while(working)
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

                   String postReq = "carorapp=app&addtf=f&uid=" + this.uid + "&sname=" + this.sname
                           + "&ename=" + this.ename + "&indi=0";

                   DataOutputStream os = new DataOutputStream(cl.getOutputStream());
                   os.writeBytes(postReq);
                   os.flush();
                   os.close();

                   BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                   String ret = br.readLine();
                   if(ret != null)
                   {
                       Log.d("DEBUG WHAT FUCK", ret);
                       if(ret.equalsIgnoreCase("0")) //DONE :D
                       {
                           done = true;
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
               working = false;
           }
        }
    }

}
