package news.nitesh.com.news;

/**
 * Created by nitesh on 12/5/2016.
 */
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class MyNewsListAdapter extends BaseAdapter implements Filterable{

    private ArrayList<MyNewsItem> listData;
    private ArrayList<MyNewsItem> tempData;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyNewsListAdapter(Context context, ArrayList<MyNewsItem> listData) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        tempData=listData;
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
            convertView = layoutInflater.inflate(R.layout.snippet_my_news_list_row, null);
            holder = new ViewHolder();
            holder.headline = (TextView) convertView.findViewById(R.id.newsHeadline);
            holder.pubDate = (TextView) convertView.findViewById(R.id.pubDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.headline.setText(listData.get(position).getHeadline());
        holder.pubDate.setText(listData.get(position).getPubDate());

        if (position % 2 == 1) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.dim_foreground_disabled_material_dark));
               //dim_foreground_disabled_material_dark ==> you chance by adding custom colors in res/values/colors
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.dim_foreground_disabled_material_light));
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {



        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listData=(ArrayList<MyNewsItem>)results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<MyNewsItem> FilteredList= new ArrayList<MyNewsItem>();
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = tempData;
                    results.count = tempData.size();
                }
                else {
                    for (int i = 0; i < tempData.size(); i++) {
                        MyNewsItem data = tempData.get(i);
                        if (data.getHeadline().toLowerCase().contains(constraint.toString()))  {
                            FilteredList.add(data);
                        }
                    }
                    results.values = FilteredList;
                    results.count = FilteredList.size();
                }
                return results;
            }
        };
        return filter;
    }

    static class ViewHolder {
        TextView headline;
        TextView pubDate;
    }

}
