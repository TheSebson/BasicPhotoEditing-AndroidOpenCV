package pl.se.pie.com.binarizationapp.ImageFactory.Types;

import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class OnlyYellow implements EditType {
    private Scalar lower = new Scalar(103, 86, 65);
    private Scalar upper = new Scalar(145, 133, 128);

    @Override
    public Bitmap processImage(Bitmap bitmap) {
        Mat mRgba = new Mat();
        Mat imgHsv = new Mat( );
        Mat imgBlue = new Mat( );
        Mat imgResult = new Mat( );

        try{
            Utils.bitmapToMat(bitmap, mRgba);
            Imgproc.cvtColor(mRgba, imgHsv, Imgproc.COLOR_RGB2HSV);
            Core.inRange(imgHsv, lower, upper, imgBlue);
            Core.bitwise_and(imgHsv, imgHsv, imgResult, imgBlue );
            Utils.matToBitmap(imgResult, bitmap);
        }catch (Exception e){
            Log.e(this.getClass().toString(), e.getMessage());
        }
        return bitmap;
    }
}
