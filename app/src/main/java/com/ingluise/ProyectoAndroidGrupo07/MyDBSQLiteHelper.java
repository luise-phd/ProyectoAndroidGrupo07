package com.ingluise.ProyectoAndroidGrupo07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBSQLiteHelper extends SQLiteOpenHelper {

    public MyDBSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory f, int version) {
        super(context, nombre, f, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE empleado(_id INTEGER PRIMARY KEY AUTOINCREMENT, ndoc TEXT, " +
                "nombre TEXT, apellidos TEXT, estado_civil TEXT, cargo TEXT, fnac TEXT, tel TEXT, dir TEXT, email TEXT)");
        db.execSQL("CREATE TABLE imagenes(_id INTEGER PRIMARY KEY AUTOINCREMENT, desc TEXT, img BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int verAnterior, int verNueva) {
        db.execSQL("DROP TABLE IF EXISTS empleado");
        db.execSQL("DROP TABLE IF EXISTS imagenes");
        db.execSQL("CREATE TABLE empleado(_id INTEGER PRIMARY KEY AUTOINCREMENT, ndoc TEXT, " +
                "nombre TEXT, apellidos TEXT, estado_civil TEXT, cargo TEXT, fnac TEXT, tel TEXT, dir TEXT, email TEXT)");
        db.execSQL("CREATE TABLE imagenes(_id INTEGER PRIMARY KEY AUTOINCREMENT, desc TEXT, img BLOB)");
    }
}
