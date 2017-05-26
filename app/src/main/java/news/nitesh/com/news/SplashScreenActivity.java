package news.nitesh.com.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.*;
import android.widget.ImageView;

/**
 * Created by nitesh on 12/28/2016.
 */

public class SplashScreenActivity extends AppCompatActivity {

    ImageView splashImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashImageView = (ImageView) findViewById(R.id.splashImageView);

        /*AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);*/
        Animation anim = android.view.animation.AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_left);
        splashImageView.setAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));

                // to end the application from Main activity(when you press back from Main activity, splash screen will be invisible)
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //splashImageView.startAnimation(alphaAnimation);

    }
}
