package com.ingluise.ProyectoAndroidGrupo07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HorasExtrasFirebaseActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;

    private EditText et1, et2, et3;
    private Spinner sp1;

    private String horas_extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horas_extras_firebase);

        et1 = findViewById(R.id.input_cod_he);
        et2 = findViewById(R.id.input_empleado);
        et3 = findViewById(R.id.input_cantidad_he);
        sp1 = findViewById(R.id.sp_meses);
    }

    public void testCrash(View view) {
        throw new RuntimeException("Test Crash"); // Force a crash
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.mnu_agregar) {
            String cod = et1.getText().toString();
            String mes = sp1.getSelectedItem().toString();
            String emp = et2.getText().toString();
            String he = et3.getText().toString();
            if (!cod.equals("") && !mes.equals("Seleccione...") &&
                    !emp.equals("") && !he.equals("")) {
                Horas_extras hext = new Horas_extras(cod, mes, emp, Integer.parseInt(he));
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference().child("horas_extras").push();
                myRef.setValue(hext);
                et1.setText("");
                et1.requestFocus();
                sp1.setSelection(0);
                et2.setText("");
                et3.setText("");
                Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_listar) {
            myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("horas_extras").orderByChild("cod").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String cod_he = "", mes_he = "", nom_emp = "";
                    int cant = 0;
                    horas_extras = "";
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Horas_extras hext = dataSnapshot.getValue(Horas_extras.class);
                        cod_he = hext.getCod();
                        mes_he = hext.getMes();
                        nom_emp = hext.getEmp();
                        cant = hext.getCan_he();
                        horas_extras += cod_he + " - " + mes_he + " - " + nom_emp + " - " + cant +"\n";
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Horas extras")
                    .setMessage(horas_extras)
                    .setPositiveButton("Aceptar", null).show();
        }
        else if(id == R.id.mnu_eliminar) {
            String cod = et1.getText().toString();
            if (!cod.equals("")) {
                myRef = FirebaseDatabase.getInstance().getReference();
                myRef.child("horas_extras").orderByChild("cod").equalTo(cod).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getChildrenCount() > 0) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                dataSnapshot.getRef().removeValue();
                            }
                            Toast.makeText(getApplicationContext(), "Registro eliminado", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "El registro no existe", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            else
                Toast.makeText(this, "Por favor, ingrese el código de las horas extras", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_buscar) {
            String cod = et1.getText().toString();
            if (!cod.equals("")) {
                myRef = FirebaseDatabase.getInstance().getReference();
                myRef.child("horas_extras").orderByChild("cod").equalTo(cod).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getChildrenCount() > 0) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Horas_extras hext = dataSnapshot.getValue(Horas_extras.class);
                                String mes = hext.getMes();
                                String emp = hext.getEmp();
                                int chext = hext.getCan_he();
                                et2.setText(emp);
                                et3.setText(""+chext);
                                Adapter adapter = sp1.getAdapter();
                                for (int i=0; i<adapter.getCount(); i++) {
                                    if (mes.equals(adapter.getItem(i))) {
                                        sp1.setSelection(i);
                                    }
                                }
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "El registro no existe", Toast.LENGTH_SHORT).show();
                            et2.setText("");
                            et3.setText("");
                            sp1.setSelection(0);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            else
                Toast.makeText(this, "Por favor, ingrese el código de las horas extras", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_editar) {
            String cod = et1.getText().toString();
            String mes = sp1.getSelectedItem().toString();
            String emp = et2.getText().toString();
            String he = et3.getText().toString();
            if (!cod.equals("") && !mes.equals("Seleccione...") &&
                    !emp.equals("") && !he.equals("")) {
                myRef = FirebaseDatabase.getInstance().getReference();
                myRef.child("horas_extras").orderByChild("cod").equalTo(cod).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String key = "";
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            key = dataSnapshot.getKey();
                        }
                        myRef.child("horas_extras").child(key).child("mes").setValue(mes);
                        myRef.child("horas_extras").child(key).child("emp").setValue(emp);
                        myRef.child("horas_extras").child(key).child("can_he").setValue(Integer.parseInt(he));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                et1.setText("");
                et1.requestFocus();
                sp1.setSelection(0);
                et2.setText("");
                et3.setText("");
                Toast.makeText(this, "Registro editado", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.mnu_listar) {
            myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("horas_extras").orderByChild("cod").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String cod_he = "", mes_he = "", nom_emp = "";
                    int cant = 0;
                    horas_extras = "";
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Horas_extras hext = dataSnapshot.getValue(Horas_extras.class);
                        cod_he = hext.getCod();
                        mes_he = hext.getMes();
                        nom_emp = hext.getEmp();
                        cant = hext.getCan_he();
                        horas_extras += cod_he + " - " + mes_he + " - " + nom_emp + " - " + cant +"\n";
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(id == R.id.mnu_limpiar) {
            et1.setText("");
            sp1.setSelection(0);
            et2.setText("");
            et3.setText("");
        }

        return super.onOptionsItemSelected(menuItem);
    }
}