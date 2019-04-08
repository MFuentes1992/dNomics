package com.example.markf.dnomics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

/**
 * Created by markf on 08/11/2018.
 */

public class DaemonHandler extends AsyncTask<String,String,String> {

    Context ctx;

    public interface AsyncResponse{
        void processFinish();
    }

    AsyncResponse asyncResponse = null;

    DaemonHandler(Context ctx, AsyncResponse asyncResponse){
        this.ctx = ctx;
        this.asyncResponse =asyncResponse;
    }

    @Override
    protected String doInBackground(String... strings) {

        Functions helper = new Functions();
        DatabaseModel dbModel = new DatabaseModel(ctx);
        SQLiteDatabase db = dbModel.getWritableDatabase();

        long countryID = dbModel.insertCountryAsync(db, strings[0], strings[1]);

        //Storing a fake image into the image data field in the person's record - all users must have an initial value
        Bitmap fakeImg = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.alisa);
        //Call ImageHandler
        ImageHandler imageMgr = new ImageHandler();
        //Convert bitmap into byte array
        imageMgr.setImageDataFromBitmap(fakeImg);
        //Insert User's Data
        //dbModel.insertPersonAsync( db,helper.fillPerson(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7], (int)countryID, strings[8], strings[9], strings[10], imageMgr.getImageData()));
        return "";
    }

    @Override
    protected void onPostExecute(String string) {
         asyncResponse.processFinish();
     }

}
