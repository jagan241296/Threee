package com.isummit.om.sample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GalleryImageZoom extends Activity {

    private TouchImageView imgview;
    int flag=0;
    private ImageView rotate;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gallery_image_zoom);


        //Get record from the Bundle in Intent
        Intent intent = getIntent();
        String image = intent.getExtras().getString("image");
        imgview=findViewById(R.id.imagezoom);
        rotate = findViewById(R.id.rotate);

        Picasso.with(getApplicationContext()).load(image).into(imgview);
        imgview.setMaxZoom(4f);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rotate.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if(flag==1)
                        {
                            rotate.setImageResource(R.drawable.icons8_rotation_96);
                            imgview.setRotation(imgview.getRotation()-90);
                            flag=0;
                        }
                        else
                        {
                            rotate.setImageResource(R.drawable.icons8_tilt_rotate);
                            imgview.setRotation(imgview.getRotation()+90);
                            flag=1;
                        }


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });

            }
        });

    }
}
