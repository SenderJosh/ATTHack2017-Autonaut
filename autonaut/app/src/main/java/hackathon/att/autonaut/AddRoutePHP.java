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

public class AddRoutePHP extends Thread {

    int uid;
    String sname, ename;

    public AddRoutePHP(int uid, String sname, String ename)
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
            url = new URL("http://jsn9.ics321.com/MyAssignments/AddRoute.php");
            HttpURLConnection cl = (HttpURLConnection)url.openConnection();
            cl.setRequestMethod("POST");
            cl.setDoOutput(true);

            String postReq = "uid=" + uid + "&startname=" + this.sname + "&endname=" + this.ename;

            DataOutputStream os = new DataOutputStream(cl.getOutputStream());
            os.writeBytes(postReq);
            os.flush();
            os.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            String ret = br.readLine();
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
