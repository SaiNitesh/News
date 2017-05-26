package news.nitesh.com.news;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by nitesh on 1/2/2017.
 */

public class PDFRendererActivity extends Activity{

    ImageView pdf_image;
    Button pdf_button_prev,pdf_button_next;
    int current_page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_renderer);

        pdf_image = (ImageView) findViewById(R.id.pdf_image);
        pdf_button_prev= (Button) findViewById(R.id.pdf_button_prev);
        pdf_button_next= (Button) findViewById(R.id.pdf_button_next);


        pdf_button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_page--;
                render();

            }
        });

        pdf_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_page++;
                render();

            }
        });

        render();

    }

    private void render(){

        try{

            pdf_image = (ImageView) findViewById(R.id.pdf_image);
            int REQ_WIDTH= 1;
            int REQ_HEIGHT= 1;
            REQ_WIDTH = pdf_image.getWidth();
            REQ_HEIGHT = pdf_image.getHeight();

            Bitmap bitmap= Bitmap.createBitmap(REQ_WIDTH ,REQ_HEIGHT, Bitmap.Config.ARGB_4444);
            //File file= new File("");  //new File("Environment.getExternalStorageDirectory() + "/testthreepdf/" + "maven.pdf"");
            File file= new File(Environment.getExternalStorageDirectory() + "/testthreepdf/" + "maven.pdf");
            Log.d("pdfrenderlog",String.valueOf(file.exists()));
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));

            if(current_page < 0){
                current_page= 0;
            }else if(current_page > renderer.getPageCount()){
                current_page = renderer.getPageCount() -1;
            }

            Matrix m =pdf_image.getImageMatrix();
            Rect rect = new Rect(0,0, REQ_WIDTH, REQ_HEIGHT);
            renderer.openPage(current_page).render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            pdf_image.setImageMatrix(m);
            pdf_image.setImageBitmap(bitmap);
            pdf_image.invalidate();

        }catch (Exception e){

            e.printStackTrace();
        }


    }


}
