package news.nitesh.com.news;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by nitesh on 12/14/2016.
 */
public class AnimationUtils {

    public static void animate(ListView view, boolean goesDown) {


        view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                ObjectAnimator animatorTranslateY;
                ObjectAnimator animatorTranslateX;
                int prevVisibleItem=0;
                boolean goesDown= true;

                /*animatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", goesDown==true ? 300 : -300, 0);
                animatorTranslateY.setDuration(1000);
                animatorTranslateY.start();*/

                ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.5F, 0.8F, 1.0F);
                animatorScaleY.setDuration(1000);
                animatorScaleY.start();

               /* animatorTranslateX = ObjectAnimator.ofFloat(view, "translationX", -50, 50, -30, 30, -20, 20, -5, 5, 0);
                animatorTranslateX.setDuration(1000);
                animatorTranslateX.start();*/

            }
        });

        /*ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(view, "translationY",goesDown == true ? 300 : -300, 0);
        animatorTranslateY.setDuration(1000);
        animatorTranslateY.start();*/



        /*YoYo.with(Techniques.RubberBand)
                .duration(1000)
                .playOn(holder.itemView);*/
//        AnimatorSet animatorSet = new AnimatorSet();
//        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX" ,0.5F, 0.8F, 1.0F);
//        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(holder.itemView, "scaleY", 0.5F, 0.8F, 1.0F);
//        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown == true ? 300 : -300, 0);
//        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -50, 50, -30, 30, -20, 20, -5, 5, 0);
//        animatorSet.playTogether(animatorTranslateX, animatorTranslateY, animatorScaleX, animatorScaleY);
//        animatorSet.setInterpolator(new AnticipateInterpolator());
//        animatorSet.setDuration(1000);
//        animatorSet.start();

    }


     // for Toolbar and tabs
    public static void animateToolbarDroppingDown(View containerToolbar) {

        containerToolbar.setRotationX(-90);
        containerToolbar.setAlpha(0.2F);
        containerToolbar.setPivotX(0.0F);
        containerToolbar.setPivotY(0.0F);
        Animator alpha = ObjectAnimator.ofFloat(containerToolbar, "alpha", 0.2F, 0.4F, 0.6F, 0.8F, 1.0F).setDuration(4000);
        Animator rotationX = ObjectAnimator.ofFloat(containerToolbar, "rotationX", -90, 60, -45, 45, -10, 30, 0, 20, 0, 5, 0).setDuration(8000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(alpha, rotationX);
        animatorSet.start();
    }
}
