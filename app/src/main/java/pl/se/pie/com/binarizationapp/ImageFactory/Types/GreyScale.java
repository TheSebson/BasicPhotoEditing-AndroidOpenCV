package pl.se.pie.com.binarizationapp.ImageFactory.Types;

import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.THRESH_OTSU;

public class GreyScale implements EditType {
    @Override
    public Bitmap processImage(Bitmap bitmap) {
        Mat mRgba = new Mat();
        Mat imgGray = new Mat( );

        try{
            Utils.bitmapToMat(bitmap, mRgba);
            Imgproc.cvtColor(mRgba, imgGray, Imgproc.COLOR_RGB2GRAY);

            Utils.matToBitmap(imgGray, bitmap);
        }catch (Exception e){
            Log.e(this.getClass().toString(), e.getMessage());
        }
        return bitmap;
    }
}
