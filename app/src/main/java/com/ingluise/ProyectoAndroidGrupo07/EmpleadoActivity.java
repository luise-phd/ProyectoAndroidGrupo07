package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EmpleadoActivity extends AppCompatActivity {
    private TextView tv1;
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor fila;

    private EditText et1, et2, et3, et4, et5, et6, et7;
    private Spinner sp1, sp2;

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
        et3 = findViewById(R.id.input_DNI);
        et4 = findViewById(R.id.input_fnac);
        et5 = findViewById(R.id.input_tel);
        et6 = findViewById(R.id.input_dir);
        et7 = findViewById(R.id.input_email);
        sp1 = findViewById(R.id.spinner_estado_civil);
        sp2 = findViewById(R.id.spinner_cargo);
    }

    public void agregarDatos(View view) {
        String dni = et3.getText().toString();
        String nom = et1.getText().toString();
        String ape = et2.getText().toString();
        String ecivil = sp1.getSelectedItem().toString();
        String cargo = sp2.getSelectedItem().toString();
        String fnac = et4.getText().toString();
        String tel = et5.getText().toString();
        String dir = et6.getText().toString();
        String email = et7.getText().toString();
        if (!nom.equals("") && !ape.equals("") && !dni.equals("") && !ecivil.equals("Seleccione...") &&
                !cargo.equals("Seleccione...") && !fnac.equals("") && !tel.equals("") &&
                !dir.equals("") && !email.equals("")) {
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("nombre", nom);
            cv.put("apellidos", ape);
            cv.put("ndoc", dni);
            cv.put("estado_civil", ecivil);
            cv.put("cargo", cargo);
            cv.put("fnac", fnac);
            cv.put("tel", tel);
            cv.put("dir", dir);
            cv.put("email", email);
            long reg = db.insert("empleado", null, cv);
            if (reg != -1) {
                Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                et5.setText("");
                et6.setText("");
                et7.setText("");
                sp1.setSelection(0);
                sp2.setSelection(0);
            } else {
                Toast.makeText(this, "No se pudo agregar el registro", Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(this, "Por favor, ingrese los datos", Toast.LENGTH_SHORT).show();
    }

    public void listarDatos(View view) {
//        db = admin.getReadableDatabase();
//        fila = db.rawQuery("SELECT * FROM empleado", null);
//        while (fila.moveToNext()) {
//            Toast.makeText(this, fila.getInt(0) + "-" + fila.getString(1) + " " + fila.getString(fila.getColumnIndex("apellidos")), Toast.LENGTH_SHORT).show();
//        }
//        db.close();
        Intent intent = new Intent(this, ListViewActivity.class);
        intent.putExtra("nomTabla", "empleado");
        startActivity(intent);
    }

    public void eliminarDatos(View view) {
        String dni = et3.getText().toString();
        if (!dni.equals("")) {
            db = admin.getWritableDatabase();
//            int reg = db.delete("empleado", "ndoc=" + "'" + dni + "'", null);
            String[] args = new String[]{dni};
            int reg = db.delete("empleado", "ndoc=?", args);
            if (reg == 0)
                Toast.makeText(this, "No se pudo eliminar el registro", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                et3.setText("");
            }
        } else
            Toast.makeText(this, "Por favor, ingrese los datos", Toast.LENGTH_SHORT).show();
    }

    public void editarDatos(View view) {
        String dni = et3.getText().toString();
        String nom = et1.getText().toString();
        String ape = et2.getText().toString();
        String ecivil = sp1.getSelectedItem().toString();
        String cargo = sp2.getSelectedItem().toString();
        String fnac = et4.getText().toString();
        String tel = et5.getText().toString();
        String dir = et6.getText().toString();
        String email = et7.getText().toString();
        if (!dni.equals("") && !nom.equals("") && !ape.equals("")) {
            db = admin.getWritableDatabase();
            cv = new ContentValues();
            cv.put("nombre", nom);
            cv.put("apellidos", ape);
            cv.put("estado_civil", ecivil);
            cv.put("cargo", cargo);
            cv.put("fnac", fnac);
            cv.put("tel", tel);
            cv.put("dir", dir);
            cv.put("email", email);
            int reg = db.update("empleado", cv, "ndoc=" + "'"+dni+"'", null);
            if (reg == 0)
                Toast.makeText(this, "No se pudo editar el registro", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(this, "Registro editado", Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                et5.setText("");
                et6.setText("");
                et7.setText("");
                sp1.setSelection(0);
                sp2.setSelection(0);
            }
        } else
            Toast.makeText(this, "Por favor, ingrese los datos", Toast.LENGTH_SHORT).show();
    }

    public void buscarDatos(View view) {
        String dni = et3.getText().toString();
        if (!dni.equals("")) {
            db = admin.getReadableDatabase();
            fila = db.rawQuery("SELECT * FROM empleado WHERE ndoc="+"'"+dni+"'", null);
            if (fila.moveToFirst()) {
                et1.setText(fila.getString(2));
                et2.setText(fila.getString(3));
                et4.setText(fila.getString(6));
                et5.setText(fila.getString(7));
                et6.setText(fila.getString(8));
                et7.setText(fila.getString(9));
                Adapter adapter = sp1.getAdapter();
                for(int i=0; i<adapter.getCount(); i++) {
                    if (fila.getString(4).equals(adapter.getItem(i))) {
                        sp1.setSelection(i);
                    }
                }
                adapter = sp2.getAdapter();
                for(int i=0; i<adapter.getCount(); i++) {
                    if (fila.getString(5).equals(adapter.getItem(i))) {
                        sp2.setSelection(i);
                    }
                }
            }
            db.close();
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