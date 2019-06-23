package pl.se.pie.com.binarizationapp.ImageFactory;

import android.graphics.Bitmap;


import pl.se.pie.com.binarizationapp.ImageFactory.Types.*;

public class ImageFactory {

    private static ImageFactory imageFactory;


    public static ImageFactory getImageFactory() {
        if (imageFactory == null)
            imageFactory = new ImageFactory();
        return imageFactory;
    }

    public Bitmap createImage(Bitmap bitmap, String type) {
        switch (type) {
            case ("Only blue color"):
                return new OnlyBlue().processImage(bitmap);
            case ("Only red color"):
                return new OnlyRed().processImage(bitmap);
            case ("Only white color"):
                return new OnlyWhite().processImage(bitmap);
            case ("Only yellow color"):
                return new OnlyYellow().processImage(bitmap);
            case ("Binarization"):
                return new Binarization().processImage(bitmap);
            case ("Black and white"):
                return new GreyScale().processImage(bitmap);
        }
        return null;

    }
}
