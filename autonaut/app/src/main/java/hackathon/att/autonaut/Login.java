package hackathon.att.autonaut;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by lichc on 3/10/2017.
 */

public class Login extends Thread {

    UserInfo userinfo;

    public Login(UserInfo userInfo)
    {
        this.userinfo = userInfo;
    }

    @Override
    public void run()
    {
        URL url = null;
        try
        {
            url = new URL("http://jsn9.ics321.com/MyAssignments/LoginSuccess.php");
            HttpURLConnection cl = (HttpURLConnection)url.openConnection();
            cl.setRequestMethod("POST");
            cl.setDoOutput(true);

            String postReq = "email=" + userinfo.getEmail() + "&pass=" + userinfo.getPass();

            DataOutputStream os = new DataOutputStream(cl.getOutputStream());
            os.writeBytes(postReq);
            os.flush();
            os.close();

            int responseCode = cl.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            String ret = br.readLine();
            if(!ret.equalsIgnoreCase("error not found"))
            {
                userinfo.setUID(Integer.parseInt(ret));
                userinfo.setFinsihedPost(true);
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
