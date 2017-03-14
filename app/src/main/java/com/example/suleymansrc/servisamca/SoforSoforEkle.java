package com.example.suleymansrc.servisamca;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SoforSoforEkle extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner sofor;
    private ArrayList arrayListServis;
    private Button kaydet;
    private EditText etTC, etisimSoyisim, etTelNo1, etTelNo2, etAdres, etEmail;
    private TextView tvservis;
    private JSONArray result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofor_sofor_ekle);
        etTC = (EditText) findViewById(R.id.etSoforTC);
        etisimSoyisim = (EditText) findViewById(R.id.etSoforisimSoyisim);
        etTelNo1 = (EditText) findViewById(R.id.etSoforTelNo1);
        etTelNo2 = (EditText) findViewById(R.id.etSoforTelNo2);
        etAdres = (EditText) findViewById(R.id.etSoforAdres);
        etEmail = (EditText) findViewById(R.id.etSoforEmail);

        tvservis = (TextView) findViewById(R.id.labelSoforServis);

        sofor = (Spinner) findViewById(R.id.spinnerSoforServis);
        sofor.setOnItemSelectedListener(SoforSoforEkle.this);

        arrayListServis = new ArrayList<String>();
        kaydet = (Button) findViewById(R.id.btnSoforEkle);
        kaydet.setOnClickListener(this);
        getDataServisPlakasi();
    }

    //GuzergahEkleme işlemi
    //start
    public void registerGuzergah() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EMİN MİSİNİZ?");
        builder.setMessage("Verileriniz kaydedilsin mi?");
        builder.setIcon(R.drawable.loginicon);
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(SoforSoforEkle.this, "Sofor bilgileri kaydediliyor...", Toast.LENGTH_SHORT).show();
                final String TC = etTC.getText().toString().trim();
                final String isimSoyisim = etisimSoyisim.getText().toString().trim();
                final String telNo1 = etTelNo1.getText().toString().trim();
                final String telNo2 = etTelNo2.getText().toString().trim();
                final String adres = etAdres.getText().toString().trim();
                final String email = etEmail.getText().toString().trim();
                final String servis_plakasi = tvservis.getText().toString().trim();
                final String sifre="123".toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Adress.URL_REGISTER_SOFOR, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("TC", TC);
                        params.put("isimSoyisim", isimSoyisim);
                        params.put("telNo1", telNo1);
                        params.put("telNo2", telNo2);
                        params.put("adres", adres);
                        params.put("servis_plakasi", servis_plakasi);
                        params.put("email", email);
                        params.put("sifre",sifre);
                        return params;
                    }
                };
                RequestHandler.getInstance(SoforSoforEkle.this).addToRequestQueue(stringRequest);

            }
        });
        builder.setNegativeButton("Hayır", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    //Servis plakalarını Spinner ekleyen kod
    // start
    private void getDataServisPlakasi() {
        StringRequest stringRequest = new StringRequest(Config.DATA_URL_SERVIS_PLAKASI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject j = null;
                try {
                    j = new JSONObject(response);
                    result = j.getJSONArray(Config.JSON_ARRAY);
                    getArrayServisPlakasi(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getArrayServisPlakasi(JSONArray j) {
        for (int i = 0; i < j.length(); i++) {
            try {
                JSONObject jsonObject = j.getJSONObject(i);
                arrayListServis.add(jsonObject.getString(Config.TAG_SERVIS_PLAKASI));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        sofor.setAdapter(new ArrayAdapter<String>(SoforSoforEkle.this, android.R.layout.simple_spinner_dropdown_item, arrayListServis));
    }

    private String getServisPlakasi(int position) {
        String sfr = "";
        try {
            JSONObject jsonObject = result.getJSONObject(position);
            sfr = jsonObject.getString(Config.TAG_SERVIS_PLAKASI);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sfr;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        tvservis.setText(getServisPlakasi(i));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    // finish


    @Override
    public void onClick(View view) {
        if (view == kaydet) {
            registerGuzergah();
        }
    }


}
