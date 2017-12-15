package jl.facedetection;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class MainActivity extends AppCompatActivity {

  ImageView pic;
  Bitmap result;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button takepic = (Button)findViewById(R.id.pic);
     pic = (ImageView)findViewById(R.id.imageView);
    Button detectface = (Button)findViewById(R.id.button4);

    detectface.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
          .setLandmarkType(FaceDetector.ALL_LANDMARKS)
          .setTrackingEnabled(false)
          .setMode(FaceDetector.FAST_MODE)
          .build();

        Frame frame = new Frame.Builder().setBitmap(result).build();

        SparseArray<Face> faces = faceDetector.detect(frame);



      }
    });

    takepic.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,0);


      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    result = (Bitmap)data.getExtras().get("data");
    pic.setImageBitmap(result);

  }
}
