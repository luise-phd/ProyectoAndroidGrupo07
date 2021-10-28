package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Metodo onCreate", Toast.LENGTH_SHORT).show();
    }

    public void goToActivity2(View view) {
        Intent newIntent = new Intent(this, SecondActivity.class);
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

        if(id == R.id.mnu_actividad2) {
            Intent newIntent = new Intent(this, SecondActivity.class);
            newIntent.putExtra("msg", "Hola Activity 2");
            newIntent.putExtra("saludo", "bienvenida");
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }
        else if(id == R.id.mnu_actividad3) {
            Intent newIntent = new Intent(this, ThirdActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
        }

        return super.onOptionsItemSelected(menuItem);
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