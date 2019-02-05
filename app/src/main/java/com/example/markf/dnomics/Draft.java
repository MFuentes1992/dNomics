package com.example.markf.dnomics;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class Draft extends AppCompatActivity {

    DatabaseModel dbModel;
    int personID;
    PersonTO activeSession = null;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);
        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(Draft.this, 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        Intent intent = getIntent();
        personID = Integer.parseInt(intent.getStringExtra("personID"));
        dbModel = new DatabaseModel(getApplicationContext());

        Init();

    }

    private void Init(){
        //Start Daemon ---HELLCAT--- To retrieve user information in another thread
        new HellCat( getApplicationContext(), new HellCat.AsyncTask(){
            @Override
            public void workHellCat(){
                MyAdapter myAdapter = new MyAdapter(Draft.this, getDraftReports(personID));
                mRecyclerView.setAdapter(myAdapter);
            }
            @Override
            public  void finishHellCat(){
            }
        }).execute();

    }

    public PersonTO getUserSession(int personID){
        PersonTO person = new PersonTO();
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        SQLiteDatabase db = dbModel.getWritableDatabase();
        person = dbModel.getPersonByID(personID);
        return person;
    }

    public ArrayList<ReportTO> getDraftReports(int personID){
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        SQLiteDatabase db = dbModel.getWritableDatabase();
        ArrayList<ReportTO> reports = dbModel.getAllDraftReports(personID);
        return  reports;
    }

    public void salir(){
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        dbModel.close();
        Intent intent = new Intent(Draft.this, MainActivity.class);
        Draft.this.startActivity(intent);
    }


}
