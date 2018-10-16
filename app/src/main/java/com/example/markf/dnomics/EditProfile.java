package com.example.markf.dnomics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class EditProfile extends AppCompatActivity {

    TextView lblUserEditProfile;
    TextView lblPasswordEditProfile;
    TextView lblNombreEditProfile;
    TextView lblSurnameEditProfile;
    TextView lblUniqueIDEditProfile;

    ImageView selectPicture;

    Button btnSave;

    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        lblUserEditProfile = (TextView)findViewById(R.id.lblUserEditProfile);
        lblPasswordEditProfile =(TextView)findViewById(R.id.lblPasswordEditProfile);
        lblNombreEditProfile = (TextView)findViewById(R.id.lblNombreEditProfile);
        lblSurnameEditProfile = (TextView)findViewById(R.id.lblSurnameEditProfile);
        lblUniqueIDEditProfile = (TextView)findViewById(R.id.lblUniqueIDEditProfile);

        selectPicture = (ImageView) findViewById(R.id.selectPicture);

        btnSave = (Button)findViewById(R.id.btnUpdateEditProfile);

        lblUserEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblPasswordEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblNombreEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblSurnameEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblUniqueIDEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnSave.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        newPicture();
    }

    private void newPicture(){
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.equals(selectPicture)){
                    openImageGalley();
                }
            }
        };
        selectPicture.setOnClickListener(clickListener);
    }

    private void openImageGalley(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                selectPicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
