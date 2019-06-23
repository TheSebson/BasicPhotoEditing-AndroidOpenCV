package pl.se.pie.com.binarizationapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import org.opencv.android.OpenCVLoader;

import pl.se.pie.com.binarizationapp.ImageFactory.ImageFactory;

public class MainActivity extends AppCompatActivity {

    private Button photo_btn;
    public  static final int PERMISSIONS_MULTIPLE_REQUEST = 123;
    private Spinner edit_type;

    private ImageView imageView;

    static {
        if(!OpenCVLoader.initDebug()){
            Log.d("Main", "OpenCV not loaded");
        }else {
            Log.d("Main", "OpenCV loaded");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

        edit_type = (Spinner) findViewById(R.id.edit_type);
        ArrayAdapter<String> type = new ArrayAdapter<String>(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.types));
        type.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        edit_type.setAdapter(type);

        imageView = (ImageView)findViewById(R.id.imageView);

        photo_btn = findViewById(R.id.photo);
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initApp();
            }
        });


}

    private void initApp(){
        if(checkPermission()) {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera,0);
        }
    }

    private boolean checkPermission() {
        if (
                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) ||
                        (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                        (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_MULTIPLE_REQUEST);
        }else return true;
        return false;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(editPhoto(edit_type.getSelectedItem().toString(), bitmap));
    }



    private Bitmap editPhoto(String selected, Bitmap bitmap){
        ImageFactory imageFactory = ImageFactory.getImageFactory();
        imageFactory.createImage(bitmap, selected);
        return bitmap;
    }
}
