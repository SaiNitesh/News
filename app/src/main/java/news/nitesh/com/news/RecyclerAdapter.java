package news.nitesh.com.news;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nitesh on 12/15/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    String[]  title,thumbnail;
    int[] imageId;

    public RecyclerAdapter(int[] imageId,String[] title, String[] thumbnail) { //RecyclerAdapter(List<RecyclerModel> recyclerList)

        Log.i("const","image:"+imageId.toString()+"titile:"+title.toString()+"thumb:"+thumbnail.toString());

        this.imageId= imageId;
        this.title= title;
        this.thumbnail=thumbnail;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //getting Layout
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_row, parent,false);
        // getting views from Layout
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        Log.i("holderData","image:"+imageId+"titile:"+title+"thumb:"+thumbnail);

        holder.recyclerImage.setImageResource(imageId[position]);
        holder.recyclerText1.setText(title[position]);
        holder.recyclerText2.setText(thumbnail[position]);

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView recyclerImage;
        TextView recyclerText1, recyclerText2;

        // Here constructor is called from,  "RecyclerViewHolder" method to get the below views
        public RecyclerViewHolder(View itemView) {
            super(itemView);

            recyclerImage= (ImageView) itemView.findViewById(R.id.recyclerImage);
            recyclerText1= (TextView) itemView.findViewById(R.id.recyclerText1);
            recyclerText2= (TextView) itemView.findViewById(R.id.recyclerText2);
        }

    }
}
