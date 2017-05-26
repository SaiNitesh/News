package news.nitesh.com.news;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import news.nitesh.com.news.models.MyParcelableBean;

/**
 * Created by nitesh on 12/11/2016.
 */
public class MyParcelableAdapter extends BaseAdapter {

    private ArrayList<MyParcelableBean> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public MyParcelableAdapter(Context context, ArrayList<MyParcelableBean> listData) {

        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.parcel_my_second_adapter, null);
            holder = new ViewHolder();
            holder.image2 = (ImageView) convertView.findViewById(R.id.image2);
            holder.id2 = (TextView) convertView.findViewById(R.id.id2);
            holder.name2 = (TextView) convertView.findViewById(R.id.name2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Log.i("finalData",listData.get(0).getName().toString());

        holder.image2.setImageURI(listData.get(position).getImage());

        String id=String.valueOf(listData.get(position).getMyId()); // need to convert into string for TextView, because it is INTEGER
        holder.id2.setText(id);
        holder.name2.setText(listData.get(position).getName());

        if (position % 2 == 1) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.dim_foreground_disabled_material_dark));
            //dim_foreground_disabled_material_dark ==> you chance by adding custom colors in res/values/colors
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.dim_foreground_disabled_material_light));
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView image2;
        TextView id2;
        TextView name2;
    }
}
