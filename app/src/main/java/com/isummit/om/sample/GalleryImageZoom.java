package com.isummit.om.sample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GalleryImageZoom extends Activity {

    private ImageView imgview;
    private Matrix matrix = new Matrix();
    private ScaleGestureDetector scaleGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gallery_image_zoom);




        //Get record from the Bundle in Intent
        Intent intent = getIntent();
        String image = intent.getExtras().getString("image");
        imgview=findViewById(R.id.imagezoom);

        Picasso.with(getApplicationContext()).load(image).into(imgview);
    }

    private class ScaleListener extends ScaleGestureDetector.
            SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
            matrix.setScale(scaleFactor, scaleFactor);
            imgview.setImageMatrix(matrix);
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }
}
