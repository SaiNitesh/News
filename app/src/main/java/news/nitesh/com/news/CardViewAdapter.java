package news.nitesh.com.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

import butterknife.ButterKnife;
import news.nitesh.com.news.R2;
import news.nitesh.com.news.models.CardViewBean;

import static news.nitesh.com.news.R2.id.progressBar;

/**
 * Created by nitesh on 12/27/2016.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    CardViewBean bean;
    Context context;

    public CardViewAdapter(CardViewBean bean, Context context) { //RecyclerAdapter(List<RecyclerModel> recyclerList)

        this.bean = bean;
        this.context = context;

        Log.i("const", "image:" + bean.getTeamName().toString() + "titile:" + bean.getTeamImage());
    }

    @Override
    public CardViewAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //getting Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview_row, parent, false);
        // getting views from Layout
        CardViewAdapter.CardViewHolder cardViewHolder = new CardViewAdapter.CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(final CardViewAdapter.CardViewHolder holder, int position) {

        //holder.cardViewText.setText(bean[position]);  if you have array list you can use "position"

        holder.itemView.setTag(bean);
        holder.cardViewText.setText(bean.getTeamName());
        Picasso.with(context)
                .load(bean.getTeamImage())
                .into(holder.cardviewImage, new Callback() {   // Callback() make sures the image is loaded, cuz picasso is done with internet
                    @Override
                    public void onSuccess() {

                        holder.progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class CardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.cardView_progress)
        ProgressBar progressBar;

        @BindView(R2.id.cardView_Image)
        ImageView cardviewImage;

        @BindView(R2.id.cardView_Text)
        TextView cardViewText;

        public CardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /*public void populate(Context context, CardViewBean bean) {

            itemView.setTag(bean);
            cardViewText.setText(bean.getTeamName());
            Picasso.with(context)
                    .load(bean.getTeamImage())
                    .into(cardviewImage, new Callback() {   // Callback() make sures the image is loaded, cuz picasso is done with internet
                        @Override
                        public void onSuccess() {

                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError() {

                        }
                    });

        }*/


    }
}
