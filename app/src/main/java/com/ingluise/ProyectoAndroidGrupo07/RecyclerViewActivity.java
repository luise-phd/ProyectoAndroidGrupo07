package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

public class RecyclerViewActivity extends AppCompatActivity {
    private final int tam = 1000000;
    private final int cols = 2;
    private CustomAdapter ca;

    private RecyclerView rv;
    private LinearLayoutManager llm;
    private GridLayoutManager glm;

    private String[] dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        //Activar boton volver en la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv = findViewById(R.id.rv_list);
        dataset = initDataset();
        ca = new CustomAdapter(dataset);
        rv.setAdapter(ca);
        setRecyclerViewLayoutManager();
    }

    public String[] initDataset() {
        dataset = new String[tam];
        for (int i=0; i < dataset.length; i++) {
            dataset[i] = "Este es el elemento # " + i;
        }
        return dataset;
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 999990;

        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        glm = new GridLayoutManager(this, cols);

//        rv.setLayoutManager(llm);
        rv.setLayoutManager(glm);
        rv.scrollToPosition(scrollPosition);
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