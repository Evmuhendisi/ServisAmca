package com.example.suleymansrc.servisamca;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SoforServisEkle extends AppCompatActivity implements View.OnClickListener {
    Button kaydet;
    private String[] servisTipi = {"14 kisilik", "16 kisilik", "18 kisilik", "20 kisilik"};
    private String[] servisMarkasi = {"Renault", "Mıtsubishi", "Volvo", "Wolkvagen"};
    private String[] servisModeli = {"2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004"};
    private Spinner spinservisTipi, spinServisMarkasi, spinServisModeli;
    private EditText etServisPlakasi;
    private ArrayAdapter arrayAdapterServisTipi, arrayAdapterServisMarkasi, arrayAdapterServisModeli;
    private ProgressDialog progressDialog;
    private TextView tvServisTipi,tvServisMarkasi,tvServisModeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_servis_ekle);
        tvServisTipi=(TextView) findViewById(R.id.labelservistipi);
        tvServisMarkasi=(TextView) findViewById(R.id.lanbelservismarkasi);
        tvServisModeli=(TextView) findViewById(R.id.labelservismodeli);
        kaydet = (Button) findViewById(R.id.btnSoforServisEkle);
        kaydet.setOnClickListener(this);
        spinServisMarkasi = (Spinner) findViewById(R.id.spinnerServisMarkasi);
        spinServisModeli = (Spinner) findViewById(R.id.spinnerServisModeli);
        spinservisTipi = (Spinner) findViewById(R.id.spinnerServisTipi);
        etServisPlakasi=(EditText) findViewById(R.id.etServisPlakasi);



        arrayAdapterServisTipi = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, servisTipi);
        spinservisTipi.setAdapter(arrayAdapterServisTipi);
        spinservisTipi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String servis_tipi = adapterView.getItemAtPosition(i).toString().trim();
                tvServisTipi.setText(servis_tipi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        arrayAdapterServisMarkasi = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, servisMarkasi);
        spinServisMarkasi.setAdapter(arrayAdapterServisMarkasi);
        spinServisMarkasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String servis_markasi = adapterView.getItemAtPosition(i).toString().trim();
                tvServisMarkasi.setText(servis_markasi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        arrayAdapterServisModeli = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, servisModeli);
        spinServisModeli.setAdapter(arrayAdapterServisModeli);
        spinServisModeli.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String servis_modeli = adapterView.getItemAtPosition(i).toString().trim();
                tvServisModeli.setText(servis_modeli);
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
            Intent intent = new Intent(SoforServisEkle.this, SifreDegistir.class);
            startActivity(intent);
        } else if (id == R.id.hakkında) {
            Intent intent = new Intent(SoforServisEkle.this, Hakkimizda.class);
            startActivity(intent);
        } else if (id == R.id.anasayfa) {
            Intent intent = new Intent(SoforServisEkle.this, SoforAnasayfa.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void registerServis() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SoforServisEkle.this);
        builder.setTitle("EMİN MİSİNİZ?");
        builder.setMessage("Verileriniz kaydedilsin mi?");
        builder.setIcon(R.drawable.loginicon);
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(SoforServisEkle.this, "Servis bilgileri kaydediliyor...", Toast.LENGTH_SHORT).show();
                final String servis_plakasi=etServisPlakasi.getText().toString().trim();
                final String servis_tipi=tvServisTipi.getText().toString().trim();
                final String servis_markasi=tvServisModeli.getText().toString().trim();
                final String servis_modeli=tvServisMarkasi.getText().toString().trim();

              //  progressDialog.setMessage("Servis oluşturuluyor...");
               // progressDialog.show();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, Adress.URL_REGISTER_SERVIS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                             //   progressDialog.dismiss();
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
                       // progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                        params.put("servis_tipi",servis_tipi);
                        params.put("servis_markasi",servis_markasi);
                        params.put("servis_modeli",servis_modeli);
                        params.put("servis_plakasi",servis_plakasi);
                        return params;
                    }
                };
               RequestHandler.getInstance(SoforServisEkle.this).addToRequestQueue(stringRequest);
            }
        });
        builder.setNegativeButton("Hayır", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view == kaydet) {
            registerServis();
        }
    }
}
