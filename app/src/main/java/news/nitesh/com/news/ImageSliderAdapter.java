package news.nitesh.com.news;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by nitesh on 12/13/2016.
 */
public class ImageSliderAdapter extends PagerAdapter {

    private int[] image_resources = {R.drawable.ic_youtube,R.drawable.ic_google,R.drawable.ic_fb};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public ImageSliderAdapter(Context ctx) {
        this.ctx=ctx;
    }


    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View imageLayout = layoutInflater.inflate(R.layout.activity_image_layout,container,false);
        ImageView sliderImage = (ImageView) imageLayout.findViewById(R.id.sliderImage);
        sliderImage.setImageResource(image_resources[position]);
        container.addView(imageLayout);

        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object); // need to remove the object when you move to another slide,
                                                      // else it will weighs so much memory of keeping all those slides when you move slides forward.
    }

}
