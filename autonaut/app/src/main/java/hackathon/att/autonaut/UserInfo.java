package hackathon.att.autonaut;

import android.os.Handler;

/**
 * Created by lichc on 3/10/2017.
 */

public class UserInfo{

    private String email, pass;
    private int uid, currentViewedItem;
    private boolean finishedPost = false;

    public static String currentSName, currentEName;

    public UserInfo(String email, String pass)
    {
        this.email = email;
        this.pass = pass;
    }

    public void login()
    {
        Login login = new Login(this);
        login.start();
    }

    public void setCurrentViewedItem(int item)
    {
        this.currentViewedItem = item;
    }

    public int getCurrentViewedItem()
    {
        return this.currentViewedItem;
    }

    public void setFinsihedPost(boolean fin)
    {
        this.finishedPost = fin;
    }

    public boolean getFinishedPost()
    {
        return this.finishedPost;
    }

    public int getUID()
    {
        return this.uid;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPass()
    {
        return this.pass;
    }

    public void setUID(int uid)
    {
        this.uid = uid;
    }
}
