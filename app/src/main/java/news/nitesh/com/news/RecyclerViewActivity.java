package news.nitesh.com.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by nitesh on 12/15/2016.
 */
public class RecyclerViewActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    int[] imageId={R.drawable.ic_fb,R.drawable.ic_google};
    String[] title= {"facebook","google"};
    String[] thumbnail= {"1","2"};

    //private List<RecyclerModel> recyclerList = new ArrayList<>();
    //recyclerList.add(new RecyclerModel(imageId,title,thumbnail));



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        adapter= new RecyclerAdapter(imageId,title,thumbnail);
        layoutManager = new LinearLayoutManager(getApplicationContext()); // always add "getApplicationContext" instead of this

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        // "RecyclerDividerItem" is an divider item decoration class
        recyclerView.addItemDecoration(new RecyclerDividerItem(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(adapter);

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


        recyclerView.addOnItemTouchListener(new RecyclerTuchLisenr(getApplicationContext(), recyclerView, clickn)
           );

    }
}
