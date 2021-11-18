package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private Cursor fila;

    private ListView lv1;
    private ArrayList<String> listado = new ArrayList<String>();

    private static final String TAG = ListViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        admin = new MyDBSQLiteHelper(this, vars.bd, null, vars.ver);

        Bundle extras = getIntent().getExtras();
        String nomTabla = extras.getString("nomTabla");

        String tabla = nomTabla.substring(0, 1).toUpperCase() + nomTabla.substring(1);

        setTitle(tabla);

        lv1 = findViewById(R.id.listView);
//        listado.add("Uno");
//        listado.add("Dos");
//        for(int i=3; i<=20; i++) {
//            listado.add("Texto "+ i);
//        }
        if (nomTabla.equals("empleado")) {
            db = admin.getReadableDatabase();
            fila = db.rawQuery("SELECT * FROM empleado", null);
            while (fila.moveToNext()) {
                listado.add(fila.getInt(0) + "-" + fila.getString(1) + "\n" + fila.getString(2) + " " + fila.getString(fila.getColumnIndex("apellidos")));
            }
            db.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listado);
        lv1.setAdapter(adapter);
        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String[] reg = lv1.getItemAtPosition(pos).toString().split("-");
                String[] emp = reg[1].split("\n");
                //Toast.makeText(getApplicationContext(), emp[0], Toast.LENGTH_SHORT).show();
                db = admin.getWritableDatabase();
                String[] args = new String[]{emp[0]};
                db.delete("empleado", "ndoc=?", args);
                Toast.makeText(getApplicationContext(), "Registro eliminado", Toast.LENGTH_SHORT).show();
                adapter.remove(lv1.getItemAtPosition(pos).toString());
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                Toast.makeText(getApplicationContext(), pos + "-"+lv1.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
                String datos = "", info = "";
                db = admin.getReadableDatabase();
                fila = db.rawQuery("SELECT * FROM empleado", null);
                while (fila.moveToNext()) {
                    datos = fila.getInt(0) + "-" + fila.getString(1) + "\n" + fila.getString(2) + " " + fila.getString(fila.getColumnIndex("apellidos"));
                    if (lv1.getItemAtPosition(pos).equals(datos)) {
                        info = fila.getInt(0) + "\nDNI: " + fila.getString(1) +
                                "\nNombre: " + fila.getString(2) + " " + fila.getString(fila.getColumnIndex("apellidos")) +
                                "\nEstado civil: " + fila.getString(4) + "\nCargo: " + fila.getString(5) +
                                "\nFecha de nacimiento: " + fila.getString(6) + "\nTeléfono: " + fila.getString(7) +
                                "\nDirección: " + fila.getString(8) + "\nCorreo electrónico: " + fila.getString(9);
                    }
                }
                db.close();

                new AlertDialog.Builder(ListViewActivity.this, R.style.Theme_AppCompat_Dialog_Alert)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Datos")
                    .setMessage(info)
                    .setPositiveButton("Aceptar", null).show();
            }
        });

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
        }

        try {
            double op = 121 / 0;
        } catch (ArithmeticException ae) {
            Log.e(TAG, "Error", ae);
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
}