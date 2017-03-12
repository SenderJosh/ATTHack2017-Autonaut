package hackathon.att.autonaut;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

public class ScrollRoutesActivity extends AppCompatActivity {

    public static final AscendDescendFinderOuter ascDescFO = new AscendDescendFinderOuter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_routes);

        final ListView listview = (ListView)findViewById(R.id.routesList);

        ImageButton addRouteButton = (ImageButton)findViewById(R.id.addButton);
        ImageButton sortByAlph = (ImageButton)findViewById(R.id.alphSort);
        ImageButton sortByDate = (ImageButton)findViewById(R.id.timeSort);

        Button but = (Button)findViewById(R.id.buttonDashboard);
        but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ScrollRoutesActivity.this, DashboardActivity.class));
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                AlertDialog.Builder noText = new AlertDialog.Builder(ScrollRoutesActivity.this);
                noText.setMessage("What would you like to do?");
                noText.setTitle("autonaut");
                final int position = i;
                noText.setPositiveButton("View Info", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Main.instance.userInfo.setCurrentViewedItem(position);
                        startActivity(new Intent(ScrollRoutesActivity.this, RouteView.class));
                    }
                });
                noText.setNegativeButton("Track Route", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //track route php stuff
                    }
                });
                noText.setCancelable(true);
                noText.create().show();
            }
        });

        addRouteButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScrollRoutesActivity.this);
                builder.setTitle("autonaut");
                builder.setMessage("Set your starting location");

                final EditText input = new EditText(ScrollRoutesActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        final String sname = input.getText().toString();

                        AlertDialog.Builder builder = new AlertDialog.Builder(ScrollRoutesActivity.this);
                        builder.setTitle("autonaut");
                        builder.setMessage("Set your ending location");

                        final EditText input2 = new EditText(ScrollRoutesActivity.this);

                        input2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                        builder.setView(input2);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final String ename = input2.getText().toString();
                                if(!sname.isEmpty() && !ename.isEmpty())
                                {
                                    //DO PHP SHIT
                                    AddRoutePHP addRoute = new AddRoutePHP(Main.instance.userInfo.getUID(), sname, ename);
                                    addRoute.start();
                                    Handler h = new Handler();
                                    h.postDelayed(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            SetTrackingPHP stp = new SetTrackingPHP(Main.instance.userInfo.getUID(), sname, ename);
                                            stp.start();
                                            Handler h1 = new Handler();
                                            h1.postDelayed(new Runnable()
                                            {
                                                @Override
                                                public void run()
                                                {
                                                    UserInfo.currentEName = ename;
                                                    UserInfo.currentSName = sname;
                                                    startActivity(new Intent(ScrollRoutesActivity.this, TrackActivity.class));
                                                }
                                            }, 1500);
                                        }
                                    }, 1500);
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
        sortByAlph.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //defaultly sorted with alpha
                listview.setAdapter(null);
                ascDescFO.getRouteInfo().clear();
                ascDescFO.toggleSortAlph();
                GetAllRouteInfo gari = new GetAllRouteInfo(Main.instance.userInfo.getUID(), ascDescFO);
                gari.start();

                Handler h = new Handler();
                h.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ListView listview = (ListView)findViewById(R.id.routesList);
                        listview.setAdapter(new AdapterListRoutes(ScrollRoutesActivity.this, ascDescFO));
                    }
                }, 1500);
            }
        });
        sortByDate.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                listview.setAdapter(null);
                ascDescFO.getRouteInfo().clear();
                ascDescFO.toggleAscDesc();
                GetAllRouteInfo gari = new GetAllRouteInfo(Main.instance.userInfo.getUID(), ascDescFO);
                gari.start();

                Handler h = new Handler();
                h.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ListView listview = (ListView)findViewById(R.id.routesList);
                        listview.setAdapter(new AdapterListRoutes(ScrollRoutesActivity.this, ascDescFO));
                    }
                }, 1500);
            }
        });

        GetAllRouteInfo gari = new GetAllRouteInfo(Main.instance.userInfo.getUID(), ascDescFO);
        gari.start();

        Handler h = new Handler();
        h.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                ListView listview = (ListView)findViewById(R.id.routesList);
                listview.setAdapter(new AdapterListRoutes(ScrollRoutesActivity.this, ascDescFO));
            }
        }, 1500);
    }
}
