package news.nitesh.com.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import news.nitesh.com.news.Three3DAnimationsCube.RubikCubeAnimationExampleActivity;

/**
 * Created by Nitesh on 10/15/2017.
 */

public class MainActivity3 extends AppCompatActivity {

    Button button40 = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);


        button40 = (Button) findViewById(R.id.b40);
        button40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RubikCubeAnimationExampleActivity.class);
                startActivity(i);
            }
        });
    }
}
