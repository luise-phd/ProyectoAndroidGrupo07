package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmpleadoActivity extends AppCompatActivity {
    private TextView tv1;
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        admin = new MyDBSQLiteHelper(this, vars.bd, null, vars.ver);

//        Toast.makeText(this,"Metodo onCreate", Toast.LENGTH_LONG).show();
        //Activar boton volver en la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getExtras();

        et1 = findViewById(R.id.input_nombre);
        et2 = findViewById(R.id.input_apellido);
    }

    public void agregarDatos(View view) {
        db = admin.getWritableDatabase();
        cv = new ContentValues();
        cv.put("nombre", et1.getText().toString());
        cv.put("apellidos", et2.getText().toString());
        long reg = db.insert("empleado", null, cv);
        if(reg != -1) {
            Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");
        }
        else {
            Toast.makeText(this, "No se pudo agregar el registro", Toast.LENGTH_SHORT).show();
        }
    }

    //Finalizar la actividad
    public void onBackPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    public void getExtras() {
        Bundle extras = getIntent().getExtras();
//        String msg = extras.getString("msg");
//        String saludo = extras.getString("saludo");
//        Toast.makeText(this, msg + ", " + saludo, Toast.LENGTH_SHORT).show();
//        Log.i("Información", msg + ", " + saludo);
    }

    public void goToActivityMain(View view) {
        Intent newIntent = new Intent(this, MainActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }
}