package news.nitesh.com.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;



/**
 * Created by nitesh on 12/15/2016.
 */



public  class RecyclerTuchLisenr implements RecyclerView.OnItemTouchListener {

    public  static interface ClickListener {

        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }


    private GestureDetector gestureDetector;
    RecyclerView recyclerView;
    ClickListener clickListener;




    public RecyclerTuchLisenr(Context applicationContext, final RecyclerView recyclerView, final ClickListener clickListener) {

        this.recyclerView= recyclerView;
        this.clickListener= clickListener;

        gestureDetector = new GestureDetector(applicationContext, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    Log.i("LongC",child.toString());
                }

            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildAdapterPosition(child));
            Log.i("interClick",child.toString());
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        View ChildView = rv.findChildViewUnder(e.getX(), e.getY());
        rv.getChildAdapterPosition(ChildView);

        Log.i("interTouch",ChildView.toString());

        YoYo.with(Techniques.FlipInX)
                .duration(700)
                .playOn(ChildView);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


}
