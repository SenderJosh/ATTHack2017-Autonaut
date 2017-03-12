package hackathon.att.autonaut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by lichc on 3/11/2017.
 */

class AdapterListRoutes extends BaseAdapter {

    Context context;
    AscendDescendFinderOuter data;
    private static LayoutInflater inflater = null;

    public AdapterListRoutes(Context context, AscendDescendFinderOuter data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.getRouteInfo().size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.getRouteInfo().get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    int whatLayout = 0; //Mod is 0 = green, Mod is 1 = blue, Mod is 2 = pink
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
        {
            if(whatLayout%3 == 0)
            {
                vi = inflater.inflate(R.layout.greenbar, null);
                TextView text = (TextView) vi.findViewById(R.id.dateGreen);
                text.setText("March 11");
                TextView textName = (TextView) vi.findViewById(R.id.name);
                textName.setText(data.getRouteInfo().get(position).startName + " to " + data.getRouteInfo().get(position).endName);
            }
            else if(whatLayout%3 == 1)
            {
                vi = inflater.inflate(R.layout.bluebar, null);
                TextView text = (TextView) vi.findViewById(R.id.dateBlue);
                text.setText("March 11");
                TextView textName = (TextView) vi.findViewById(R.id.name);
                textName.setText(data.getRouteInfo().get(position).startName + " to " + data.getRouteInfo().get(position).endName);
            }
            else
            {
                vi = inflater.inflate(R.layout.pinkbar, null);
                TextView text = (TextView) vi.findViewById(R.id.datePink);
                text.setText("March 11");
                TextView textName = (TextView) vi.findViewById(R.id.name);
                textName.setText(data.getRouteInfo().get(position).startName + " to " + data.getRouteInfo().get(position).endName);

            }
            whatLayout++;
        }
        return vi;
    }
}