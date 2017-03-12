package hackathon.att.autonaut;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        ((TextView)findViewById(R.id.textviewWhereToWhere)).setText(UserInfo.currentSName + " to " + UserInfo.currentEName);

        ImageView iv = ((ImageView)findViewById(R.id.trackingBlueSquares));
        Animation animRot = AnimationUtils.loadAnimation(TrackActivity.this, R.anim.rotate);
        animRot.setFillAfter(true);
        iv.startAnimation(animRot);

        final TrackingPHP tPHP = new TrackingPHP(Main.instance.userInfo.getUID(), UserInfo.currentSName, UserInfo.currentEName);
        tPHP.start();
        final Handler h = new Handler();
        h.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if(!TrackingPHP.done)
                {
                    TrackingPHP.working = true;
                    h.postDelayed(this, 10000);
                }
                else
                {
                    finish();
                    startActivity(new Intent(TrackActivity.this, ScrollRoutesActivity.class));
                }
            }
        }, 10000);
    }
}
