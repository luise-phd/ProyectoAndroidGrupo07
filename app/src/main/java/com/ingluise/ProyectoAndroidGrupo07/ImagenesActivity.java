package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImagenesActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor fila;

    private ImageButton btnCamara;
    private ImageView imgView;
    private EditText txtDesc;

    private Bitmap bmp1, bmp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);

        admin = new MyDBSQLiteHelper(this, vars.bd, null, vars.ver);

        btnCamara = findViewById(R.id.btnCamara);
        imgView = findViewById(R.id.imageView);
        imgView.setImageDrawable(null);
        txtDesc = findViewById(R.id.input_descripcion);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
    }

    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }

    public void abrirGaleria(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // De la camara
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }
        // De la Galería
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            InputStream inputStream;
            try {
                inputStream = getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                imgView.setImageBitmap(bitmap);
//                Toast.makeText(this, ""+data.getData(), Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camara, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.mnu_guardar) {
            String desc = txtDesc.getText().toString();
            if (!desc.equals("")) {
                if (imgView.getDrawable() != null) {
                    String imgCodificada = "";
                    imgView.buildDrawingCache(true);
                    bmp1 = imgView.getDrawingCache(true);
                    bmp2 = Bitmap.createScaledBitmap(bmp1, imgView.getWidth(), imgView.getHeight(), true);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmp2.compress(Bitmap.CompressFormat.PNG, 25, baos);
                    byte[] imagen = baos.toByteArray();
                    imgCodificada = Base64.encodeToString(imagen, Base64.DEFAULT);
                    db = admin.getWritableDatabase();
                    cv = new ContentValues();
                    cv.put("desc", desc);
                    cv.put("img", imgCodificada);
                    long reg = db.insert("imagenes", null, cv);
                    if (reg != -1) {
                        Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
                        txtDesc.setText("");
                        imgView.setImageBitmap(null);
                        imgView.destroyDrawingCache();
                        imgView.setImageDrawable(null);
                    } else {
                        Toast.makeText(this, "No se pudo agregar el registro", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(this, "Por favor, tome la fotografía", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Por favor, ingrese la descripción", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_buscar) {
            LayoutInflater li = LayoutInflater.from(this);
            View nuevaVista = li.inflate(R.layout.inputdialog, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(nuevaVista);
            final EditText userInput = nuevaVista.findViewById(R.id.input_description);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Bitmap decodedByte = null;
                            byte[] decodeString;
                            db = admin.getReadableDatabase();
                            fila = db.rawQuery("SELECT * FROM imagenes WHERE desc='"+userInput.getText().toString()+"'", null);
                            String desc = "";
                            if (fila.moveToFirst()) {
                                decodeString = Base64.decode(fila.getString(2), Base64.DEFAULT);
                                decodedByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                                txtDesc.setText(fila.getString(1));
                                imgView.setImageBitmap(decodedByte);
                            } else
                                Toast.makeText(getApplicationContext(), "La imagen no existe", Toast.LENGTH_SHORT).show();
                            db.close();
                        }
                    })
                    .setNegativeButton("Cancelar", null);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        return super.onOptionsItemSelected(menuItem);
    }
}