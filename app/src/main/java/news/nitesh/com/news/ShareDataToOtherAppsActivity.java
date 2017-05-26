package news.nitesh.com.news;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by nitesh on 12/29/2016.
 */

public class ShareDataToOtherAppsActivity extends FragmentActivity {

    EditText shareDataEdit;
    Button sendDataButton, shareDataButton;
    ImageView shareImageView;
    private Animator mCurrentAnimator;
    Button shareImageButton;
    Button shareMultiImageButton;
    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_data_to_apps);

        shareDataEdit = (EditText) findViewById(R.id.shareDataEdit);
        sendDataButton = (Button) findViewById(R.id.sendDataButton);
        shareDataButton = (Button) findViewById(R.id.shareDataButton);
        shareImageView = (ImageView) findViewById(R.id.shareImageView);
        shareImageButton = (Button) findViewById(R.id.shareImageButton);
        shareMultiImageButton = (Button) findViewById(R.id.shareMultiImageButton);

        /*The most straightforward and common use of the ACTION_SEND action
        is sending text content from one activity to another.
        Optionally, you can set some standard extras for the intent to send data to Email applications
        like GMail App: EXTRA_EMAIL, EXTRA_CC, EXTRA_BCC, EXTRA_SUBJECT. If the receiving application
        is not designed to use them, it simply ignores them.

        */

        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message;
                message = shareDataEdit.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND); //cuz we are sending the data
                intent.putExtra(Intent.EXTRA_TEXT, message); //(name, value)
                intent.setType("text/plain"); // mime type
                startActivity(intent);
            }
        });

        shareDataButton.setOnClickListener(new View.OnClickListener() {
            String shareBody = "Here is the share content body";

            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });


        Resources i = shareImageView.getResources();
        String imageUri = "drawable://" + R.drawable.ic_real_madrid;
        //shareImageView.setBackgroundResource(R.drawable.te7);

        Uri path = Uri.parse("android.resource://news.nitesh.com.news/" + R.drawable.ic_real_madrid);
        Uri otherPath = Uri.parse("android.resource://news.nitesh.com.news/drawable/icon");

        String pathA = path.toString();
        String pathB = otherPath.toString();

        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //secondImage.setVisibility(View.VISIBLE);  // if you have second image in this layout it will be invisible
                //seconImage.setVisibility(View.GONE);


                zoomImageFromThumb(shareImageView, R.drawable.ic_real_madrid);
                shareDataEdit.setVisibility(v.GONE);
                shareDataButton.setVisibility(v.GONE);
                sendDataButton.setVisibility(v.GONE);

            }
        });

        shareImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {

                }
                if (action == MotionEvent.ACTION_UP) {

                }

                return false;
            }
        });


        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);


        shareImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String imagePath = Environment.getExternalStorageDirectory()
                        + "/pgguru/" + "/pic1.png";
                File imageFileToShare = new File(imagePath);
                // Get the URI of the image 'pic1.png' exists under pgguru right under SD card
                Uri uri = Uri.fromFile(imageFileToShare);
                // Set the action to be performed i.e 'Send Data'*/
                Uri uri = Uri.parse("android.resource://news.nitesh.com.news/" + R.drawable.ic_real_madrid);

                /*Log.i("uri", uri.toString());
                if(getExtension(uri)==".jpeg" || getExtension(uri)=="") {
                    Log.i("extens", getExtension(uri));
                    String uriToString=uri.toString()+".jpeg";
                    Log.i("urito",uriToString);
                    uri=Uri.parse(uriToString);
                }*/

                Intent sendIntent = new Intent(Intent.ACTION_SEND);

                // Set the type of data i.e 'image/* which means image/png, image/jpg, image/jpeg etc.,'
                sendIntent.setType("image/*");

                /*MimeTypeMap mime = MimeTypeMap.getSingleton();
                String type = mime.getExtensionFromMimeType(getContentResolver().getType(uri));
                Log.i("mime",type+" ");
                //getContentResolver().getType(uri) gives "image/jpeg"*/
                // Add the URI holding a stream of data
                sendIntent.putExtra(Intent.EXTRA_STREAM, uri);

                // Launches the activity; Open 'Gallery' if you set it as default app to handle Image
                startActivity(sendIntent);
            }
        });


        shareMultiImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String imagePath1 = Environment.getExternalStorageDirectory()
                        + "/pgguru/" + "/pic1.png";
                String imagePath2 = Environment.getExternalStorageDirectory()
                        + "/pgguru/" + "/pic2.png";
                // Multiple Images' file objects
                File image1FileToShare = new File(imagePath1);
                File image2FileToShare = new File(imagePath2);
                // Get URI of Image's location
                Uri image1URI = Uri.fromFile(image1FileToShare);
                Uri image2URI = Uri.fromFile(image2FileToShare);*/

                Uri image1URI = Uri.parse("android.resource://news.nitesh.com.news/" + R.drawable.ic_rm_team);
                Uri image2URI = Uri.parse("android.resource://news.nitesh.com.news/" + R.drawable.ic_real_madrid);

                ArrayList<Uri> imageUris = new ArrayList<Uri>();
                imageUris.add(image1URI); // Add your image URIs here
                imageUris.add(image2URI); // Add your image URIs here
                // Set the action to be performed i.e 'Send Multiple Data'
                Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                // Add the URIs holding a stream of data
                sendIntent.putExtra(Intent.EXTRA_STREAM, imageUris);
                // Set the type of data i.e 'image/* which means image/png, image/jpg, image/jpeg etc.,'
                sendIntent.setType("image/*");
                // Launches the activity; Open 'Gallery' if you set it as default app to handle Image
                startActivity(sendIntent);
            }
        });


    }

    public static String getExtension(Uri uri) {
        String name = uri.toString();
        String ext;
        if (name.matches("^.+?\\d$")) { // String ends with digits or ".*\\d"
            ext = ".jpeg";

        } else {

            if (name.lastIndexOf(".") == -1) {
                ext = "";

            } else {
                int index = name.lastIndexOf(".");
                ext = name.substring(index + 1, name.length());
            }
        }
        return ext;
    }


    private void zoomImageFromThumb(final View shareImageView, int ic_real_madrid) {

        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
        expandedImageView.setImageResource(ic_real_madrid);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        shareImageView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.shareDataFrame)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        shareImageView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        shareImageView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        shareImageView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });


    }


}

