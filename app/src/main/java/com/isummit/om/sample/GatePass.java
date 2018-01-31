package com.isummit.om.sample;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GatePass extends AppCompatActivity {
    ImageView imageView;
    Button button;
    EditText editText;
    String EditTextValue ;
    private ProgressDialog progress;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;
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
            Picasso.with(getApplicationContext()).load(Environment.getExternalStorageDirectory()+ "/Android/data/"+ getApplicationContext().getPackageName()+"/Files/MI_QRCode.jpg").into(imageView);
        }catch (Exception e) {

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.show();
                EditTextValue = editText.getText().toString().toUpperCase();

                rootRef= FirebaseDatabase.getInstance().getReference();

                rootRef.child("collegeID").child(EditTextValue).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            if (dataSnapshot.getValue() != null) {
                                try {

                                    onSucess();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {

                                Toast.makeText(GatePass.this,"Please Enter a valid College ID", Toast.LENGTH_SHORT).show();
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
        });
    }

    public void onSucess()
    {
        try {
            bitmap = TextToImageEncode(EditTextValue);

            progress.dismiss();
            imageView.setImageBitmap(bitmap);
            editText.setText("");
            storeImage(bitmap);

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
                        getResources().getColor(R.color.BLACK):getResources().getColor(R.color.WHITE);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Toast.makeText(GatePass.this,"Check Storage permissions", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error damn: "+e);
            Toast.makeText(GatePass.this,"FileNotFound Exception", Toast.LENGTH_SHORT).show();
            return;
        } catch (IOException e) {
            Toast.makeText(GatePass.this,"IO Exception", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private  File getOutputMediaFile(){
// To be safe, you should check that the SDCard is mounted
// using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new
                File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

// This location works best if you want the created images to be shared
// between applications and persist after your app has been uninstalled.

// Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
// Create a media file name
        //String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_QRCode.jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }
}