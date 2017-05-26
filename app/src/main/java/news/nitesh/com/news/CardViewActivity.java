package news.nitesh.com.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import news.nitesh.com.news.models.CardViewBean;

/**
 * Created by nitesh on 12/27/2016.
 */

public class CardViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView_cardView;
    private  RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    CardViewBean bean= new CardViewBean("RealMadrid",R.drawable.ic_real_madrid);

    //private List<RecyclerModel> recyclerList = new ArrayList<>();
    //recyclerList.add(new RecyclerModel(imageId,title,thumbnail));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        recyclerView_cardView = (RecyclerView) findViewById(R.id.cardView_recycler_view);
        adapter= new CardViewAdapter(bean,this);
        layoutManager = new LinearLayoutManager(getApplicationContext()); // always add "getApplicationContext" instead of this

        recyclerView_cardView.setLayoutManager(layoutManager);
        recyclerView_cardView.setHasFixedSize(true);
        // "RecyclerDividerItem" is an divider item decoration class
       // recyclerView_cardView.addItemDecoration(new RecyclerDividerItem(this, LinearLayoutManager.VERTICAL));

        recyclerView_cardView.setAdapter(adapter);

        RecyclerTuchLisenr.ClickListener clickn= new RecyclerTuchLisenr.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                YoYo.with(Techniques.FlipInX)
                        .duration(700)
                        .playOn(view);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        };


        recyclerView_cardView.addOnItemTouchListener(new RecyclerTuchLisenr(getApplicationContext(), recyclerView_cardView, clickn)
        );

    }
}
