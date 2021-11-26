package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Intent newIntent;
    private TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(this,"Metodo onCreate", Toast.LENGTH_SHORT).show();
        t1 = (TextView) findViewById(R.id.textView);
        t1.setText("Hola Mundo!");
        t1.setTextSize(24);
        t1.setTextColor(Color.BLUE);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
//            for (int i=76; i < 1000; i++) {
//                t1.setTop(i);
//            }
//            Toast.makeText(this, ""+t1.getTop(), Toast.LENGTH_SHORT).show();
        }
    }

    public void goToActivity2(View view) {
        Intent newIntent = new Intent(this, EmpleadoActivity.class);
        newIntent.putExtra("msg", "Hola Activity 2");
        newIntent.putExtra("saludo", "bienvenida");
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(newIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.mnu_empleado:
                newIntent = new Intent(this, EmpleadoActivity.class);
    //            newIntent.putExtra("msg", "Hola Activity 2");
    //            newIntent.putExtra("saludo", "bienvenida");
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_imagenes:
                newIntent = new Intent(this, ImagenesActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_hor_scrollview:
                newIntent = new Intent(this, HorizontalScrollVIewActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_ver_scrollview:
                newIntent = new Intent(this, ScrollViewActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_Cargo:
                newIntent = new Intent(this, CargoFirebaseActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_api_rest:
                newIntent = new Intent(this, APIRestActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_encuesta:
                newIntent = new Intent(this, EncuestaActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_recyclerview:
                newIntent = new Intent(this, RecyclerViewActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_hextras:
                newIntent = new Intent(this, HorasExtrasFirebaseActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_location:
                newIntent = new Intent(this, LocationActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;
            case R.id.mnu_nomina:
                newIntent = new Intent(this, NominaActivity.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                break;

        }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Información")
                    .setMessage("¿Desea salir?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.this.finish();
                        }
                    }).show();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this,"Metodo onStart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this,"Metodo onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(this,"Metodo onPause", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(this,"Metodo onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Información", "La actividad fue destruida");
    }
}