package com.example.markf.dnomics;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditProfile extends AppCompatActivity {

    Intent intent = null;
    PersonTO activeSession = null;
    boolean updatedFlag = false;

    TextView lblUserEditProfile;
    TextView lblPasswordEditProfile;
    TextView lblNombreEditProfile;
    TextView lblSurnameEditProfile;
    TextView lblUniqueIDEditProfile;
    TextView lblEmailEditProfile;

    EditText txtUsuarioEditProfile;
    EditText txtPasswordEditProfile;
    EditText txtNombreEditProfile;
    EditText txtApellidoEditProfile;
    EditText txtUniqueEditProfile;
    EditText txtEmailEditProfile;

    ImageView selectPicture;

    String usuario;
    String password;
    String nombre;
    String surname;
    String uniqueID;
    String email;


    Button btnSave;

    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        intent = getIntent();
        Init();
        lblUserEditProfile = (TextView)findViewById(R.id.lblUserEditProfile);
        lblPasswordEditProfile =(TextView)findViewById(R.id.lblPasswordEditProfile);
        lblNombreEditProfile = (TextView)findViewById(R.id.lblNombreEditProfile);
        lblSurnameEditProfile = (TextView)findViewById(R.id.lblSurnameEditProfile);
        lblUniqueIDEditProfile = (TextView)findViewById(R.id.lblUniqueIDEditProfile);
        lblEmailEditProfile = (TextView)findViewById(R.id.lblEmailEditProfile);
        txtUsuarioEditProfile = (EditText)findViewById(R.id.txtUsuarioEditProfile);
        txtPasswordEditProfile = (EditText)findViewById(R.id.txtPasswordEditProfile);
        txtNombreEditProfile = (EditText)findViewById(R.id.txtNombreEditProfile);
        txtApellidoEditProfile = (EditText)findViewById(R.id.txtApellidoEditProfile);
        txtUniqueEditProfile = (EditText)findViewById(R.id.txtUniqueEditProfile);
        txtEmailEditProfile = (EditText)findViewById(R.id.txtEmailEditProfile);

        selectPicture = (ImageView) findViewById(R.id.selectPicture);

        btnSave = (Button)findViewById(R.id.btnUpdateEditProfile);

        lblUserEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblPasswordEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblNombreEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblSurnameEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblUniqueIDEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        lblEmailEditProfile.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));
        btnSave.setTypeface(FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOMESOLID));

        newPicture();
        Save();
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


    private void Save(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUser();
            }
        });
    }


    private void UpdateUser(){
        new HellCat(getApplicationContext(), new HellCat.AsyncTask() {
            @Override
            public void finishHellCat() {
                if(updatedFlag){
                    Toast.makeText(getApplicationContext(), "Datos Actualizados", Toast.LENGTH_LONG).show();
                    goToEditDashboard();
                }else {
                    Toast.makeText(getApplicationContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void workHellCat() {
                DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
                PersonTO updatedInfo = new PersonTO();
                updatedInfo.setPersonID(activeSession.getPersonID());
                updatedInfo.setUserName(txtUsuarioEditProfile.getText().toString());
                updatedInfo.setPassword(txtPasswordEditProfile.getText().toString());
                updatedInfo.setName(txtNombreEditProfile.getText().toString());
                updatedInfo.setSurName(txtApellidoEditProfile.getText().toString());
                updatedInfo.setUniqueID(txtUniqueEditProfile.getText().toString());
                updatedInfo.setEmail(txtEmailEditProfile.getText().toString());
                updatedInfo.setCountry_alphaID(activeSession.getCountry_alphaID());
                updatedInfo.setCreateDate(activeSession.getCreateDate());
                updatedInfo.setUpdateDate(getCurrentDate());
                updatedInfo.setBirthDate(activeSession.getBirthDate());
                updatedInfo.setImgData(activeSession.getImgData());
                updatedFlag = dbModel.updatePerson(updatedInfo);
            }
        }).execute();
    }

    public String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    private void Init(){

        //Get user's info through the activity intent, when came from new entry

        usuario = intent.getStringExtra("_usuario");
        password = intent.getStringExtra("_password");
        nombre = intent.getStringExtra("_nombre");
        surname = intent.getStringExtra("_surname");
        uniqueID = intent.getStringExtra("_uniqueid");
        email = intent.getStringExtra("_email");

        //Start Daemon ---HELLCAT--- To retrieve user information in another thread
        new HellCat( getApplicationContext(), new HellCat.AsyncTask(){
            @Override
            public void workHellCat(){
                activeSession = getUserSession(usuario, password);
            }
            @Override
            public  void finishHellCat(){
                Log.d("HellCat", "Finishing Thread...");
                if(activeSession.getName().length() <= 0){
                    salir();
                }
                ImageHandler bitmapImage = new ImageHandler(activeSession.getImgData());
                selectPicture.setImageBitmap(bitmapImage.getImageDataInBitmap());
                txtUsuarioEditProfile.setText(activeSession.getUserName());
                txtPasswordEditProfile.setText(activeSession.getPassword());
                txtNombreEditProfile.setText(activeSession.getName());
                txtApellidoEditProfile.setText(activeSession.getSurName());
                txtUniqueEditProfile.setText(activeSession.getUniqueID());
                txtEmailEditProfile.setText(activeSession.getEmail());
            }
        }).execute();

        activeSession = getUserSession(usuario, password);
    }

    public PersonTO getUserSession(String userName, String pass){
        PersonTO person = new PersonTO();
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        SQLiteDatabase db = dbModel.getWritableDatabase();
        person = dbModel.getPersonByUserNamePass(db,userName,pass);
        Log.d("PersonName:", person.getName());
        return person;
    }

    public void salir(){
        DatabaseModel dbModel = new DatabaseModel(getApplicationContext());
        dbModel.close();
        usuario = "";
        password = "";
        nombre = "";
        surname = "";
        uniqueID = "";
        email = "";

        Intent intent = new Intent(EditProfile.this, MainActivity.class);
        EditProfile.this.startActivity(intent);
    }

    private void goToEditDashboard(){
        Intent intent = new Intent(EditProfile.this, Dashboard.class);
        intent.putExtra("_usuario", usuario);
        intent.putExtra("_password", password);
        intent.putExtra("_nombre", nombre);
        intent.putExtra("_surname", surname);
        intent.putExtra("_uniqueid", uniqueID);
        intent.putExtra("_email",email);
        EditProfile.this.startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            //Call the ImageHandler in order to convert the image to bytes and save it into the DB
            ImageHandler imageMgr = new ImageHandler();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                //Convert Bitmap to byte array
                imageMgr.setImageDataFromBitmap(bitmap);
                // Log.d(TAG, String.valueOf(bitmap));
                selectPicture.setImageBitmap(bitmap);
                selectPicture.getLayoutParams().height = 300;
                selectPicture.getLayoutParams().width = 300;
                activeSession.setImgData(imageMgr.bitmapToByte(bitmap));
                selectPicture.requestLayout();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
