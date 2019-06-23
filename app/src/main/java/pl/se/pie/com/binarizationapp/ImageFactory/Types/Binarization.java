package pl.se.pie.com.binarizationapp.ImageFactory.Types;

import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.THRESH_OTSU;

public class Binarization implements EditType {


    public Bitmap processImage(Bitmap bmp)
    {
        Mat mRgba = new Mat();
        Mat imgGray = new Mat( );
        Mat imgThreshold = new Mat( );

        try{
            Utils.bitmapToMat(bmp, mRgba);
            Imgproc.cvtColor(mRgba, imgGray, Imgproc.COLOR_RGB2GRAY);
            Imgproc.threshold( imgGray, imgThreshold, 0, 255, THRESH_BINARY | THRESH_OTSU );
            Utils.matToBitmap(imgThreshold, bmp);
        }catch (Exception e){
            Log.e(this.getClass().toString(), e.getMessage());
        }
        return bmp;
    }
}
