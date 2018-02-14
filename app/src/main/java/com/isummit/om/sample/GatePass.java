package com.isummit.om.sample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GatePass extends AppCompatActivity {
    ImageView imageView;
    CardView button;
    EditText editText;
    String EditTextValue, qrcodeText;
    private ProgressDialog progress;
    public final static int QRcodeWidth = 500;
    Bitmap bitmap;
    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_pass);

        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait while checking the ID");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog

        try {
            File filePath = GatePass.this.getFileStreamPath("qrcode.png");
            FileInputStream fi = new FileInputStream(filePath);
            Bitmap thumbnail = BitmapFactory.decodeStream(fi);
            imageView.setImageBitmap(thumbnail);
        } catch (Exception ex) {
            System.out.println(ex);

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                button.startAnimation(myAnim);
                EditTextValue = editText.getText().toString().toUpperCase();
                if(EditTextValue.equals(""))
                {
                    Toast.makeText(GatePass.this, "Please Enter a valid College ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(GatePass.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        progress.show();
                        rootRef = FirebaseDatabase.getInstance().getReference();
                        rootRef.child("collegeID").child(EditTextValue).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                try {
                                    if (dataSnapshot.getValue() != null) {
                                        try {

                                            qrcodeText = dataSnapshot.getValue().toString();
                                            onSucess();

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {

                                        Toast.makeText(GatePass.this, "Please Enter a valid College ID", Toast.LENGTH_SHORT).show();
                                        progress.dismiss();
                                        return;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


            }
        });
    }

    FileOutputStream out = null;

    public void onSucess() {
        try {
            bitmap = TextToImageEncode(qrcodeText);

            progress.dismiss();
            imageView.setImageBitmap(bitmap);
            editText.setText("");
            SaveImage(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black_color) : getResources().getColor(R.color.WHITE);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    private void SaveImage(Bitmap finalBitmap) {


        try {
            FileOutputStream fos = GatePass.this.openFileOutput("qrcode.png", Context.MODE_PRIVATE);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 80, fos);
            System.out.println("Bitmap write success");
            fos.close();
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}