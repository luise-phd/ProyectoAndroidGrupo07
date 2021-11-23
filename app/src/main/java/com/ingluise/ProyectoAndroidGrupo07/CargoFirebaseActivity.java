package com.ingluise.ProyectoAndroidGrupo07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CargoFirebaseActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;

    private EditText et1;
    private String cargos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_firebase);

        et1 = findViewById(R.id.input_cargo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.mnu_agregar) {
            String cargo = et1.getText().toString();
            if (!cargo.equals("")) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("cargo").push();
                myRef.setValue(cargo);
                et1.setText("");
                Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Por favor, ingrese el cargo", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_listar) {
            myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("cargo").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    cargos = "";
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        cargos += dataSnapshot.getValue(String.class) +"\n";
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cargo")
                .setMessage(cargos)
                .setPositiveButton("Aceptar", null).show();
        }
        else if(id == R.id.mnu_eliminar) {

        }
        else if(id == R.id.mnu_buscar) {

        }
        else if(id == R.id.mnu_editar) {

        }
        else if(id == R.id.mnu_limpiar) {
            et1.setText("");
        }

        return super.onOptionsItemSelected(menuItem);
    }
}