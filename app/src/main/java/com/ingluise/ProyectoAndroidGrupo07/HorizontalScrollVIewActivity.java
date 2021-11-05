package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class HorizontalScrollVIewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroollview);
        //Activar boton volver en la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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