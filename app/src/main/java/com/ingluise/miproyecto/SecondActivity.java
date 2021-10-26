package com.ingluise.miproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toast.makeText(this,"Metodo onCreate", Toast.LENGTH_LONG).show();
        getExtras();
    }

    public void getExtras() {
        Bundle extras = getIntent().getExtras();
        String msg = extras.getString("msg");
        String saludo = extras.getString("saludo");
        Toast.makeText(this, msg + ", " + saludo, Toast.LENGTH_SHORT).show();
        Log.i("Información", msg + ", " + saludo);
    }

    public void goToActivityMain(View view) {
        Intent newIntent = new Intent(this, MainActivity.class);
        startActivity(newIntent);
    }
}