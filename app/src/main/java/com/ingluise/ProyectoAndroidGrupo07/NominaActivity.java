package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class NominaActivity extends AppCompatActivity {
    private MyDBSQLiteHelper admin;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Cursor fila;

    private Spinner sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomina);

        admin = new MyDBSQLiteHelper(this, vars.bd, null, vars.ver);

        sp1 = findViewById(R.id.sp_empleado);

        db = admin.getReadableDatabase();
        fila = db.rawQuery("SELECT ndoc AS _id, (ndoc || ' - ' || nombre || ' ' || apellidos) AS emp FROM empleado", null);
        MatrixCursor empleados = new MatrixCursor(new String[]{"_id", "emp"});
        empleados.addRow(new String[]{"-1", "Seleccione..."});
        Cursor[] cursors = {empleados, fila};
        Cursor cursorExtended = new MergeCursor(cursors);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_spinner_dropdown_item,
                cursorExtended, new String[]{"emp"}, new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        sp1.setAdapter(adapter);
        db.close();

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String emp = cursorExtended.getString(1);
                Toast.makeText(getApplicationContext(), emp, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}