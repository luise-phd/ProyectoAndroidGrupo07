package com.ingluise.ProyectoAndroidGrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class APIRestActivity extends AppCompatActivity {
    private static final String TAG = APIRestActivity.class.getSimpleName();

    private EditText et1, et2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apirest);

        //Activar boton volver en la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et1 = findViewById(R.id.input_URL);
        et2 = findViewById(R.id.input_clave);
        tv1 = findViewById(R.id.textView15);
    }

    public void conectar(View view) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL guthubEndpoint = new URL(et1.getText().toString());
                    HttpsURLConnection myConnection = (HttpsURLConnection) guthubEndpoint.openConnection();
                    myConnection.setRequestProperty("User-agent", "my-rest-api-v0.1");
                    myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
                    myConnection.setRequestProperty("Contact-Me", "usuario@gmail.com");
                    if (myConnection.getResponseCode() == 200) {
                        Log.e(TAG, "Conexión exitosa");
                        APIRestActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv1.setText("Conexión exitosa");
                            }
                        });
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String key = jsonReader.nextName();
                            if (key.equals(et2.getText().toString())) {
                                String value = jsonReader.nextString();
                                APIRestActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv1.setText(value);
                                    }
                                });
                                break;
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.close();
                        myConnection.disconnect();
                    } else {
                        Log.e(TAG, "No se pudo realizar la conexión: "+myConnection.getResponseCode());
                        APIRestActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    tv1.setText("No se pudo realizar la conexión: "+myConnection.getResponseCode());
                                } catch (IOException e) {
                                    tv1.setText("IOException: " + e.getMessage().toString());
                                }
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    Log.e(TAG, "MalformedURLException: " + e.getMessage());
                    APIRestActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText("MalformedURLException: " + e.getMessage().toString());
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, "IOException: " + e.getMessage());
                    APIRestActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText("IOException: " + e.getMessage().toString());
                        }
                    });
                }
            }
        });
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