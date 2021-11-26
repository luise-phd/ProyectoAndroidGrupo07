package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class EncuestaActivity extends AppCompatActivity {
    private TextView tv1;
    private EditText et1, et2, et3, et4, et5;
    private TextInputEditText ti1;
    private Spinner sp1;
    private SeekBar sb1;
    private Button b1;
    private CheckBox ch1, ch2, ch3, ch4, ch5, ch6;
    private RadioButton rb1, rb2, rb3;
    private Switch sw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        //Activar boton volver en la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b1 = (Button) findViewById(R.id.button4);
        sb1 = findViewById(R.id.seekBar);
        tv1 = findViewById(R.id.textView13);
        et1 = findViewById(R.id.editTextTextPersonName3);
        et2 = findViewById(R.id.editTextDate2);
        et3 = findViewById(R.id.editTextPhone2);
        et4 = findViewById(R.id.editTextTextEmailAddress2);
        et5 = findViewById(R.id.editTextTextMultiLine);
        sp1 = findViewById(R.id.spinner);
        ch1 = findViewById(R.id.checkBox);
        ch2 = findViewById(R.id.checkBox2);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        ti1 = findViewById(R.id.textInputEditText);
        sw1 = findViewById(R.id.switch1);
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv1.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b1.setText(R.string.agregar);

    }

    public void guardarDatos(View view) {
        String nom = "Nombre: " + et1.getText();
        String fnac = "Fecha de nacimiento: " + et2.getText();
        String tel = "Teléfono: " + et3.getText();
        String email = "Corrreo electrónico: " + et4.getText();
        String dir = "Dirección: " + ti1.getText();
        String niv_ing = "Nivel de inglés: " + sp1.getSelectedItem();
        String lengs = "Lenguajes de programación: ";
        if(ch1.isChecked())
            lengs += ch1.getText().toString() + ", ";
        if(ch2.isChecked())
            lengs += ch2.getText().toString();
        String tiempo = "Experiencia: ";
        if(rb1.isChecked())
            tiempo += rb1.getText().toString() + " año";
        if(rb2.isChecked())
            tiempo += rb2.getText().toString() + " años";
        if(rb1.isChecked())
            tiempo += rb3.getText().toString() + " años";
        String gus_prog = "¿Te gusta programar? ";
        if(sw1.isChecked())
            gus_prog += "SI";
        else
            gus_prog += "NO";
        String niv_sat = "Nivel de satisfacción: " + tv1.getText().toString();
        String obs = "Observaciones: " + et5.getText().toString();

        new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Resumen")
            .setMessage(nom + "\n" + fnac + "\n" + tel + "\n" + email + "\n" + dir +
                    "\n" + niv_ing + "\n" + lengs + "\n" + tiempo + "\n" + gus_prog +
                    "\n" + niv_sat + "\n" + obs)
            .setPositiveButton("Aceptar", null).show();
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