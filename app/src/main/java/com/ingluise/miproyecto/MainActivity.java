package com.ingluise.miproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        startActivity(newIntent);
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