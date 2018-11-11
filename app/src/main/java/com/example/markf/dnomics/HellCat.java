package com.example.markf.dnomics;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by markf on 10/11/2018.
 */

public class HellCat extends AsyncTask<Void, Void, Void> {

    public interface AsyncTask{
        void finishHellCat();
        void workHellCat();
    }

    AsyncTask asyncTask = null;
    Context ctx = null;

    HellCat(Context ctx, AsyncTask asyncTask){
        this.ctx = ctx;
        this.asyncTask = asyncTask;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        asyncTask.workHellCat();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        asyncTask.finishHellCat();
    }
}
