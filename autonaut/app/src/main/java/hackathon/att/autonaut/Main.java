package hackathon.att.autonaut;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Main extends AppCompatActivity {

    public static Main instance;

    public UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.Login);
        Button login = (Button)findViewById(R.id.FinalLogin);
        ImageView iv = (ImageView)(findViewById(R.id.trackingBlueSquares));
        EditText email = (EditText)(findViewById(R.id.email));
        EditText pass = (EditText)(findViewById(R.id.password));

        email.setVisibility(View.INVISIBLE);
        pass.setVisibility(View.INVISIBLE);
        login.setVisibility(View.INVISIBLE);
        boolean firstClicked = false;

        b.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Button b = (Button)findViewById(R.id.Login);
                Button login = (Button)findViewById(R.id.FinalLogin);
                ImageView iv = (ImageView)(findViewById(R.id.trackingBlueSquares));
                EditText email = (EditText)(findViewById(R.id.email));
                EditText pass = (EditText)(findViewById(R.id.password));

                Animation transparent = new AlphaAnimation(1.0f, 0.f);
                transparent.setDuration(1000);
                // Scaling
                Animation scale = new ScaleAnimation(iv.getScaleX(), iv.getScaleX()*7, iv.getScaleY(), iv.getScaleY(), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                // 1 second duration
                scale.setDuration(500);
                // Animation set to join both scaling and moving
                AnimationSet animSetIV = new AnimationSet(true);
                animSetIV.setFillAfter(true);
                animSetIV.setFillEnabled(true);
                animSetIV.addAnimation(scale);

                AnimationSet animSetBut = new AnimationSet(true);
                animSetBut.setFillAfter(true);
                animSetBut.setFillEnabled(true);
                animSetBut.addAnimation(transparent);
                // Launching animation set
                iv.startAnimation(animSetIV);
                b.startAnimation(animSetBut);
                b.setVisibility(View.GONE);

                login.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_in));
                email.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_in));
                pass.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_in));

                login.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                pass.setVisibility(View.VISIBLE);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Button login = (Button)findViewById(R.id.FinalLogin);
                ImageView iv = (ImageView)(findViewById(R.id.trackingBlueSquares));
                EditText email = (EditText)(findViewById(R.id.email));
                EditText pass = (EditText)(findViewById(R.id.password));

                if(!((email.getText().toString().trim().equals("") || email.getText() == null) || pass.getText().toString().trim().equals("") || email.getText() == null))
                {
                    login.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_out));
                    email.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_out));
                    pass.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_out));
                    login.setVisibility(View.INVISIBLE);
                    email.setVisibility(View.INVISIBLE);
                    pass.setVisibility(View.INVISIBLE);

                    Animation scale = new ScaleAnimation(iv.getScaleX(), iv.getScaleX()/7, iv.getScaleY(), iv.getScaleY(), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    // 1 second duration
                    scale.setDuration(500);
                    // Animation set to join both scaling and moving
                    AnimationSet animSetIV = new AnimationSet(true);
                    animSetIV.setFillAfter(true);
                    animSetIV.setFillEnabled(true);
                    animSetIV.addAnimation(scale);

                    iv.startAnimation(scale);

                    Animation animRot = AnimationUtils.loadAnimation(Main.this, R.anim.rotate);
                    animRot.setFillAfter(true);
                    iv.startAnimation(animRot);

                    userInfo = new UserInfo(email.getText().toString(), pass.getText().toString());
                    userInfo.login();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(userInfo.getFinishedPost())
                            {
                                finish();
                                startActivity(new Intent(Main.this, ScrollRoutesActivity.class));
                            }
                            else
                            {
                                (findViewById(R.id.Login)).callOnClick();
                            }
                        }
                    }, 2000);
                }
                else
                {
                    AlertDialog.Builder noText = new AlertDialog.Builder(instance);
                    noText.setMessage("Please input your login details");
                    noText.setTitle("autonaut");
                    noText.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // You don't have to do anything here if you just want it dismissed when clicked
                        }
                    });
                    noText.setCancelable(true);
                    noText.create().show();
                }
            }
        });



    }
}
