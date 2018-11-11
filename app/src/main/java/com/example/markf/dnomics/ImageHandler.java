package com.example.markf.dnomics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by markf on 06/11/2018.
 */

public class ImageHandler {

    private byte[] imageData = null;

    ImageHandler(){
    }

    ImageHandler(byte[] byteArray){
        imageData = byteArray;
    }

    public void setImageDataFromBitmap(Bitmap image){
        if(image != null){
            imageData = bitmapToByte(image);
        }else{
            imageData = null;
        }
    }

    public byte[] bitmapToByte(Bitmap bitmap){
        try{
            ByteArrayOutputStream stream  = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
            byte[] x = stream.toByteArray();
            stream.close();
            return x;
        }catch(IOException e){e.printStackTrace();}
        return null;
    }

    public Bitmap getImageDataInBitmap() {
        if (imageData != null) {
            //turn byte[] to bitmap
            return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        }
        return null;
    }

    public byte[] getImageData() {
        return imageData;
    }

}
