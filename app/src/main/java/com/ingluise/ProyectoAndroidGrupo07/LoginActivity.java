package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText et1, et2;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Ocultar ActionBar
        getSupportActionBar().hide();
        et1 = (EditText) findViewById(R.id.editTextTextPersonName);
        et2 = (EditText) findViewById(R.id.editTextTextPassword);
        tv1 = (TextView) findViewById(R.id.textView5);
        tv1.setClickable(true);
        tv1.setLinkTextColor(Color.BLUE);
//        String texto = "<a href='https://www.google.com/'>Recordar contraseña</a>";
        String texto = "<a href=''>Recordar contraseña</a>";
//        tv1.setMovementMethod(LinkMovementMethod.getInstance());
        tv1.setText(Html.fromHtml(texto));
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et1.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Su contraseña es: admin", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(View view) {
        String user = et1.getText().toString();
        String pass = et2.getText().toString();
        if(!user.equals("") && !pass.equals("")) {
            if(user.equals("admin") && pass.equals("admin")) {
                Intent newIntent = new Intent(this, MainActivity.class);
                startActivity(newIntent);
                finish();
            }
            else {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                et1.requestFocus();
            }
        }
        else {
            if(user.equals("")) {
                et1.requestFocus();
                Toast.makeText(this, "Por favor, ingrese el usuario", Toast.LENGTH_SHORT).show();
            }
            else if (pass.equals("")) {
                et2.requestFocus();
                Toast.makeText(this, "Por favor, ingrese la contraseña", Toast.LENGTH_SHORT).show();
            }
        }
    }
}