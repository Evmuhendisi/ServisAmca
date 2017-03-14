package com.example.suleymansrc.servisamca;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SoforOkulEkleme extends AppCompatActivity implements View.OnClickListener {
    Button kaydet;
    private String[] il = {"İstanbul", "Ankara", "Edirne", "Tekirdağ"};
    private String[] ilce = {"Gaziosmanpasa", "Eyup", "Gokcebey", "Reşadiye"};
    private String[] mahalle = {"Bağlarbaşı", "nusratiye", "Alipaşa"};
    private String[] okul_turu = {"Anaokul", "İlkokul", "Ortaokul", "Lise"};
    private Spinner spinil, spinilce, spinmahalle, spinokulturu;
    private EditText etOkulAdi;
    private ArrayAdapter adapteril, adapterilce, adaptermahalle, adapterokulturu;
    private TextView etil, etilce, etmahalle, etokulturu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_okul_ekleme);
        kaydet = (Button) findViewById(R.id.btnSoforOkulEkle);
        kaydet.setOnClickListener(this);
        spinil = (Spinner) findViewById(R.id.spinneril);
        spinilce = (Spinner) findViewById(R.id.spinnerilce);
        spinmahalle = (Spinner) findViewById(R.id.spinnermahalle);
        spinokulturu = (Spinner) findViewById(R.id.spinnerOkulTuru);
        etOkulAdi = (EditText) findViewById(R.id.etOkulAdi);
        etil = (TextView) findViewById(R.id.labelil);
        etilce = (TextView) findViewById(R.id.labelilce);
        etmahalle = (TextView) findViewById(R.id.labelmahalle);
        etokulturu = (TextView) findViewById(R.id.labelokulturu);

        adapteril = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, il);
        spinil.setAdapter(adapteril);
        spinil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etil.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        adapterilce = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ilce);
        spinilce.setAdapter(adapterilce);
        spinilce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etilce.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        adaptermahalle = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, mahalle);
        spinmahalle.setAdapter(adaptermahalle);
        spinmahalle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etmahalle.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        adapterokulturu = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, okul_turu);
        spinokulturu.setAdapter(adapterokulturu);
        spinokulturu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etokulturu.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cikis) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.sifremidegitir) {
            Intent intent = new Intent(SoforOkulEkleme.this, SifreDegistir.class);
            startActivity(intent);
        } else if (id == R.id.hakkında) {
            Intent intent = new Intent(SoforOkulEkleme.this, Hakkimizda.class);
            startActivity(intent);
        } else if (id == R.id.anasayfa) {
            Intent intent = new Intent(SoforOkulEkleme.this, SoforAnasayfa.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void registerOkul() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EMİN MİSİNİZ?");
        builder.setMessage("Verileriniz kaydedilsin mi?");
        builder.setIcon(R.drawable.loginicon);
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(SoforOkulEkleme.this, "Servis bilgileri kaydediliyor...", Toast.LENGTH_SHORT).show();
                final String il = etil.getText().toString().trim();
                final String ilce = etilce.getText().toString().trim();
                final String mahalle = etmahalle.getText().toString().trim();
                final String okul_adi = etOkulAdi.getText().toString().trim();
                final String okul_turu = etokulturu.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Adress.URL_REGISTER_OKUL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject=new JSONObject(response);
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("il", il);
                        params.put("ilce", ilce);
                        params.put("mahalle", mahalle);
                        params.put("okul_adi", okul_adi);
                        params.put("okul_turu", okul_turu);
                        return params;
                    }
                };
                RequestHandler.getInstance(SoforOkulEkleme.this).addToRequestQueue(stringRequest);
            }
        });
        builder.setNegativeButton("Hayır", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view == kaydet) {
            registerOkul();
        }
    }
}
